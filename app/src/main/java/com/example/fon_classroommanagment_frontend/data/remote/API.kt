package com.example.fon_classroommanagment_frontend.data.remote

import com.example.fon_classroommanagment_frontend.common.Routes
import com.example.fon_classroommanagment_frontend.common.Routes.APPOINTMENT_DELETE
import com.example.fon_classroommanagment_frontend.common.Routes.APPOINTMENT_TYPES
import com.example.fon_classroommanagment_frontend.common.Routes.CLASSROOMS
import com.example.fon_classroommanagment_frontend.common.Routes.CLASSROOMS_CHIP
import com.example.fon_classroommanagment_frontend.common.Routes.CLASSROOMS_DATE
import com.example.fon_classroommanagment_frontend.common.Routes.CLASSROOM_AVAILABILITY
import com.example.fon_classroommanagment_frontend.common.Routes.CLASSROOM_CHIP
import com.example.fon_classroommanagment_frontend.common.Routes.CLASSROOM_DETAILS
import com.example.fon_classroommanagment_frontend.common.Routes.CLASSROOM_TYPES
import com.example.fon_classroommanagment_frontend.common.Routes.EDUCATION_TITLES
import com.example.fon_classroommanagment_frontend.common.Routes.EMPLOYEE_DEPARTMANTS
import com.example.fon_classroommanagment_frontend.common.Routes.EMPLOYEE_TYPES
import com.example.fon_classroommanagment_frontend.common.Routes.LOGIN
import com.example.fon_classroommanagment_frontend.common.Routes.REGISTER
import com.example.fon_classroommanagment_frontend.common.Routes.RESERVE
import com.example.fon_classroommanagment_frontend.common.Routes.SEACH_CLASSROOMS
import com.example.fon_classroommanagment_frontend.common.Routes.USER_APPOINTMENTS
import com.example.fon_classroommanagment_frontend.common.Routes.USER_DETAILS
import com.example.fon_classroommanagment_frontend.data.remote.dto.*
import com.example.fon_classroommanagment_frontend.domain.model.*
import retrofit2.Call
import retrofit2.http.*
import java.util.*

interface API {
    @FormUrlEncoded
    @POST(LOGIN)
    fun Login(@Field("username") username:String,@Field("password") password:String):Call<Unit>


    @POST(REGISTER)
    fun Register(@Body userRegistrationDTO: UserRegistrationDTO):Call<Unit>
   // ovo bi pisali na svaku rutu ,pravimo interceptors@Header("Authorization: Bearer...")

    @GET(EMPLOYEE_DEPARTMANTS)
  suspend  fun GetAllDepartments():List<EmployeeDepartment>

    @GET(EDUCATION_TITLES)
  suspend  fun GetAllEducationTitle():List<EducationTitle>


    @GET(EMPLOYEE_TYPES)
   suspend fun GetAllEmployeeTypes():List<EmployeeType>

    @GET(CLASSROOM_TYPES)
    suspend fun GetAllClassroomTypes():List<ClassroomType>

    @GET(CLASSROOMS)
    suspend fun  GetAllClassroomsPaging(@Query("page") page:Int):List<ClassroomCardDTO>

    //@GET("searchClassroom")
//    @HTTP(method = "GET", path = "searchClassroom", hasBody = true)
    //vidi za body i get
    @POST(SEACH_CLASSROOMS)
    suspend fun  GetSearchClassrooms(@Body searchClassroomDTO: SearchClassroomDTO,):List<ClassroomCardDTO>

    @POST(CLASSROOMS_DATE)
    suspend fun  getReservationsForDate(@Body requestAppointmetDateDTO: RequestAppointmetDaetForClassroomDTO):List<GetForDateAppointmentDTO>


    @GET(APPOINTMENT_TYPES)
    suspend fun  getAllAppointmentTypes():List<AppointmentType>

   @GET(CLASSROOM_CHIP)
    suspend fun GetClassroomsChip(@Query("name") name: String): List<ClassroomChipDTO>

    @GET(CLASSROOMS_CHIP)
    suspend fun GetAllClassroomsChip(@Query("page") page:Int): List<ClassroomChipDTO>

    @POST(CLASSROOM_AVAILABILITY)
    suspend fun  isClassroomAvailableForDate(@Body requestIsClassroomAvailableForDateDTO: RequestIsClassroomAvailableForDateDTO):Boolean

    @POST(RESERVE)
    suspend fun reserve(@Body dto: List<ReserveDTO>)

    @GET(CLASSROOM_DETAILS)
    suspend fun getClassroomDetails(@Query("classroomId") classroomId: Long): ClassroomDetailsDTO

    @GET(USER_DETAILS)
     suspend fun getUserDetails():UserDetailsDTO

     @GET(USER_APPOINTMENTS)
    suspend fun GetAllAppointmentsForUser(): List<AppointmentsForUserDTO>

    @DELETE(APPOINTMENT_DELETE)
    suspend fun deleteAppointment(@Query("id") id: UUID)




}