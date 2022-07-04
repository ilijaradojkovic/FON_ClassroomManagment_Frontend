package com.example.fon_classroommanagment_frontend.domain.use_case.appointment_insertion_page_cases.components

import com.example.fon_classroommanagment_frontend.common.Strings.appointment_type_error
import com.example.fon_classroommanagment_frontend.common.Strings.appointment_type_error_explained_epmty
import com.example.fon_classroommanagment_frontend.common.Validation

class ValidateAppointmentType {
    operator fun invoke(classroomType:Int):Validation{
        var validation:Validation=Validation.Success()
        if(classroomType==-1){
            validation=Validation.Error(appointment_type_error,
                appointment_type_error_explained_epmty)

            return validation
        }
        return  validation
    }
}