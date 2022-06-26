package com.example.fon_classroommanagment_frontend.data.di

import com.example.fon_classroommanagment_frontend.data.repository.AppointmentRepositoryImpl
import com.example.fon_classroommanagment_frontend.domain.repository.*
import com.example.fon_classroommanagment_frontend.domain.use_case.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseDI {

    @Singleton
    @Provides
    fun provideLoginUseCase(authRepository: AuthRepository): LoginUseCase = LoginUseCase(authRepository)




    @Singleton
    @Provides
    fun provideRegisterUseCase(authRepository: AuthRepository): RegisterUseCase = RegisterUseCase(authRepository)


    @Singleton
    @Provides
    fun provideGetAllDepartmentsUseCase(commonDataRepository: CommonDataRepository): GetAllDepartmentsUseCase =
        GetAllDepartmentsUseCase(commonDataRepository)



    @Singleton
    @Provides
    fun provideGetAllEducationTitlesUseCase(commonDataRepository: CommonDataRepository): GetAllEducationTitlesUseCase = GetAllEducationTitlesUseCase(commonDataRepository)


    @Singleton
    @Provides
    fun provideGetAllEmployeeTypesUseCase(commonDataRepository: CommonDataRepository): GetAllEmployeeTypesUseCase = GetAllEmployeeTypesUseCase(commonDataRepository)



    @Singleton
    @Provides
    fun provideGetAllClassroomTypesUserCase(commonDataRepositoryImpl: CommonDataRepository): GetAllClassroomTypesUserCase =
        GetAllClassroomTypesUserCase(commonDataRepositoryImpl )


    @Provides
    @Singleton
    fun provideGetClassroomsUseCase(classroomRepository: ClassroomRepository): GetClassroomsUseCase = GetClassroomsUseCase(classroomRepository)

    @Provides
    @Singleton
    fun provideGetAllClassroomSearchedUseCase(classroomRepository: ClassroomRepository): GetAllClassroomSearchedUseCase =
        GetAllClassroomSearchedUseCase(classroomRepository)




    @Provides
    @Singleton
    fun provideGetReservationsForDateUseCse(reservationRepository: AppointmentRepository): GetReservationsForClassroomAndDateUseCse =
        GetReservationsForClassroomAndDateUseCse(reservationRepository)


    @Provides
    @Singleton
    fun provideGetAllReservationTypesUseCase(commonDataRepository: CommonDataRepository): GetAllReservationTypesUseCase =
        GetAllReservationTypesUseCase(commonDataRepository)

    @Provides
    @Singleton
    fun provideGetClassroomChips(classroomRepository: ClassroomRepository): GetAllClassroomsChipUseCase =
        GetAllClassroomsChipUseCase(classroomRepository)

    @Provides
    @Singleton
    fun ProvideCheckAvailabilityClassroomForDateUseCase(reservationRepository: AppointmentRepository):CheckAvailabilityClassroomForDateUseCase=
        CheckAvailabilityClassroomForDateUseCase(reservationRepository)
    @Provides
    @Singleton
    fun ProvideGetAllClassroomChipsPaging(classroomRepository: ClassroomRepository):GetAllClassroomChipsPaging=
        GetAllClassroomChipsPaging(classroomRepository)
    @Provides
    @Singleton
    fun ProvideGetClassroomDetailsUseCase(classroomRepository: ClassroomRepository):GetClassroomDetailsUseCase=
        GetClassroomDetailsUseCase(classroomRepository)

    @Provides
    @Singleton
    fun ProvideUserDetailsUseCase(userRepository: UserRepository):UserDetailsUseCase=
        UserDetailsUseCase(userRepository)
    @Provides
    @Singleton
    fun ProvideGetAppointmentsForUserUseCase(userRepository: UserRepository):GetAppointmentsForUserUseCase=
        GetAppointmentsForUserUseCase(userRepository)

    @Provides
    @Singleton
    fun ProvideDeleteAppointmentUseCase(appointmentRepository: AppointmentRepository):DeleteAppointmentUseCase=
        DeleteAppointmentUseCase(appointmentRepository)
    @Provides
    @Singleton
    fun ProvideIsUserAdminUseCase(userRepository: UserRepository):IsUserAdminUseCase=
        IsUserAdminUseCase(userRepository)
}