package com.example.fon_classroommanagment_frontend.data

import androidx.compose.ui.graphics.Color
import java.time.LocalDateTime

data class Event(
    val type:String,
    val classroomName:String,
    val start:LocalDateTime,
    val end:LocalDateTime,


)