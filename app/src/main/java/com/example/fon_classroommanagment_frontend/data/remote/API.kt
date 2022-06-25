package com.example.fon_classroommanagment_frontend.data.remote

import com.example.fon_classroommanagment_frontend.data.remote.dto.*
import com.example.fon_classroommanagment_frontend.domain.model.*
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
    suspend fun  GetAllClassroomsPaging(@Query("page") page:Int):List<ClassroomCardDTO>

    //@GET("searchClassroom")
//    @HTTP(method = "GET", path = "searchClassroom", hasBody = true)
    //vidi za body i get
    @POST("searchClassroom")
    suspend fun  GetSearchClassrooms(@Body searchClassroomDTO: SearchClassroomDTO,):List<ClassroomCardDTO>

    @POST("GetForDateAndClassroom")
    suspend fun  getReservationsForDate(@Body requestAppointmetDateDTO: RequestAppointmetDaetForClassroomDTO):List<GetForDateAppointmentDTO>


    @GET("allAppointmentTypes")
    suspend fun  getAllAppointmentTypes():List<AppointmentType>

   @GET("getClassroomsChip")
    suspend fun GetClassroomsChip(@Query("name") name: String): List<ClassroomChipDTO>

    @GET("getClassroomsChipAll")
    suspend fun GetAllClassroomsChip(@Query("page") page:Int): List<ClassroomChipDTO>

    @POST("IsClassroomAvailableForDate")
    suspend fun  isClassroomAvailableForDate(@Body requestIsClassroomAvailableForDateDTO: RequestIsClassroomAvailableForDateDTO):Boolean

    @POST("reserve")
    suspend fun reserve(@Body dto: List<ReserveDTO>)
    @GET("classroomDetails")
    suspend fun getClassroomDetails(@Query("classroomId") classroomId: Long): ClassroomDetailsDTO


}