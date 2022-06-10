package com.example.fon_classroommanagment_frontend.domain.repository

import com.example.fon_classroommanagment_frontend.data.EducationTitle
import com.example.fon_classroommanagment_frontend.data.EmployeeDepartment
import com.example.fon_classroommanagment_frontend.data.EmployeeType

interface CommonDataRepository {

    suspend fun getAllDepartments():List<EmployeeDepartment>

    suspend fun getAllEmployeeTypes():List<EmployeeType>

    suspend fun getAllEducationTitles():List<EducationTitle>

}