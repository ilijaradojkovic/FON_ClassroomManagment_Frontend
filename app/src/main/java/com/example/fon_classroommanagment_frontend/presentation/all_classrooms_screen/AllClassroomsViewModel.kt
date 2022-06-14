package com.example.fon_classroommanagment_frontend.presentation.all_classrooms_screen

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fon_classroommanagment_frontend.common.Response
import com.example.fon_classroommanagment_frontend.data.remote.dto.FilterDTO
import com.example.fon_classroommanagment_frontend.domain.model.Classroom
import com.example.fon_classroommanagment_frontend.domain.use_case.GetClassroomsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AllClassroomsViewModel @Inject constructor( private val getClassroomsUseCase: GetClassroomsUseCase):ViewModel() {


    var filterDto = mutableStateOf(FilterDTO())

    private val _searchText= mutableStateOf("")
    val searchText=_searchText

    private var _classrooms = mutableStateListOf<Classroom>()
    val classrooms=_classrooms

    init {
        getAllClassrooms(1)
    }

    fun filter(){
        Log.i("cao",filterDto.toString())
    }

    fun getAllClassrooms(page:Int){
        getClassroomsUseCase().onEach {
            result->
            when(result){
                is Response.Success->{
                    result.data?.let { _classrooms.addAll(it) }
                }
                is Response.Loading->{Log.i("cao","loading")}
                is Response.Error->{
                    result.message?.let { Log.i("cao", it) }
                }
            }
        }.launchIn(viewModelScope)
    }


}