package com.example.fon_classroommanagment_frontend.domain.repository

import com.example.fon_classroommanagment_frontend.data.remote.API
import com.example.fon_classroommanagment_frontend.domain.model.Classroom
import javax.inject.Inject

interface ClassroomRepository {

    suspend fun getAllClassroomsPage():List<Classroom>
}