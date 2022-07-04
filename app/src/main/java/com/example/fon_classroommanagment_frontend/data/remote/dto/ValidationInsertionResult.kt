package com.example.fon_classroommanagment_frontend.data.remote.dto

import com.example.fon_classroommanagment_frontend.common.Validation

data class ValidationInsertionResult(
    val nameValidationResult:Validation,
    val descriptionValidationResult:Validation,
    val reasonValidationResult:Validation,
    val startTimeValidationResult:Validation,
    val endTimeValidationResult:Validation,
    val appointmentTypeValidationResult:Validation,
    val classroomsValidationResult:Validation,
    val attendeesValidationResult:Validation
)