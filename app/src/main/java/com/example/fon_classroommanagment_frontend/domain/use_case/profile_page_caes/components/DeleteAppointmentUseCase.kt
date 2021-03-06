package com.example.fon_classroommanagment_frontend.domain.use_case.profile_page_caes.components

import android.util.Log
import com.example.fon_classroommanagment_frontend.common.Response
import com.example.fon_classroommanagment_frontend.data.remote.dto.ClassroomChipDTO
import com.example.fon_classroommanagment_frontend.domain.repository.AppointmentRepository
import com.example.fon_classroommanagment_frontend.domain.repository.ClassroomRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.util.*
import javax.inject.Inject

class DeleteAppointmentUseCase  @Inject constructor(private val appointmentRepository: AppointmentRepository) {
    operator fun invoke(id:UUID): Flow<Response<Unit>> = flow {
        try{

            emit(Response.Loading())

             appointmentRepository.deleteAppointment(id)

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