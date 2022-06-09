package com.example.fon_classroommanagment_frontend.common

sealed class Response<T>(val data:T?=null,val message:String?=null) {

    class Success<T>( data:T?=null): Response<T>(data)
    class Error<T>(message: String?): Response<T>(message = message)
    class Loading<T>(): Response<T>()

}