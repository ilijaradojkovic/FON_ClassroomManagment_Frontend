package com.example.fon_classroommanagment_frontend.domain.use_case.appointment_insertion_page_cases

import android.content.SharedPreferences
import com.example.fon_classroommanagment_frontend.domain.use_case.*
import com.example.fon_classroommanagment_frontend.domain.use_case.appointment_insertion_page_cases.components.*
import javax.inject.Inject

class AppointmentInsertionUseCase @Inject  constructor(
    val getAllReservationTypesUseCase: GetAllReservationTypesUseCase,
    val getAllClassroomsChipUseCase: GetAllClassroomsMainInformationUseCase,
    val getAppointmentDataUseCase: GetLocalAppointmentDataUseCase,
    val sharedPreferences: SharedPreferences,
    val saveAppointmentDataUseCase: SaveAppointmentDataUseCase,
    val updateAppointmentDataUseCase: UpdateLocalAppointmentDataUseCase,
    val validateInsertionAppointmentUseCase: ValidateInsertionAppointmentUseCase
){
}