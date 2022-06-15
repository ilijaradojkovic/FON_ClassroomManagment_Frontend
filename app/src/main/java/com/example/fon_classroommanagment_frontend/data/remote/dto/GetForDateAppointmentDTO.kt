package com.example.fon_classroommanagment_frontend.data.remote.dto

import com.google.gson.annotations.SerializedName


data  class GetForDateAppointmentDTO(


    @SerializedName("start_timeInHours")
   val Start_timeInHours:Int,

@SerializedName("end_timeInHours")
     val End_timeInHours:Int ,

   val typeName: String ,

    val classroomName: String ,

   val decription: String
)