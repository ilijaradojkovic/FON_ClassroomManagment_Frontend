package com.example.fon_classroommanagment_frontend.presentation.details_appointment_screen

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fon_classroommanagment_frontend.common.Response
import com.example.fon_classroommanagment_frontend.common.UIRequestResponse
import com.example.fon_classroommanagment_frontend.common.Validation
import com.example.fon_classroommanagment_frontend.data.remote.dto.*
import com.example.fon_classroommanagment_frontend.domain.model.AppointmentType
import com.example.fon_classroommanagment_frontend.domain.use_case.appointment_details_page_cases.AppointmentDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.util.*
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class DetailsAppointmentViewModel @Inject constructor(
val appointmentDetailsUseCase: AppointmentDetailsUseCase
    ) :ViewModel() {


    var nameText by  mutableStateOf("")
    var reasonText by mutableStateOf("")
    var numAttendeesText by mutableStateOf("")
    var descriptionText by  mutableStateOf("")
    var typeClass by mutableStateOf(-1)
    var classrooms =  mutableStateListOf<ClassroomChipDTO>()
    var startTime by   mutableStateOf("")
    var endTime by  mutableStateOf("")
    var forDate by   mutableStateOf(LocalDate.now())
    var appointmentId by mutableStateOf<UUID>(UUID.randomUUID())

    var nameTextError by  mutableStateOf("")
    var reasonTextError by mutableStateOf("")
    var numAttendeesTextError by mutableStateOf("")
    var descriptionTextError by  mutableStateOf("")
    var typeClassError by mutableStateOf("")
    var classroomsError by  mutableStateOf("")
    var startTimeError by   mutableStateOf("")
    var endTimeError by  mutableStateOf("")
    var forDateError by   mutableStateOf(LocalDate.now())
    private var _classroomChips = mutableStateListOf<ClassroomChipDTO>()
    val classroomNames= _classroomChips

    var nameTextErrorExplained by  mutableStateOf("")
    var reasonTextErrorExplained by mutableStateOf("")
    var numAttendeesTextErrorExplained by mutableStateOf("")
    var descriptionTextErrorExplained by  mutableStateOf("")
    var startTimeErrorExplained by   mutableStateOf("")
    var endTimeErrorExplained by  mutableStateOf("")

   private var _uiResponse = mutableStateOf(UIRequestResponse())
    val uiResponse = _uiResponse
    private var _appointmentTypes = mutableStateListOf<AppointmentType>()
    val appointmentTypes = _appointmentTypes

    fun saveAppointment() {
        if(validate()) {
            appointmentDetailsUseCase.updateAppointmentDataUseCase(createUpdateDTO()).onEach { result ->
                when (result) {
                    is Response.Loading -> {
                        _uiResponse.value=UIRequestResponse(isLoading = true)

                    }
                    is Response.Error -> {
                        _uiResponse.value=UIRequestResponse(isError = true)

                    }
                    is Response.Success -> {
                        _uiResponse.value=UIRequestResponse(success = true)
                    }
                }
            }.launchIn(viewModelScope)
        }
    }
    fun restart(){
        _uiResponse.value=UIRequestResponse()
    }

    private fun validate(): Boolean {
        val validated=  appointmentDetailsUseCase.validateInsertionAppointmentUseCase(createValidationDTO())
        setUpErrors(validated)

        return validated.successfull
    }
    private fun createValidationDTO(): InsertionAppointmentValidationDTO =
        InsertionAppointmentValidationDTO(
            nameText,reasonText,descriptionText,numAttendeesText,typeClass,startTime,endTime,classrooms.toList()
        )

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

    fun convertToDateViaInstant(): Date? {
        return Date.from(
            forDate.atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant()
        )
    }

    private fun createUpdateDTO():UpdateAppointmentDTO{

        return UpdateAppointmentDTO(
            appointmentId,
            classrooms[0].id,
            nameText,
            SimpleDateFormat("yyyy-MM-dd").format(convertToDateViaInstant()!!),
            descriptionText,
            reasonText,
            numAttendeesText.toInt(),
            startTime.trim().toInt(),
           endTime.trim().toInt(),
            typeClass.toLong())
    }
    init {

        getAllReservationTypes()
    }

    private fun getAllReservationTypes() {
        appointmentDetailsUseCase.getAllReservationTypesUseCase().onEach {
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
    fun getAppointmentData(id:UUID){

        appointmentDetailsUseCase.getAppointmentDetailsUseCase(id).onEach {
            result->
            when(result){
                is Response.Success->{
              if(result.data!=null)
                    setData(result.data)
                }
                is Response.Error->{
                Log.i("cao",result.message.toString())
                }
                is Response.Loading->{

                }
            }
        }.launchIn(viewModelScope)
    }

    private fun setData(data: AppointmentDetailsDTO) {
        Log.i("cao",data.toString())
        nameText=data.name
        reasonText =data.reason
         numAttendeesText =data.number_of_attendies.toString()
         descriptionText =data.decription
            typeClass= data.type?.id ?:-1
         classrooms.add(ClassroomChipDTO(data.classroomId,data.classroomName) )
         startTime =data.start_timeInHours.toString()
         endTime =data.end_timeInHours.toString()
        appointmentId= data.id!!
         forDate = data.date?.let { convertToLocalDateViaMilisecond(it) }

    }
    fun convertToLocalDateViaMilisecond(dateToConvert: Date): LocalDate? {
        return Instant.ofEpochMilli(dateToConvert.time)
            .atZone(ZoneId.systemDefault())
            .toLocalDate()
    }

    fun getAllClassroomNamesSearched(query:String) {

        _classroomChips.clear()
        appointmentDetailsUseCase.getAllClassroomsChipUseCase(query).onEach {
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

    val getTextSelected = mutableStateOf(_appointmentTypes.firstOrNull{x->x.id==typeClass}?.name ?: "Select")

}