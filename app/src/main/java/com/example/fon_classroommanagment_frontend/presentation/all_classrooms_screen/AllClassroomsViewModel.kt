package com.example.fon_classroommanagment_frontend.presentation.all_classrooms_screen

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fon_classroommanagment_frontend.common.Response
import com.example.fon_classroommanagment_frontend.common.UIRequestResponse
import com.example.fon_classroommanagment_frontend.data.remote.dto.FilterDTO
import com.example.fon_classroommanagment_frontend.data.remote.dto.ClassroomCardDTO
import com.example.fon_classroommanagment_frontend.data.remote.dto.SearchClassroomDTO
import com.example.fon_classroommanagment_frontend.domain.use_case.GetAllClassroomSearched
import com.example.fon_classroommanagment_frontend.domain.use_case.GetClassroomsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AllClassroomsViewModel @Inject constructor( private val getClassroomsUseCase: GetClassroomsUseCase,private  val getAllClassroomSearched: GetAllClassroomSearched):ViewModel() {

    private var page:Int=1


    var filterDto = mutableStateOf(FilterDTO())

    var searchText = mutableStateOf("")

    private var _classrooms = mutableStateListOf<ClassroomCardDTO>()
    val classrooms=_classrooms

    private var _searchClassrooms= mutableStateListOf<ClassroomCardDTO>()
    val searchedClassrooms= _searchClassrooms

    private var _networkState= mutableStateOf(UIRequestResponse())
    val networkState=_networkState

    init {
        getAllClassrooms()
    }

    fun filter(){
        Log.i("cao",filterDto.toString())
    }

    fun getAllClassrooms(){
        if(!networkState.value.isLoading) {
            getClassroomsUseCase(page).onEach { result ->
                when (result) {
                    is Response.Success -> {
                        result.data?.let {
                            page++
                            if(it.isEmpty()) Log.i("cao","nema vise podataka")
                            _networkState.value = UIRequestResponse(success = true)
                            _classrooms.addAll(it)

                        }
                    }
                    is Response.Loading -> {
                        _networkState.value = UIRequestResponse(isLoading = true)


                    }
                    is Response.Error -> {
                        _networkState.value = UIRequestResponse(isError = true)

                        result.message?.let { Log.i("cao", it) }
                    }
                }
            }.launchIn(viewModelScope)
        }
        else{
            Log.i("cao","cekaj vec ucitavam")
        }
    }

    fun searchClassrooms(searchText: String) {
        _searchClassrooms.clear()
        getAllClassroomSearched(createClassroomSearchObject(searchText)).onEach {
            result->
            when(result){
                is Response.Loading->{Log.i("cao","loading")}
                is Response.Error->{
                    result.message?.let { Log.i("cao", it) }
                }
                is Response.Success->{
                    result.data?.let { _searchClassrooms.addAll(it) }
                }
            }

        }.launchIn(viewModelScope)
    }

    private fun createClassroomSearchObject(searchText: String) = SearchClassroomDTO(searchText)
      fun shouldDisplaySeachData() = !searchText.value.isEmpty() && _searchClassrooms.size!=0

}