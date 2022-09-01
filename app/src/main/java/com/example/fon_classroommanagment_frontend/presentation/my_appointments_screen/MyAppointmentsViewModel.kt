package com.example.fon_classroommanagment_frontend.presentation.my_appointments_screen

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fon_classroommanagment_frontend.common.RequestReservastion
import com.example.fon_classroommanagment_frontend.common.Response
import com.example.fon_classroommanagment_frontend.common.UIRequestResponse
import com.example.fon_classroommanagment_frontend.data.remote.dto.MyAppointmentUI
import com.example.fon_classroommanagment_frontend.data.remote.dto.RequestIsClassroomAvailableForDateDTO
import com.example.fon_classroommanagment_frontend.data.remote.dto.ReserveDTO
import com.example.fon_classroommanagment_frontend.domain.use_case.*
import com.example.fon_classroommanagment_frontend.domain.use_case.admin_page_cases.components.ReserveAppointmetsUseCase
import com.example.fon_classroommanagment_frontend.domain.use_case.appointment_insertion_page_cases.components.GetAllAppointmentsUseCase
import com.example.fon_classroommanagment_frontend.domain.use_case.my_appointment_page_use_case.MyAppointmentsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MyAppointmentsViewModel @Inject constructor(
private val myAppointmentUseCase: MyAppointmentsUseCase
):ViewModel() {



    private var _reservations = mutableStateListOf<MyAppointmentUI>()
    val reservations=_reservations

    private var _reservantionState= mutableStateOf(UIRequestResponse())
    val reservationState=_reservantionState




    private fun createAppointmentUI(reserveDTO: RequestReservastion): Collection<MyAppointmentUI> {
        return reserveDTO.reqserveDTO.map { x-> MyAppointmentUI(x, UIRequestResponse(isLoading = true)) }
    }

    private fun checkAppointmentsAvailability() {
        if (validateAppointments()) {
            viewModelScope.launch {
                _reservations.forEachIndexed { index, x ->

                    myAppointmentUseCase.checkAvailabilityClassroomForDateUseCase(
                        CreateRequestAvailabilityClassroomDTO(x.reserveDTO)
                    ).onEach { result ->
                        when (result) {
                            is Response.Success -> {

                                if (!result.data!!) _reservations[index] =
                                    x.copy(uiRequestResponse = UIRequestResponse(isError = true))
                                else {
                                    _reservations[index] =
                                        x.copy(uiRequestResponse = UIRequestResponse(success = true))
                                }
                            }
                            is Response.Error -> {
                                _reservations[index] =
                                    x.copy(uiRequestResponse = UIRequestResponse(isError = true))

                            }
                            is Response.Loading -> {
                                _reservations[index] =
                                    x.copy(uiRequestResponse = UIRequestResponse(isLoading = true))
                            }

                        }
                    }.launchIn(viewModelScope)
                }
            }
        }
    }



    fun getAllAppointments(){
        myAppointmentUseCase.getAllAppointmentsUseCase().onEach {
            result->
            when(result){
                is Response.Error->{

                }
                is Response.Loading->{

                }
                is Response.Success->{
                    _reservations.clear()
                    result.data?.let {
                        _reservations.addAll(it.map { x-> MyAppointmentUI(x,
                            UIRequestResponse()
                        ) })
                    }
                    checkAppointmentsAvailability()
                }
            }
        }.launchIn(viewModelScope)
    }


    private fun CreateRequestAvailabilityClassroomDTO(reserveDTO: ReserveDTO): RequestIsClassroomAvailableForDateDTO {
    return RequestIsClassroomAvailableForDateDTO(reserveDTO.date_appointment,reserveDTO.classroomId,reserveDTO.start_timeInHours,reserveDTO.end_timeInHours)
        }

    fun deleteRequest(it: ReserveDTO) {
        _reservations.removeIf { x-> x.reserveDTO==it }
        myAppointmentUseCase.deleteLocalAppointmentUseCase(it.id!!).onEach {
            result->
            when(result) {
                is Response.Success -> {
                    Log.i("cao","success")
                }
                is Response.Error -> {
                    Log.i("cao","error" +result.message)
                }
                is Response.Loading -> {
                    Log.i("cao","loading")
                }
            }

        }.launchIn(viewModelScope)

    }

    fun sendAppointments() {
        if(_reservations.isEmpty()){

//obradi ovo
        }
       else  if(validateAppointments()){

            myAppointmentUseCase.reserveAppointmetsUseCase(createList()).onEach {
        result->
                when(result){
                    is Response.Success -> {

                        _reservantionState.value= UIRequestResponse(success = true)
                        _reservations.clear()


                    }
                    is Response.Error -> {
                        _reservantionState.value=UIRequestResponse(isError = true)

                        Log.i("cao","error ${result.message}")

                    }
                    is Response.Loading -> {
                        _reservantionState.value=UIRequestResponse(isLoading = true)


                    }

                }

            }.launchIn(viewModelScope)
        }
        else{
           _reservantionState.value= UIRequestResponse(isError = true)
        }
    }

    private fun createList(): List<ReserveDTO>
    =_reservations.map { it.reserveDTO }.toList()

//ovde nes
    private fun validateAppointments(): Boolean
    {
       // _reservations.all { x-> x.uiRequestResponse.success } && _reservations.isNotEmpty()
        val resultList= LinkedList<ReserveDTO>()
       _reservations.forEach {

           if(

               resultList.any { ele->
                   it.reserveDTO.classroomId==ele.classroomId &&
                   it.reserveDTO.date==ele.date &&
                           ((it.reserveDTO.start_timeInHours<=ele.start_timeInHours  && it.reserveDTO.end_timeInHours> ele.start_timeInHours && it.reserveDTO.end_timeInHours<=ele.end_timeInHours) ||
                           (it.reserveDTO.start_timeInHours>=ele.start_timeInHours && it.reserveDTO.start_timeInHours<ele.end_timeInHours && it.reserveDTO.end_timeInHours <= ele.end_timeInHours)||
                           (it.reserveDTO.start_timeInHours>=ele.start_timeInHours && it.reserveDTO.start_timeInHours< ele.end_timeInHours && ele.end_timeInHours<=it.reserveDTO.end_timeInHours))
               }
           ){
               it.uiRequestResponse=UIRequestResponse(isError = true)
           }else{


               //it.uiRequestResponse=UIRequestResponse(success = true)
               resultList.add(it.reserveDTO)
           }
       }
        return _reservations.size==resultList.size
    }

    fun restart() {
        _reservantionState.value=UIRequestResponse()
    }

    fun saveRequest(requestReservation: RequestReservastion) {
        //Log.i("cao",requestReservation.toString())
         requestReservation.reqserveDTO.forEach {
             _reservations.firstOrNull{x-> x.reserveDTO.classroomId==it.classroomId && x.reserveDTO.start_timeInHours==it.start_timeInHours && x.reserveDTO.end_timeInHours==it.end_timeInHours && x.reserveDTO.date_appointment.equals(it.date_appointment)}

         }
    }

}