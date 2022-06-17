package com.example.fon_classroommanagment_frontend.data.repository

import com.example.fon_classroommanagment_frontend.data.remote.API
import com.example.fon_classroommanagment_frontend.domain.model.*
import com.example.fon_classroommanagment_frontend.domain.repository.CommonDataRepository
import javax.inject.Inject

class CommonDataRepositoryImpl @Inject constructor(
    private val api: API) : CommonDataRepository {
    override suspend fun getAllDepartments(): List<EmployeeDepartment> {


            return api.GetAllDepartments()

    }

    override suspend fun getAllEmployeeTypes(): List<EmployeeType> {
        return api.GetAllEmployeeTypes()

    }

    override suspend fun getAllEducationTitles(): List<EducationTitle> {
        return api.GetAllEducationTitle()

    }

    override suspend fun getAllClassroomTypes(): List<ClassroomType> {
        return  api.GetAllClassroomTypes()
    }

    override suspend fun getAllAppointmentTypes(): List<AppointmentType> {
      return  api.getAllAppointmentTypes()
    }


}