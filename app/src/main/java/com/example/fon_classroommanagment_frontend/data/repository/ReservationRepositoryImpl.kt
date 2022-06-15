package com.example.fon_classroommanagment_frontend.data.repository

import com.example.fon_classroommanagment_frontend.data.remote.API
import com.example.fon_classroommanagment_frontend.data.remote.dto.GetForDateAppointmentDTO
import com.example.fon_classroommanagment_frontend.data.remote.dto.RequestAppointmetDateDTO
import com.example.fon_classroommanagment_frontend.domain.repository.ReservationRepository
import javax.inject.Inject

class ReservationRepositoryImpl @Inject constructor(private val api: API) :ReservationRepository  {
    override suspend fun getReservationsForDate(requestAppointmetDateDTO: RequestAppointmetDateDTO):
            List<GetForDateAppointmentDTO> {
        return api.getReservationsForDate(requestAppointmetDateDTO)
    }
}