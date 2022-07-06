package com.example.fon_classroommanagment_frontend.domain.use_case.profile_page_caes

import android.content.SharedPreferences
import com.example.fon_classroommanagment_frontend.domain.use_case.profile_page_caes.components.GetEmployeesInfoAdmin
import com.example.fon_classroommanagment_frontend.domain.use_case.profile_page_caes.components.*
import javax.inject.Inject

class ProfileUseCases @Inject constructor(
    val userDetailsUseCase: UserDetailsUseCase,
    val getAppointmentsForUserUseCase: GetAppointmentsForUserUseCase,
    val deleteAppointmentUseCase: DeleteAppointmentUseCase,
    val getRequestedAppointmentsUseCase: GetRequestedAppointmentsUseCase,
    val sharedPreferences: SharedPreferences,
    val changeEmailUseCase: ChangeEmailUseCase,
    val changePasswordUseCase: ChangePasswordUseCase,
    val logoutUseCase: LogoutUseCase,
    val getEmployeesInfoAdmin: GetEmployeesInfoAdmin,
    val getUserRolesUseCase: GetUserRolesUseCase,
    val updateRoleUseCase: UpdateRoleUseCase
) {
}