package com.example.fon_classroommanagment_frontend.data.di

import com.example.fon_classroommanagment_frontend.data.remote.API
import com.example.fon_classroommanagment_frontend.data.repository.AuthRepositoryImpl
import com.example.fon_classroommanagment_frontend.domain.repository.AuthRepository
import com.example.fon_classroommanagment_frontend.domain.use_case.LoginUseCase
import com.example.fon_classroommanagment_frontend.presentation.login_screen.LoginViewModel
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
    fun provideApi(): API = Retrofit.Builder().baseUrl("http://10.0.2.2:8080").addConverterFactory(GsonConverterFactory.create()).build().create(
        API::class.java)


    @Singleton
    @Provides
    fun provideAuthRepository(api: API): AuthRepository = AuthRepositoryImpl(api)

    @Singleton
    @Provides
    fun provideLoginUseCase(authRepository: AuthRepository):LoginUseCase=LoginUseCase(authRepository)

    @Singleton
    @Provides
    fun provideAuthViewModel(loginUseCase: LoginUseCase): LoginViewModel = LoginViewModel(loginUseCase)



}