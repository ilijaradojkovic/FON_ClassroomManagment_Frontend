package com.example.fon_classroommanagment_frontend.presentation.profile_screen

import android.content.SharedPreferences
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.ImageBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fon_classroommanagment_frontend.common.Constants
import com.example.fon_classroommanagment_frontend.common.Response
import com.example.fon_classroommanagment_frontend.common.UIRequestResponse
import com.example.fon_classroommanagment_frontend.data.remote.dto.AppointmentsForUserDTO
import com.example.fon_classroommanagment_frontend.data.remote.dto.RequestedAppointmentsDTO
import com.example.fon_classroommanagment_frontend.data.remote.dto.UserDetailsDTO
import com.example.fon_classroommanagment_frontend.domain.use_case.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.util.*
import javax.inject.Inject



@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class ProfileViewModel @Inject constructor(private val userDetailsUseCase: UserDetailsUseCase, private val getAppointmentsForUserUseCase: GetAppointmentsForUserUseCase, private val deleteAppointmentUseCase: DeleteAppointmentUseCase, private val getRequestedAppointmentsUseCase: GetRequestedAppointmentsUseCase, private  val sharedPreferences: SharedPreferences,private val changeEmailUseCase: ChangeEmailUseCase,private  val changePasswordUseCase:ChangePasswordUseCase,private val logoutUseCase: LogoutUseCase) :ViewModel() {

    private var _userDetails= mutableStateOf(UserDetailsDTO())
    val userDetails=_userDetails


    val isAdmin = mutableStateOf(sharedPreferences.getString(Constants.ROLE_KEY,"")==Constants.ADMIN_ROLE_ID)

    private var _appointmentsForUser= mutableStateListOf<AppointmentsForUserDTO>()
    val appointmentsForUser=_appointmentsForUser

    private var _networkState = mutableStateOf(UIRequestResponse())
    val networkState=_networkState
    private var _optionsState = mutableStateOf(UIRequestResponse())
    val optionsState=_optionsState

    private var _deleteState= mutableStateOf(UIRequestResponse())
    val deleteState = _deleteState

    private var _appointmentsRequested=mutableStateListOf<RequestedAppointmentsDTO>()
    val appointmentsRequested=_appointmentsRequested


init {

    getUserDetails()

    if(isAdmin.value)
        getRequestedAppointments()

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
     fun getUserAppointments() {
         Log.i("cao","pozivam")
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
                    Log.i("cao",result.data.toString())
                    _appointmentsForUser.clear()
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

     fun getRequestedAppointments() {
        getRequestedAppointmentsUseCase().onEach {

                result->
            when(result){
                is Response.Loading->{

                    _networkState.value= UIRequestResponse(isLoading = true)
                }
                is Response.Error->{

                    _networkState.value= UIRequestResponse(isError = true)

                }
                is Response.Success->{
                    Log.i("cao",result.data.toString())
                    _appointmentsRequested.clear()

                    _networkState.value= UIRequestResponse(success = true)
                    result.data?.let { _appointmentsRequested.addAll(it) }


                }

            }
        }.launchIn(viewModelScope)
    }

    fun logout() {
      logoutUseCase().launchIn(viewModelScope)
    }
    fun changeEmail(email:String){
        _optionsState.value=UIRequestResponse()
        changeEmailUseCase(email).onEach {
            result->
            when(result){
                is Response.Loading-> {
                    _optionsState.value = UIRequestResponse(isLoading = true)
                }
                is Response.Error->{
                    Log.i("cao",result.message.toString())
                    _optionsState.value = UIRequestResponse(isError = true)

                }
                is Response.Success->{
                    _optionsState.value = UIRequestResponse(success = true)

                }
            }
        }.launchIn(viewModelScope)
    }

    fun changePassword(password: String) {
        _optionsState.value= UIRequestResponse()
        changePasswordUseCase(password).onEach {
            result ->

            when(result){
                is Response.Loading-> {
                    _optionsState.value = UIRequestResponse(isLoading = true)
                }
                is Response.Error->{
                    Log.i("cao",result.message.toString())

                    _optionsState.value = UIRequestResponse(isError = true)

                }
                is Response.Success->{
                    _optionsState.value = UIRequestResponse(success = true)

                }
            }
        }.launchIn(viewModelScope)
    }

    fun restartChangedState() {
        _optionsState.value=UIRequestResponse()
    }

}