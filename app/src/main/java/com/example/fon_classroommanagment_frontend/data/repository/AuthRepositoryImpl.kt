package com.example.fon_classroommanagment_frontend.data.repository


import android.content.SharedPreferences
import android.util.Log
import com.auth0.android.jwt.JWT
import com.example.fon_classroommanagment_frontend.common.Constants.REFRESH_TOKEN_KEY
import com.example.fon_classroommanagment_frontend.common.Constants.ROLE_KEY
import com.example.fon_classroommanagment_frontend.common.Constants.VALIDATION_TOKEN_KEY
import com.example.fon_classroommanagment_frontend.common.TokenResponse
import com.example.fon_classroommanagment_frontend.data.remote.API
import com.example.fon_classroommanagment_frontend.data.remote.dto.UserLoginDTO
import com.example.fon_classroommanagment_frontend.data.remote.dto.UserRegistrationDTO
import com.example.fon_classroommanagment_frontend.domain.repository.AuthRepository
import retrofit2.HttpException
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

            if(validationToken!=null && refreshToken!=null) {
                val jwt = JWT(validationToken)
                val role= jwt.claims["roles"]!!.asArray(String::class.java)[0].toString()

                sharedPreferences.edit().remove(ROLE_KEY).remove(VALIDATION_TOKEN_KEY).remove(
                    REFRESH_TOKEN_KEY).apply()
                sharedPreferences.edit()
                    .putString(VALIDATION_TOKEN_KEY, "Bearer $validationToken")
                    .putString(REFRESH_TOKEN_KEY,refreshToken)
                    .putString(ROLE_KEY,role)
                    .apply()
             return  TokenResponse(validationToken, refreshToken)

            }
            else throw  Exception()


    }

    override suspend fun Register(registrationDTO: UserRegistrationDTO) {

            val callResponse=api.Register(registrationDTO).awaitResponse()
            if(!callResponse.isSuccessful) throw HttpException(callResponse)



    }

    override suspend fun Logout() {
        api.logout()
    }
}