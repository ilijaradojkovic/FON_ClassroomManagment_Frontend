package com.example.fon_classroommanagment_frontend.common

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UIRequestResponse(
    val isError:Boolean=false,
    val isLoading:Boolean=false,
    val success:Boolean=false

) : Parcelable