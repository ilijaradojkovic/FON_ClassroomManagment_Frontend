package com.example.fon_classroommanagment_frontend.presentation.signin_screen.aditional_info_screen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fon_classroommanagment_frontend.common.Response
import com.example.fon_classroommanagment_frontend.data.EmployeeDepartment
import com.example.fon_classroommanagment_frontend.data.remote.dto.UserRegistrationDTO
import com.example.fon_classroommanagment_frontend.data.repository.CommonDataRepositoryImpl
import com.example.fon_classroommanagment_frontend.domain.use_case.GetAllDepartmentsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AditionalInfoViewModel @Inject constructor(private val getAllDepartmentsUseCase: GetAllDepartmentsUseCase):ViewModel() {

    var registerObject:UserRegistrationDTO=UserRegistrationDTO()



    private var _departments = mutableStateListOf<EmployeeDepartment>()
    var departments = _departments
    init {
        getAllData()
    }
    fun getAllData(){
        getAllDepartmentsUseCase().onEach {
            result->
            when(result){
                is Response.Success->{

                    result.data?.let { _departments.addAll(it)
                    }
                }
                is Response.Error->{


                }
                is Response.Loading->{

                }
            }
        }.launchIn(viewModelScope)

    }
}