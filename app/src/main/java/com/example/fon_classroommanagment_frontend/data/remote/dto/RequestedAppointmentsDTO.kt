package com.example.fon_classroommanagment_frontend.data.remote.dto


data class RequestedAppointmentsDTO(
     val id:Long=-1L,
     val type:String="",
     val image: String?=null,

     val firstName: String="" ,

     val lastName: String ="",

     val number_of_requests: Long?=0,
)