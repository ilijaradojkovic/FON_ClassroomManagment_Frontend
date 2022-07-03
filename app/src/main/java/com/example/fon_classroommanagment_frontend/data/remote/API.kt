package com.example.fon_classroommanagment_frontend.data.remote

import com.example.fon_classroommanagment_frontend.common.Routes
import com.example.fon_classroommanagment_frontend.common.Routes.APPOINTMENT_DELETE
import com.example.fon_classroommanagment_frontend.common.Routes.CLASSROOM_PAGING
import com.example.fon_classroommanagment_frontend.common.Routes.CLASSROOM_PAGING_PARTIAL_INFO
import com.example.fon_classroommanagment_frontend.common.Routes.APPOINTMENT_ClASSROOM
import com.example.fon_classroommanagment_frontend.common.Routes.APPOINTMENT_RESERVE
import com.example.fon_classroommanagment_frontend.common.Routes.APPOINTMENT_AVAILABILITY
import com.example.fon_classroommanagment_frontend.common.Routes.APPOINTMENT_CONFIRM
import com.example.fon_classroommanagment_frontend.common.Routes.APPOINTMENT_CONFIRM_ALL
import com.example.fon_classroommanagment_frontend.common.Routes.APPOINTMENT_DECLINE
import com.example.fon_classroommanagment_frontend.common.Routes.CLASSROOM_PARTIAL_INFO
import com.example.fon_classroommanagment_frontend.common.Routes.CLASSROOM_DETAILS
import com.example.fon_classroommanagment_frontend.common.Routes.CLASSROOM_FILTER
import com.example.fon_classroommanagment_frontend.common.Routes.COMMON_ALL_APPOINTMENT_TYPES
import com.example.fon_classroommanagment_frontend.common.Routes.COMMON_ALL_CLASSROOM_TYPES
import com.example.fon_classroommanagment_frontend.common.Routes.COMMON_ALL_EDUCATION_TITLES
import com.example.fon_classroommanagment_frontend.common.Routes.COMMON_ALL_EMPLOYEE_DEPARTMENTS
import com.example.fon_classroommanagment_frontend.common.Routes.COMMON_ALL_EMPLOYEE_TYPES
import com.example.fon_classroommanagment_frontend.common.Routes.LOGIN
import com.example.fon_classroommanagment_frontend.common.Routes.REGISTER
import com.example.fon_classroommanagment_frontend.common.Routes.CLASSROOM_SEARCH
import com.example.fon_classroommanagment_frontend.common.Routes.EMAIL_RESET
import com.example.fon_classroommanagment_frontend.common.Routes.LOGOUT
import com.example.fon_classroommanagment_frontend.common.Routes.PASSWORD_RESET
import com.example.fon_classroommanagment_frontend.common.Routes.USER_APPOINTMENTS
import com.example.fon_classroommanagment_frontend.common.Routes.USER_APPOINTMENTS_PENDING
import com.example.fon_classroommanagment_frontend.common.Routes.USER_DETAILS
import com.example.fon_classroommanagment_frontend.common.Routes.USER_REQUESTED_APPOINTMENTS
import com.example.fon_classroommanagment_frontend.data.remote.dto.*
import com.example.fon_classroommanagment_frontend.domain.model.*
import retrofit2.Call
import retrofit2.Response
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

    @POST(CLASSROOM_PAGING)
    suspend fun  GetAllClassroomsPaging(@Path("page") page:Int,@Body filterDTO: FilterDTO):List<ClassroomCardDTO>

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

    @GET(USER_APPOINTMENTS_PENDING)
    suspend fun getRequestedPendingAppointmentsForUser(@Query("id") id: Long): List<AppointmentRequestedUserDTO>

    @GET(USER_REQUESTED_APPOINTMENTS)
    suspend fun getRequestedPendingAppointments(): List<RequestedAppointmentsDTO>


    @POST(CLASSROOM_FILTER)
    suspend fun filterClassrooms(@Body() filterDTO: FilterDTO): List<ClassroomCardDTO>?


    @POST(APPOINTMENT_CONFIRM)
    suspend fun confirmAppointment(@Query("id") appointmentId: UUID)
    @POST(APPOINTMENT_DECLINE)
    suspend fun declineAppointment(@Query("id") appointmentId: UUID)
    @POST(APPOINTMENT_CONFIRM_ALL)
    suspend  fun confirmAllAppointments(@Body toList: List<UUID>)

    @POST(EMAIL_RESET)
    suspend fun changeEmail(@Body email: ChangeEmailDTO)

    @POST(PASSWORD_RESET)
 suspend fun changePassword(@Body changePasswordDTO: ChangePasswordDTO)

  @POST(LOGOUT)
   suspend fun Logout():Response<Unit>


}