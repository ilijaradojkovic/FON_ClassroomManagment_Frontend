package com.example.fon_classroommanagment_frontend.domain.repository

import com.example.fon_classroommanagment_frontend.data.remote.dto.UserLoginDTO
import com.example.fon_classroommanagment_frontend.data.remote.dto.UserRegistrationDTO

interface AuthRepository {

    suspend fun Login(userLoginDTO: UserLoginDTO)

    suspend fun  Register(registrationDTO: UserRegistrationDTO)
}