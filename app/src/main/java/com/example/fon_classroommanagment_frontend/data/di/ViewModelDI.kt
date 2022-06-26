package com.example.fon_classroommanagment_frontend.data.di

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.fon_classroommanagment_frontend.common.StoreUserEmail
import com.example.fon_classroommanagment_frontend.domain.use_case.*
import com.example.fon_classroommanagment_frontend.presentation.all_classrooms_screen.AllClassroomsViewModel
import com.example.fon_classroommanagment_frontend.presentation.all_reservation_screen.AllReservationViewModel
import com.example.fon_classroommanagment_frontend.presentation.appointment_screen.AppointmentCreationViewModel
import com.example.fon_classroommanagment_frontend.presentation.common.bars.FilterViewModel
import com.example.fon_classroommanagment_frontend.presentation.details_classroom_screen.DetailsClassromViewModel
import com.example.fon_classroommanagment_frontend.presentation.login_screen.LoginViewModel
import com.example.fon_classroommanagment_frontend.presentation.signin_screen.RegisterViewModel
import com.example.fon_classroommanagment_frontend.presentation.signin_screen.aditional_info_screen.AditionalInfoViewModel
import com.example.fon_classroommanagment_frontend.presentation.signin_screen.type_education_screen.TypeEducationViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ViewModelDI {



    @Singleton
    @Provides
    fun provideLoginViewModel(loginUseCase: LoginUseCase): LoginViewModel = LoginViewModel(loginUseCase)

    @Singleton
    @Provides
    fun provideRegisterViewModel(registerUseCase: RegisterUseCase): RegisterViewModel = RegisterViewModel(registerUseCase)


    @Singleton
    @Provides
    fun provideAditionalInfoViewModel(getAllDepartmentsUseCase: GetAllDepartmentsUseCase)=
        AditionalInfoViewModel(getAllDepartmentsUseCase)

    @Singleton
    @Provides
    fun provideTypeEducationViewModel(getAllEmployeeTypesUseCase: GetAllEmployeeTypesUseCase, getAllEducationTitlesUseCase: GetAllEducationTitlesUseCase, registerUseCase: RegisterUseCase)=
        TypeEducationViewModel(getAllEmployeeTypesUseCase,getAllEducationTitlesUseCase,registerUseCase)

    @Singleton
    @Provides
    fun provideFilterViewModel(getAllClassroomTypesUserCase: GetAllClassroomTypesUserCase): FilterViewModel =
        FilterViewModel(getAllClassroomTypesUserCase)

    @Provides
    @Singleton
    fun provideAllClassroomsViewModel(getClassroomsUseCase: GetClassroomsUseCase,getAllClassroomSearched: GetAllClassroomSearchedUseCase): AllClassroomsViewModel =
        AllClassroomsViewModel(getClassroomsUseCase,getAllClassroomSearched)

    @Provides
    @Singleton
    fun provideAllReservationVIewModel(getReservationsForDateUseCse: GetReservationsForClassroomAndDateUseCse, getAllClassroomChipsPaging: GetAllClassroomChipsPaging): AllReservationViewModel =
        AllReservationViewModel(getReservationsForDateUseCse,getAllClassroomChipsPaging)
 @RequiresApi(Build.VERSION_CODES.O)

 @Provides
    @Singleton
    fun provideAppointmentCreationViewModel( getAllReservationTypesUseCase: GetAllReservationTypesUseCase,getAllClassroomSearched: GetAllClassroomsChipUseCase): AppointmentCreationViewModel =
     AppointmentCreationViewModel(getAllReservationTypesUseCase,getAllClassroomSearched)

    @Provides
    @Singleton
    fun provideDetailsClassromViewModel( getClassroomDetailsUseCase: GetClassroomDetailsUseCase,getReservationsForClassroomAndDateUseCse: GetReservationsForClassroomAndDateUseCse): DetailsClassromViewModel =
        DetailsClassromViewModel(getClassroomDetailsUseCase,getReservationsForClassroomAndDateUseCse)

}