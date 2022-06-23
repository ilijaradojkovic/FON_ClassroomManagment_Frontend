package com.example.fon_classroommanagment_frontend.domain.use_case

import com.example.fon_classroommanagment_frontend.common.Response
import com.example.fon_classroommanagment_frontend.data.remote.dto.RequestIsClassroomAvailableForDateDTO
import com.example.fon_classroommanagment_frontend.domain.repository.ReservationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CheckAvailabilityClassroomForDateUseCase @Inject constructor(private val reservationRepositoryImpl: ReservationRepository) {

//promeni povratni tip
    operator fun invoke(requestIsClassroomAvailableForDateDTO: RequestIsClassroomAvailableForDateDTO): Flow<Response<Boolean>> = flow {

    try{

        emit(Response.Loading())

        val isAvailable=  reservationRepositoryImpl.getReservationAvailabilityForDate(requestIsClassroomAvailableForDateDTO)

        emit(Response.Success(isAvailable))
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