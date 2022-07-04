package com.example.fon_classroommanagment_frontend.domain.use_case.appointment_insertion_page_cases.components

import com.example.fon_classroommanagment_frontend.data.remote.dto.InsertionAppointmentValidationDTO
import com.example.fon_classroommanagment_frontend.data.remote.dto.ReserveDTO
import com.example.fon_classroommanagment_frontend.data.remote.dto.ValidationInsertionResult
import javax.inject.Inject

class ValidateInsertionAppointmentUseCase @Inject constructor(
private  val validateName:ValidateName,
private val validateAppointmentType: ValidateAppointmentType,
private val validateClassrooms: ValidateClassrooms,
private val validateDescription: ValidateDescription,
private val validateEndTime: ValidateEndTime,
private val validateReason: ValidateReason,
private val validateStartTime: ValidateStartTime,
private  val validateAttendees: ValidateAttendees
) {
    operator fun invoke(insertionDTO: InsertionAppointmentValidationDTO): ValidationInsertionResult
    {
        return ValidationInsertionResult(
        validateName(insertionDTO.name),
        validateDescription(insertionDTO.description),
        validateReason(insertionDTO.reason),
        validateStartTime(insertionDTO.startTime),
        validateEndTime(insertionDTO.endTime,insertionDTO.startTime),
        validateAppointmentType(insertionDTO.appointmentType),
        validateClassrooms(insertionDTO.classrooms), validateAttendees(insertionDTO.numberOfAttendees),
        )
    }
}