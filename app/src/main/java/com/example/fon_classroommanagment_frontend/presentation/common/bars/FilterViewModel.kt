package com.example.fon_classroommanagment_frontend.presentation.common.bars

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fon_classroommanagment_frontend.common.Constants
import com.example.fon_classroommanagment_frontend.common.Response
import com.example.fon_classroommanagment_frontend.domain.model.ClassroomType
import com.example.fon_classroommanagment_frontend.domain.use_case.GetAllClassroomTypesUserCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class FilterViewModel @Inject constructor(private val getAllClassroomTypesUserCase: GetAllClassroomTypesUserCase) :ViewModel() {

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
    var sliderPositionConverted = mutableStateOf((_sliderPosition.value.start* Constants.MAX_CAPACITY).toInt()..(_sliderPosition.value.endInclusive* Constants.MAX_CAPACITY).toInt())
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

    fun changeRangeCapacity(range: ClosedFloatingPointRange<Float>){
        _sliderPosition.value=range
        sliderPositionConverted.value=(range.start* Constants.MAX_CAPACITY).toInt().. (range.endInclusive* Constants.MAX_CAPACITY).toInt()
    }
    fun changeAircondition(value:Boolean) {_aircontition.value=value}

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

}