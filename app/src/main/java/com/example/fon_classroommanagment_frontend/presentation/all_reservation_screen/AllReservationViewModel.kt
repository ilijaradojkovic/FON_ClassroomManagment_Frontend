package com.example.fon_classroommanagment_frontend.presentation.all_reservation_screen

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fon_classroommanagment_frontend.common.Response
import com.example.fon_classroommanagment_frontend.common.UIRequestResponse
import com.example.fon_classroommanagment_frontend.data.Event
import com.example.fon_classroommanagment_frontend.data.remote.dto.RequestAppointmetDateDTO
import com.example.fon_classroommanagment_frontend.domain.use_case.GetReservationsForDateUseCse
import com.foreverrafs.datepicker.state.DatePickerState
import com.foreverrafs.datepicker.state.rememberDatePickerState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*
import javax.inject.Inject

@HiltViewModel
class AllReservationViewModel @Inject constructor(private val getReservationsForDateUseCase: GetReservationsForDateUseCse):ViewModel() {

    private val _reservationsForDay= mutableStateListOf<Event>()
    val reservationForDay = _reservationsForDay

    private  var _uiState = mutableStateOf(UIRequestResponse())
    val uiState= _uiState
    @RequiresApi(Build.VERSION_CODES.O)
    fun getReservationsForData(initialDate: LocalDate) {
        _reservationsForDay.clear()
        getReservationsForDateUseCase(CreateRequestAppointmetDateDTO(initialDate)).onEach {

            result->
            when(result){
                is Response.Success->{

                    _uiState.value=UIRequestResponse(success = true)
                    val transofmed=result.data?.map { x->Event(x.typeName,x.classroomName, TransformToLocalDate(x.Start_timeInHours),TransformToLocalDate(x.End_timeInHours)) }
                    if (transofmed != null) {

                        _reservationsForDay.addAll(transofmed)
                    }

                }
                is Response.Error->{

                  }
                is Response.Loading->{
                    _uiState.value=UIRequestResponse(isLoading = true)
                   }
            }
        }
            .launchIn(viewModelScope)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun TransformToLocalDate(hours: Int): LocalDateTime {
        var localDate=    LocalDateTime.now()

       localDate= localDate.withHour(hours)
        localDate=localDate.withMinute(0)

        return localDate
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun CreateRequestAppointmetDateDTO(localDate: LocalDate)= RequestAppointmetDateDTO(localDate)


}