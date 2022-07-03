package com.example.fon_classroommanagment_frontend.domain.repository

import com.example.fon_classroommanagment_frontend.data.remote.dto.*

interface UserRepository {
    suspend fun getUserDetailsLocal(id: Long):RequestedAppointmentsDTO?
    suspend fun  getUserDetails():UserDetailsDTO
    suspend fun getUserAppointments(): List<AppointmentsForUserDTO>
    suspend fun getRequestedAppointments(): List<RequestedAppointmentsDTO>
    suspend fun getRequestedPendingAppointments(id: Long): List<AppointmentRequestedUserDTO>

    suspend fun getUserRequestedAPpointmentsLocal():List<RequestedAppointmentsDTO>
    suspend fun changeEmail(email: ChangeEmailDTO)
    suspend fun changePassword(changePasswordDTO: ChangePasswordDTO)


}