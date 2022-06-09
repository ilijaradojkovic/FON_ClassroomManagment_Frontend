package com.example.fon_classroommanagment_frontend.data

data class UserRegistrationDTO(

   val email:String,
 val irstName:String,
   val lastName:String,
val department:EmployeeDepartment,
   val title:EducationTitle,
   val type:EmployeeType,
val password:String

)
