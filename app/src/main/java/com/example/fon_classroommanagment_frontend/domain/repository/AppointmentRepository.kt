package com.example.fon_classroommanagment_frontend.domain.repository

import com.example.fon_classroommanagment_frontend.data.remote.dto.*
import java.util.*

interface AppointmentRepository {
    suspend fun  getReservationsForDate(
        requestAppointmetDateDTO: RequestAppointmetDaetForClassroomDTO,
    ):List<GetForDateAppointmentDTO>

    suspend fun  getReservationAvailabilityForDate(requestIsClassroomAvailableForDateDTO:RequestIsClassroomAvailableForDateDTO):Boolean
    suspend fun Reserve(appointmets: List<ReserveDTO>)
    suspend fun deleteAppointment(appointmentId: UUID)
    suspend fun confirmAppointment(appointmentId: UUID)
    suspend fun declineAppointment(appointmentId:UUID)
    suspend  fun confirmAllAppointments(toList: List<AppointmentRequestedUserDTO>)
    suspend fun getSelectedAppointment(id:UUID):ReserveDTO
    suspend fun saveAppointment(reserveDTO: ReserveDTO)


}