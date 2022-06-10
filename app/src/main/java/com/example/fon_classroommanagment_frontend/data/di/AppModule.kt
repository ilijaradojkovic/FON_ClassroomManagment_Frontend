package com.example.fon_classroommanagment_frontend.data.di

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.example.fon_classroommanagment_frontend.common.Constants.URL
import com.example.fon_classroommanagment_frontend.data.remote.API
import com.example.fon_classroommanagment_frontend.data.repository.AuthRepositoryImpl
import com.example.fon_classroommanagment_frontend.data.repository.CommonDataRepositoryImpl
import com.example.fon_classroommanagment_frontend.domain.repository.AuthRepository
import com.example.fon_classroommanagment_frontend.domain.repository.CommonDataRepository
import com.example.fon_classroommanagment_frontend.domain.use_case.GetAllDepartmentsUseCase
import com.example.fon_classroommanagment_frontend.domain.use_case.LoginUseCase
import com.example.fon_classroommanagment_frontend.domain.use_case.RegisterUseCase
import com.example.fon_classroommanagment_frontend.presentation.login_screen.LoginViewModel
import com.example.fon_classroommanagment_frontend.presentation.signin_screen.RegisterViewModel
import com.example.fon_classroommanagment_frontend.presentation.signin_screen.aditional_info_screen.AditionalInfoViewModel
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
    fun provideHttpClient():OkHttpClient = OkHttpClient.Builder().addInterceptor( Interceptor() {


          val newRequest  = it.request().newBuilder()
                .addHeader("Authorization", "Bearer " + "Token")
                .build()
             it.proceed(newRequest)
        }
    ).build()

    @Singleton
    @Provides
    fun provideApi(gson: Gson): API = Retrofit.Builder()
      //  .client(provideHttpClient())
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create(gson)).build().create(
        API::class.java)

    @Provides
    @Singleton
    fun provideSharedPref(app: Application): SharedPreferences {
        return app.getSharedPreferences("prefs", MODE_PRIVATE)
    }
    @Singleton
    @Provides
    fun provideAuthRepository(api: API,sharedPreferences: SharedPreferences): AuthRepository = AuthRepositoryImpl(api,sharedPreferences)

    @Singleton
    @Provides
    fun provideLoginUseCase(authRepository: AuthRepository):LoginUseCase=LoginUseCase(authRepository)

    @Singleton
    @Provides
    fun provideLoginViewModel(loginUseCase: LoginUseCase): LoginViewModel = LoginViewModel(loginUseCase)



    @Singleton
    @Provides
    fun provideRegisterUseCase(authRepository: AuthRepository):RegisterUseCase= RegisterUseCase(authRepository)

    @Singleton
    @Provides
    fun provideRegisterViewModel(registerUseCase: RegisterUseCase): RegisterViewModel = RegisterViewModel(registerUseCase)


    @Singleton
    @Provides
    fun provideCommonDataRepository(api: API):CommonDataRepository=CommonDataRepositoryImpl(api)

    @Singleton
    @Provides
    fun provideGetAllDepartmentsUseCase(commonDataRepository: CommonDataRepository):GetAllDepartmentsUseCase=
        GetAllDepartmentsUseCase(commonDataRepository)

    @Singleton
    @Provides
    fun provideAditionalInfoViewModel(getAllDepartmentsUseCase: GetAllDepartmentsUseCase)=AditionalInfoViewModel(getAllDepartmentsUseCase)
}