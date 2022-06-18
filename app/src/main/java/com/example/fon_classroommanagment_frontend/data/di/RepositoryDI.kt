package com.example.fon_classroommanagment_frontend.data.di

import android.content.SharedPreferences
import com.example.fon_classroommanagment_frontend.data.remote.API
import com.example.fon_classroommanagment_frontend.data.repository.AuthRepositoryImpl
import com.example.fon_classroommanagment_frontend.data.repository.ClassroomRepositoryImpl
import com.example.fon_classroommanagment_frontend.data.repository.CommonDataRepositoryImpl
import com.example.fon_classroommanagment_frontend.data.repository.ReservationRepositoryImpl
import com.example.fon_classroommanagment_frontend.domain.repository.AuthRepository
import com.example.fon_classroommanagment_frontend.domain.repository.ClassroomRepository
import com.example.fon_classroommanagment_frontend.domain.repository.CommonDataRepository
import com.example.fon_classroommanagment_frontend.domain.repository.ReservationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryDI {

    @Singleton
    @Provides
    fun provideAuthRepository(api: API, sharedPreferences: SharedPreferences): AuthRepository = AuthRepositoryImpl(api,sharedPreferences)


    @Singleton
    @Provides
    fun provideCommonDataRepository(api: API): CommonDataRepository = CommonDataRepositoryImpl(api)

    @Provides
    @Singleton
    fun provideClassroomRepository(api: API): ClassroomRepository = ClassroomRepositoryImpl(api)

    @Provides
    @Singleton
    fun provideReservationRepositoryImpl(api: API): ReservationRepository =
        ReservationRepositoryImpl(api)

}