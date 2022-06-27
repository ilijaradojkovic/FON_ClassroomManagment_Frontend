package com.example.fon_classroommanagment_frontend.data.repository

import com.example.fon_classroommanagment_frontend.data.remote.API
import com.example.fon_classroommanagment_frontend.data.remote.dto.*
import com.example.fon_classroommanagment_frontend.domain.repository.ClassroomRepository
import javax.inject.Inject

class ClassroomRepositoryImpl @Inject constructor(private val api: API) :ClassroomRepository  {
    override suspend fun getAllClassroomsPage(page:Int):List<ClassroomCardDTO> {
       return  api.GetAllClassroomsPaging(page)
    }

    override suspend fun getAllClassroomsSearchPage(searchClassroomDTO: SearchClassroomDTO): List<ClassroomCardDTO> {
        return  api.getSearchClassrooms(searchClassroomDTO.page,searchClassroomDTO.name)
    }

    override suspend fun getClassroomsChip(name: String): List<ClassroomChipDTO> {
        return  api.GetClassroomsChip(name)
    }

    override suspend fun getAllClassroomsChip(page: Int): List<ClassroomChipDTO> {
        return api.GetAllClassroomsChip(page)
    }

    override suspend fun getclassroomDetails(classroomId: Long): ClassroomDetailsDTO {
        return api.getClassroomDetails(classroomId)
    }

    override suspend fun filter(filterDTO: FilterDTO): List<ClassroomCardDTO>? {
        return api.filterClassrooms(filterDTO)
    }


}