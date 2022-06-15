package com.example.fon_classroommanagment_frontend.data.remote

import com.example.fon_classroommanagment_frontend.common.Constants.TOKEN_VALIDATION
import com.example.fon_classroommanagment_frontend.data.remote.dto.*
import com.example.fon_classroommanagment_frontend.domain.model.*
import com.example.fon_classroommanagment_frontend.domain.use_case.GetReservationsForDateUseCse
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

    @GET("allClassroomTypes")
    suspend fun GetAllClassroomTypes():List<ClassroomType>

    @GET("getClassrooms")
    suspend fun  GetAllClassroomsPaging(@Query("page") page:Int, @Header("Authorization") authHeader:String=TOKEN_VALIDATION):List<ClassroomCardDTO>

    //@GET("searchClassroom")
//    @HTTP(method = "GET", path = "searchClassroom", hasBody = true)
    //vidi za body i get
    @POST("searchClassroom")
    suspend fun  GetSearchClassrooms(@Body searchClassroomDTO: SearchClassroomDTO,@Header("Authorization") authHeader:String=TOKEN_VALIDATION):List<ClassroomCardDTO>

    @POST("GetForDate")
    suspend fun  getReservationsForDate(@Body requestAppointmetDateDTO: RequestAppointmetDateDTO,@Header("Authorization") authHeader:String=TOKEN_VALIDATION):List<GetForDateAppointmentDTO>

}