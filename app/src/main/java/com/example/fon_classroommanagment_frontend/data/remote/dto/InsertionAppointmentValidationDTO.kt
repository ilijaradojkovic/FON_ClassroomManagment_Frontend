package com.example.fon_classroommanagment_frontend.data.remote.dto

data  class InsertionAppointmentValidationDTO(
val name:String,
val reason:String,
val description:String,
val numberOfAttendees:String,
val appointmentType:Int,
val startTime:String,
val endTime:String,
val classrooms: List<ClassroomChipDTO>
)