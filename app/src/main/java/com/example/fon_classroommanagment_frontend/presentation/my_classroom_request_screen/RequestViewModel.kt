package com.example.fon_classroommanagment_frontend.presentation.my_classroom_request_screen

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
import com.example.fon_classroommanagment_frontend.domain.use_case.CheckAvailabilityClassroomForDateUseCase
import com.example.fon_classroommanagment_frontend.domain.use_case.ReserveAppointmetsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RequestViewModel @Inject constructor(private val checkAvailabilityClassroomForDateUseCase: CheckAvailabilityClassroomForDateUseCase,private val reserveAppointmetsUseCase: ReserveAppointmetsUseCase):ViewModel() {



    private var _reservations = mutableStateListOf<MyAppointmentUI>()
    val reservations=_reservations

    private var _reservantionState= mutableStateOf(UIRequestResponse())
    val reservationState=_reservantionState


fun addRequest(reserveDTO: RequestReservastion){

    _reservations.addAll(createAppointmentUI(reserveDTO))
    checkAppointmentsAvailability()

}

    private fun createAppointmentUI(reserveDTO: RequestReservastion): Collection<MyAppointmentUI> {
        return reserveDTO.reqserveDTO.map { x-> MyAppointmentUI(x, UIRequestResponse(isLoading = true)) }
    }

    private fun checkAppointmentsAvailability() {

        viewModelScope.launch {
            _reservations.forEachIndexed { index, x ->
                checkAvailabilityClassroomForDateUseCase(CreateRequestAvailabilityClassroomDTO(x.reserveDTO)).onEach { result ->
                    when (result) {
                        is Response.Success -> {
                            if (!result.data!!) _reservations[index] =
                                x.copy(uiRequestResponse = UIRequestResponse(isError = true))
                            else _reservations[index] =
                                x.copy(uiRequestResponse = UIRequestResponse(success = true))
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





    private fun CreateRequestAvailabilityClassroomDTO(reserveDTO: ReserveDTO): RequestIsClassroomAvailableForDateDTO {
    return RequestIsClassroomAvailableForDateDTO(reserveDTO.date_appointment,reserveDTO.classroomId,reserveDTO.start_timeInHours,reserveDTO.end_timeInHours)
        }

    fun deleteRequest(it: ReserveDTO) {
        _reservations.removeIf { x-> x.reserveDTO==it }

    }

    fun sendAppointments() {
        if(_reservations.isEmpty()){


        }
       else  if(validateAppointments()){

            reserveAppointmetsUseCase(createList()).onEach {
        result->
                when(result){
                    is Response.Success -> {
                        _reservantionState.value= UIRequestResponse(success = true)
                        Log.i("cao","success ${result.data}")

                    }
                    is Response.Error -> {
                        _reservantionState.value=UIRequestResponse(isError = true)

                        Log.i("cao","error ${result.message}")

                    }
                    is Response.Loading -> {
                        _reservantionState.value=UIRequestResponse(isLoading = true)

                        Log.i("cao","loading")
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


    private fun validateAppointments(): Boolean
     =   _reservations.all { x-> x.uiRequestResponse.success } && _reservations.isNotEmpty()

    fun restart() {
        _reservantionState.value=UIRequestResponse()
    }

}