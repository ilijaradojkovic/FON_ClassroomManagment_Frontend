package com.example.fon_classroommanagment_frontend.presentation.appointment_screen

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.example.fon_classroommanagment_frontend.common.Constants.MAX_CAPACITY
import com.example.fon_classroommanagment_frontend.data.remote.dto.ReserveDTO
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDate
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class AppointmentCreationViewModel @Inject constructor():ViewModel() {


    var nameText by  mutableStateOf("") 
    var reasonText by mutableStateOf("") 
    var numAttendeesText by mutableStateOf("") 
    var descriptionText by  mutableStateOf("") 
    var typeClass by mutableStateOf(0) 
    var classrooms =  mutableStateListOf(0) 
    var startTime by   mutableStateOf("") 
    var endTime by  mutableStateOf("") 
    var forDate by   mutableStateOf(LocalDate.now()) 
    //errors
    var nameTextError by  mutableStateOf("")
    var reasonTextError by mutableStateOf("")
    var numAttendeesTextError by mutableStateOf("")
    var descriptionTextError by  mutableStateOf("")
    var typeClassError by mutableStateOf("")
    var classroomsError =  mutableStateListOf("")
    var startTimeError by   mutableStateOf("")
    var endTimeError by  mutableStateOf("")
    var forDateError by   mutableStateOf(LocalDate.now())

    fun CreateAppointment(){

        if(validate()){

        }
    
}
    
   private  fun validate():Boolean{
        
       var result=true
           
       if(!validateName()) result=false
       if(!validateReasonText()) result=false
        if(!validateAttendees()) return false
       return result
       
    }

    private fun validateReasonText(): Boolean {
        if(reasonText.isEmpty() || reasonText.length>=45) {
            reasonTextError="Please enter valid reason"
            return false
        }

        reasonTextError=""
        return true
    }

    private fun validateName(): Boolean {
        if(nameText.isEmpty() || nameText.length>=45) {
            nameTextError="Please enter valid name"
            return false
        }

        nameTextError=""
        return true

    }
    private fun validateAttendees(): Boolean {
        if(numAttendeesText.isEmpty() || numAttendeesText.toInt()>MAX_CAPACITY) {
            numAttendeesTextError="Please enter valid number of attendees"
            return false
        }

        numAttendeesTextError=""
        return true

    }
}