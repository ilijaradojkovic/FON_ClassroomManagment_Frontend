package com.example.fon_classroommanagment_frontend.domain.repository

import com.example.fon_classroommanagment_frontend.data.remote.dto.ClassroomCardDTO

interface ClassroomRepository {

    suspend fun getAllClassroomsPage(page:Int):List<ClassroomCardDTO>
}