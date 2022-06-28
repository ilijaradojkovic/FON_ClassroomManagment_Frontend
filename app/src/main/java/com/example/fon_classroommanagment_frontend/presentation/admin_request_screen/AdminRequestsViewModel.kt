package com.example.fon_classroommanagment_frontend.presentation.admin_request_screen

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fon_classroommanagment_frontend.common.Response
import com.example.fon_classroommanagment_frontend.data.remote.dto.AppointmentRequestedUserDTO
import com.example.fon_classroommanagment_frontend.data.remote.dto.RequestedAppointmentsDTO
import com.example.fon_classroommanagment_frontend.data.remote.dto.UserDetailsDTO
import com.example.fon_classroommanagment_frontend.domain.use_case.RetriveUserDetailsDataUseCase
import com.example.fon_classroommanagment_frontend.domain.use_case.RetriveUserRequestedAppointmentsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AdminRequestsViewModel @Inject constructor(private val retriveUserDetailsDataUseCase: RetriveUserDetailsDataUseCase,private val retriveUserRequestedAppointmentsUseCase : RetriveUserRequestedAppointmentsUseCase) :ViewModel() {

    private var _userDetails = mutableStateOf(RequestedAppointmentsDTO())
    val userDetails=_userDetails
    private var _userRequests = mutableStateListOf<AppointmentRequestedUserDTO>()
    val userRequests=_userRequests




     fun getUserDetails(id: Long) {
        retriveUserDetailsDataUseCase(id).onEach {
            result->
            _userDetails.value=result
        }.launchIn(viewModelScope)
    }

    fun getRequests(id: Long) {
        retriveUserRequestedAppointmentsUseCase(id).onEach {
                result->
            when(result){
                is Response.Success->{
                    result.data?.let { _userRequests.addAll(it) }
                }
                is Response.Loading->{
                    Log.i("cao","loading")

                }
                 is Response.Error->{
                     Log.i("cao","error ${result.message}")

                 }
            }

        }.launchIn(viewModelScope)
    }
}