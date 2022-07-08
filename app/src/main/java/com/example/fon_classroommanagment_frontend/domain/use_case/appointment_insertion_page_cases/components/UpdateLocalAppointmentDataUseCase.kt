package com.example.fon_classroommanagment_frontend.domain.use_case.appointment_insertion_page_cases.components

import com.example.fon_classroommanagment_frontend.common.Response
import com.example.fon_classroommanagment_frontend.data.remote.dto.ReserveDTO
import com.example.fon_classroommanagment_frontend.domain.repository.AppointmentRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UpdateLocalAppointmentDataUseCase @Inject constructor(private val appointmentRepository: AppointmentRepository) {
    operator fun invoke(reserveDTO: ReserveDTO): Flow<Response<Unit>> = flow {

        try {
            emit(Response.Loading())
                appointmentRepository.updateLocalAppointment(reserveDTO)



            emit(Response.Success())

        } catch (e: Exception) {

            emit(Response.Error(e.localizedMessage ?: "neocekivana greska"))
        }
    }
}