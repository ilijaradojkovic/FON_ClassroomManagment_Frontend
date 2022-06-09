package com.example.fon_classroommanagment_frontend.data.remote

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface API {
    @FormUrlEncoded
    @POST("/login")
   suspend fun Login(@Field("username") email:String,@Field("password") password:String):Call<Unit>

}