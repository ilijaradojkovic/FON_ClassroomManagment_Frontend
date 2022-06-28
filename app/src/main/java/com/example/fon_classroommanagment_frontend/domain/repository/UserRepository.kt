package com.example.fon_classroommanagment_frontend.domain.repository

import com.example.fon_classroommanagment_frontend.data.remote.dto.AppointmentRequestedUserDTO
import com.example.fon_classroommanagment_frontend.data.remote.dto.AppointmentsForUserDTO
import com.example.fon_classroommanagment_frontend.data.remote.dto.RequestedAppointmentsDTO
import com.example.fon_classroommanagment_frontend.data.remote.dto.UserDetailsDTO

interface UserRepository {
    suspend fun getUserDetailsLocal(id: Long):RequestedAppointmentsDTO?
    suspend fun  getUserDetails():UserDetailsDTO
    suspend fun getUserAppointments(): List<AppointmentsForUserDTO>
    suspend fun getRequestedAppointments(): List<RequestedAppointmentsDTO>
    suspend fun getRequestedPendingAppointments(id: Long): List<AppointmentRequestedUserDTO>

    suspend fun getUserRequestedAPpointmentsLocal():List<RequestedAppointmentsDTO>


}