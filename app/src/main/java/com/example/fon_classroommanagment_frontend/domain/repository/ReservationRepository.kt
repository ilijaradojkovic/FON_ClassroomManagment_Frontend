package com.example.fon_classroommanagment_frontend.domain.repository

import com.example.fon_classroommanagment_frontend.data.remote.dto.GetForDateAppointmentDTO
import com.example.fon_classroommanagment_frontend.data.remote.dto.RequestAppointmetDaetForClassroomDTO
import com.example.fon_classroommanagment_frontend.data.remote.dto.RequestIsClassroomAvailableForDateDTO
import com.example.fon_classroommanagment_frontend.data.remote.dto.ReserveDTO

interface ReservationRepository {
    suspend fun  getReservationsForDate(
        requestAppointmetDateDTO: RequestAppointmetDaetForClassroomDTO,
    ):List<GetForDateAppointmentDTO>

    suspend fun  getReservationAvailabilityForDate(requestIsClassroomAvailableForDateDTO:RequestIsClassroomAvailableForDateDTO):Boolean
    suspend fun Reserve(appointmets: List<ReserveDTO>)


}