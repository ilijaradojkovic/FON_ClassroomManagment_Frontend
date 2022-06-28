package com.example.fon_classroommanagment_frontend.data.repository

import com.example.fon_classroommanagment_frontend.data.remote.API
import com.example.fon_classroommanagment_frontend.data.remote.dto.*
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

    override suspend fun deleteAppointment(appointmentId: UUID) {
       api.deleteAppointment(appointmentId)
        //Log.i("cao","repo "+res.awaitResponse().headers())
    }

    override suspend fun confirmAppointment(appointmentId: UUID) {
        api.confirmAppointment(appointmentId)
    }

    override suspend fun declineAppointment(appointmentId: UUID) {
       api.declineAppointment(appointmentId)
    }

    override suspend fun confirmAllAppointments(toList: List<AppointmentRequestedUserDTO>) {
       api.confirmAllAppointments(toList.map { x->x.id })
    }


}