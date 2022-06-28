package com.example.fon_classroommanagment_frontend.data.remote.dto

import java.util.*

data  class AppointmentRequestedUserDTO (
     val classroomName: String? = null,
     val title: String? = null,
     val date: Date? = null,
     val startTime:Int = 0,
     val endTime:Int = 0
    )