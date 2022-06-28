package com.example.fon_classroommanagment_frontend.data.remote.dto

import java.util.*

data  class AppointmentRequestedUserDTO (
     val id:UUID,
     val classroomName: String = "",
     val title: String = "",
     val date: Date? = null,
     val startTime:Int = 0,
     val endTime:Int = 0
    )