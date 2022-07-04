package com.example.fon_classroommanagment_frontend.data.di

import android.content.SharedPreferences
import com.example.fon_classroommanagment_frontend.domain.repository.*
import com.example.fon_classroommanagment_frontend.domain.use_case.*
import com.example.fon_classroommanagment_frontend.domain.use_case.admin_page_cases.AdminUseCases
import com.example.fon_classroommanagment_frontend.domain.use_case.admin_page_cases.components.ConfirmAppointmentUseCase
import com.example.fon_classroommanagment_frontend.domain.use_case.admin_page_cases.components.ConfirmAppointmentsUseCase
import com.example.fon_classroommanagment_frontend.domain.use_case.admin_page_cases.components.DeclineAppointmentUseCase
import com.example.fon_classroommanagment_frontend.domain.use_case.admin_page_cases.components.RetriveUserDetailsDataUseCase
import com.example.fon_classroommanagment_frontend.domain.use_case.appointment_insertion_page_cases.AppointmentInsertionUseCase
import com.example.fon_classroommanagment_frontend.domain.use_case.appointment_insertion_page_cases.components.*
import com.example.fon_classroommanagment_frontend.domain.use_case.appointments_page_cases.AppointmentsUseCase
import com.example.fon_classroommanagment_frontend.domain.use_case.appointments_page_cases.components.GetAllClassroomMainInformation
import com.example.fon_classroommanagment_frontend.domain.use_case.appointments_page_cases.components.GetReservationsForClassroomAndDateUseCse
import com.example.fon_classroommanagment_frontend.domain.use_case.classrooms_page_cases.ClassroomsUseCase
import com.example.fon_classroommanagment_frontend.domain.use_case.classrooms_page_cases.components.GetAllClassroomSearchedUseCase
import com.example.fon_classroommanagment_frontend.domain.use_case.classrooms_page_cases.components.GetAllClassroomTypesUserCase
import com.example.fon_classroommanagment_frontend.domain.use_case.classrooms_page_cases.components.GetClassroomsUseCase
import com.example.fon_classroommanagment_frontend.domain.use_case.details_classroom_page_cases.ClassroomDetailsUseCase
import com.example.fon_classroommanagment_frontend.domain.use_case.details_classroom_page_cases.components.GetClassroomDetailsUseCase
import com.example.fon_classroommanagment_frontend.domain.use_case.my_appointment_page_use_case.components.CheckAvailabilityClassroomForDateUseCase
import com.example.fon_classroommanagment_frontend.domain.use_case.my_appointment_page_use_case.components.DeleteLocalAppointmentUseCase
import com.example.fon_classroommanagment_frontend.domain.use_case.profile_page_caes.ProfileUseCases
import com.example.fon_classroommanagment_frontend.domain.use_case.profile_page_caes.components.*
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
    fun provideGetClassroomChips(classroomRepository: ClassroomRepository): GetAllClassroomsMainInformationUseCase =
        GetAllClassroomsMainInformationUseCase(classroomRepository)

    @Provides
    @Singleton
    fun ProvideCheckAvailabilityClassroomForDateUseCase(reservationRepository: AppointmentRepository): CheckAvailabilityClassroomForDateUseCase =
        CheckAvailabilityClassroomForDateUseCase(reservationRepository)
    @Provides
    @Singleton
    fun ProvideGetAllClassroomChipsPaging(classroomRepository: ClassroomRepository): GetAllClassroomMainInformation =
        GetAllClassroomMainInformation(classroomRepository)
    @Provides
    @Singleton
    fun ProvideGetClassroomDetailsUseCase(classroomRepository: ClassroomRepository): GetClassroomDetailsUseCase =
        GetClassroomDetailsUseCase(classroomRepository)

    @Provides
    @Singleton
    fun ProvideUserDetailsUseCase(userRepository: UserRepository): UserDetailsUseCase =
        UserDetailsUseCase(userRepository)
    @Provides
    @Singleton
    fun ProvideGetAppointmentsForUserUseCase(userRepository: UserRepository): GetAppointmentsForUserUseCase =
        GetAppointmentsForUserUseCase(userRepository)

    @Provides
    @Singleton
    fun ProvideDeleteAppointmentUseCase(appointmentRepository: AppointmentRepository): DeleteAppointmentUseCase =
        DeleteAppointmentUseCase(appointmentRepository)
    @Provides
    @Singleton
    fun ProvideGetRequestedAppointmentsUseCase(userRepository: UserRepository): GetRequestedAppointmentsUseCase =
        GetRequestedAppointmentsUseCase(userRepository)
    @Provides
    @Singleton
    fun ProvideFilterUseCase(classroomRepository: ClassroomRepository):FilterUseCase=
        FilterUseCase(classroomRepository)
    @Provides
    @Singleton
    fun ProvideRetriveUserDetailsDataUseCase(userRepository: UserRepository): RetriveUserDetailsDataUseCase =
        RetriveUserDetailsDataUseCase(userRepository)
    @Provides
    @Singleton
    fun ProvideRetriveUserRequestedAppointmentsUseCase(userRepository: UserRepository):RetriveUserRequestedAppointmentsUseCase=
        RetriveUserRequestedAppointmentsUseCase(userRepository)

    @Provides
    @Singleton
    fun ProvideConfirmAppointmentsUseCase(appointmentRepository: AppointmentRepository): ConfirmAppointmentsUseCase =
        ConfirmAppointmentsUseCase(appointmentRepository)

    @Provides
    @Singleton
    fun ProvideConfirmAppointmentUseCase(appointmentRepository: AppointmentRepository): ConfirmAppointmentUseCase =
        ConfirmAppointmentUseCase(appointmentRepository)

    @Provides
    @Singleton
    fun ProvideDeclinedAppointmentUseCase(appointmentRepository: AppointmentRepository): DeclineAppointmentUseCase =
        DeclineAppointmentUseCase(appointmentRepository)
    @Provides
    @Singleton
    fun ProvideGetAppointmentDataUseCase(appointmentRepository: AppointmentRepository):GetAppointmentDataUseCase =
        GetAppointmentDataUseCase(appointmentRepository)
    @Provides
    @Singleton
    fun ProvideSaveAppointmentDataUseCase(appointmentRepository: AppointmentRepository): SaveAppointmentDataUseCase =
        SaveAppointmentDataUseCase(appointmentRepository)
    @Provides
    @Singleton
    fun ProvideUpdateAppointmentDataUseCase(appointmentRepository: AppointmentRepository): UpdateAppointmentDataUseCase =
        UpdateAppointmentDataUseCase(appointmentRepository)
    @Provides
    @Singleton
    fun ProvideGetAllAppointmentsUseCase(appointmentRepository: AppointmentRepository): GetAllAppointmentsUseCase =
        GetAllAppointmentsUseCase(appointmentRepository)
    @Provides
    @Singleton
    fun ProvideDeleteLocalAppointmentUseCase(appointmentRepository: AppointmentRepository): DeleteLocalAppointmentUseCase =
        DeleteLocalAppointmentUseCase(appointmentRepository)
    @Provides
    @Singleton
    fun ProvideChangeEmailUseCase(userRepository: UserRepository,authRepository: AuthRepository): ChangeEmailUseCase =
        ChangeEmailUseCase(userRepository,authRepository)
  @Provides
    @Singleton
    fun ProvideChangePasswordUseCase(userRepository: UserRepository,authRepository: AuthRepository,sharedPreferences: SharedPreferences): ChangePasswordUseCase =
      ChangePasswordUseCase(userRepository,authRepository,sharedPreferences)
  @Provides
    @Singleton
    fun ProvideLogoutUseCase(authRepository: AuthRepository): LogoutUseCase =
    LogoutUseCase(authRepository)
  @Provides
    @Singleton
    fun ProvideClassroomsUseCase(
      getClassroomsUseCase: GetClassroomsUseCase,
      getAllClassroomSearched: GetAllClassroomSearchedUseCase,
      getAllClassroomTypesUserCase: GetAllClassroomTypesUserCase
  ):ClassroomsUseCase =
   ClassroomsUseCase(getClassroomsUseCase,getAllClassroomSearched,getAllClassroomTypesUserCase)

  @Provides
    @Singleton
    fun ProvideAppointmentsUseCase(
        getReservationsForDateUseCase: GetReservationsForClassroomAndDateUseCse,
        getAllClassroomChipsPaging: GetAllClassroomMainInformation
  ):AppointmentsUseCase =
      AppointmentsUseCase(getReservationsForDateUseCase,getAllClassroomChipsPaging)

