package com.example.fon_classroommanagment_frontend.domain.use_case.profile_page_caes.components

import com.example.fon_classroommanagment_frontend.common.Response
import com.example.fon_classroommanagment_frontend.data.remote.dto.ChangeEmailDTO
import com.example.fon_classroommanagment_frontend.data.remote.dto.UserLoginDTO
import com.example.fon_classroommanagment_frontend.domain.repository.AuthRepository
import com.example.fon_classroommanagment_frontend.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ChangeEmailUseCase  @Inject constructor(private val userRepository: UserRepository,private  val authRepository: AuthRepository) {

    operator fun invoke(email:String): Flow<Response<Unit>> = flow {

        try{
            val changeEmail=ChangeEmailDTO(email)
            emit(Response.Loading())
            userRepository.changeEmail(changeEmail)
            //authRepository.Login(UserLoginDTO(email,))
            emit(Response.Success())

        }catch (httpException: HttpException){//response error sto ne pocinje sa 2 kod
            emit(Response.Error(httpException.localizedMessage ?:"neocekivana greska"))
        }
        catch (io: IOException){
            //ako nemamo net ne moze da prica sa api
            emit(Response.Error("Nema neta"))
        }catch (e:Exception){
            emit(Response.Error(e.localizedMessage ?:"neocekivana greska"))
        }

    }
}