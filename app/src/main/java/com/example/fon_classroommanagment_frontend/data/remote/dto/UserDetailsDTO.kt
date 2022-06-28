package com.example.fon_classroommanagment_frontend.data.remote.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserDetailsDTO(
     val id:Long=-1L,
     val firstName:String="",
     val lastName:String="",
     val typeName:String="",
     val image:  String? =null
) : Parcelable
