package com.example.fon_classroommanagment_frontend.presentation.signin_screen.type_education_screen

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fon_classroommanagment_frontend.common.Response
import com.example.fon_classroommanagment_frontend.data.EducationTitle
import com.example.fon_classroommanagment_frontend.data.EmployeeType
import com.example.fon_classroommanagment_frontend.data.remote.dto.UserRegistrationDTO
import com.example.fon_classroommanagment_frontend.domain.use_case.GetAllEducationTitlesUseCase
import com.example.fon_classroommanagment_frontend.domain.use_case.GetAllEmployeeTypesUseCase
import com.example.fon_classroommanagment_frontend.domain.use_case.RegisterUseCase
import com.example.fon_classroommanagment_frontend.presentation.signin_screen.RegisterState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class TypeEducationViewModel  @Inject constructor(private val getAllEmployeeTypesUseCase: GetAllEmployeeTypesUseCase,private val getAllEducationTitlesUseCase: GetAllEducationTitlesUseCase,private val registerUseCase: RegisterUseCase):ViewModel(){

    private val _registerDataTitleState = mutableStateOf(RegisterState())
    val registerDataTitleState=_registerDataTitleState

    private val _registerDataTypeState = mutableStateOf(RegisterState())
    val registerDataTypeState=_registerDataTypeState



    private val _state= mutableStateOf(RegisterState())
    val state: State<RegisterState> = _state

    var registerObject:UserRegistrationDTO= UserRegistrationDTO()

    private val _titles = mutableStateListOf<EducationTitle>()
    val  titles= _titles

    private  val _types= mutableStateListOf<EmployeeType>()
    val types=_types

    private val _dialog = mutableStateOf(false)
    val dialog: State<Boolean> =_dialog

init {

    getAllEducationTitles()
    getAllEmployeTypes()
}

    fun Register(){
        _dialog.value=true
        registerUseCase(registerObject).onEach { response ->
            when (response) {

                is Response.Success->{_state.value= RegisterState(success = true)
                }
                is Response.Error->{_state.value= RegisterState(isError = true)
                }
                is Response.Loading->{_state.value= RegisterState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getAllEmployeTypes(){
        getAllEmployeeTypesUseCase().onEach {
            response->
            when(response){
                is Response.Loading->{
                    Log.i("cao","types se load")
                    _registerDataTypeState.value= RegisterState(isLoading = true)
                }
                is Response.Success->{
                    Log.i("cao","types success")
                    _registerDataTypeState.value=RegisterState(success = true)
                    response.data?.let { _types.addAll(it) }
                }
                is Response.Error -> {
                    Log.i("cao","types error")
                    _registerDataTypeState.value=RegisterState(isError = true)
                }


            }

        }.launchIn(viewModelScope)
    }
    private fun getAllEducationTitles(){
    getAllEducationTitlesUseCase().onEach {
        response->
        when(response){
            is Response.Loading->{
                Log.i("cao","titles se load")
                _registerDataTitleState.value= RegisterState(isLoading = true)
            }
            is Response.Success->{
                Log.i("cao","titles usccess")
                _registerDataTitleState.value=RegisterState(success = true)
                response.data?.let { _titles.addAll(it) }
            }
            is Response.Error -> {
                Log.i("cao","titles error")
                _registerDataTitleState.value=RegisterState(isError = true)
            }
        }


    }.launchIn(viewModelScope)
    }

    fun restert() {
        _state.value= RegisterState()
        _dialog.value=false
    }
}