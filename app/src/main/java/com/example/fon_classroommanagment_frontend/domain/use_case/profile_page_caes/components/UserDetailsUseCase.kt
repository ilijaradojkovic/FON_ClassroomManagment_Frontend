package com.example.fon_classroommanagment_frontend.domain.use_case.profile_page_caes.components

import android.util.Log
import com.example.fon_classroommanagment_frontend.common.Response
import com.example.fon_classroommanagment_frontend.data.remote.dto.ReserveDTO
import com.example.fon_classroommanagment_frontend.data.remote.dto.UserDetailsDTO
import com.example.fon_classroommanagment_frontend.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class UserDetailsUseCase @Inject constructor(private val userRepository: UserRepository) {

    operator  fun invoke(): Flow<Response<UserDetailsDTO>> = flow {
        try{

            emit(Response.Loading())
            val userDetails=userRepository.getUserDetails()
            emit(Response.Success(userDetails))
        }catch (httpException: HttpException){//response error sto ne pocinje sa 2 kod
            emit(Response.Error(httpException.toString() ?:"neocekivana greska"))
        }
        catch (io: IOException){
            //ako nemamo net ne moze da prica sa api
            emit(Response.Error("Nema neta"))
        }
    }
}