package com.example.fon_classroommanagment_frontend.data.repository

import android.util.Log
import com.example.fon_classroommanagment_frontend.data.remote.API
import com.example.fon_classroommanagment_frontend.data.remote.dto.AppointmentRequestedUserDTO
import com.example.fon_classroommanagment_frontend.data.remote.dto.AppointmentsForUserDTO
import com.example.fon_classroommanagment_frontend.data.remote.dto.RequestedAppointmentsDTO
import com.example.fon_classroommanagment_frontend.data.remote.dto.UserDetailsDTO
import com.example.fon_classroommanagment_frontend.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositorImpl @Inject constructor(private val api: API) :UserRepository {

    var userDetailsDTO :UserDetailsDTO= UserDetailsDTO()
    var userReqquestedAppointmentsDTO:List<RequestedAppointmentsDTO> = emptyList()
    override suspend fun getUserDetailsLocal(id: Long): RequestedAppointmentsDTO? {
       return  userReqquestedAppointmentsDTO.firstOrNull{item->item.id==id}
    }
    override suspend fun getUserRequestedAPpointmentsLocal():List<RequestedAppointmentsDTO> {
        return  userReqquestedAppointmentsDTO
    }


    override suspend fun getUserDetails(): UserDetailsDTO {

        userDetailsDTO= api.getUserDetails()
        return userDetailsDTO
    }

    override suspend fun getUserAppointments(): List<AppointmentsForUserDTO> {
        return api.GetAllAppointmentsForUser()
    }

    override suspend fun getRequestedAppointments(): List<RequestedAppointmentsDTO> {
        userReqquestedAppointmentsDTO=api.getRequestedPendingAppointments()
        return userReqquestedAppointmentsDTO
    }

    override suspend fun getRequestedPendingAppointments(id: Long): List<AppointmentRequestedUserDTO> {
       return api.getRequestedPendingAppointmentsForUser(id)
    }




}