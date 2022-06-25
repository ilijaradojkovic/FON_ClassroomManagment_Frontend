package com.example.fon_classroommanagment_frontend.data.remote.dto

import com.example.fon_classroommanagment_frontend.domain.model.ClassroomType

data class ClassroomDetailsDTO(

    val number_of_seats:Int,
    val number_of_computers:Int,
    val aircondition:Boolean,
    val projector:Boolean,
    val type: ClassroomType,
    val povrsina:Int,
    val sprat:Int,
    val br_tabli:Int,
    val months_percentage:List<Double>


)