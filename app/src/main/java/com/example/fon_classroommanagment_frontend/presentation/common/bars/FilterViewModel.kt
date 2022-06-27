package com.example.fon_classroommanagment_frontend.presentation.common.bars

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fon_classroommanagment_frontend.common.Constants.MAX_CAPACITY
import com.example.fon_classroommanagment_frontend.common.Response
import com.example.fon_classroommanagment_frontend.data.remote.dto.FilterDTO
import com.example.fon_classroommanagment_frontend.domain.model.ClassroomType
import com.example.fon_classroommanagment_frontend.domain.use_case.GetAllClassroomTypesUserCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class FilterViewModel @Inject constructor(private val getAllClassroomTypesUserCase: GetAllClassroomTypesUserCase) :ViewModel() {


    private var _filterDTO = mutableStateOf(FilterDTO())
    val filterDTO=_filterDTO

    private var _classroomTypes= mutableStateListOf<ClassroomType>()
    val classroomTypes =_classroomTypes


    private var _classroomTypesChoosen= mutableStateListOf<ClassroomType>()
   // val classroomTypeChoosen=_classroomTypesChoosen


    private var _aircontition= mutableStateOf(true)
    val aircontition by  _aircontition

    private var _projector= mutableStateOf(true)
    val projector by _projector


    private var _sortByCapacity= mutableStateOf(false)
    val sortByCapacity by _sortByCapacity

   private  var _sliderPosition =  mutableStateOf(0f ..1f)
    var sliderPosition by _sliderPosition
    var sliderPositionConverted = mutableStateOf((_sliderPosition.value.start* MAX_CAPACITY).toInt()..(_sliderPosition.value.endInclusive* MAX_CAPACITY).toInt())

    init{
        getAllClassroomTypes()
    }

    fun filter():FilterDTO
        {

            return FilterDTO(
                sliderPositionConverted.value.first,
                sliderPositionConverted.value.last,
                _aircontition.value,
                _projector.value,
                _classroomTypesChoosen.toList(),
                _sortByCapacity.value
            )
        }
            private fun getAllClassroomTypes() {
        getAllClassroomTypesUserCase().onEach {
                result->
            when(result){
                is Response.Success->{

                    result.data?.let { _classroomTypes.addAll(it)
                        //_filterDTO.value.types=_classroomTypes
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

    fun changeRangeCapacity(range: ClosedFloatingPointRange<Float>){
        _sliderPosition.value=range
        sliderPositionConverted.value=(range.start* MAX_CAPACITY).toInt().. (range.endInclusive* MAX_CAPACITY).toInt()
    }
    fun changeAircondition(value:Boolean) {
        _aircontition.value=value}

    fun changeProjector(value:Boolean) {_projector.value=value}

    fun changeSortByCapacity(value: Boolean){_sortByCapacity.value=value}
    fun shouldShowCoosenType(classroomType: ClassroomType):Boolean=_classroomTypesChoosen.contains(classroomType)
     fun handleClassroomTypeChoosen(classroomType: ClassroomType){
        if(_classroomTypesChoosen.contains(classroomType))
            deleteClassroomType(classroomType)
        else
           insertClassroomType(classroomType)
    }

    private fun insertClassroomType(classroomType: ClassroomType){
        _classroomTypesChoosen.add(classroomType)
    }

    private fun deleteClassroomType(classroomType: ClassroomType){
        _classroomTypesChoosen.remove(classroomType)
    }

    fun setFilterDTO(_filterDTO: FilterDTO) {
        _classroomTypesChoosen.clear()
        _classroomTypesChoosen.addAll(_filterDTO.types)
        _projector.value=_filterDTO.projector
        _aircontition.value=_filterDTO.aircondition
        _sliderPosition.value= _filterDTO.min_capacity/ MAX_CAPACITY.toFloat().._filterDTO.max_capacity/ MAX_CAPACITY.toFloat()
        sliderPositionConverted.value=_filterDTO.min_capacity.._filterDTO.max_capacity
        _sortByCapacity.value=_filterDTO.sortByCapacity
       // _classroomTypesChoosen=_classroomTypes

    }

}