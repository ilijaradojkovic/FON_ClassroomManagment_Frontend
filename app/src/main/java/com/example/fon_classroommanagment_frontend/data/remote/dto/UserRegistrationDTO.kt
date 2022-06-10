package com.example.fon_classroommanagment_frontend.data.remote.dto
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import com.example.fon_classroommanagment_frontend.data.EducationTitle
import com.example.fon_classroommanagment_frontend.data.EmployeeDepartment
import com.example.fon_classroommanagment_frontend.data.EmployeeType
@Parcelize
data class UserRegistrationDTO(

    val email:String="",
    val password:String="",
    val firstName:String="",
    val lastName:String="",
    val department: EmployeeDepartment?=null,
    val title: EducationTitle?=null,
    val type: EmployeeType?=null


) : Parcelable
