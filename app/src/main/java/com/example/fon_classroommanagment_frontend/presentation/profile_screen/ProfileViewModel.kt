package com.example.fon_classroommanagment_frontend.presentation.profile_screen

import android.content.SharedPreferences
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.ImageBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fon_classroommanagment_frontend.common.Constants
import com.example.fon_classroommanagment_frontend.common.Response
import com.example.fon_classroommanagment_frontend.common.UIRequestResponse
import com.example.fon_classroommanagment_frontend.data.remote.dto.AppointmentsForUserDTO
import com.example.fon_classroommanagment_frontend.data.remote.dto.UserDetailsDTO
import com.example.fon_classroommanagment_frontend.domain.use_case.DeleteAppointmentUseCase
import com.example.fon_classroommanagment_frontend.domain.use_case.GetAppointmentsForUserUseCase
import com.example.fon_classroommanagment_frontend.domain.use_case.UserDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.util.*
import javax.inject.Inject



@HiltViewModel
class ProfileViewModel @Inject constructor(private val userDetailsUseCase: UserDetailsUseCase,private val getAppointmentsForUserUseCase: GetAppointmentsForUserUseCase,private val deleteAppointmentUseCase: DeleteAppointmentUseCase,private  val sharedPreferences: SharedPreferences) :ViewModel() {

    private var _userDetails= mutableStateOf(UserDetailsDTO())
    val userDetails=_userDetails


    val isAdmin = mutableStateOf(sharedPreferences.getString(Constants.ROLE_KEY,"")==Constants.ADMIN_ROLE_ID)

    private var _appointmentsForUser= mutableStateListOf<AppointmentsForUserDTO>()
    val appointmentsForUser=_appointmentsForUser

    private var _networkState = mutableStateOf(UIRequestResponse())
    val networkState=_networkState

    private var _deleteState= mutableStateOf(UIRequestResponse())
    val deleteState = _deleteState

init {

    getUserDetails()
    getUserAppointments()

}


    private fun getUserDetails() {
        userDetailsUseCase().onEach {
            result->
            when(result){
                is Response.Loading->{
                    _networkState.value=UIRequestResponse(isLoading = true)

                }
                is Response.Error->{
                    _networkState.value= UIRequestResponse(isError = true)
                }
                is Response.Success->{
                    _networkState.value=UIRequestResponse(success = true)

                    _userDetails.value= result.data!!

                }
            }
        }.launchIn(viewModelScope)
    }
    private fun getUserAppointments() {
        getAppointmentsForUserUseCase().onEach {
            result->
            when(result){
                is Response.Loading->{
                    _networkState.value= UIRequestResponse(isLoading = true)
                    }
                is Response.Error->{
                    _networkState.value= UIRequestResponse(isError = true)

                }
                is Response.Success->{
                    _networkState.value= UIRequestResponse(success = true)
                    result.data?.let { _appointmentsForUser.addAll(it) }

                }
            }
        }.launchIn(viewModelScope)
    }
    fun byteArrayToBitmap(): ImageBitmap? {
return null
       // return userDetails.value.image?.let { BitmapFactory.decodeByteArray(userDetails.value.image, 0, it.size).asImageBitmap() }
    }

    fun deleteAppointment(appointment: AppointmentsForUserDTO) {

        deleteAppointmentUseCase(appointment.id).onEach {
            result->
            when(result){
                is Response.Success->{_appointmentsForUser.remove(appointment)
                    _deleteState.value=UIRequestResponse(success = true)}
                is Response.Error->{
                    _deleteState.value=UIRequestResponse(isError = true)

                }
                is Response.Loading->{
                    _deleteState.value=UIRequestResponse(isLoading = true)
                //loading
                }
                 }

        }.launchIn(viewModelScope)

    }


}