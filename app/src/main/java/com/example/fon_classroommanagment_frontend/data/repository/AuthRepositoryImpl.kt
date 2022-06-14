package com.example.fon_classroommanagment_frontend.data.repository

import android.content.SharedPreferences
import android.util.Log
import com.example.fon_classroommanagment_frontend.common.Constants.TOKEN_VALIDATION
import com.example.fon_classroommanagment_frontend.common.TokenResponse
import com.example.fon_classroommanagment_frontend.data.remote.API
import com.example.fon_classroommanagment_frontend.data.remote.dto.UserLoginDTO
import com.example.fon_classroommanagment_frontend.data.remote.dto.UserRegistrationDTO
import com.example.fon_classroommanagment_frontend.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.awaitResponse



import javax.inject.Inject


class AuthRepositoryImpl @Inject constructor(
    private val api: API,
    private val sharedPreferences: SharedPreferences

    ): AuthRepository {



    override suspend fun Login(userLoginDTO: UserLoginDTO): TokenResponse {

            val callResponse= api.Login(userLoginDTO.username,userLoginDTO.password)
            val awaitResponse = callResponse.awaitResponse()
            val validationToken= awaitResponse.headers()["validationToken"]
            val refreshToken= awaitResponse.headers()["refreshToken"]
        if (validationToken != null) {
            TOKEN_VALIDATION="Bearer $validationToken"
        }
            if(validationToken!=null && refreshToken!=null) {
                sharedPreferences.edit()
                    .putString("jwt_validation_token", "Bearer $validationToken")
                    .putString("jwt_refresh_token",refreshToken)
                    .apply()
             return  TokenResponse(validationToken, refreshToken)

            }
            else throw  Exception()


    }

    override suspend fun Register(registrationDTO: UserRegistrationDTO) {


            val callResponse=api.Register(registrationDTO).awaitResponse()
            if(!callResponse.isSuccessful) throw HttpException(callResponse)



    }
}