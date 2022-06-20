package com.example.fon_classroommanagment_frontend.common

import android.os.Parcelable
import com.example.fon_classroommanagment_frontend.data.remote.dto.ReserveDTO
import kotlinx.parcelize.Parcelize

@Parcelize
data class RequestReservastion(
    val reqserveDTO:List<ReserveDTO>

)
    : Parcelable