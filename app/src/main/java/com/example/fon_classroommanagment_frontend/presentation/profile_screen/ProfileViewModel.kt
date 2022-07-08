package com.example.fon_classroommanagment_frontend.presentation.profile_screen

import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.util.Base64
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
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

    private var _logoutState = mutableStateOf(UIRequestResponse())
    val logoutState=_logoutState

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
     fun getImage(): Bitmap? {
        return base64ToBytes(userDetails.value.image)?.let { bytesToBitmap(it) }
    }

   private  fun base64ToBytes(base64: String?): ByteArray? {
        return Base64.decode(base64, 0)
    }
   private  fun bytesToBitmap(bytes: ByteArray): Bitmap? {
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
    }
    private fun getEmployeesData(){
        profileUseCases.getEmployeesInfoAdmin().onEach {
            result->
            when(result){
               is Response.Success->{
                   result.data?.let { listEmployees ->
                       _employees.clear()
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

                    _appointmentsForUser.clear()
                    _networkState.value= UIRequestResponse(success = true)

                    result.data?.let { _appointmentsForUser.addAll(it) }

                }
            }


        }.launchIn(viewModelScope)

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

                    _appointmentsRequested.clear()

                    _networkState.value= UIRequestResponse(success = true)
                    result.data?.let { _appointmentsRequested.addAll(it) }


                }

            }
        }.launchIn(viewModelScope)
    }

    fun logout() {
        profileUseCases.logoutUseCase().onEach {
            result->
            when(result){

                is Response.Loading->{
                    _logoutState.value= UIRequestResponse(isLoading = true)
                }
                 is Response.Error->{
                     _logoutState.value=UIRequestResponse(isError = true)

                 }
                is Response.Success->{
                    _logoutState.value=UIRequestResponse(success = true)

                }

            }
            Log.i("cao",result.toString())

        }.launchIn(viewModelScope)
    }
    fun changeEmail(email:String){
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

    private fun restartPasswordState() {
        _passwordChangedState.value=UIRequestResponse()
    }
    private  fun restartEmailState() {
        _emailChangedState.value=UIRequestResponse()
    }
    fun restart(){
        restartPasswordState()
        restartEmailState()
        restartLogoutState()
        restartDeleteState()
    }

    private fun restartDeleteState() {
        _deleteState.value= UIRequestResponse()
    }

    private fun restartLogoutState() {
        _logoutState.value= UIRequestResponse()
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
                        _userRoles.clear()

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


    }

    fun getImageWithBase64(image: String?): Bitmap? {
        if(image==null) return null
        return base64ToBytes(image)?.let { bytesToBitmap(it) }

    }

}