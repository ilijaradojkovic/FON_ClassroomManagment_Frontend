package com.example.fon_classroommanagment_frontend.data.remote.dto

import java.util.*


data class UpdateAppointmentDTO (

     val id: UUID? = null,

     val classroomId: Long = -1L,

     val name: String = "",
     val date: String="",

     val decription: String = "",

     val reason: String = "",


     val number_of_attendies:Int = 0,


     val start_timeInHours:Int = 0,

     val end_timeInHours:Int = 0,


     val type: Long = -1L
)