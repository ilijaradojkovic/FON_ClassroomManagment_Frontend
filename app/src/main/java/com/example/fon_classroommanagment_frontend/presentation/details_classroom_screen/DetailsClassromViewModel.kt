package com.example.fon_classroommanagment_frontend.presentation.details_classroom_screen

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
import com.example.fon_classroommanagment_frontend.data.remote.dto.ClassroomDetailsDTO
import com.example.fon_classroommanagment_frontend.data.remote.dto.GetForDateAppointmentDTO
import com.example.fon_classroommanagment_frontend.data.remote.dto.RequestAppointmetDaetForClassroomDTO
import com.example.fon_classroommanagment_frontend.domain.use_case.GetClassroomDetailsUseCase
import com.example.fon_classroommanagment_frontend.domain.use_case.GetReservationsForClassroomAndDateUseCse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.time.LocalDate
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class DetailsClassromViewModel @Inject constructor(private val getClassroomDetailsUseCase: GetClassroomDetailsUseCase,private val getReservationsForClassroomAndDateUseCse: GetReservationsForClassroomAndDateUseCse) :ViewModel() {

   private val _uiResponseClassroomInfo= mutableStateOf(UIRequestResponse())
    val uiResponseClassroomInfo=_uiResponseClassroomInfo

    private val _uiResponseClassroomAppointment= mutableStateOf(UIRequestResponse())
    val uiResponseClassroomAppointment=_uiResponseClassroomAppointment

    private val _clasroom = mutableStateOf(ClassroomDetailsDTO())
     val classroom=_clasroom

    private val _appointmentsForClassroom= mutableStateListOf<Event>()
    val appointmentsForClassroom=_appointmentsForClassroom

    var classroomId = mutableStateOf(-1L)
    fun getClassroom(classroomId: Long) {
        _uiResponseClassroomInfo.value= UIRequestResponse()

        getClassroomDetailsUseCase.invoke(classroomId).onEach {

            result->
            when(result){
                is Response.Loading->{
                    _uiResponseClassroomInfo.value= UIRequestResponse(isLoading = true)
                }
                is Response.Error->{
                    Log.i("cao","error ${result.message}")

                    _uiResponseClassroomInfo.value=UIRequestResponse(isError = true)
                }
                is Response.Success->{
                    _clasroom.value= result.data!!
                    Log.i("cao",_clasroom.toString())
                    _uiResponseClassroomInfo.value=UIRequestResponse(success = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getAppointmentsForClassroom(initDate:LocalDate){
        _appointmentsForClassroom.clear()
        getReservationsForClassroomAndDateUseCse(CreateRequestAppointmetDateDTO(initDate)).onEach {
            result->
            when(result){
                is Response.Success->{
                    _uiResponseClassroomAppointment.value= UIRequestResponse(success = true)
                    val transofmed=result.data?.map { x->Event(x.typeName,x.classroomName, TransformToLocalDate(x.Start_timeInHours),TransformToLocalDate(x.End_timeInHours)) }

                    result.data?.let {
                        if (transofmed != null) {
                            _appointmentsForClassroom.addAll(transofmed)
                        }
                    }
                }
                is Response.Error->{
                    Log.i("cao","error ${result.message}")
                    _uiResponseClassroomAppointment.value=UIRequestResponse(isError = true)


                }
                is Response.Loading->{

                    _uiResponseClassroomAppointment.value=UIRequestResponse(isLoading = true)

                }
            }
        }.launchIn(viewModelScope)

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun CreateRequestAppointmetDateDTO(localDate: LocalDate)= RequestAppointmetDaetForClassroomDTO(localDate,classroomId.value)

    @RequiresApi(Build.VERSION_CODES.O)
    private fun TransformToLocalDate(hours: Int): LocalDateTime {
        var localDate=    LocalDateTime.now()

        localDate= localDate.withHour(hours)
        localDate=localDate.withMinute(0)

        return localDate
    }



}