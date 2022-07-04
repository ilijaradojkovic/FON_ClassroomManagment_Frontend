package com.example.fon_classroommanagment_frontend.domain.repository

import com.example.fon_classroommanagment_frontend.domain.model.*

interface CommonDataRepository {

    suspend fun getAllDepartments():List<EmployeeDepartment>

    suspend fun getAllEmployeeTypes():List<EmployeeType>

    suspend fun getAllEducationTitles():List<EducationTitle>

    suspend fun getAllClassroomTypes():List<ClassroomType>
    suspend fun getAllAppointmentTypes(): List<AppointmentType>
    suspend fun getUserRoles():List<UserRole>

}