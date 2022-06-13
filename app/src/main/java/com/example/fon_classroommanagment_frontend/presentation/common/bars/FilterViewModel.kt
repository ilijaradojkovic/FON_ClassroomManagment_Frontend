package com.example.fon_classroommanagment_frontend.presentation.common.bars

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fon_classroommanagment_frontend.common.Response
import com.example.fon_classroommanagment_frontend.domain.model.ClassroomType
import com.example.fon_classroommanagment_frontend.domain.use_case.GetAllClassroomTypesUserCase
import com.example.fon_classroommanagment_frontend.presentation.signin_screen.RegisterState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class FilterViewModel @Inject constructor(private val getAllClassroomTypesUserCase: GetAllClassroomTypesUserCase) :ViewModel() {

    private var _classroomTypes= mutableStateListOf<ClassroomType>()
    var classroomTypes =_classroomTypes


    init{
        getAllClassroomTypes()
    }

    private fun getAllClassroomTypes() {
        getAllClassroomTypesUserCase().onEach {
                result->
            when(result){
                is Response.Success->{

                    result.data?.let { _classroomTypes.addAll(it)
                    }
                }
                is Response.Error->{
                   // _registerState.value= RegisterState(isError = true)

                }
                is Response.Loading->{
                    //_registerState.value= RegisterState(isLoading = true)
                }

            }
            //dobijam 401 mozda ne saljem token

        }.launchIn(viewModelScope)
    }

}