package com.example.fon_classroommanagment_frontend.data.remote.dto

import com.example.fon_classroommanagment_frontend.common.Constants.MAX_CAPACITY
import com.example.fon_classroommanagment_frontend.common.Constants.MIN_CAPACITY
import com.example.fon_classroommanagment_frontend.domain.model.ClassroomType

data  class FilterDTO(
    var minCapacity:Int=MIN_CAPACITY,
    var maxCapacity:Int= MAX_CAPACITY,
    var aircondition:Boolean=true,
    var projeector:Boolean=true,
    val types:List<ClassroomType> = emptyList(),
    var sortByCapacity:Boolean=false
)