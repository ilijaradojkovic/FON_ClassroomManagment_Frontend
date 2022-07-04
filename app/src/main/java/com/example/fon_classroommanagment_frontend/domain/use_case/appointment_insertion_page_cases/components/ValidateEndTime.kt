package com.example.fon_classroommanagment_frontend.domain.use_case.appointment_insertion_page_cases.components

import com.example.fon_classroommanagment_frontend.common.Constants
import com.example.fon_classroommanagment_frontend.common.Strings
import com.example.fon_classroommanagment_frontend.common.Strings.end_time_error
import com.example.fon_classroommanagment_frontend.common.Strings.end_time_error_explained_after_start_time
import com.example.fon_classroommanagment_frontend.common.Strings.end_time_error_explained_epmty
import com.example.fon_classroommanagment_frontend.common.Strings.end_time_error_explained_not_int
import com.example.fon_classroommanagment_frontend.common.Strings.end_time_error_explained_not_working_at
import com.example.fon_classroommanagment_frontend.common.Strings.end_time_error_explained_num_digits
import com.example.fon_classroommanagment_frontend.common.Validation

class ValidateEndTime {
    operator  fun invoke(endTime:String,startTime:String):Validation{
        var validation:Validation=Validation.Success()
        if(endTime.isEmpty()){
            validation=Validation.Error(end_time_error, end_time_error_explained_epmty)


            return validation
        }
        //korisnik moze da unese zarez
        if(endTime.toIntOrNull()==null){
            validation=Validation.Error(end_time_error, end_time_error_explained_not_int)
            return validation
        }
        if(endTime.length>=3){
            validation=Validation.Error(end_time_error, end_time_error_explained_num_digits)
            return validation


        }
        if(endTime.toInt()> Constants.MAX_WORK_TIME || endTime.toInt()< Constants.MIN_WORK_TIME){
            validation=Validation.Error(end_time_error, end_time_error_explained_not_working_at+endTime)
            return validation

        }
        if( startTime.isNotEmpty() && endTime.toInt()<startTime.toInt()){
            validation=Validation.Error(end_time_error, end_time_error_explained_after_start_time+endTime)
            return validation


        }
        return validation
    }
}