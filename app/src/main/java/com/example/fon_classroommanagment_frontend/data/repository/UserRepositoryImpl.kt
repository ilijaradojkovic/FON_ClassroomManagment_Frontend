package com.example.fon_classroommanagment_frontend.data.repository

import com.example.fon_classroommanagment_frontend.data.remote.API
import com.example.fon_classroommanagment_frontend.data.remote.dto.AppointmentsForUserDTO
import com.example.fon_classroommanagment_frontend.data.remote.dto.UserDetailsDTO
import com.example.fon_classroommanagment_frontend.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val api: API) :UserRepository {
    override suspend fun getUserDetails(): UserDetailsDTO {
        return api.getUserDetails()
    }

    override suspend fun getUserAppointments(): List<AppointmentsForUserDTO> {
        return api.GetAllAppointmentsForUser()
    }

    override suspend fun isUserAdmin(): Boolean {
       return api.isUserAdmin()
    }
}