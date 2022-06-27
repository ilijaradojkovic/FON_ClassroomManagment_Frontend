package com.example.fon_classroommanagment_frontend.common

object Routes {

    //auth
    const val  LOGIN:String="login"
    const val  REGISTER:String="register"

    //classroom
    const val CLASSROOMS="getClassrooms"
    const val CLASSROOM_TYPES="allClassroomTypes"
    const val CLASSROOMS_DATE="GetForDateAndClassroom"
    const val CLASSROOM_CHIP="getClassroomsChip"
    const val CLASSROOMS_CHIP="getClassroomsChipAll"
    const val CLASSROOM_AVAILABILITY="IsClassroomAvailableForDate"
    const val CLASSROOM_DETAILS="classroomDetails"
    const val SEACH_CLASSROOMS="searchClassroom"

    //appointments
    const val RESERVE="reserve"
    const val APPOINTMENT_TYPES="allAppointmentTypes"
    const val APPOINTMENT_DELETE="DeleteReservation"


    //user
    const val USER_DETAILS="UserDetails"
    const val USER_APPOINTMENTS="getAppointmentsForUser"

    //employee
    const val EMPLOYEE_DEPARTMANTS="allEmployeeDepartment"
    const val EDUCATION_TITLES="allEducationTitle"
    const val EMPLOYEE_TYPES="allEmployeeTypes"


}