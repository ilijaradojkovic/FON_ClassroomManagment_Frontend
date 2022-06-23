package com.example.fon_classroommanagment_frontend.data.repository

import android.util.Log
import com.example.fon_classroommanagment_frontend.data.remote.API
import com.example.fon_classroommanagment_frontend.data.remote.dto.GetForDateAppointmentDTO
import com.example.fon_classroommanagment_frontend.data.remote.dto.RequestAppointmetDateDTO
import com.example.fon_classroommanagment_frontend.data.remote.dto.RequestIsClassroomAvailableForDateDTO
import com.example.fon_classroommanagment_frontend.data.remote.dto.ReserveDTO
import com.example.fon_classroommanagment_frontend.domain.repository.ReservationRepository
import java.lang.Exception
import javax.inject.Inject

class ReservationRepositoryImpl @Inject constructor(private val api: API) :ReservationRepository  {
    override suspend fun getReservationsForDate(requestAppointmetDateDTO: RequestAppointmetDateDTO):
            List<GetForDateAppointmentDTO> {
        return api.getReservationsForDate(requestAppointmetDateDTO)
    }

    override suspend fun getReservationAvailabilityForDate(requestIsClassroomAvailableForDateDTO: RequestIsClassroomAvailableForDateDTO): Boolean {
        return  api.isClassroomAvailableForDate(requestIsClassroomAvailableForDateDTO)
    }

    override suspend fun Reserve(appointmets: List<ReserveDTO>) {

        api.reserve(appointmets)


    }

}