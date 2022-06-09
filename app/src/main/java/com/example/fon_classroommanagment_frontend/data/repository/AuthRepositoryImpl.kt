package com.example.fon_classroommanagment_frontend.data.repository

import android.util.Log
import com.example.fon_classroommanagment_frontend.data.remote.API
import com.example.fon_classroommanagment_frontend.data.remote.dto.UserLoginDTO
import com.example.fon_classroommanagment_frontend.data.remote.dto.UserRegistrationDTO
import com.example.fon_classroommanagment_frontend.domain.repository.AuthRepository
import java.lang.Exception
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val api: API): AuthRepository {
    override suspend fun Login(userLoginDTO: UserLoginDTO) {
        try {
         val callResponse= api.Login(userLoginDTO.email, userLoginDTO.password)
            Log.i("cao",callResponse.toString())
        }
        catch (e:Exception){

        }
    }

    override suspend fun Register(registrationDTO: UserRegistrationDTO) {
        TODO("Not yet implemented")
    }
}