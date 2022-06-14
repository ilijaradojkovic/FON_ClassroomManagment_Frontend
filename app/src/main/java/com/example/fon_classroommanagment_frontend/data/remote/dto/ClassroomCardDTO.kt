package com.example.fon_classroommanagment_frontend.data.remote.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ClassroomCardDTO(
  val id:Long,
val name:String,
  val number_of_seats:Int,
  val projector:Boolean,
  @SerializedName("rc")
  val isRC:Boolean
) : Parcelable
