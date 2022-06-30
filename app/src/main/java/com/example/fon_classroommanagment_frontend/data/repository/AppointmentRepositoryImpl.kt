package com.example.fon_classroommanagment_frontend.data.repository

import android.util.Log
import com.example.fon_classroommanagment_frontend.data.remote.API
import com.example.fon_classroommanagment_frontend.data.remote.dto.*
import com.example.fon_classroommanagment_frontend.domain.repository.AppointmentRepository
import java.util.*
import javax.inject.Inject

class AppointmentRepositoryImpl @Inject constructor(private val api: API) :AppointmentRepository  {

    private val myAppointments= mutableSetOf<ReserveDTO>()

    override suspend fun getSelectedAppointment(id:UUID): ReserveDTO
    {
        return myAppointments.first{x->x.id==id}
    }

    override suspend fun saveAppointment(reserveDTO: ReserveDTO) {
        myAppointments.add(reserveDTO)
    }

    override suspend fun updateAppointment(reserveDTO: ReserveDTO) {
        myAppointments.map { x ->
            if (x.id == reserveDTO.id) {
                x.name = reserveDTO.name
                x.classroomName = reserveDTO.classroomName
                x.end_timeInHours = reserveDTO.end_timeInHours
                x.date_appointment = reserveDTO.date_appointment
                x.date = reserveDTO.date
                x.start_timeInHours = reserveDTO.start_timeInHours
                x.decription = reserveDTO.decription
                x.number_of_attendies = reserveDTO.number_of_attendies
                x.reason = reserveDTO.reason
                x.type = reserveDTO.type
            }
        }
    }

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