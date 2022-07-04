package com.example.fon_classroommanagment_frontend.domain.use_case.details_classroom_page_cases.components

import android.util.Log
import com.example.fon_classroommanagment_frontend.common.Response
import com.example.fon_classroommanagment_frontend.data.remote.dto.ClassroomCardDTO
import com.example.fon_classroommanagment_frontend.data.remote.dto.ClassroomDetailsDTO
import com.example.fon_classroommanagment_frontend.data.repository.ClassroomRepositoryImpl
import com.example.fon_classroommanagment_frontend.domain.repository.ClassroomRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetClassroomDetailsUseCase @Inject constructor(private val classroomRepositoryImpl: ClassroomRepository){
    operator fun invoke(classroomId:Long): Flow<Response<ClassroomDetailsDTO>> = flow{

        try{
            emit(Response.Loading())
            val types=  classroomRepositoryImpl.getclassroomDetails(classroomId)
            emit(Response.Success(types))
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