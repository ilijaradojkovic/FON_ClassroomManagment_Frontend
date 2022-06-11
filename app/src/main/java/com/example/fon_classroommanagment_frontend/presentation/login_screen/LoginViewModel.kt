package com.example.fon_classroommanagment_frontend.presentation.login_screen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fon_classroommanagment_frontend.common.Response
import com.example.fon_classroommanagment_frontend.common.TokenResponse
import com.example.fon_classroommanagment_frontend.data.EducationTitle
import com.example.fon_classroommanagment_frontend.data.EmployeeDepartment
import com.example.fon_classroommanagment_frontend.data.EmployeeType
import com.example.fon_classroommanagment_frontend.data.remote.dto.UserLoginDTO
import com.example.fon_classroommanagment_frontend.data.remote.dto.UserRegistrationDTO
import com.example.fon_classroommanagment_frontend.domain.repository.AuthRepository
import com.example.fon_classroommanagment_frontend.domain.use_case.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) :ViewModel() {

    private val resultChannel= Channel<Response<TokenResponse>>()
    val authResult= resultChannel.receiveAsFlow()

    private val _state= mutableStateOf(LoginState())
    val state: State<LoginState> = _state

    var errorMessageEmail by mutableStateOf("")
   var errorMessagePassword by mutableStateOf("")

     fun Login(email: String, password: String) {

         val userLoginDTO=CreateUserLoginDTO(email, password)
            loginUseCase(userLoginDTO).onEach {
                result->
                when(result){
                    //neka vraca token popravi ovo
                    is Response.Success->{
                        _state.value= LoginState(success = result.data.toString())

                    }
                    is Response.Error->{
                        _state.value= LoginState(isError = result.message ?: "Nepoznati error")
                        errorMessageEmail ="Please enter valid email"
                        errorMessagePassword="Please enter valid password"
                    }
                    is Response.Loading->{
                        _state.value=LoginState(isLoading = true)
                        errorMessagePassword=""
                        errorMessageEmail=""
                    }
                }
                resultChannel.send(result)

            }.launchIn(viewModelScope)


    }

     fun CreateUserLoginDTO(email: String, password: String): UserLoginDTO =
        UserLoginDTO(email.trim(),password.trim())


}