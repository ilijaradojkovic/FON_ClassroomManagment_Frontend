package com.example.fon_classroommanagment_frontend.data.remote.dto

import java.text.SimpleDateFormat
import java.util.*

data class RequestIsClassroomAvailableForDateDTO(
    val date_appointment:Date,
    val classroomId:Long,
    val start_timeInHours:Int,
    val end_timeInHours:Int,
    val date: String =SimpleDateFormat("yyyy-MM-dd").format(date_appointment)

)
