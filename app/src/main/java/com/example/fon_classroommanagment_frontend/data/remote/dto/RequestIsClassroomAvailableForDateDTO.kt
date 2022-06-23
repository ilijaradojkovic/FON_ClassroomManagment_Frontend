package com.example.fon_classroommanagment_frontend.data.remote.dto

import java.text.SimpleDateFormat
import java.util.*

data class RequestIsClassroomAvailableForDateDTO(
    val date_appointment:Date,
    val classroomId:Long,
    val date: String =SimpleDateFormat("yyyy-MM-dd").format(date_appointment)

)
