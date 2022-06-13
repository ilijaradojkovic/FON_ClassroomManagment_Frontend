package com.example.fon_classroommanagment_frontend.presentation.signin_screen.aditional_info_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fon_classroommanagment_frontend.common.Response
import com.example.fon_classroommanagment_frontend.domain.model.EmployeeDepartment
import com.example.fon_classroommanagment_frontend.data.remote.dto.UserRegistrationDTO
import com.example.fon_classroommanagment_frontend.domain.use_case.GetAllDepartmentsUseCase
import com.example.fon_classroommanagment_frontend.presentation.signin_screen.RegisterState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AditionalInfoViewModel @Inject constructor(private val getAllDepartmentsUseCase: GetAllDepartmentsUseCase):ViewModel() {

    var registerObject:UserRegistrationDTO=UserRegistrationDTO()



    private var _departments = mutableStateListOf<EmployeeDepartment>()
    var departments = _departments

    private val _registerState = mutableStateOf(RegisterState())
    val registerState by _registerState



    init {
        getAllData()
    }
    fun getAllData(){
        getAllDepartmentsUseCase().onEach {
            result->
            when(result){
                is Response.Success->{
                    _registerState.value=RegisterState(success = true)
                    result.data?.let { _departments.addAll(it)
                    }
                }
                is Response.Error->{
                    _registerState.value=RegisterState(isError = true)

                }
                is Response.Loading->{
                    _registerState.value=RegisterState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)

    }
}