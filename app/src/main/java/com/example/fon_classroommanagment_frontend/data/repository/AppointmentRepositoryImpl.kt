package com.example.fon_classroommanagment_frontend.data.repository

import com.example.fon_classroommanagment_frontend.data.remote.API
import com.example.fon_classroommanagment_frontend.data.remote.dto.GetForDateAppointmentDTO
import com.example.fon_classroommanagment_frontend.data.remote.dto.RequestAppointmetDaetForClassroomDTO
import com.example.fon_classroommanagment_frontend.data.remote.dto.RequestIsClassroomAvailableForDateDTO
import com.example.fon_classroommanagment_frontend.data.remote.dto.ReserveDTO
import com.example.fon_classroommanagment_frontend.domain.repository.AppointmentRepository
import java.util.*
import javax.inject.Inject

class AppointmentRepositoryImpl @Inject constructor(private val api: API) :AppointmentRepository  {
    override suspend fun getReservationsForDate(
        requestAppointmetDateDTO: RequestAppointmetDaetForClassroomDTO,
    ):
            List<GetForDateAppointmentDTO> {
        return api.getReservationsForDate(requestAppointmetDateDTO)
    }

    override suspend fun getReservationAvailabilityForDate(requestIsClassroomAvailableForDateDTO: RequestIsClassroomAvailableForDateDTO): Boolean {
        return  api.isClassroomAvailableForDate(requestIsClassroomAvailableForDateDTO)
    }

    override suspend fun Reserve(appointmets: List<ReserveDTO>) {

        api.reserve(appointmets)


    }

    override suspend fun deleteAppointment(id: UUID) {
        api.deleteAppointment(id)
    }

}