package com.example.fon_classroommanagment_frontend.data.remote.dto

import com.example.fon_classroommanagment_frontend.domain.model.ClassroomType

data class ClassroomDetailsDTO(

    val name:String="",
    val number_of_seats:Int=1,
    val number_of_computers:Int=1,
    val aircondition:Boolean=false,
    val projector:Boolean=false,
    val type: ClassroomType= ClassroomType(1,""),
    val povrsina:Int=1,
    val sprat:Int=1,
    val br_tabli:Int=1,
    val months_percentage:List<List<Double>> = emptyList()


)