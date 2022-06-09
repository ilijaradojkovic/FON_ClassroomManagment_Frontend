package com.example.fon_classroommanagment_frontend.data.remote.dto

import com.example.fon_classroommanagment_frontend.data.EducationTitle
import com.example.fon_classroommanagment_frontend.data.EmployeeDepartment
import com.example.fon_classroommanagment_frontend.data.EmployeeType

data class UserRegistrationDTO(

    val email:String,
    val firstName:String,
    val lastName:String,
    val department: EmployeeDepartment,
    val title: EducationTitle,
    val type: EmployeeType,
    val password:String

)
