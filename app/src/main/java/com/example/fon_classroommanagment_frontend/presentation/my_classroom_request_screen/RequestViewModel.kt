package com.example.fon_classroommanagment_frontend.presentation.my_classroom_request_screen

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.fon_classroommanagment_frontend.common.RequestReservastion
import com.example.fon_classroommanagment_frontend.data.remote.dto.ReserveDTO
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RequestViewModel @Inject constructor():ViewModel() {

    private var _reservations = mutableStateListOf<ReserveDTO>()
    val reservations=_reservations

init {
    Log.i("cao","created")
}
fun addRequest(reserveDTO: RequestReservastion){

    _reservations.addAll(reserveDTO.reqserveDTO)

}

    fun deleteRequest(it: ReserveDTO) {
        _reservations.remove(it)

    }
}