package com.example.fon_classroommanagment_frontend.data.remote.dto

import com.example.fon_classroommanagment_frontend.domain.model.AppointmentType
import java.util.*

data  class AppointmentDetailsDTO (
    val id: UUID? = null,

    val name: String="",
    val decription: String = "",

    val reason: String = "",

    val number_of_attendies:Int= 0,

    val start_timeInHours:Int = 0,

    val end_timeInHours:Int = 0,
    val  classroomId:Long=0L,
    val classroomName:String="",
    val  type:AppointmentType?=null,
    val  date:Date?=null

    )