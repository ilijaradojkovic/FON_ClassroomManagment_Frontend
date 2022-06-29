package com.example.fon_classroommanagment_frontend.presentation.signin_screen

import android.R.attr.scaleHeight
import android.R.attr.scaleWidth
import android.graphics.Bitmap
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fon_classroommanagment_frontend.common.Constants.EMAIL_REGEX
import com.example.fon_classroommanagment_frontend.common.Response
import com.example.fon_classroommanagment_frontend.data.remote.dto.UserRegistrationDTO
import com.example.fon_classroommanagment_frontend.domain.use_case.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.io.ByteArrayOutputStream
import javax.inject.Inject


@HiltViewModel
class RegisterViewModel @Inject constructor(private val registerUseCase: RegisterUseCase) :ViewModel(){


    var _emailText = mutableStateOf("")
    var _passwordText = mutableStateOf("")
    var _passwordRepeatText = mutableStateOf("")
    var _fullNameText = mutableStateOf("")
    var _image= mutableStateOf<Bitmap?>(null)


   private   var UserRegistrationDTO = mutableStateOf(UserRegistrationDTO())
    val userRegistrationDTO by UserRegistrationDTO
    var errorMessageEmail by mutableStateOf("")
    var errorMessagePassword by mutableStateOf("")
    var errorFullName by mutableStateOf("")

    private var _canNavigate =mutableStateOf(false)
    val canNavigate by _canNavigate

init {

    _canNavigate.value=false
}
    fun Register(){


        if(Validate(_emailText.value,_passwordText.value,_passwordRepeatText.value,_fullNameText.value)){
            UserRegistrationDTO.value=CreateUserRegisterDTO()
            restartCoreData()
            registerUseCase(UserRegistrationDTO.value).onEach {
                result->
                when(result){
                    is Response.Loading->{
                        Log.i("cao","loading")

                    }
                    is Response.Error->{
                        Log.i("cao","error ${result.message}")

                    }
                    is Response.Success->{
                        Log.i("cao","success regster")
                    }
                }
            }.launchIn(viewModelScope)
        }




    }
    fun CreateUserRegisterDTO(
    ): UserRegistrationDTO {
        val fullNameStrs=_fullNameText.value.split(" ")
        return UserRegistrationDTO(_emailText.value, _passwordText.value,fullNameStrs[0],fullNameStrs[1])
           // ,transformBitpamToBtye(_image.value))
    }

    fun transformBitpamToBtye(bitmapimg: Bitmap?): ByteArray {




        var image= byteArrayOf()
        if(bitmapimg!=null) {
            val scaledBitmap = Bitmap.createScaledBitmap(bitmapimg, 1, 1, true)

            val stream = ByteArrayOutputStream()

            scaledBitmap.compress(Bitmap.CompressFormat.PNG, 10, stream)
            image = stream.toByteArray()
        }
    //    Log.i("cao",image.size.toString())
        return image

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

    fun restartCoreData(){
        _fullNameText.value=""
        _image.value=null
        _passwordText.value=""
        _emailText.value=""
        _passwordRepeatText.value=""
    }

    fun restart() {

        _canNavigate.value=false
        errorFullName=""
        errorMessageEmail=""
        errorMessagePassword=""
    }

}