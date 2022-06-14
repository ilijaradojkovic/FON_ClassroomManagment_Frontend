package com.example.fon_classroommanagment_frontend.data.repository

import com.example.fon_classroommanagment_frontend.data.remote.API
import com.example.fon_classroommanagment_frontend.data.remote.dto.ClassroomCardDTO
import com.example.fon_classroommanagment_frontend.domain.repository.ClassroomRepository
import javax.inject.Inject

class ClassroomRepositoryImpl @Inject constructor(private val api: API) :ClassroomRepository  {
    override suspend fun getAllClassroomsPage(page:Int):List<ClassroomCardDTO> {
       return  api.GetAllClassroomsPaging(page)
    }
}