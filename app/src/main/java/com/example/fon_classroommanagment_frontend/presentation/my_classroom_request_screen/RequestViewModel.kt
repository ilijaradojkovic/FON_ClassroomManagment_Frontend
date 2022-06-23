package com.example.fon_classroommanagment_frontend.presentation.my_classroom_request_screen

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fon_classroommanagment_frontend.common.RequestReservastion
import com.example.fon_classroommanagment_frontend.common.Response
import com.example.fon_classroommanagment_frontend.common.UIRequestResponse
import com.example.fon_classroommanagment_frontend.data.remote.dto.MyAppointmentUI
import com.example.fon_classroommanagment_frontend.data.remote.dto.RequestIsClassroomAvailableForDateDTO
import com.example.fon_classroommanagment_frontend.data.remote.dto.ReserveDTO
import com.example.fon_classroommanagment_frontend.domain.use_case.CheckAvailabilityClassroomForDateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class RequestViewModel @Inject constructor(private val checkAvailabilityClassroomForDateUseCase: CheckAvailabilityClassroomForDateUseCase):ViewModel() {



    private var _reservations = mutableStateListOf<MyAppointmentUI>()
    val reservations=_reservations

init {
    Log.i("cao","created")
}
fun addRequest(reserveDTO: RequestReservastion){

    _reservations.addAll(createAppointmentUI(reserveDTO))
    checkAppointmentsAvailability()

}

    private fun createAppointmentUI(reserveDTO: RequestReservastion): Collection<MyAppointmentUI> {
        return reserveDTO.reqserveDTO.map { x-> MyAppointmentUI(x, UIRequestResponse(isLoading = true)) }
    }

    private fun checkAppointmentsAvailability() {
        _reservations.forEach { x->
            checkAvailabilityClassroomForDateUseCase(CreateRequestAvailabilityClassroomDTO(x.reserveDTO)).onEach {
                result->
                when(result){
                    is Response.Success->{
                        x.uiRequestResponse= UIRequestResponse(success = true)
                        Log.i("cao","provera appointmenta ${result.data}")}
                    is Response.Error->{
                        x.uiRequestResponse=UIRequestResponse(isError = true)
                        Log.i("cao","provera appointmenta error ${result.message}")}
                    is Response.Loading->{
                        x.uiRequestResponse=UIRequestResponse(isLoading = true)
                        Log.i("cao","provera appointmenta loading")}
                }
            }.launchIn(viewModelScope)
        }
    }

    private fun CreateRequestAvailabilityClassroomDTO(reserveDTO: ReserveDTO): RequestIsClassroomAvailableForDateDTO {
    return RequestIsClassroomAvailableForDateDTO(reserveDTO.date,reserveDTO.classroomId)
        }

    fun deleteRequest(it: ReserveDTO) {
        _reservations.removeIf { x-> x.reserveDTO==it }

    }
}