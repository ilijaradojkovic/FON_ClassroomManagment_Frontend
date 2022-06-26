package com.example.fon_classroommanagment_frontend.data.remote.dto

import java.util.*

data class AppointmentsForUserDTO (
    val classroomName:String,
    val appointmentName:String,
    val date : Date,
    val start_timeInHours:Int,
    val end_timeInHours:Int

        )