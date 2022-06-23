package com.example.fon_classroommanagment_frontend.domain.repository

import com.example.fon_classroommanagment_frontend.data.remote.API
import com.example.fon_classroommanagment_frontend.data.remote.dto.GetForDateAppointmentDTO
import com.example.fon_classroommanagment_frontend.data.remote.dto.RequestAppointmetDateDTO
import com.example.fon_classroommanagment_frontend.data.remote.dto.RequestIsClassroomAvailableForDateDTO
import com.example.fon_classroommanagment_frontend.data.remote.dto.ReserveDTO
import javax.inject.Inject

interface ReservationRepository {
    suspend fun  getReservationsForDate(requestAppointmetDateDTO: RequestAppointmetDateDTO):List<GetForDateAppointmentDTO>

    suspend fun  getReservationAvailabilityForDate(requestIsClassroomAvailableForDateDTO:RequestIsClassroomAvailableForDateDTO):Boolean
    suspend fun Reserve(appointmets: List<ReserveDTO>)


}