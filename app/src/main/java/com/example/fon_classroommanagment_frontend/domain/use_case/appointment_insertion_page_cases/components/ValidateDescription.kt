package com.example.fon_classroommanagment_frontend.domain.use_case.appointment_insertion_page_cases.components

import com.example.fon_classroommanagment_frontend.common.Strings.description_error
import com.example.fon_classroommanagment_frontend.common.Strings.description_error_explained_epmty
import com.example.fon_classroommanagment_frontend.common.Validation

class ValidateDescription {

    operator  fun  invoke(description:String):Validation{
        var validation:Validation=Validation.Success()
        if(description.isEmpty()){
            validation=Validation.Error(description_error, description_error_explained_epmty)

            return validation
        }
       return  validation
    }
}