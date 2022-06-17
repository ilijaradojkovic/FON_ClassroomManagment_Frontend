package com.example.fon_classroommanagment_frontend.data.remote.dto


import java.util.*


data class ReserveDTO (
     val email: String? = null,
     val classroomId: Long,
     val name: String,
     val date: Date,
     val decription: String,
     val reason: String,
     val number_of_attendies:Int,
     val start_timeInHours:Int,
     val end_timeInHours:Int,
     //appointmentType
     val type:Int
)