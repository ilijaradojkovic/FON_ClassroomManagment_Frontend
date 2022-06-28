package com.example.fon_classroommanagment_frontend.data.di

import android.content.SharedPreferences
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.fon_classroommanagment_frontend.domain.use_case.*
import com.example.fon_classroommanagment_frontend.presentation.classrooms_screen.ClassroomsViewModel
import com.example.fon_classroommanagment_frontend.presentation.appointments_screen.AppointmentViewModel
import com.example.fon_classroommanagment_frontend.presentation.appointment_insetion_screen.AppointmentInsertionViewModel
import com.example.fon_classroommanagment_frontend.presentation.common.bars.FilterViewModel
import com.example.fon_classroommanagment_frontend.presentation.details_classroom_screen.DetailsClassromViewModel
import com.example.fon_classroommanagment_frontend.presentation.login_screen.LoginViewModel
import com.example.fon_classroommanagment_frontend.presentation.profile_screen.ProfileViewModel
import com.example.fon_classroommanagment_frontend.presentation.signin_screen.RegisterViewModel
import com.example.fon_classroommanagment_frontend.presentation.signin_screen.aditional_info_screen.AditionalInfoViewModel
import com.example.fon_classroommanagment_frontend.presentation.signin_screen.type_education_screen.TypeEducationViewModel
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


    @Singleton
    @Provides
    fun provideAditionalInfoViewModel(getAllDepartmentsUseCase: GetAllDepartmentsUseCase):AditionalInfoViewModel=
        AditionalInfoViewModel(getAllDepartmentsUseCase)

    @Singleton
    @Provides
    fun provideTypeEducationViewModel(getAllEmployeeTypesUseCase: GetAllEmployeeTypesUseCase, getAllEducationTitlesUseCase: GetAllEducationTitlesUseCase, registerUseCase: RegisterUseCase):TypeEducationViewModel=
        TypeEducationViewModel(getAllEmployeeTypesUseCase,getAllEducationTitlesUseCase,registerUseCase)

    @Singleton
    @Provides
    fun provideFilterViewModel(getAllClassroomTypesUserCase: GetAllClassroomTypesUserCase): FilterViewModel =
        FilterViewModel(getAllClassroomTypesUserCase)

    @Provides
    @Singleton
    fun provideAllClassroomsViewModel(getClassroomsUseCase: GetClassroomsUseCase,getAllClassroomSearched: GetAllClassroomSearchedUseCase,filterUseCase: FilterUseCase): ClassroomsViewModel =
        ClassroomsViewModel(getClassroomsUseCase,getAllClassroomSearched)

    @Provides
    @Singleton
    fun provideAllReservationVIewModel(getReservationsForDateUseCse: GetReservationsForClassroomAndDateUseCse, getAllClassroomChipsPaging: GetAllClassroomChipsPaging): AppointmentViewModel =
        AppointmentViewModel(getReservationsForDateUseCse,getAllClassroomChipsPaging)
 @RequiresApi(Build.VERSION_CODES.O)

 @Provides
    @Singleton
    fun provideAppointmentCreationViewModel( getAllReservationTypesUseCase: GetAllReservationTypesUseCase,getAllClassroomSearched: GetAllClassroomsChipUseCase,sharedPreferences: SharedPreferences): AppointmentInsertionViewModel =
     AppointmentInsertionViewModel(getAllReservationTypesUseCase,getAllClassroomSearched,sharedPreferences)

    @Provides
    @Singleton
    fun provideDetailsClassromViewModel( getClassroomDetailsUseCase: GetClassroomDetailsUseCase,getReservationsForClassroomAndDateUseCse: GetReservationsForClassroomAndDateUseCse): DetailsClassromViewModel =
        DetailsClassromViewModel(getClassroomDetailsUseCase,getReservationsForClassroomAndDateUseCse)
    @RequiresApi(Build.VERSION_CODES.O)
    @Provides
    @Singleton
    fun provideProfileViewModel( userDetailsUseCase: UserDetailsUseCase,  getAppointmentsForUserUseCase: GetAppointmentsForUserUseCase,  deleteAppointmentUseCase: DeleteAppointmentUseCase,  getRequestedAppointmentsUseCase: GetRequestedAppointmentsUseCase, sharedPreferences: SharedPreferences
        ): ProfileViewModel =
        ProfileViewModel(userDetailsUseCase,getAppointmentsForUserUseCase,deleteAppointmentUseCase,getRequestedAppointmentsUseCase,sharedPreferences)

}