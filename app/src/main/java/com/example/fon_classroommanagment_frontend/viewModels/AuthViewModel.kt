package com.example.fon_classroommanagment_frontend.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.fon_classroommanagment_frontend.repositories.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(authRepository: AuthRepository) :ViewModel() {

    fun Login(email:String){
        Log.i("cao",email)
    }

}