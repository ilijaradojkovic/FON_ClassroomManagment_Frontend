package com.example.fon_classroommanagment_frontend.domain.use_case.classrooms_page_cases

import com.example.fon_classroommanagment_frontend.domain.use_case.classrooms_page_cases.components.GetAllClassroomSearchedUseCase
import com.example.fon_classroommanagment_frontend.domain.use_case.classrooms_page_cases.components.GetAllClassroomTypesUserCase
import com.example.fon_classroommanagment_frontend.domain.use_case.classrooms_page_cases.components.GetClassroomsUseCase
import javax.inject.Inject

class ClassroomsUseCase @Inject constructor(
    val getClassroomsUseCase: GetClassroomsUseCase,
    val getAllClassroomSearched: GetAllClassroomSearchedUseCase,
    val getAllClassroomTypesUserCase: GetAllClassroomTypesUserCase
) {



}