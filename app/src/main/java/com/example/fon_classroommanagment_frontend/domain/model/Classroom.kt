package com.example.fon_classroommanagment_frontend.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Classroom(
  val id:Long,
val name:String,
  val number_of_seats:Int,
  val projector:Boolean,
  val isRC:Boolean
) : Parcelable
