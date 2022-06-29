package com.example.fon_classroommanagment_frontend.data.remote.dto
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import com.example.fon_classroommanagment_frontend.domain.model.EducationTitle
import com.example.fon_classroommanagment_frontend.domain.model.EmployeeDepartment
import com.example.fon_classroommanagment_frontend.domain.model.EmployeeType
@Parcelize
data class UserRegistrationDTO(

    val email:String="",
    val password:String="",
    val firstName:String="",
    val lastName:String="",
    //var image:String="",

) : Parcelable
