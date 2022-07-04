package com.example.fon_classroommanagment_frontend.presentation.appointment_insetion_screen

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fon_classroommanagment_frontend.common.Constants
import com.example.fon_classroommanagment_frontend.common.Response
import com.example.fon_classroommanagment_frontend.common.UIRequestResponse
import com.example.fon_classroommanagment_frontend.common.Validation
import com.example.fon_classroommanagment_frontend.data.remote.dto.ClassroomChipDTO
import com.example.fon_classroommanagment_frontend.data.remote.dto.InsertionAppointmentValidationDTO
import com.example.fon_classroommanagment_frontend.data.remote.dto.ReserveDTO
import com.example.fon_classroommanagment_frontend.data.remote.dto.ValidationInsertionResult
import com.example.fon_classroommanagment_frontend.domain.model.AppointmentType
import com.example.fon_classroommanagment_frontend.domain.use_case.appointment_insertion_page_cases.AppointmentInsertionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.time.LocalDate
import java.time.ZoneId
import java.util.*
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class AppointmentInsertionViewModel @Inject constructor(
    private val appointmentInsertionUseCase: AppointmentInsertionUseCase
    ):ViewModel() {

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
    var startTimeErrorExplained by   mutableStateOf("")
    var endTimeErrorExplained by  mutableStateOf("")


    var reserveDTO= mutableStateListOf<ReserveDTO>()

    var myEmail by mutableStateOf(appointmentInsertionUseCase.sharedPreferences.getString(Constants.EMAIL_KEY,""))

    private var _appointmentTypes = mutableStateListOf<AppointmentType>()
    val appointmentTypes = _appointmentTypes

    private var _classroomChips = mutableStateListOf<ClassroomChipDTO>()
    val classroomNames= _classroomChips

    private var _creationState = mutableStateOf(false)
    val creationState = _creationState
    private var _idToUpdate = mutableStateOf<UUID?>(null)


    private var _shouldUpdate = mutableStateOf(false)
    private var _updateState = mutableStateOf(UIRequestResponse())
     var updateState = _updateState

    init {
        getAllReservationTypes()

    }

     fun getAllClassroomNamesSearched(query:String) {

         _classroomChips.clear()
         appointmentInsertionUseCase.getAllClassroomsChipUseCase(query).onEach {
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
     fun getAppointmentData(appointmentID: String) {
        appointmentInsertionUseCase.getAppointmentDataUseCase(UUID.fromString(appointmentID)).onEach {
                result->
            when(result){
                is Response.Loading->{
                    Log.i("cao","uzimam")
                }
                is Response.Error->{
                    Log.i("cao","greska")
                }
                is Response.Success->{
                    _idToUpdate.value=UUID.fromString(appointmentID)
                    result.data?.let {
                        _shouldUpdate.value=true
                        setLocalVariables(it)
                    }

                }
            }
        }.launchIn(viewModelScope)
    }
     private fun getAllReservationTypes() {
        appointmentInsertionUseCase.getAllReservationTypesUseCase().onEach {
        result->
            when(result){
                is Response.Loading->{}
                is Response.Error->{}
                is Response.Success->{
                    _appointmentTypes.clear()
                    result.data?.let { _appointmentTypes.addAll(it) }

                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getDateFromLocalDateTime(): Date
        =Date.from(forDate.atStartOfDay(
            ZoneId.systemDefault()).toInstant())

    private  fun validate():Boolean{
        

           val validated=appointmentInsertionUseCase.validateInsertionAppointmentUseCase(createValidationDTO())
            setUpErrors(validated)

       return validated.successfull
       
    }

    private fun setUpErrors(validated: ValidationInsertionResult) {
       setUpNameErrors(validated.nameValidationResult)
        setUpReasonErrors(validated.reasonValidationResult)
        setUpDescriptionErrors(validated.descriptionValidationResult)
        setUpStartTimeErrors(validated.startTimeValidationResult)
        setUpEndTimeErrors(validated.endTimeValidationResult)
        setUpAppointmentTypeErrors(validated.appointmentTypeValidationResult)
        setUpClassroomsErrors(validated.classroomsValidationResult)
    }

    private fun setUpClassroomsErrors(classroomsValidationResult: Validation) {
        classroomsError=classroomsValidationResult.error
    }

    private fun setUpAppointmentTypeErrors(appointmentTypeValidationResult: Validation) {
        typeClassError=appointmentTypeValidationResult.error
    }

    private fun setUpEndTimeErrors(endTimeValidationResult: Validation) {
    endTimeError=endTimeValidationResult.error
        endTimeErrorExplained=endTimeValidationResult.errorExplained
    }

    private fun setUpStartTimeErrors(startTimeValidationResult: Validation) {
    startTimeError=startTimeValidationResult.error
        startTimeErrorExplained=startTimeValidationResult.errorExplained
    }

    private fun setUpDescriptionErrors(descriptionValidationResult: Validation) {
        descriptionTextError=descriptionValidationResult.error
        descriptionTextErrorExplained=descriptionValidationResult.errorExplained
    }

    private fun setUpReasonErrors(reasonValidationResult: Validation) {
        reasonTextError=reasonValidationResult.error
        reasonTextErrorExplained=reasonValidationResult.errorExplained
    }

    private fun setUpNameErrors(nameValidationResult: Validation) {
        nameTextError=nameValidationResult.error
        nameTextErrorExplained=nameValidationResult.errorExplained
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
        _shouldUpdate.value=false
        _idToUpdate.value=null
        _updateState.value=UIRequestResponse()


    }

    fun addClassroom(classroom: ClassroomChipDTO) {
        classrooms.add(classroom)

    }

    private fun setLocalVariables(reserveDTO: ReserveDTO) {

            nameText=reserveDTO.name
            descriptionText=reserveDTO.decription
            reasonText=reserveDTO.reason
        numAttendeesText= reserveDTO.number_of_attendies.toString()
       forDate=reserveDTO.date_appointment.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
        typeClass=reserveDTO.type
        startTime=reserveDTO.start_timeInHours.toString()
        endTime=reserveDTO.end_timeInHours.toString()
        classrooms.clear()
        classrooms.add(ClassroomChipDTO(reserveDTO.classroomId,reserveDTO.classroomName))

    }

    fun getTextSelected(): String {
        return _appointmentTypes.firstOrNull{x->x.id==typeClass}?.name ?: "Select"
    }

    fun saveAppointment() {
        val reserveDTOToSave=createReservationDTO(classrooms[0])
        reserveDTOToSave.id=_idToUpdate.value


        appointmentInsertionUseCase.updateAppointmentDataUseCase(reserveDTOToSave).onEach {
            result->
            when(result){
                is Response.Success->{
                    _updateState.value= UIRequestResponse(success = true)
                }
                is Response.Loading->{
                    _updateState.value=UIRequestResponse(isLoading = true)

                }
                is Response.Error->{
                    _updateState.value=UIRequestResponse(isError = true)

                }
            }

        }.launchIn(viewModelScope)
    }

    fun createAppointment(){

        if(validate()){
            classrooms.forEach { classroomChipDTO ->

                reserveDTO.add(createReservationDTO(classroomChipDTO))

            }
            appointmentInsertionUseCase.saveAppointmentDataUseCase(reserveDTO).launchIn(viewModelScope)

            _creationState.value=true
        }

    }

    private fun createReservationDTO(classroom: ClassroomChipDTO):ReserveDTO=
        ReserveDTO(UUID.randomUUID(),
            myEmail,
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

    private fun createValidationDTO():InsertionAppointmentValidationDTO=
        InsertionAppointmentValidationDTO(
            nameText,reasonText,descriptionText,numAttendeesText,typeClass,startTime,endTime,classrooms.toList()
        )

}