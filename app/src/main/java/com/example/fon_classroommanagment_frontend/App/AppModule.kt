package com.example.fon_classroommanagment_frontend.App

import com.example.fon_classroommanagment_frontend.api.API
import com.example.fon_classroommanagment_frontend.repositories.AuthRepository
import com.example.fon_classroommanagment_frontend.viewModels.AuthViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideApi():API= Retrofit.Builder().baseUrl("http://localhost:8000/").addConverterFactory(GsonConverterFactory.create()).build().create(API::class.java)


    @Singleton
    @Provides
    fun provideAuthRepository(api: API):AuthRepository= AuthRepository(api)

    @Singleton
    @Provides
    fun provideAuthViewModel(authRepository: AuthRepository):AuthViewModel=AuthViewModel(authRepository)
}