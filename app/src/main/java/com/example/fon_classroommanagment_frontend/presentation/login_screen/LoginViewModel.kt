package com.example.fon_classroommanagment_frontend.presentation.login_screen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fon_classroommanagment_frontend.common.Response
import com.example.fon_classroommanagment_frontend.data.remote.dto.UserLoginDTO
import com.example.fon_classroommanagment_frontend.data.remote.dto.UserRegistrationDTO
import com.example.fon_classroommanagment_frontend.domain.repository.AuthRepository
import com.example.fon_classroommanagment_frontend.domain.use_case.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) :ViewModel() {

    private val _state= mutableStateOf(LoginState())
    val state: State<LoginState> = _state

     fun Login(email: String, password: String) {

            val userLoginDTO=CreateUserLoginDTO(email, password)
            loginUseCase(userLoginDTO).onEach {
                result->
                when(result){
                    //neka vraca token popravi ovo
                    is Response.Success->{_state.value= LoginState(success = result.data.toString())
                    }
                    is Response.Error->{_state.value= LoginState(isError = result.message ?: "Nepoznati error")
                    }
                    is Response.Loading->{_state.value=LoginState(isLoading = true)}
                }
            }.launchIn(viewModelScope)


    }

     fun CreateUserLoginDTO(email: String, password: String): UserLoginDTO =
        UserLoginDTO(email,password)

     fun CreateUserRegisterDTO(
        email: String,
        password: String,
        passwordRepeat: String,
        fullName: String
    ): UserRegistrationDTO {
        TODO("Not yet implemented")
    }


}