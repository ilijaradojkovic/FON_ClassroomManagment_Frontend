package com.example.fon_classroommanagment_frontend.data.remote.dto

import android.os.Parcelable
import com.example.fon_classroommanagment_frontend.common.UIRequestResponse
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class MyAppointmentUI(
    val reserveDTO: ReserveDTO,

     var uiRequestResponse: UIRequestResponse
) : Parcelable
