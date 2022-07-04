package com.example.fon_classroommanagment_frontend.domain.use_case.appointments_page_cases.components

import com.example.fon_classroommanagment_frontend.common.Response
import com.example.fon_classroommanagment_frontend.data.remote.dto.ClassroomChipDTO
import com.example.fon_classroommanagment_frontend.data.repository.ClassroomRepositoryImpl
import com.example.fon_classroommanagment_frontend.domain.repository.ClassroomRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetAllClassroomMainInformation @Inject constructor(private val classroomRepository: ClassroomRepository) {
    operator fun invoke(page:Int): Flow<Response<List<ClassroomChipDTO>>> = flow {
        try{

            emit(Response.Loading())

            val classroomTypes=  classroomRepository.getAllClassroomsChip(page)

            emit(Response.Success(classroomTypes))
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