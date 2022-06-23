package com.example.fon_classroommanagment_frontend.data.remote.dto

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

  class RequestAppointmetDateDTO(
         datumToFormat: LocalDate
){
init {
    formatDate(datumToFormat)
}
       lateinit var  datum: String

       fun formatDate(localDate: LocalDate){
          val date=Date.from(localDate.atStartOfDay(
              ZoneId.systemDefault()).toInstant())

           datum=SimpleDateFormat("yyyy-MM-dd").format(date)


       }
  }
