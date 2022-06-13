package com.example.fon_classroommanagment_frontend.domain.repository

import com.example.fon_classroommanagment_frontend.domain.model.ClassroomType
import com.example.fon_classroommanagment_frontend.domain.model.EducationTitle
import com.example.fon_classroommanagment_frontend.domain.model.EmployeeDepartment
import com.example.fon_classroommanagment_frontend.domain.model.EmployeeType

interface CommonDataRepository {

    suspend fun getAllDepartments():List<EmployeeDepartment>

    suspend fun getAllEmployeeTypes():List<EmployeeType>

    suspend fun getAllEducationTitles():List<EducationTitle>

    suspend fun getAllClassroomTypes():List<ClassroomType>

}