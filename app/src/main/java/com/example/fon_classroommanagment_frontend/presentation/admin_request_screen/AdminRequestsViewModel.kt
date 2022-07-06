package com.example.fon_classroommanagment_frontend.presentation.admin_request_screen

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fon_classroommanagment_frontend.common.Response
import com.example.fon_classroommanagment_frontend.common.UIRequestResponse
import com.example.fon_classroommanagment_frontend.data.remote.dto.AppointmentRequestedUserDTO
import com.example.fon_classroommanagment_frontend.data.remote.dto.RequestedAppointmentsDTO
import com.example.fon_classroommanagment_frontend.data.remote.dto.UserDetailsDTO
import com.example.fon_classroommanagment_frontend.domain.use_case.*
import com.example.fon_classroommanagment_frontend.domain.use_case.admin_page_cases.AdminUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.util.*
import javax.inject.Inject

@HiltViewModel
class AdminRequestsViewModel @Inject constructor(
private val adminUseCases: AdminUseCases
    ) :ViewModel() {

    private var _userDetails = mutableStateOf(RequestedAppointmentsDTO())
    val userDetails=_userDetails
    private var _userRequests = mutableStateListOf<AppointmentRequestedUserDTO>()
    val userRequests=_userRequests

    private var _uiStateRequests= mutableStateOf(UIRequestResponse())
    val uiStateRequests=_uiStateRequests

    private var _uiStateActionPefromed= mutableStateOf(UIRequestResponse())
    val uiStateActionPefromed=_uiStateActionPefromed

    fun getImageWithBase64(image: String?): Bitmap? {
        if(image==null) return null
        return base64ToBytes(image)?.let { bytesToBitmap(it) }

    }
    private  fun bytesToBitmap(bytes: ByteArray): Bitmap? {
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
    }
    private  fun base64ToBytes(base64: String?): ByteArray? {
        return Base64.decode(base64, 0)
    }
     fun getUserDetails(id: Long) {
         adminUseCases.retriveUserDetailsDataUseCase(id).onEach {
            result->

            _userDetails.value=result
        }.launchIn(viewModelScope)
    }

    fun confirmAllAppointments(){
        adminUseCases.confirmAppointmentsUseCase(_userRequests.toList()).onEach {
            result->
            when(result){
                is Response.Success->{
                    _uiStateActionPefromed.value= UIRequestResponse(success = true)
                    _userRequests.clear()


                }
                is Response.Error->{
                    _uiStateActionPefromed.value=UIRequestResponse(isError = true)


                }
                 is Response.Loading->{

                     _uiStateActionPefromed.value=UIRequestResponse(isLoading = true)


                 }
            }
        }.launchIn(viewModelScope)
    }
    fun getRequests(id: Long) {
        adminUseCases.retriveUserRequestedAppointmentsUseCase(id).onEach {
                result->
            when(result){
                is Response.Success->{
                    _uiStateRequests.value=UIRequestResponse(success = true)
                    result.data?.let { _userRequests.addAll(it) }
                }
                is Response.Loading->{

                    _uiStateRequests.value=UIRequestResponse(isLoading = true)
                }
                 is Response.Error->{

                     _uiStateRequests.value=UIRequestResponse(isError = true)
                 }
            }

        }.launchIn(viewModelScope)
    }

    fun declineAppointment(appointmentId: UUID) {
        adminUseCases.declineAppointmentUseCase(appointmentId).onEach {
                result->
            when(result){
                is Response.Loading->{

                    Log.i("cao","loading confirm appointemnt")
                    _uiStateActionPefromed.value=UIRequestResponse(isLoading = true)

                }
                is Response.Error->{
                    _uiStateActionPefromed.value=UIRequestResponse(isError = true)

                }
                is Response.Success->{
                    _userRequests.removeIf { x->x.id==appointmentId }
                    _uiStateActionPefromed.value=UIRequestResponse(success = true)


                }
            }


        }.launchIn(viewModelScope)
    }

    fun confirmAppointment(appointmentId:UUID) {
        adminUseCases.confirmAppointmentUseCase(appointmentId).onEach {
            result->
                when(result){
                    is Response.Loading->{
                        _uiStateActionPefromed.value=UIRequestResponse(isLoading = true)

                    }
                    is Response.Error->{
                        _uiStateActionPefromed.value=UIRequestResponse(isError = true)


                    }
                    is Response.Success->{
                        _uiStateActionPefromed.value=UIRequestResponse(success = true)

                        _userRequests.removeIf { x->x.id==appointmentId }

                    }
                }


        }.launchIn(viewModelScope)
    }


}