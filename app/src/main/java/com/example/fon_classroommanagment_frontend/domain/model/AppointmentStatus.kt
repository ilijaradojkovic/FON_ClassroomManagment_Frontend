package com.example.fon_classroommanagment_frontend.domain.model

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.example.fon_classroommanagment_frontend.R
import com.example.fon_classroommanagment_frontend.common.Constants
import com.example.fon_classroommanagment_frontend.common.Constants.APPROVED_KEY
import com.example.fon_classroommanagment_frontend.common.Constants.DECLINED_KEY
import com.example.fon_classroommanagment_frontend.common.Constants.PENDING_KEY

sealed  class AppointmentStatus( val state:Long,  val color: Color) {

    class Accepted(isDarkMode:Boolean):AppointmentStatus(APPROVED_KEY,  if(isDarkMode) Color(0xFF006A66) else Color(0xFF4EDAD4))
    class Pending(isDarkMode:Boolean):AppointmentStatus(PENDING_KEY, if(isDarkMode) Color(0xFFFEBA4B)  else Color(0xFF815600))
    class Declined(isDarkMode:Boolean):AppointmentStatus(DECLINED_KEY, if(isDarkMode) Color(0xFFFFB4AB) else Color(0xFFBA1A1A))


}