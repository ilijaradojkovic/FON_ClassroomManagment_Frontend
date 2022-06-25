package com.example.fon_classroommanagment_frontend.data.remote.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class ClassroomChipDTO(
    val id: Long ,
     val name: String
) : Parcelable