package com.example.fon_classroommanagment_frontend.domain.use_case.appointment_insertion_page_cases.components

import androidx.compose.ui.res.stringResource
import com.example.fon_classroommanagment_frontend.R
import com.example.fon_classroommanagment_frontend.common.Strings
import com.example.fon_classroommanagment_frontend.common.Validation

class ValidateName {

    operator  fun invoke(name:String):Validation{
        var validation:Validation=Validation.Success()
        if(name.isEmpty()) {
           validation=Validation.Error(Strings.name_error,Strings.name_error_explained_epmty)
            return validation
        }
        if(name.length>=45){
            validation=Validation.Error(Strings.name_error,Strings.name_error_explained_max_length)
            return validation
        }
        return  validation
    }
}