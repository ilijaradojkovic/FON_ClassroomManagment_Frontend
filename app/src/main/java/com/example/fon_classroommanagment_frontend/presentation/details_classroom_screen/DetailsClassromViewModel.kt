package com.example.fon_classroommanagment_frontend.presentation.details_classroom_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fon_classroommanagment_frontend.common.Response
import com.example.fon_classroommanagment_frontend.domain.use_case.GetClassroomDetailsUseCase
import com.example.fon_classroommanagment_frontend.domain.use_case.GetClassroomsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class DetailsClassromViewModel @Inject constructor(private val getClassroomDetailsUseCase: GetClassroomDetailsUseCase) :ViewModel() {



    fun getClassroom(classroomId: Long) {
        getClassroomDetailsUseCase(classroomId).onEach {
            result->
            when(result){
                is Response.Loading->{
                    Log.i("cao","Loading")
                }
                is Response.Error->{
                    Log.i("cao",result.message.toString())
                }
                is Response.Success->{
                    Log.i("cao",result.data.toString())
                }
            }
        }.launchIn(viewModelScope)
    }


}