package com.example.fon_classroommanagment_frontend.domain.use_case.appointment_insertion_page_cases.components

import com.example.fon_classroommanagment_frontend.common.Constants
import com.example.fon_classroommanagment_frontend.common.Strings.start_time_error
import com.example.fon_classroommanagment_frontend.common.Strings.start_time_error_explained_epmty
import com.example.fon_classroommanagment_frontend.common.Strings.start_time_error_explained_not_int
import com.example.fon_classroommanagment_frontend.common.Strings.start_time_error_explained_not_working_at
import com.example.fon_classroommanagment_frontend.common.Strings.start_time_error_explained_num_digits
import com.example.fon_classroommanagment_frontend.common.Validation

class ValidateStartTime {
    operator  fun invoke(startTime:String):Validation{
        var validation:Validation=Validation.Success()
        if(startTime.isEmpty()){
           validation=Validation.Error(start_time_error, start_time_error_explained_epmty)
            return validation
        }
        if(startTime.toIntOrNull()==null){

            validation=Validation.Error(start_time_error, start_time_error_explained_not_int)
            return validation
        }
        if(startTime.length>=3){

            validation=Validation.Error(start_time_error, start_time_error_explained_num_digits)
            return validation
        }
        if(startTime.toInt()> Constants.MAX_WORK_TIME || startTime.toInt()< Constants.MIN_WORK_TIME){
            validation=Validation.Error(start_time_error, start_time_error_explained_not_working_at)
            return validation
        }
    return  validation
    }
}