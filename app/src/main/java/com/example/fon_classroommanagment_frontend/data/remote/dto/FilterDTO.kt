package com.example.fon_classroommanagment_frontend.data.remote.dto

import com.example.fon_classroommanagment_frontend.common.Constants.MAX_CAPACITY
import com.example.fon_classroommanagment_frontend.common.Constants.MIN_CAPACITY
import com.example.fon_classroommanagment_frontend.domain.model.ClassroomType
import java.util.*

data  class FilterDTO(
    var min_capacity:Int=MIN_CAPACITY,
    var max_capacity:Int= MAX_CAPACITY,
    var aircondition:Boolean=true,
    var projector:Boolean=true,
    var types: List<ClassroomType> = emptyList(),
    var sortByCapacity:Boolean=false
)