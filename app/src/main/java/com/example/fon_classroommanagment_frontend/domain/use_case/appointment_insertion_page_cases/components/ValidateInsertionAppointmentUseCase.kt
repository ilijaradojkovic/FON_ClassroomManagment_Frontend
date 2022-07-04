package com.example.fon_classroommanagment_frontend.domain.use_case.appointment_insertion_page_cases.components

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
    operator fun invoke()
    {
//        validateName()
    }
}