@Provides
    @Singleton
    fun ProvideProfileUseCase(
     userDetailsUseCase: UserDetailsUseCase,
     getAppointmentsForUserUseCase: GetAppointmentsForUserUseCase,
     deleteAppointmentUseCase: DeleteAppointmentUseCase,
     getRequestedAppointmentsUseCase: GetRequestedAppointmentsUseCase,
     sharedPreferences: SharedPreferences,
     changeEmailUseCase: ChangeEmailUseCase,
     changePasswordUseCase: ChangePasswordUseCase,
     logoutUseCase: LogoutUseCase
  ):ProfileUseCases =
      ProfileUseCases(
          userDetailsUseCase, getAppointmentsForUserUseCase, deleteAppointmentUseCase, getRequestedAppointmentsUseCase, sharedPreferences, changeEmailUseCase, changePasswordUseCase, logoutUseCase
      )
    @Provides
    @Singleton
    fun ProvideClassroomDetailsUseCaseUseCase(
         getClassroomDetailsUseCase: GetClassroomDetailsUseCase,
         getReservationsForClassroomAndDateUseCse: GetReservationsForClassroomAndDateUseCse
  ): ClassroomDetailsUseCase =
        ClassroomDetailsUseCase(
            getClassroomDetailsUseCase,getReservationsForClassroomAndDateUseCse
        )
    @Provides
    @Singleton
    fun ProvideAppointmentInsertionUseCaseUseCase(
        getAllReservationTypesUseCase: GetAllReservationTypesUseCase,
        getAllClassroomsChipUseCase: GetAllClassroomsMainInformationUseCase,
        getAppointmentDataUseCase: GetAppointmentDataUseCase,
        sharedPreferences: SharedPreferences,
        saveAppointmentDataUseCase: SaveAppointmentDataUseCase,
        updateAppointmentDataUseCase: UpdateAppointmentDataUseCase
    , validateInsertionAppointmentUseCase: ValidateInsertionAppointmentUseCase
  ): AppointmentInsertionUseCase =
        AppointmentInsertionUseCase(getAllReservationTypesUseCase,
            getAllClassroomsChipUseCase,
            getAppointmentDataUseCase,
            sharedPreferences,
            saveAppointmentDataUseCase,
            updateAppointmentDataUseCase,
            validateInsertionAppointmentUseCase

        )


    @Provides
    @Singleton
    fun ProvideAdminUseCasesUseCaseUseCase(
        retriveUserDetailsDataUseCase: RetriveUserDetailsDataUseCase,
        retriveUserRequestedAppointmentsUseCase : RetriveUserRequestedAppointmentsUseCase,
        confirmAppointmentUseCase: ConfirmAppointmentUseCase,
        declineAppointmentUseCase: DeclineAppointmentUseCase,
        confirmAppointmentsUseCase: ConfirmAppointmentsUseCase,

    ): AdminUseCases =
        AdminUseCases(retriveUserDetailsDataUseCase,
            retriveUserRequestedAppointmentsUseCase,
            confirmAppointmentUseCase,
            declineAppointmentUseCase,
            confirmAppointmentsUseCase,
        )
    @Provides
    @Singleton
    fun ProvideValidationNameUseCase():ValidateName=ValidateName()
    @Provides
    @Singleton
    fun ProvideValidationReasonUseCase():ValidateReason=ValidateReason()
    @Provides
    @Singleton
    fun ProvideValidationDescriptionUseCase():ValidateDescription= ValidateDescription()
       @Provides
    @Singleton
    fun ProvideValidationStartTimeUseCase():ValidateStartTime=ValidateStartTime()
       @Provides
    @Singleton
    fun ProvideValidationEndTimeUseCase():ValidateEndTime=ValidateEndTime()

    @Provides
    @Singleton
    fun ProvidValidationeClassroomsUseCase():ValidateClassrooms=ValidateClassrooms()

    @Provides
    @Singleton
    fun ProvideValidationAttendeesUseCase():ValidateAttendees=ValidateAttendees()
    @Provides
    @Singleton
    fun ProvideValidationAppointmentTypeUseCase():ValidateAppointmentType= ValidateAppointmentType()



    @Provides
    @Singleton
    fun ProvideValidateInsertionAppointmentUseCase(
        validateStartTime: ValidateStartTime,
        validateEndTime: ValidateEndTime,
        validateName: ValidateName,
        validateDescription: ValidateDescription,
        validateReason: ValidateReason,
        validateAttendees: ValidateAttendees,
        validateAppointmentType: ValidateAppointmentType,
        validateClassrooms: ValidateClassrooms
    ): ValidateInsertionAppointmentUseCase =
      ValidateInsertionAppointmentUseCase(validateName,
          validateAppointmentType,
          validateClassrooms,
          validateDescription,
          validateEndTime,
          validateReason,
          validateStartTime,validateAttendees)
}