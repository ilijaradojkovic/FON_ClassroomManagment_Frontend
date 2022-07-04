package com.example.fon_classroommanagment_frontend.domain.use_case.classrooms_page_cases.components

import com.example.fon_classroommanagment_frontend.common.Response
import com.example.fon_classroommanagment_frontend.domain.model.ClassroomType
import com.example.fon_classroommanagment_frontend.domain.model.EmployeeDepartment
import com.example.fon_classroommanagment_frontend.domain.repository.CommonDataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetAllClassroomTypesUserCase @Inject constructor(private val commonDataRepositoryImpl: CommonDataRepository) {

    operator fun invoke(): Flow<Response<List<ClassroomType>>> = flow{

        try{

            emit(Response.Loading())

            val classroomTypes=  commonDataRepositoryImpl.getAllClassroomTypes()

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