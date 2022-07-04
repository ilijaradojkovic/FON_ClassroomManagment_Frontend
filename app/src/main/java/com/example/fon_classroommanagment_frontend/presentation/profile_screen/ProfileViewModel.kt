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
import com.example.fon_classroommanagment_frontend.data.remote.dto.*
import com.example.fon_classroommanagment_frontend.domain.model.UserRole
import com.example.fon_classroommanagment_frontend.domain.use_case.*
import com.example.fon_classroommanagment_frontend.domain.use_case.profile_page_caes.ProfileUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.util.*
import javax.inject.Inject



@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileUseCases: ProfileUseCases
) :ViewModel() {

    private var _userDetails= mutableStateOf(UserDetailsDTO())
    val userDetails=_userDetails


    var isAdmin = mutableStateOf(profileUseCases.sharedPreferences.getString(Constants.ROLE_KEY,"")==Constants.ADMIN_ROLE_ID)

    private var _appointmentsForUser= mutableStateListOf<AppointmentsForUserDTO>()
    val appointmentsForUser=_appointmentsForUser

    private var _networkState = mutableStateOf(UIRequestResponse())
    val networkState=_networkState
    private var _passwordChangedState = mutableStateOf(UIRequestResponse())
    val passwordChangedState=_passwordChangedState

    private var _emailChangedState = mutableStateOf(UIRequestResponse())
    val emailChangedState=_emailChangedState

    private var _deleteState= mutableStateOf(UIRequestResponse())
    val deleteState = _deleteState

    private var _updateRoleState= mutableStateOf(UIRequestResponse())
    val updateRoleState = _updateRoleState

    private var _appointmentsRequested=mutableStateListOf<RequestedAppointmentsDTO>()
    val appointmentsRequested=_appointmentsRequested

    private var _employees= mutableStateListOf<UI_EmployeeAdminCardDTO>()
    val employees=_employees

    private var _userRoles= mutableStateListOf<UserRole>()
    val userRoles=_userRoles

init {


}


    private fun getEmployeesData(){
        profileUseCases.getEmployeesInfoAdmin().onEach {
            result->
            when(result){
               is Response.Success->{
                   result.data?.let { listEmployees ->
                       listEmployees.forEach {
                           employee->
                           _employees.add(UI_EmployeeAdminCardDTO(employeeAdminCardDTO = employee))
                       }
                        }
               }
                is Response.Error->{
                    Log.i("cao","error"+result.message)

                }
                is Response.Loading->{
                    Log.i("cao","loading")
                }
            }
        }.launchIn(viewModelScope)
    }
    private fun getUserDetails() {
        profileUseCases.userDetailsUseCase().onEach {
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
         profileUseCases.getAppointmentsForUserUseCase().onEach {
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

        profileUseCases.deleteAppointmentUseCase(appointment.id).onEach {
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
         profileUseCases.getRequestedAppointmentsUseCase().onEach {

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
        profileUseCases.logoutUseCase().launchIn(viewModelScope)
    }
    fun changeEmail(email:String){
        Log.i("cao","change email")
        _emailChangedState.value=UIRequestResponse()
        profileUseCases.changeEmailUseCase(email).onEach {
            result->
            when(result){
                is Response.Loading-> {
                    _emailChangedState.value = UIRequestResponse(isLoading = true)
                }
                is Response.Error->{

                    _emailChangedState.value = UIRequestResponse(isError = true)

                }
                is Response.Success->{
                    _emailChangedState.value = UIRequestResponse(success = true)


                }
            }
        }.launchIn(viewModelScope)
    }

    fun changePassword(password: String) {
        _passwordChangedState.value= UIRequestResponse()
        profileUseCases.changePasswordUseCase(password).onEach {
            result ->

            when(result){
                is Response.Loading-> {
                    _passwordChangedState.value = UIRequestResponse(isLoading = true)
                }
                is Response.Error->{
                    Log.i("cao",result.message.toString())

                    _passwordChangedState.value = UIRequestResponse(isError = true)

                }
                is Response.Success->{
                    _passwordChangedState.value = UIRequestResponse(success = true)

                }
            }
        }.launchIn(viewModelScope)
    }

    fun restartPasswordState() {
        _passwordChangedState.value=UIRequestResponse()
    }fun restartEmailState() {
        _emailChangedState.value=UIRequestResponse()
    }

    fun onStart() {
        isAdmin = mutableStateOf(profileUseCases.sharedPreferences.getString(Constants.ROLE_KEY,"")==Constants.ADMIN_ROLE_ID)

        getUserDetails()

        if(isAdmin.value) {
            getUserRoles()
            getRequestedAppointments()
            getEmployeesData()
        }
    }

    private fun getUserRoles() {
        profileUseCases.getUserRolesUseCase().onEach {
            result->
                when(result){
                    is Response.Loading->{}
                    is Response.Error->{}
                    is Response.Success->{

                        result.data?.let { _userRoles.addAll(it) }
                        Log.i("cao",result.data.toString())
                    }
                }

                    }.launchIn(viewModelScope)
    }

    fun UpdateRole(idRole: Long,idUser:UUID) {

        profileUseCases.updateRoleUseCase(UpdateRoleDTO(idUser,idRole)).onEach {
            result->
            when(result){
                is Response.Success->{

                    _updateRoleState.value= UIRequestResponse(success = true)
                }
                is Response.Error->{
                    _updateRoleState.value=UIRequestResponse(isError = true)

                    Log.i("cao","error"+result.message)
                }
                is Response.Loading->{
                    _updateRoleState.value=UIRequestResponse(isLoading = true)

                }
            }
        }.launchIn(viewModelScope)
    }

    fun showRoles(show: Boolean, id: String) {
        val l=_employees.toList()
            l.forEach {
            if(it.employeeAdminCardDTO.id==id) it.showRoles=show
        }
        _employees.clear()
        _employees.addAll(l)
        Log.i("cao",_employees.toList().toString())

    }

}