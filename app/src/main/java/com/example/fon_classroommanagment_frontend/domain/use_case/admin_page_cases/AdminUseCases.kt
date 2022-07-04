package com.example.fon_classroommanagment_frontend.domain.use_case.admin_page_cases

import com.example.fon_classroommanagment_frontend.domain.use_case.*
import com.example.fon_classroommanagment_frontend.domain.use_case.admin_page_cases.components.ConfirmAppointmentUseCase
import com.example.fon_classroommanagment_frontend.domain.use_case.admin_page_cases.components.ConfirmAppointmentsUseCase
import com.example.fon_classroommanagment_frontend.domain.use_case.admin_page_cases.components.DeclineAppointmentUseCase
import com.example.fon_classroommanagment_frontend.domain.use_case.admin_page_cases.components.RetriveUserDetailsDataUseCase
import javax.inject.Inject

class AdminUseCases @Inject constructor(
    val retriveUserDetailsDataUseCase: RetriveUserDetailsDataUseCase,
    val retriveUserRequestedAppointmentsUseCase : RetriveUserRequestedAppointmentsUseCase,
    val confirmAppointmentUseCase: ConfirmAppointmentUseCase,
    val declineAppointmentUseCase: DeclineAppointmentUseCase,
    val confirmAppointmentsUseCase: ConfirmAppointmentsUseCase
) {
}