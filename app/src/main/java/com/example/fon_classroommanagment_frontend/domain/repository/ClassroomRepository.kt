package com.example.fon_classroommanagment_frontend.domain.repository

import com.example.fon_classroommanagment_frontend.data.remote.dto.ClassroomCardDTO
import com.example.fon_classroommanagment_frontend.data.remote.dto.ClassroomChipDTO
import com.example.fon_classroommanagment_frontend.data.remote.dto.SearchClassroomDTO

interface ClassroomRepository {

    suspend fun getAllClassroomsPage(page:Int):List<ClassroomCardDTO>

    suspend fun getAllClassroomsSearchPage(searchClassroomDTO: SearchClassroomDTO):List<ClassroomCardDTO>

    suspend fun getAllClassroomsChip(name:String):List<ClassroomChipDTO>

}