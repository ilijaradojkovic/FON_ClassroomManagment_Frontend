package com.example.fon_classroommanagment_frontend.data.remote.dto

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneId
import java.util.*

  class RequestAppointmetDaetForClassroomDTO(
      datel: LocalDate,
      private val classroomId:Long
){
init {
    formatDate(datel)
}
       lateinit var  date: String

       fun formatDate(localDate: LocalDate){
          val date=Date.from(localDate.atStartOfDay(
              ZoneId.systemDefault()).toInstant())

           this.date =SimpleDateFormat("yyyy-MM-dd").format(date)


       }
  }
