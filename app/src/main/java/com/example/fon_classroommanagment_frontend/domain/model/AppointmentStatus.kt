package com.example.fon_classroommanagment_frontend.domain.model

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.example.fon_classroommanagment_frontend.R

sealed  class AppointmentStatus(private val state:Int,private  val color: Color) {

    class Accepted(isDarkMode:Boolean):AppointmentStatus(1, Color( if(isDarkMode) R.color.accepted_dark else R.color.accepted_light))
    class Pending(isDarkMode:Boolean):AppointmentStatus(2,Color( if(isDarkMode) R.color.pending_dark else R.color.pending_light))
    class Declined(isDarkMode:Boolean):AppointmentStatus(3,Color( if(isDarkMode) R.color.declined_dark else R.color.declined_light))
}