package com.example.fon_classroommanagment_frontend.presentation.appointment_screen

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fon_classroommanagment_frontend.common.Constants.MAX_CAPACITY
import com.example.fon_classroommanagment_frontend.common.Constants.MAX_WORK_TIME
import com.example.fon_classroommanagment_frontend.common.Constants.MIN_WORK_TIME
import com.example.fon_classroommanagment_frontend.common.Response
import com.example.fon_classroommanagment_frontend.data.remote.dto.ReserveDTO
import com.example.fon_classroommanagment_frontend.domain.model.AppointmentType
import com.example.fon_classroommanagment_frontend.domain.use_case.GetAllReservationTypesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.time.LocalDate
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class AppointmentCreationViewModel @Inject constructor(private val getAllReservationTypesUseCase: GetAllReservationTypesUseCase):ViewModel() {


    var nameText by  mutableStateOf("") 
    var reasonText by mutableStateOf("") 
    var numAttendeesText by mutableStateOf("") 
    var descriptionText by  mutableStateOf("") 
    var typeClass by mutableStateOf(-1)
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

    var nameTextErrorExplained by  mutableStateOf("")
    var reasonTextErrorExplained by mutableStateOf("")
    var numAttendeesTextErrorExplained by mutableStateOf("")
    var descriptionTextErrorExplained by  mutableStateOf("")
   // var typeClassErrorExplained by mutableStateOf("")
    var classroomsErrorExplained =  mutableStateListOf("")
    var startTimeErrorExplained by   mutableStateOf("")
    var endTimeErrorExplained by  mutableStateOf("")
    var forDateErrorExplained by   mutableStateOf(LocalDate.now())



    private var _appointmentTypes = mutableStateListOf<AppointmentType>()
    val appointmentTypes = _appointmentTypes
    init {
        getAllReservationTypes()
    }

    private fun getAllReservationTypes() {
        getAllReservationTypesUseCase().onEach {
        result->
            when(result){
                is Response.Loading->{}
                is Response.Error->{}
                is Response.Success->{
                    result.data?.let { _appointmentTypes.addAll(it) }

                }
            }
        }.launchIn(viewModelScope)
    }


    fun createAppointment(){

        if(validate()){

        }
    
}
    
   private  fun validate():Boolean{
        
       var result=true
           
       if(!validateName()) result=false
       if(!validateReasonText()) result=false
        if(!validateAttendees()) result= false
       if(!validateStartTime()) result =false
       if(!validateEndTime()) result= false
       if(!validateAppointmentType()) result=false
       return result
       
    }

    private fun validateAppointmentType(): Boolean {
        if(typeClass==-1){

            typeClassError="Please select appointment type"
            return false
        }
        return true
    }
    //stavi da polja budu single line

    private fun validateEndTime(): Boolean {
        if(endTime.isEmpty()){
            endTimeError="Please enter valid end time"
            endTimeErrorExplained="End time cant be empty"
            return false
        }
        //korisnik moze da unese zarez
        if(endTime.toIntOrNull()==null){
            endTimeError="Please enter valid end time"
            endTimeErrorExplained="End time must be digit"
            return false
        }
        if(endTime.length>=3){
            endTimeError="Please enter valid end time"
            endTimeErrorExplained="End time filed mush have 2 or les digits"
            return false
        }
        if(endTime.toInt()>MAX_WORK_TIME || endTime.toInt()< MIN_WORK_TIME){
            endTimeError="Please enter valid end time"
            endTimeErrorExplained="We dont work at ${endTime.toInt()}"
            return false
        }
        if( startTime.isNotEmpty() && endTime.toInt()<startTime.toInt()){
            endTimeError="Please enter valid end time"
            endTimeErrorExplained="Star time cant be after end time"
            return false
        }
        endTimeError=""
        endTimeErrorExplained=""
        return true

    }

    private fun validateStartTime(): Boolean {
        Log.i("cao",startTime)
        if(startTime.isEmpty()){
            startTimeError="Please enter valid end time"
            startTimeErrorExplained="Start time cant be empty"
            return false
        }
        if(startTime.toIntOrNull()==null){
            startTimeError="Please enter valid end time"
            startTimeErrorExplained="Start time must be digit"
            return false
        }
        if(startTime.length>=3){
            startTimeError="Please enter valid end time"
            startTimeErrorExplained="Start time filed mush have 2 or les digits"
            return false
        }
        if(startTime.toInt()>MAX_WORK_TIME || startTime.toInt()< MIN_WORK_TIME){
            startTimeError="Please enter valid end time"
            startTimeErrorExplained="We dont work at ${startTime.toInt()}"
            return false
        }

        startTimeError=""
        startTimeErrorExplained=""
        return true
    }

    private fun validateReasonText(): Boolean {
        if(reasonText.isEmpty()) {
            reasonTextError="Please enter valid reason"
            reasonTextErrorExplained="Reason field cant be empty"
            return false

        }
        if(reasonText.length>=45){
            reasonTextError="Please enter valid reason"
            reasonTextErrorExplained="Reason field length must be less then 45"
            return false
        }

        reasonTextError=""
        reasonTextErrorExplained=""
        return true
    }

    private fun validateName(): Boolean {
        if(nameText.isEmpty()) {
            nameTextError="Please enter valid name"
            nameTextErrorExplained="Name cant be empty"
            return false
        }
        if(nameText.length>=45){
            nameTextError="Please enter valid name"
            nameTextErrorExplained= "Name field length must be less then 45"

            return false
        }
        nameTextErrorExplained=""
        nameTextError=""
        return true

    }
    private fun validateAttendees(): Boolean {
        if(numAttendeesText.isEmpty()) {
            numAttendeesTextError="Please enter valid number of attendees"
            numAttendeesTextErrorExplained="Attendees filed cant be empty"
            return false
        }
        if(numAttendeesText.toInt()>MAX_CAPACITY){
            numAttendeesTextError="Please enter valid number of attendees"
            numAttendeesTextErrorExplained="Attendees filed is over its max capacity"
            return false
        }

        numAttendeesTextError=""
        return true

    }
}