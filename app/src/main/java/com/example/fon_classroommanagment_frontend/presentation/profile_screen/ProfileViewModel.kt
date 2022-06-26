package com.example.fon_classroommanagment_frontend.presentation.profile_screen

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fon_classroommanagment_frontend.common.Response
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
class ProfileViewModel @Inject constructor(private val userDetailsUseCase: UserDetailsUseCase,private val getAppointmentsForUserUseCase: GetAppointmentsForUserUseCase,private val deleteAppointmentUseCase: DeleteAppointmentUseCase) :ViewModel() {

    private var _userDetails= mutableStateOf(UserDetailsDTO())
    val userDetails=_userDetails

    private var _appointmentsForUser= mutableStateListOf<AppointmentsForUserDTO>()
    val appointmentsForUser=_appointmentsForUser

init {
    getUserDetails()
    getUserAppointments()
}

    private fun getUserDetails() {
        userDetailsUseCase().onEach {
            result->
            when(result){
                is Response.Loading->{
                   // Log.i("cao","loadgin")
                }
                is Response.Error->{Log.i("cao",result.message.toString()+"ovde")}
                is Response.Success->{

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
                    //lloading
                    }
                is Response.Error->{Log.i("cao",result.message.toString()+ "safa")}
                is Response.Success->{

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
                    Log.i("cao",_appointmentsForUser.size.toString())}
                is Response.Error->{Log.i("cao",result.message.toString()+"ge")}
                is Response.Loading->{
                //loading
                }
                 }

        }.launchIn(viewModelScope)

    }


}