package com.example.fon_classroommanagment_frontend.domain.use_case

import android.util.Log
import com.example.fon_classroommanagment_frontend.common.Response
import com.example.fon_classroommanagment_frontend.common.TokenResponse
import com.example.fon_classroommanagment_frontend.data.remote.dto.UserLoginDTO
import com.example.fon_classroommanagment_frontend.data.repository.AuthRepositoryImpl
import com.example.fon_classroommanagment_frontend.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

import javax.inject.Inject

class LoginUseCase @Inject constructor(private val authRepository: AuthRepository) {

    operator fun invoke(userLoginDTO: UserLoginDTO): Flow<Response<TokenResponse>> = flow {
        try{
            emit(Response.Loading())

          val tokenResponse=  authRepository.Login(userLoginDTO)
            emit(Response.Success(tokenResponse))
        }catch (httpException:HttpException){//response error sto ne pocinje sa 2 kod
            emit(Response.Error(httpException.localizedMessage ?:"neocekivana greska"))
        }
        catch (io:IOException){
            //ako nemamo net ne moze da prica sa api
            emit(Response.Error("Nema neta"))
        }catch (e:Exception){
            emit(Response.Error(e.localizedMessage ?:"neocekivana greska"))
        }
    }
}