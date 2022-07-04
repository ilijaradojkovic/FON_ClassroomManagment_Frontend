package com.example.fon_classroommanagment_frontend.domain.use_case.profile_page_caes.components

import com.example.fon_classroommanagment_frontend.common.Response
import com.example.fon_classroommanagment_frontend.domain.model.UserRole
import com.example.fon_classroommanagment_frontend.domain.repository.AuthRepository
import com.example.fon_classroommanagment_frontend.domain.repository.CommonDataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetUserRolesUseCase  @Inject constructor(private val commonDataRepository: CommonDataRepository) {

    operator  fun invoke(): Flow<Response<List<UserRole>>> = flow {
        try{
            emit(Response.Loading())
          val roles=  commonDataRepository.getUserRoles()
            emit(Response.Success(roles))
        }catch (httpException: HttpException){//response error sto ne pocinje sa 2 kod
            emit(Response.Error(httpException.localizedMessage ?:"neocekivana greska"))
        }
        catch (io: IOException){
            //ako nemamo net ne moze da prica sa api
            emit(Response.Error("Nema neta"))
        }
    }
}