package com.example.fon_classroommanagment_frontend.data.di

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.example.fon_classroommanagment_frontend.common.Constants

import com.example.fon_classroommanagment_frontend.common.Constants.URL
import com.example.fon_classroommanagment_frontend.data.remote.API
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideGson():Gson = GsonBuilder()
        .setLenient()
        .create()

    @Singleton
    @Provides
    fun provideHttpClient(sharedPreferences: SharedPreferences):OkHttpClient = OkHttpClient.Builder().addInterceptor( Interceptor() {


          val newRequest  = it.request().newBuilder()
                .addHeader("Authorization", sharedPreferences.getString(Constants.VALIDATION_TOKEN_KEY,"")?:"")
                .build()
             it.proceed(newRequest)
        }
    ).build()

    @Singleton
    @Provides
    fun provideApi(gson: Gson,sharedPreferences: SharedPreferences): API = Retrofit.Builder()
      .client(provideHttpClient(sharedPreferences))
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create(gson)).build().create(
        API::class.java)

    @Provides
    @Singleton
    fun provideSharedPref(app: Application): SharedPreferences {
        return app.getSharedPreferences("prefs", MODE_PRIVATE)
    }




}