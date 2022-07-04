package com.example.fon_classroommanagment_frontend.domain.use_case.appointment_insertion_page_cases.components

import com.example.fon_classroommanagment_frontend.common.Strings.classrooms_error
import com.example.fon_classroommanagment_frontend.common.Strings.classrooms_error_explained_epmty
import com.example.fon_classroommanagment_frontend.common.Validation
import com.example.fon_classroommanagment_frontend.data.remote.dto.ClassroomChipDTO

class ValidateClassrooms {
    operator  fun invoke(classrooms:List<ClassroomChipDTO>):Validation{
        var validation:Validation=Validation.Success()
        if(classrooms.isEmpty()){
            validation=Validation.Error(classrooms_error, classrooms_error_explained_epmty)

            return validation
        }
        return validation
    }
}