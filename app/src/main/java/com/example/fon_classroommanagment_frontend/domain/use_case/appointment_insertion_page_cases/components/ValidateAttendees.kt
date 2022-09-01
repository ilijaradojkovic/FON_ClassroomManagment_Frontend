package com.example.fon_classroommanagment_frontend.domain.use_case.appointment_insertion_page_cases.components

import com.example.fon_classroommanagment_frontend.common.Constants
import com.example.fon_classroommanagment_frontend.common.Strings.attendees_error
import com.example.fon_classroommanagment_frontend.common.Strings.attendees_error_empty
import com.example.fon_classroommanagment_frontend.common.Strings.attendees_error_max_capacity
import com.example.fon_classroommanagment_frontend.common.Strings.attendees_error_not_valid_number
import com.example.fon_classroommanagment_frontend.common.Validation

class ValidateAttendees {
    operator  fun invoke(numAttendeesText:String):Validation{
        var validation:Validation=Validation.Success()

        if(numAttendeesText.isEmpty()) {
            validation=Validation.Error(attendees_error, attendees_error_empty)
       return  validation
        }
        if(numAttendeesText.contains(",") || numAttendeesText.contains(".")) {
            validation=Validation.Error(attendees_error, attendees_error_not_valid_number)
            return  validation
        }

        if(numAttendeesText.toInt()> Constants.MAX_CAPACITY){
            validation=Validation.Error(attendees_error, attendees_error_max_capacity)

            return validation
        }


        return validation
    }
}