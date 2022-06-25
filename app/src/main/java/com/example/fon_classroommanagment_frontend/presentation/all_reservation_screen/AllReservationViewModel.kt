package com.example. fon_classroommanagment_frontend.presentation.all_reservation_screen

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
import com.example.fon_classroommanagment_frontend.data.remote.dto.ClassroomChipDTO
import com.example.fon_classroommanagment_frontend.data.remote.dto.RequestAppointmetDaetForClassroomDTO
import com.example.fon_classroommanagment_frontend.domain.use_case.GetAllClassroomChipsPaging
import com.example.fon_classroommanagment_frontend.domain.use_case.GetReservationsForClassroomAndDateUseCse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.time.LocalDate
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class AllReservationViewModel @Inject constructor(private val getReservationsForDateUseCase: GetReservationsForClassroomAndDateUseCse, private val getAllClassroomChipsPaging: GetAllClassroomChipsPaging):ViewModel() {

    private var page=1

    private var _selectedClassroom = mutableStateOf(1L)
    val selectedClassroom = _selectedClassroom

    private val _reservationsForDay= mutableStateListOf<Event>()
    val reservationForDay = _reservationsForDay

    private val _classrooms = mutableStateListOf<ClassroomChipDTO>()
    val classrooms= _classrooms


    private  var _uiState = mutableStateOf(UIRequestResponse())
    val uiState= _uiState




    fun getAllClassrooms() {
        getAllClassroomChipsPaging(page).onEach {
            result->
                when(result){
                    is Response.Success->{
                        if(result.data?.size!! >0)
                                page++
                        result.data.let { _classrooms.addAll(it) }
                    }
                    is Response.Error->{_uiState.value=UIRequestResponse(isError = true)}
                    is Response.Loading->{Log.i("cao","loading sve ucionice")}

                }


        }.launchIn(viewModelScope)

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getReservationsForData(initialDate: LocalDate,) {

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
                    if(!_uiState.value.isError)_uiState.value=UIRequestResponse(isError = true)
                  }
                is Response.Loading->{
                    _uiState.value=UIRequestResponse(isLoading = true)
                   }
            }

        }.launchIn(viewModelScope)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun TransformToLocalDate(hours: Int): LocalDateTime {
        var localDate=    LocalDateTime.now()

       localDate= localDate.withHour(hours)
        localDate=localDate.withMinute(0)

        return localDate
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun CreateRequestAppointmetDateDTO(localDate: LocalDate)= RequestAppointmetDaetForClassroomDTO(localDate,_selectedClassroom.value)
    @RequiresApi(Build.VERSION_CODES.O)
    fun selectClassroom(it: Long) {
        //ispravi
        _selectedClassroom.value=it

    }


}