package com.example.fon_classroommanagment_frontend.presentation.login_screen

data class LoginState(
    val isError:String?=null,
    val isLoading:Boolean=false,
    val success:String?=null
    //response od api cala

)
