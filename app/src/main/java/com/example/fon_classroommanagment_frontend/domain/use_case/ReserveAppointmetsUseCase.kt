package com.example.fon_classroommanagment_frontend.domain.use_case

import android.util.Log
import com.example.fon_classroommanagment_frontend.common.Response
import com.example.fon_classroommanagment_frontend.data.remote.dto.ReserveDTO
import com.example.fon_classroommanagment_frontend.data.remote.dto.UserRegistrationDTO
import com.example.fon_classroommanagment_frontend.domain.repository.ReservationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.util.*
import javax.inject.Inject

class ReserveAppointmetsUseCase @Inject constructor(private val reservationRepository: ReservationRepository) {

    operator  fun invoke(appointmets: List<ReserveDTO>): Flow<Response<Unit>> = flow {
        try{
            Log.i("cao",appointmets.toString())
            emit(Response.Loading())
            reservationRepository.Reserve(appointmets)
            emit(Response.Success())
        }catch (httpException: HttpException){//response error sto ne pocinje sa 2 kod
            emit(Response.Error(httpException.toString() ?:"neocekivana greska"))
        }
        catch (io: IOException){
            //ako nemamo net ne moze da prica sa api
            emit(Response.Error("Nema neta"))
        }
    }
}