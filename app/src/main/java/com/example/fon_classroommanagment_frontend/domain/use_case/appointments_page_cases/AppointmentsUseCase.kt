package com.example.fon_classroommanagment_frontend.domain.use_case.appointments_page_cases

import com.example.fon_classroommanagment_frontend.domain.use_case.appointments_page_cases.components.GetAllClassroomMainInformation
import com.example.fon_classroommanagment_frontend.domain.use_case.appointments_page_cases.components.GetReservationsForClassroomAndDateUseCse
import javax.inject.Inject

class AppointmentsUseCase @Inject constructor(
     val getReservationsForDateUseCase: GetReservationsForClassroomAndDateUseCse,
     val getAllClassroomChipsPaging: GetAllClassroomMainInformation,
) {
}