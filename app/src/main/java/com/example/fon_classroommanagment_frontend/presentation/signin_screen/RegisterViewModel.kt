package com.example.fon_classroommanagment_frontend.presentation.signin_screen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fon_classroommanagment_frontend.common.Constants.EMAIL_REGEX
import com.example.fon_classroommanagment_frontend.common.Response
import com.example.fon_classroommanagment_frontend.data.EducationTitle
import com.example.fon_classroommanagment_frontend.data.EmployeeDepartment
import com.example.fon_classroommanagment_frontend.data.EmployeeType
import com.example.fon_classroommanagment_frontend.data.remote.dto.UserRegistrationDTO
import com.example.fon_classroommanagment_frontend.domain.use_case.RegisterUseCase
import com.example.fon_classroommanagment_frontend.presentation.login_screen.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val registerUseCase: RegisterUseCase) :ViewModel(){

    private val _state= mutableStateOf(RegisterState())
    val state: State<RegisterState> = _state

   private   var UserRegistrationDTO = mutableStateOf(UserRegistrationDTO())
    val userRegistrationDTO by UserRegistrationDTO
    var errorMessageEmail by mutableStateOf("")
    var errorMessagePassword by mutableStateOf("")
    var errorFullName by mutableStateOf("")

    private var _canNavigate =mutableStateOf(false)
    val canNavigate by _canNavigate


    fun Register(email: String,password: String,passwordRepeat: String,fullName: String){

      //  if(Validate(email,password,passwordRepeat,fullName)){
            UserRegistrationDTO.value=CreateUserRegisterDTO(email,password,fullName)
            _canNavigate.value=true
       // }



//        registerUseCase(registerDTO).onEach {result->
//            when(result){
//
//                is Response.Success->{_state.value= RegisterState(success = result.data.toString())
//                }
//                is Response.Error->{_state.value= RegisterState(isError = result.message ?: "Nepoznati error")
//                }
//                is Response.Loading->{_state.value= RegisterState(isLoading = true)
//                }
//            }
//        }.launchIn(viewModelScope)
    }
    fun CreateUserRegisterDTO(
        email: String,
        password: String,
        fullName: String
    ): UserRegistrationDTO {
        val fullNameStrs=fullName.split(" ")
        return UserRegistrationDTO(email, password,fullNameStrs[0],fullNameStrs[1])
    }

     fun Validate(email:String,password:String,passwordRepeat: String,fullName: String): Boolean {
         var isError=false
         if(EmaiLValidation(email)) {
             isError=true
             errorMessageEmail="Please enter valid email"
         }else errorMessageEmail=""
         if(PasswordValidation(password)){
             isError=true
             errorMessagePassword="Please enter valid password"
         }else{
             errorMessagePassword=""
             if(!password.equals(passwordRepeat)){
                 isError=true
                 errorMessagePassword="Passwords are not the same"
             }
         }
         if(FullNameValidation(fullName)){
             isError=true
             errorFullName="Please enter valid full name"
         }
         else{ errorFullName=""
            val fname=fullName.split(" ")
             if(fname.size<2){
                 isError=true
                 errorFullName="Please enter valid full name"
             }else {
                 val first_name = fname[0]
                 val last_name = fname[1]
                 if (first_name.isBlank() || last_name.isBlank() || first_name.isEmpty() || last_name.isEmpty() || first_name.length >= 20 || last_name.length >= 20) {
                     isError = true
                     errorFullName = "Please enter valid full name"
                 }
             }
         }
        return !isError
     }

    private fun FullNameValidation(fullName: String): Boolean {
        return fullName.isEmpty() || fullName.isBlank()
    }

    private fun PasswordValidation(password: String): Boolean {
        return password.isBlank() || password.isEmpty() || password.length<4
    }

    private fun EmaiLValidation(email: String): Boolean {
        return email.isEmpty() || email.isBlank() || !Regex(EMAIL_REGEX).matches(email)
    }

}