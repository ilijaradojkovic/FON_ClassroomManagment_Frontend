package com.example.fon_classroommanagment_frontend.domain.use_case

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.fon_classroommanagment_frontend.common.Response
import com.example.fon_classroommanagment_frontend.data.remote.dto.GetForDateAppointmentDTO
import com.example.fon_classroommanagment_frontend.data.remote.dto.RequestAppointmetDateDTO
import com.example.fon_classroommanagment_frontend.domain.repository.ReservationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject


class GetReservationsForDateUseCse @Inject constructor(private  val reservationRepositoryImpl: ReservationRepository ) {

    @RequiresApi(Build.VERSION_CODES.O)
    operator  fun invoke(requestAppointmetDateDTO: RequestAppointmetDateDTO): Flow<Response<List<GetForDateAppointmentDTO>>> = flow {

        try{


            emit(Response.Loading())

            val reservations=  reservationRepositoryImpl.getReservationsForDate(requestAppointmetDateDTO)
            emit(Response.Success(reservations))
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