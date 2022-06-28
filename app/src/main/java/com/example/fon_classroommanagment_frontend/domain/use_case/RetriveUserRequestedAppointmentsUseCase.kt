package com.example.fon_classroommanagment_frontend.domain.use_case

import com.example.fon_classroommanagment_frontend.data.remote.dto.RequestedAppointmentsDTO
import com.example.fon_classroommanagment_frontend.data.remote.dto.UserDetailsDTO
import com.example.fon_classroommanagment_frontend.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RetriveUserRequestedAppointmentsUseCase @Inject constructor(private val userRepository: UserRepository) {

    operator  fun invoke(id: Long): Flow<List<RequestedAppointmentsDTO>> = flow {
        emit(userRepository.getRequestedAppointments())
    }
}