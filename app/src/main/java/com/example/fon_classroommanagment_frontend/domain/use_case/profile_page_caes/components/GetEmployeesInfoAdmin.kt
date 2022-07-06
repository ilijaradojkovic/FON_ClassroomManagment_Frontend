package com.example.fon_classroommanagment_frontend.domain.use_case.profile_page_caes.components

import com.example.fon_classroommanagment_frontend.common.Response
import com.example.fon_classroommanagment_frontend.data.remote.dto.EmployeeAdminCardDTO
import com.example.fon_classroommanagment_frontend.data.remote.dto.ReserveDTO
import com.example.fon_classroommanagment_frontend.domain.repository.AppointmentRepository
import com.example.fon_classroommanagment_frontend.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.*
import javax.inject.Inject

class GetEmployeesInfoAdmin  @Inject constructor(private val userRepository: UserRepository){
    operator fun invoke(): Flow<Response<List<EmployeeAdminCardDTO>>> = flow{

        try{
            emit(Response.Loading())

            val employeesList= userRepository.getEmployeesPermissions()

            emit(Response.Success(employeesList))

        }catch (e:Exception){

            emit(Response.Error(e.localizedMessage ?:"neocekivana greska"))
        }
    }
}