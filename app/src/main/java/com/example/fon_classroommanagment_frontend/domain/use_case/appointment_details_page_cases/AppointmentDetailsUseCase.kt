package com.example.fon_classroommanagment_frontend.domain.use_case.appointment_details_page_cases

import com.example.fon_classroommanagment_frontend.domain.use_case.appointment_details_page_cases.components.GetAppointmentDetailsUseCase
import com.example.fon_classroommanagment_frontend.domain.use_case.appointment_details_page_cases.components.UpdateAppointmentUseCase
import com.example.fon_classroommanagment_frontend.domain.use_case.appointment_insertion_page_cases.components.GetAllClassroomsMainInformationUseCase
import com.example.fon_classroommanagment_frontend.domain.use_case.appointment_insertion_page_cases.components.GetAllReservationTypesUseCase
import com.example.fon_classroommanagment_frontend.domain.use_case.appointment_insertion_page_cases.components.ValidateInsertionAppointmentUseCase

data  class AppointmentDetailsUseCase(
    val getAppointmentDetailsUseCase: GetAppointmentDetailsUseCase,
    val getAllReservationTypesUseCase: GetAllReservationTypesUseCase,
    val getAllClassroomsChipUseCase: GetAllClassroomsMainInformationUseCase,
    val updateAppointmentDataUseCase: UpdateAppointmentUseCase,
    val validateInsertionAppointmentUseCase: ValidateInsertionAppointmentUseCase
)