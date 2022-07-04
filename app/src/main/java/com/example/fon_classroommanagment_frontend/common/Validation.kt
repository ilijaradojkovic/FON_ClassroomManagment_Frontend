package com.example.fon_classroommanagment_frontend.common

sealed class Validation(val isSuccess:Boolean=false,val error:String="",val errorExplained:String=""){

    class Success(): Validation(isSuccess = true)
    class Error(message: String,errorExplained: String): Validation(error = message, errorExplained = errorExplained)

}
