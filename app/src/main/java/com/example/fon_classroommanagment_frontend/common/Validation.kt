package com.example.fon_classroommanagment_frontend.common

sealed class Validation(val isSuccess:Boolean=false,val error:String?=null,val errorExplained:String?=null){

    class Success(): Validation(isSuccess = true)
    class Error(message: String,errorExplained: String): Validation(error = message, errorExplained = errorExplained)

}
