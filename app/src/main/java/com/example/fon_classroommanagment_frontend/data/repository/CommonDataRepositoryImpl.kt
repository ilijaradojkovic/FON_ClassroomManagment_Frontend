package com.example.fon_classroommanagment_frontend.data.repository

import android.util.Log
import com.example.fon_classroommanagment_frontend.data.EducationTitle
import com.example.fon_classroommanagment_frontend.data.EmployeeDepartment
import com.example.fon_classroommanagment_frontend.data.EmployeeType
import com.example.fon_classroommanagment_frontend.data.remote.API
import com.example.fon_classroommanagment_frontend.domain.repository.CommonDataRepository
import javax.inject.Inject

class CommonDataRepositoryImpl @Inject constructor(
    private val api: API) : CommonDataRepository {
    override suspend fun getAllDepartments(): List<EmployeeDepartment> {
        try {

            return api.GetAllDepartments()
        } catch (e: Exception) {
            Log.i("cao", e.toString())
        }
        return emptyList()
    }

    override suspend fun getAllEmployeeTypes(): List<EmployeeType> {
        return api.GetAllEmployeeTypes()

    }

    override suspend fun getAllEducationTitles(): List<EducationTitle> {
        return api.GetAllEducationTitle()

    }
}