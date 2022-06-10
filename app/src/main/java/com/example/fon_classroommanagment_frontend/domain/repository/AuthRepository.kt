package com.example.fon_classroommanagment_frontend.domain.repository

import com.example.fon_classroommanagment_frontend.common.TokenResponse
import com.example.fon_classroommanagment_frontend.data.remote.dto.UserLoginDTO
import com.example.fon_classroommanagment_frontend.data.remote.dto.UserRegistrationDTO

interface AuthRepository {

     suspend  fun Login(userLoginDTO: UserLoginDTO): TokenResponse

    suspend fun  Register(registrationDTO: UserRegistrationDTO)
}