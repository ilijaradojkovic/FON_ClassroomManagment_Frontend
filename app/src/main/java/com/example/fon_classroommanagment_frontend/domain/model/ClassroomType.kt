package com.example.fon_classroommanagment_frontend.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ClassroomType(val id:Int,val name:String) : Parcelable
