package com.example.fon_classroommanagment_frontend.data.di

import android.content.SharedPreferences
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.fon_classroommanagment_frontend.domain.use_case.*
import com.example.fon_classroommanagment_frontend.domain.use_case.admin_page_cases.AdminUseCases
import com.example.fon_classroommanagment_frontend.domain.use_case.appointment_insertion_page_cases.AppointmentInsertionUseCase
import com.example.fon_classroommanagment_frontend.domain.use_case.appointments_page_cases.AppointmentsUseCase
import com.example.fon_classroommanagment_frontend.domain.use_case.appointments_page_cases.components.GetReservationsForClassroomAndDateUseCse
import com.example.fon_classroommanagment_frontend.domain.use_case.classrooms_page_cases.ClassroomsUseCase
import com.example.fon_classroommanagment_frontend.domain.use_case.details_classroom_page_cases.ClassroomDetailsUseCase
import com.example.fon_classroommanagment_frontend.domain.use_case.details_classroom_page_cases.components.GetClassroomDetailsUseCase
import com.example.fon_classroommanagment_frontend.domain.use_case.profile_page_caes.ProfileUseCases
import com.example.fon_classroommanagment_frontend.presentation.admin_request_screen.AdminRequestsViewModel
import com.example.fon_classroommanagment_frontend.presentation.classrooms_screen.ClassroomsViewModel
import com.example.fon_classroommanagment_frontend.presentation.appointments_screen.AppointmentViewModel
import com.example.fon_classroommanagment_frontend.presentation.appointment_insetion_screen.AppointmentInsertionViewModel
import com.example.fon_classroommanagment_frontend.presentation.classrooms_screen.FilterViewModel
import com.example.fon_classroommanagment_frontend.presentation.details_classroom_screen.DetailsClassromViewModel
import com.example.fon_classroommanagment_frontend.presentation.login_screen.LoginViewModel
import com.example.fon_classroommanagment_frontend.presentation.profile_screen.ProfileViewModel
import com.example.fon_classroommanagment_frontend.presentation.signin_screen.RegisterViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ViewModelDI {



    @Singleton
    @Provides
    fun provideLoginViewModel(loginUseCase: LoginUseCase,sharedPreferences: SharedPreferences): LoginViewModel = LoginViewModel(loginUseCase,sharedPreferences)

    @Singleton
    @Provides
    fun provideRegisterViewModel(registerUseCase: RegisterUseCase): RegisterViewModel = RegisterViewModel(registerUseCase)


//    @Singleton
//    @Provides
//    fun provideAditionalInfoViewModel(getAllDepartmentsUseCase: GetAllDepartmentsUseCase):AditionalInfoViewModel=
//        AditionalInfoViewModel(getAllDepartmentsUseCase)
//
//    @Singleton
//    @Provides
//    fun provideTypeEducationViewModel(getAllEmployeeTypesUseCase: GetAllEmployeeTypesUseCase, getAllEducationTitlesUseCase: GetAllEducationTitlesUseCase, registerUseCase: RegisterUseCase):TypeEducationViewModel=
//        TypeEducationViewModel(getAllEmployeeTypesUseCase,getAllEducationTitlesUseCase,registerUseCase)

    @Singleton
    @Provides
    fun provideFilterViewModel(): FilterViewModel =
        FilterViewModel()

    @Provides
    @Singleton
    fun provideAllClassroomsViewModel(classroomsUseCase: ClassroomsUseCase): ClassroomsViewModel =
        ClassroomsViewModel(classroomsUseCase)

    @Provides
    @Singleton
    fun provideAllReservationVIewModel(appointmentsUseCase: AppointmentsUseCase): AppointmentViewModel =
        AppointmentViewModel(appointmentsUseCase)
 @RequiresApi(Build.VERSION_CODES.O)

 @Provides
    @Singleton
    fun provideAppointmentCreationViewModel(appointmentInsertionUseCase: AppointmentInsertionUseCase): AppointmentInsertionViewModel =
     AppointmentInsertionViewModel(appointmentInsertionUseCase)

    @Provides
    @Singleton
    fun provideDetailsClassromViewModel(classroomDetailsUseCase: ClassroomDetailsUseCase): DetailsClassromViewModel =
        DetailsClassromViewModel(classroomDetailsUseCase)
    @RequiresApi(Build.VERSION_CODES.O)
    @Provides
    @Singleton
    fun provideProfileViewModel(profileUseCases: ProfileUseCases): ProfileViewModel =
        ProfileViewModel(profileUseCases)

    @Provides
    @Singleton
    fun provideAdminRequestsViewModel(adminUseCases: AdminUseCases) : AdminRequestsViewModel =
        AdminRequestsViewModel(adminUseCases)


}