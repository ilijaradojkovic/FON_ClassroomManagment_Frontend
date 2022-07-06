package com.example.fon_classroommanagment_frontend.presentation.signin_screen

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Base64
import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fon_classroommanagment_frontend.common.Constants.EMAIL_REGEX
import com.example.fon_classroommanagment_frontend.common.Response
import com.example.fon_classroommanagment_frontend.common.UIRequestResponse
import com.example.fon_classroommanagment_frontend.data.remote.dto.UserRegistrationDTO
import com.example.fon_classroommanagment_frontend.domain.use_case.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.io.ByteArrayOutputStream
import javax.inject.Inject


@HiltViewModel
class RegisterViewModel @Inject constructor(private val registerUseCase: RegisterUseCase) :ViewModel(){


    var emailText = mutableStateOf("")
    var passwordText = mutableStateOf("")
    var passwordRepeatText = mutableStateOf("")
    var fullNameText = mutableStateOf("")
    var image= mutableStateOf<Bitmap?>(null)
    var imageURI= mutableStateOf<String?>(null)


    private val _dialog = mutableStateOf(false)
    val dialog: State<Boolean> =_dialog

    private val _state= mutableStateOf(UIRequestResponse())
    val state: State<UIRequestResponse> = _state

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

        _state.value= UIRequestResponse(isLoading = true)
        if(Validate(emailText.value,passwordText.value,passwordRepeatText.value,fullNameText.value)){
            UserRegistrationDTO.value=CreateUserRegisterDTO()
            restartCoreData()
            registerUseCase(UserRegistrationDTO.value).onEach {
                result->
                when(result){
                    is Response.Loading->{
                        _dialog.value=true


                        Log.i("cao","loading")

                    }
                    is Response.Error->{
                        _dialog.value=true

                        _state.value=UIRequestResponse(isError = true)

                        Log.i("cao","error ${result.message}")

                    }
                    is Response.Success->{
                        _dialog.value=true

                        _state.value=UIRequestResponse(success = true)

                        Log.i("cao","success regster")
                    }
                }
            }.launchIn(viewModelScope)
        }




    }
    fun CreateUserRegisterDTO(
    ): UserRegistrationDTO {
        val fullNameStrs=fullNameText.value.split(" ")

        return UserRegistrationDTO(emailText.value, passwordText.value,fullNameStrs[0],fullNameStrs[1],
          bytesToBase64(bitmapToBytes(image.value)))
    }
    fun bitmapToBytes(photo: Bitmap?): ByteArray? {
        if(photo!=null) {
            val stream = ByteArrayOutputStream()
            photo.compress(Bitmap.CompressFormat.PNG, 100, stream)
            return stream.toByteArray()
        }
        return null
    }
    fun bytesToBase64(bytes: ByteArray?): String? {
        return Base64.encodeToString(bytes, 0)
    }


//    fun transformBitpamToBtye(bitmap: Bitmap?): ByteArray? {
//
//        if(bitmap!=null) {
//            val scaledBitmap = Bitmap.createScaledBitmap(bitmap, bitmap.width, bitmap.height, true)
//
//            val stream = ByteArrayOutputStream()
//
//            scaledBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
//            return stream.toByteArray()
//
//            //    Log.i("cao",image.size.toString())
//
//        }
//        return null
//
//    }
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
        fullNameText.value=""
        image.value=null
        passwordText.value=""
        emailText.value=""
        passwordRepeatText.value=""

    }

    fun restart() {

        _canNavigate.value=false
        errorFullName=""
        errorMessageEmail=""
        errorMessagePassword=""
        _dialog.value=false
        _state.value=UIRequestResponse()
    }

    fun setImageURI(imageUri: Uri) {

        this.imageURI.value=imageUri.toString()
    }

}