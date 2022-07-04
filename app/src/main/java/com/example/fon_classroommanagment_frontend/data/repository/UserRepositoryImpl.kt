package com.example.fon_classroommanagment_frontend.data.repository

import android.content.SharedPreferences
import com.example.fon_classroommanagment_frontend.common.Constants
import com.example.fon_classroommanagment_frontend.data.remote.API
import com.example.fon_classroommanagment_frontend.data.remote.dto.*
import com.example.fon_classroommanagment_frontend.domain.repository.UserRepository
import okhttp3.Interceptor
import okhttp3.OkHttpClient
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

    override suspend fun changeEmail(email: ChangeEmailDTO) {
        api.changeEmail(email)
    }

    override suspend fun changePassword(changePasswordDTO: ChangePasswordDTO) {
//        ok.interceptors. = Interceptor() {
//
//
//            val newRequest  = it.request().newBuilder()
//                .addHeader("Authorization", sharedPreferences.getString(Constants.VALIDATION_TOKEN_KEY,"")?:"")
//                .build()
//            it.proceed(newRequest)
//        }

        api.changePassword(changePasswordDTO)
    }

    override suspend fun getEmployeesPermissions(): List<EmployeeAdminCardDTO> {
        return api.getEmployeesAdminData()
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