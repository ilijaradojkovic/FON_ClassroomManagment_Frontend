package com.example.fon_classroommanagment_frontend.presentation.appointment_screen

import android.content.SharedPreferences
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fon_classroommanagment_frontend.common.Constants
import com.example.fon_classroommanagment_frontend.common.Constants.MAX_CAPACITY
import com.example.fon_classroommanagment_frontend.common.Constants.MAX_WORK_TIME
import com.example.fon_classroommanagment_frontend.common.Constants.MIN_WORK_TIME
import com.example.fon_classroommanagment_frontend.common.Response
import com.example.fon_classroommanagment_frontend.data.remote.dto.ClassroomChipDTO
import com.example.fon_classroommanagment_frontend.data.remote.dto.ReserveDTO
import com.example.fon_classroommanagment_frontend.domain.model.AppointmentType
import com.example.fon_classroommanagment_frontend.domain.use_case.GetAllClassroomsChipUseCase
import com.example.fon_classroommanagment_frontend.domain.use_case.GetAllReservationTypesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.time.LocalDate
import java.time.ZoneId
import java.util.*
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class AppointmentCreationViewModel @Inject constructor(private val getAllReservationTypesUseCase: GetAllReservationTypesUseCase,private val getAllClassroomsChipUseCase: GetAllClassroomsChipUseCase,   sharedPreferences: SharedPreferences):ViewModel() {

    var nameText by  mutableStateOf("") 
    var reasonText by mutableStateOf("") 
    var numAttendeesText by mutableStateOf("") 
    var descriptionText by  mutableStateOf("") 
    var typeClass by mutableStateOf(-1)
    var classrooms =  mutableStateListOf<ClassroomChipDTO>()
    var startTime by   mutableStateOf("") 
    var endTime by  mutableStateOf("") 
    var forDate by   mutableStateOf(LocalDate.now()) 
    //errors
    var nameTextError by  mutableStateOf("")
    var reasonTextError by mutableStateOf("")
    var numAttendeesTextError by mutableStateOf("")
    var descriptionTextError by  mutableStateOf("")
    var typeClassError by mutableStateOf("")
    var classroomsError by  mutableStateOf("")
    var startTimeError by   mutableStateOf("")
    var endTimeError by  mutableStateOf("")
    var forDateError by   mutableStateOf(LocalDate.now())

    var nameTextErrorExplained by  mutableStateOf("")
    var reasonTextErrorExplained by mutableStateOf("")
    var numAttendeesTextErrorExplained by mutableStateOf("")
    var descriptionTextErrorExplained by  mutableStateOf("")
   // var typeClassErrorExplained by mutableStateOf("")
   // var classroomsErrorExplained =  mutableStateListOf("")
    var startTimeErrorExplained by   mutableStateOf("")
    var endTimeErrorExplained by  mutableStateOf("")
  //  var forDateErrorExplained by   mutableStateOf(LocalDate.now())

    var reserveDTO= mutableStateListOf<ReserveDTO>()

    var myEmail by mutableStateOf(sharedPreferences.getString(Constants.EMAIL_KEY,""))

    private var _appointmentTypes = mutableStateListOf<AppointmentType>()
    val appointmentTypes = _appointmentTypes

    private var _classroomChips = mutableStateListOf<ClassroomChipDTO>()
    val classroomNames= _classroomChips

    private var _creationState = mutableStateOf(false)
    val creationState = _creationState
    init {
        getAllReservationTypes()

    }

     fun getAllClassroomNamesSearched(query:String) {
         _classroomChips.clear()
         getAllClassroomsChipUseCase(query).onEach {
                result->
            when(result){
                is Response.Loading->{}
                is Response.Error->{}
                is Response.Success->{

                    result.data?.let {_classroomChips.addAll(it) }

                }
            }
        }.launchIn(viewModelScope)
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
          classrooms.forEach { classroomChipDTO ->
               
               reserveDTO.add(createReservationDTO(classroomChipDTO))
          }
            _creationState.value=true
        }

}

   // private fun CreateReserveDTO(): Collection<ReserveDTO> =

       //classrooms.map {x-> ReserveDTO("myemail",x.,nameText,getDateFromLocalDateTime(),descriptionText,reasonText,numAttendeesText.toInt(),startTime.toInt(),endTime.toInt(),typeClass)  }

    private fun getDateFromLocalDateTime(): Date
        =Date.from(forDate.atStartOfDay(
            ZoneId.systemDefault()).toInstant())

    private fun createReservationDTO(classroom: ClassroomChipDTO):ReserveDTO=
        ReserveDTO(myEmail,
            classroom.id,
            nameText,
            getDateFromLocalDateTime(),
            descriptionText,
            reasonText,
            numAttendeesText.toInt()
            ,startTime.toInt()
            ,endTime.toInt()
            ,typeClass,
            classroom.name
            )

    private  fun validate():Boolean{
        
       var result=true
           
       if(!validateName()) result=false
       if(!validateReasonText()) result=false
        if(!validateAttendees()) result= false
       if(!validateStartTime()) result =false
       if(!validateEndTime()) result= false
       if(!validateAppointmentType()) result=false
       if(!validateClassroomsAppointment()) result=false
        if(!validateDescription()) result=false
        if(!validateEmail()) {
            Log.i("cao","email nije ok")
            result=false}
       return result
       
    }

    private fun validateEmail(): Boolean {
        if(myEmail.isNullOrEmpty() ) return false
        return true
    }

    private fun validateDescription(): Boolean {
    if(descriptionText.isEmpty()){
        descriptionTextError="Please enter valid description"
        descriptionTextErrorExplained="Description cant be empty"
        return false
    }
        descriptionTextError=""
        descriptionTextErrorExplained=""
        return true
    }

    private fun validateClassroomsAppointment(): Boolean {
    if(classrooms.isEmpty()){
        classroomsError="Please select at least one classroom"
        return false
    }
        classroomsError=""
        return true
    }

    private fun validateAppointmentType(): Boolean {
        if(typeClass==-1){

            typeClassError="Please select appointment type"
            return false
        }
        typeClassError=""
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

    fun restart() {

        _creationState.value=false
        reserveDTO.clear()
        nameText=""
        reasonText=""
        numAttendeesText=""
        typeClass=-1
        classrooms.clear()
        forDate= LocalDate.now()
        startTime=""
        endTime=""
        descriptionText=""


    }

    fun addClassroom(classroom: ClassroomChipDTO) {
        classrooms.add(classroom)

    }

    fun setEmail(email: String) {
        myEmail=email
    }
}