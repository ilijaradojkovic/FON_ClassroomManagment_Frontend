package com.example.fon_classroommanagment_frontend.presentation.classrooms_screen

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
import com.example.fon_classroommanagment_frontend.domain.model.ClassroomType
import com.example.fon_classroommanagment_frontend.domain.use_case.GetAllClassroomSearchedUseCase
import com.example.fon_classroommanagment_frontend.domain.use_case.GetAllClassroomTypesUserCase
import com.example.fon_classroommanagment_frontend.domain.use_case.GetClassroomsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ClassroomsViewModel @Inject constructor(private val getClassroomsUseCase: GetClassroomsUseCase, private  val getAllClassroomSearched: GetAllClassroomSearchedUseCase,private val getAllClassroomTypesUserCase: GetAllClassroomTypesUserCase):ViewModel() {

    private var page:Int=1
    private var searchPage:Int=1

    private var _classroomTypes= mutableStateListOf<ClassroomType>()
    val classroomTypes =_classroomTypes

    var filterDto = mutableStateOf(FilterDTO())

    private  var _searchText = mutableStateOf("")
    var searchText = _searchText

    var searchRequested= mutableStateOf(false)

    private var _searchClassrooms= mutableStateListOf<ClassroomCardDTO>()
    val searchedClassrooms= _searchClassrooms

    private var _networkStateSearch= mutableStateOf(UIRequestResponse())
    val networkStateSearch=_networkStateSearch


    private var _classrooms = mutableSetOf<ClassroomCardDTO>()
    val classrooms=_classrooms



    private var _networkState= mutableStateOf(UIRequestResponse())
    val networkState=_networkState

    private var _allClassroomsTypeState= mutableStateOf(UIRequestResponse())
    val allClassroomsTypeState =_allClassroomsTypeState

//handle kada je error pri searchu
     fun changeSearchText(searchText: String){
        if(searchText.isEmpty()) {
            searchRequested.value = false

        }
    searchPage=1
        this._searchText.value=searchText
    }
    init{
        getAllClassroomTypes()

    }

    fun filter(_filterDTO: FilterDTO) {

            filterDto.value=_filterDTO
            page=1
            _classrooms.clear()

            getAllClassrooms()





    }
    private fun getAllClassroomTypes() {
        getAllClassroomTypesUserCase().onEach {
                result->
            when(result){
                is Response.Success->{
                    result.data?.let {
                        filterDto.value.types=it
                        _classroomTypes.clear()
                        _classroomTypes.addAll(it)
                        _allClassroomsTypeState.value= UIRequestResponse(success = true)
                        filter(filterDto.value)
                        //_filterDTO.value.types=_classroomTypes

                    }
                }
                is Response.Error->{
                    _allClassroomsTypeState.value=UIRequestResponse(isError = true)
                }
                is Response.Loading->{
                    _allClassroomsTypeState.value=UIRequestResponse(isLoading = true)
                }

            }
            //dobijam 401 mozda ne saljem token

        }.launchIn(viewModelScope)
    }


    fun getAllClassrooms(){

        if(!networkState.value.isLoading) {
            getClassroomsUseCase(page,filterDto).onEach { result ->
                when (result) {
                    is Response.Success -> {
                        result.data?.let {
                            if(it.isNotEmpty())
                                page++

                            _networkState.value = UIRequestResponse(success = true)

                            _classrooms.addAll(it)

                        }
                    }
                    is Response.Loading -> {
                        _networkState.value = UIRequestResponse(isLoading = true)


                    }
                    is Response.Error -> {
                        _networkState.value = UIRequestResponse(isError = true)


                    }
                }
            }.launchIn(viewModelScope)
        }
        else{
            //Log.i("cao","cekaj vec ucitavam")
        }
    }
    fun getMoreSearchData(){
        performSeach()
    }

    fun searchClassrooms() {
        searchRequested.value=true

        _searchClassrooms.clear()
        performSeach()

    }
   private  fun performSeach(){
       if(!_networkStateSearch.value.isLoading)
           getAllClassroomSearched(createClassroomSearchObject(_searchText.value,searchPage)).onEach {
                   result->
               when(result){
                   is Response.Loading->{_networkStateSearch.value=UIRequestResponse(isLoading = true)}
                   is Response.Error->{
                       result.message?.let { _networkStateSearch.value= UIRequestResponse(isError = true) }
                   }
                   is Response.Success->{
                       searchPage++
                       _networkStateSearch.value=UIRequestResponse(success = true)
                       result.data?.let { _searchClassrooms.addAll(it) }
                   }
               }


           }.launchIn(viewModelScope)

   }

    private fun createClassroomSearchObject(searchText: String, searchPage: Int) = SearchClassroomDTO(searchText,searchPage)
    fun shouldDisplaySeachData(): Boolean {

     return searchRequested.value
    }

    fun restartFilter() {
        filterDto.value=FilterDTO()
        filterDto.value.types=classroomTypes.toList()
    }


}