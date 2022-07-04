package com.example.fon_classroommanagment_frontend.domain.use_case.admin_page_cases.components

import com.example.fon_classroommanagment_frontend.data.remote.dto.RequestedAppointmentsDTO
import com.example.fon_classroommanagment_frontend.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RetriveUserDetailsDataUseCase @Inject constructor(private val userRepository: UserRepository) {

    operator  fun invoke(id:Long): Flow<RequestedAppointmentsDTO> = flow {
       val userRequestedDetails= userRepository.getUserDetailsLocal(id)
        userRequestedDetails?.let {
            emit(it)
        }
    }

}