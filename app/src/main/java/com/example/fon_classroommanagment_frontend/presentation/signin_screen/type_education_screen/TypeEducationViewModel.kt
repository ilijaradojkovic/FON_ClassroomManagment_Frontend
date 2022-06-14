package com.example.fon_classroommanagment_frontend.presentation.signin_screen.type_education_screen

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fon_classroommanagment_frontend.common.Response
import com.example.fon_classroommanagment_frontend.domain.model.EducationTitle
import com.example.fon_classroommanagment_frontend.domain.model.EmployeeType
import com.example.fon_classroommanagment_frontend.data.remote.dto.UserRegistrationDTO
import com.example.fon_classroommanagment_frontend.domain.use_case.GetAllEducationTitlesUseCase
import com.example.fon_classroommanagment_frontend.domain.use_case.GetAllEmployeeTypesUseCase
import com.example.fon_classroommanagment_frontend.domain.use_case.RegisterUseCase
import com.example.fon_classroommanagment_frontend.common.UIRequestResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class TypeEducationViewModel  @Inject constructor(private val getAllEmployeeTypesUseCase: GetAllEmployeeTypesUseCase,private val getAllEducationTitlesUseCase: GetAllEducationTitlesUseCase,private val registerUseCase: RegisterUseCase):ViewModel(){

    private val _registerDataTitleState = mutableStateOf(UIRequestResponse())
    val registerDataTitleState=_registerDataTitleState

    private val _registerDataTypeState = mutableStateOf(UIRequestResponse())
    val registerDataTypeState=_registerDataTypeState



    private val _state= mutableStateOf(UIRequestResponse())
    val state: State<UIRequestResponse> = _state

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

                is Response.Success->{_state.value= UIRequestResponse(success = true)
                }
                is Response.Error->{_state.value= UIRequestResponse(isError = true)
                }
                is Response.Loading->{_state.value= UIRequestResponse(isLoading = true)
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
                    _registerDataTypeState.value= UIRequestResponse(isLoading = true)
                }
                is Response.Success->{
                    Log.i("cao","types success")
                    _registerDataTypeState.value= UIRequestResponse(success = true)
                    response.data?.let { _types.addAll(it) }
                }
                is Response.Error -> {
                    Log.i("cao","types error")
                    _registerDataTypeState.value= UIRequestResponse(isError = true)
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
                _registerDataTitleState.value= UIRequestResponse(isLoading = true)
            }
            is Response.Success->{
                Log.i("cao","titles usccess")
                _registerDataTitleState.value= UIRequestResponse(success = true)
                response.data?.let { _titles.addAll(it) }
            }
            is Response.Error -> {
                Log.i("cao","titles error")
                _registerDataTitleState.value= UIRequestResponse(isError = true)
            }
        }


    }.launchIn(viewModelScope)
    }



    fun restart() {

        if(_registerDataTitleState.value.isError || _registerDataTypeState.value.isError) {

            _registerDataTitleState.value = UIRequestResponse()
            _registerDataTitleState.value = UIRequestResponse()
            getAllEducationTitles()
            getAllEmployeTypes()
        }
        _dialog.value=false
    }
}