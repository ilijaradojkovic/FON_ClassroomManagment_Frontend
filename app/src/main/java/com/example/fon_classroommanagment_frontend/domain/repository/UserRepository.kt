package com.example.fon_classroommanagment_frontend.domain.repository

import com.example.fon_classroommanagment_frontend.data.remote.dto.UserDetailsDTO

interface UserRepository {

    suspend fun  getUserDetails():UserDetailsDTO
}