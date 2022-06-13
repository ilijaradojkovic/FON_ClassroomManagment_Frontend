package com.example.fon_classroommanagment_frontend.presentation.all_classrooms_screen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.fon_classroommanagment_frontend.data.remote.dto.FilterDTO
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AllClassroomsViewModel @Inject constructor():ViewModel() {


    var filterDto = mutableStateOf(FilterDTO())

    private val _searchText= mutableStateOf("")
    val searchText=_searchText


    fun filter(){
        Log.i("cao",filterDto.toString())
    }


}