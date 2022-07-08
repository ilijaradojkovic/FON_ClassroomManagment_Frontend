package com.example.fon_classroommanagment_frontend.domain.use_case.appointment_details_page_cases.components

import com.example.fon_classroommanagment_frontend.common.Response
import com.example.fon_classroommanagment_frontend.data.remote.dto.AppointmentDetailsDTO
import com.example.fon_classroommanagment_frontend.data.remote.dto.ReserveDTO
import com.example.fon_classroommanagment_frontend.domain.repository.AppointmentRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.*
import javax.inject.Inject

class GetAppointmentDetailsUseCase @Inject constructor(private val appointmentRepository: AppointmentRepository){
    operator fun invoke(id: UUID): Flow<Response<AppointmentDetailsDTO>> = flow{

        try{
            emit(Response.Loading())

            val reserveDto=    appointmentRepository.getAppointmentDetails(id)

            emit(Response.Success(reserveDto))

        }catch (e:Exception){

            emit(Response.Error(e.localizedMessage ?:"neocekivana greska"))
        }
    }
}