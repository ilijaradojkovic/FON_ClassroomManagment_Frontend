package com.example.fon_classroommanagment_frontend.domain.use_case.appointment_insertion_page_cases.components

import com.example.fon_classroommanagment_frontend.common.Strings.reason_error
import com.example.fon_classroommanagment_frontend.common.Strings.reason_error_explained_epmty
import com.example.fon_classroommanagment_frontend.common.Strings.reason_error_explained_max_length
import com.example.fon_classroommanagment_frontend.common.Validation

class ValidateReason {

    operator fun invoke(reason:String):Validation{
        var validation:Validation=Validation.Success()
        if(reason.isEmpty()) {
            validation=Validation.Error(reason_error, reason_error_explained_epmty)

            return validation

        }
        if(reason.length>=45){
            validation=Validation.Error(reason_error, reason_error_explained_max_length)

            return validation
        }
        return validation

    }
}