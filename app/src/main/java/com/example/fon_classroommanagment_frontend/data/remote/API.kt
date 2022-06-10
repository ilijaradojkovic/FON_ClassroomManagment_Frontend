package com.example.fon_classroommanagment_frontend.data.remote

import com.example.fon_classroommanagment_frontend.data.EducationTitle
import com.example.fon_classroommanagment_frontend.data.EmployeeDepartment
import com.example.fon_classroommanagment_frontend.data.EmployeeType
import com.example.fon_classroommanagment_frontend.data.remote.dto.UserRegistrationDTO
import retrofit2.Call
import retrofit2.http.*

interface API {
    @FormUrlEncoded
    @POST("login")
    fun Login(@Field("username") username:String,@Field("password") password:String):Call<Unit>


    @POST("register")
    fun Register(@Body userRegistrationDTO: UserRegistrationDTO):Call<Unit>
   // ovo bi pisali na svaku rutu ,pravimo interceptors@Header("Authorization: Bearer...")

    @GET("allEmployeeDepartment")
  suspend  fun GetAllDepartments():List<EmployeeDepartment>
    @GET("allEducationTitle")
  suspend  fun GetAllEducationTitle():List<EducationTitle>
    @GET("allEmployeeTypes")
   suspend fun GetAllEmployeeTypes():List<EmployeeType>
}