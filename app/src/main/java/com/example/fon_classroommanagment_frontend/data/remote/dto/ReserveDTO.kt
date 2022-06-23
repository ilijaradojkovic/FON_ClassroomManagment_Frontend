package com.example.fon_classroommanagment_frontend.data.remote.dto


import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.text.SimpleDateFormat
import java.util.*

@Parcelize
data class ReserveDTO (
     val email: String? = null,
     val classroomId: Long,
     val name: String,
     val date_appointment: Date,
     val decription: String,
     val reason: String,
     val number_of_attendies:Int,
     val start_timeInHours:Int,
     val end_timeInHours:Int,
     //appointmentType
     val type:Int,
     //dodato
     val classroomName:String,
     val date:String=SimpleDateFormat("yyyy-MM-dd").format(date_appointment)

     ) : Parcelable