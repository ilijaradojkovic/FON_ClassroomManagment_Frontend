package com.example.fon_classroommanagment_frontend.data.remote

import com.example.fon_classroommanagment_frontend.common.Routes.APPOINTMENT_DELETE
import com.example.fon_classroommanagment_frontend.common.Routes.CLASSROOM_PAGING
import com.example.fon_classroommanagment_frontend.common.Routes.CLASSROOM_PAGING_PARTIAL_INFO
import com.example.fon_classroommanagment_frontend.common.Routes.APPOINTMENT_ClASSROOM
import com.example.fon_classroommanagment_frontend.common.Routes.APPOINTMENT_RESERVE
import com.example.fon_classroommanagment_frontend.common.Routes.APPOINTMENT_AVAILABILITY
import com.example.fon_classroommanagment_frontend.common.Routes.CLASSROOM_PARTIAL_INFO
import com.example.fon_classroommanagment_frontend.common.Routes.CLASSROOM_DETAILS
import com.example.fon_classroommanagment_frontend.common.Routes.COMMON_ALL_APPOINTMENT_TYPES
import com.example.fon_classroommanagment_frontend.common.Routes.COMMON_ALL_CLASSROOM_TYPES
import com.example.fon_classroommanagment_frontend.common.Routes.COMMON_ALL_EDUCATION_TITLES
import com.example.fon_classroommanagment_frontend.common.Routes.COMMON_ALL_EMPLOYEE_DEPARTMENTS
import com.example.fon_classroommanagment_frontend.common.Routes.COMMON_ALL_EMPLOYEE_TYPES
import com.example.fon_classroommanagment_frontend.common.Routes.LOGIN
import com.example.fon_classroommanagment_frontend.common.Routes.REGISTER
import com.example.fon_classroommanagment_frontend.common.Routes.CLASSROOM_SEARCH
import com.example.fon_classroommanagment_frontend.common.Routes.USER_APPOINTMENTS
import com.example.fon_classroommanagment_frontend.common.Routes.USER_DETAILS
import com.example.fon_classroommanagment_frontend.common.Routes.USER_REQUESTED_APPOINTMENTS
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

    @GET(COMMON_ALL_EMPLOYEE_DEPARTMENTS)
  suspend  fun GetAllDepartments():List<EmployeeDepartment>

    @GET(COMMON_ALL_EDUCATION_TITLES)
  suspend  fun GetAllEducationTitle():List<EducationTitle>


    @GET(COMMON_ALL_EMPLOYEE_TYPES)
   suspend fun GetAllEmployeeTypes():List<EmployeeType>

    @GET(COMMON_ALL_CLASSROOM_TYPES)
    suspend fun GetAllClassroomTypes():List<ClassroomType>

    @GET(CLASSROOM_PAGING)
    suspend fun  GetAllClassroomsPaging(@Path("page") page:Int):List<ClassroomCardDTO>

    //@GET("searchClassroom")
//    @HTTP(method = "GET", path = "searchClassroom", hasBody = true)
    //vidi za body i get
    @GET(CLASSROOM_SEARCH)
    suspend fun  getSearchClassrooms(@Path("page") page:Int, @Query("name") name:String):List<ClassroomCardDTO>

    @POST(APPOINTMENT_ClASSROOM)
    suspend fun  getReservationsForDate(@Body requestAppointmetDateDTO: RequestAppointmetDaetForClassroomDTO):List<GetForDateAppointmentDTO>


    @GET(COMMON_ALL_APPOINTMENT_TYPES)
    suspend fun  getAllAppointmentTypes():List<AppointmentType>

   @GET(CLASSROOM_PARTIAL_INFO)
    suspend fun GetClassroomsChip(@Query("name") name: String): List<ClassroomChipDTO>

    @GET(CLASSROOM_PAGING_PARTIAL_INFO)
    suspend fun GetAllClassroomsChip(@Path("page") page:Int): List<ClassroomChipDTO>

    @POST(APPOINTMENT_AVAILABILITY)
    suspend fun  isClassroomAvailableForDate(@Body requestIsClassroomAvailableForDateDTO: RequestIsClassroomAvailableForDateDTO):Boolean

    @POST(APPOINTMENT_RESERVE)
    suspend fun reserve(@Body dto: List<ReserveDTO>)

    @GET(CLASSROOM_DETAILS)
    suspend fun getClassroomDetails(@Query("id") classroomId: Long): ClassroomDetailsDTO

    @GET(USER_DETAILS)
     suspend fun getUserDetails():UserDetailsDTO

     @GET(USER_APPOINTMENTS)
    suspend fun GetAllAppointmentsForUser(): List<AppointmentsForUserDTO>

    @DELETE(APPOINTMENT_DELETE)
    suspend fun deleteAppointment(@Query("id") id: UUID)

    @GET(USER_REQUESTED_APPOINTMENTS)
    suspend fun getRequestedAppointments(): List<RequestedAppointmentsDTO>


}