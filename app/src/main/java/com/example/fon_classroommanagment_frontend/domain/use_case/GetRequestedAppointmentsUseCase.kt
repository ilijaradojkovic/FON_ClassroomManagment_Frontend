package com.example.fon_classroommanagment_frontend.domain.use_case

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.fon_classroommanagment_frontend.common.Response
import com.example.fon_classroommanagment_frontend.data.remote.dto.GetForDateAppointmentDTO
import com.example.fon_classroommanagment_frontend.data.remote.dto.RequestAppointmetDaetForClassroomDTO
import com.example.fon_classroommanagment_frontend.data.remote.dto.RequestedAppointmentsDTO
import com.example.fon_classroommanagment_frontend.domain.repository.AppointmentRepository
import com.example.fon_classroommanagment_frontend.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetRequestedAppointmentsUseCase @Inject constructor(private  val userRepository: UserRepository ) {

    @RequiresApi(Build.VERSION_CODES.O)
    operator  fun invoke(): Flow<Response<List<RequestedAppointmentsDTO>>> = flow {

        try{


            emit(Response.Loading())

            val reservations=  userRepository.getRequestedAppointments()
            emit(Response.Success(reservations))
        }catch (httpException: HttpException){//response error sto ne pocinje sa 2 kod
            emit(Response.Error(httpException.localizedMessage ?:"neocekivana greska"))
        }
        catch (io: IOException){

            //ako nemamo net ne moze da prica sa api
            emit(Response.Error("No internet"))
        }catch (e:Exception){

            emit(Response.Error(e.localizedMessage ?:"neocekivana greska"))
        }
    }
}