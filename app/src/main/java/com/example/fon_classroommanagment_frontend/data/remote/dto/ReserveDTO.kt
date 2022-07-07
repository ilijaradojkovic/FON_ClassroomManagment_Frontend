package com.example.fon_classroommanagment_frontend.data.remote.dto


import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.text.SimpleDateFormat
import java.util.*

@Parcelize
data class ReserveDTO (
    var id: UUID? =UUID.randomUUID(),
    val email: String? = null,
    var classroomId: Long,
    var name: String,
    var date_appointment: Date,
    var decription: String,
    var reason: String,
    var number_of_attendies:Int,
    var start_timeInHours:Int,
    var end_timeInHours:Int,
     //appointmentType
    var type:Int,
     //dodato
    var classroomName:String,
    var date:String=SimpleDateFormat("yyyy-MM-dd").format(date_appointment)

     ) : Parcelable