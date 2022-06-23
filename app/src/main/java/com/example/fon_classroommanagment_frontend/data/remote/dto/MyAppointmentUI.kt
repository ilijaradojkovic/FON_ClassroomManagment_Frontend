package com.example.fon_classroommanagment_frontend.data.remote.dto

import com.example.fon_classroommanagment_frontend.common.UIRequestResponse

data class MyAppointmentUI(
    val reserveDTO: ReserveDTO,
    var uiRequestResponse: UIRequestResponse
)
