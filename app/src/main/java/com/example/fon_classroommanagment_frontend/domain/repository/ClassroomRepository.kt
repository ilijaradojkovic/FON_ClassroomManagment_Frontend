package com.example.fon_classroommanagment_frontend.domain.repository

import androidx.compose.runtime.MutableState
import com.example.fon_classroommanagment_frontend.data.remote.dto.*

interface ClassroomRepository {

    suspend fun getAllClassroomsPage(page: Int, filterDto: FilterDTO):List<ClassroomCardDTO>

    suspend fun getAllClassroomsSearchPage(searchClassroomDTO: SearchClassroomDTO):List<ClassroomCardDTO>

    suspend fun getClassroomsChip(name:String):List<ClassroomChipDTO>
    suspend fun getAllClassroomsChip(page:Int):List<ClassroomChipDTO>

    suspend fun getclassroomDetails(classroomId: Long): ClassroomDetailsDTO
    //suspend fun filter(filterDTO: FilterDTO): List<ClassroomCardDTO>?

}