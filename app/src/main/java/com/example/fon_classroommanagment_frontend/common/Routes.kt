package com.example.fon_classroommanagment_frontend.common

object Routes {

    //auth
    const val  LOGIN:String="login"
    const val  REGISTER:String="register"
    const val  LOGOUT:String="logout"

    //classroom
    private const val CLASSROOM_PREFIX="classroom"
    const val CLASSROOM_FILTER="${CLASSROOM_PREFIX}/filter"
    const val CLASSROOM_PAGING="${CLASSROOM_PREFIX}/{page}"

    const val CLASSROOM_PARTIAL_INFO="${CLASSROOM_PREFIX}/main-info"
    const val CLASSROOM_PAGING_PARTIAL_INFO="${CLASSROOM_PREFIX}/main-info/{page}"
    const val CLASSROOM_DETAILS= CLASSROOM_PREFIX
    const val CLASSROOM_SEARCH="${CLASSROOM_PREFIX}/{page}/search"

    //appointments
    private const val APPOINTMENT_PREFIX="appointment"
    const val APPOINTMENT_DELETE = APPOINTMENT_PREFIX
    const val APPOINTMENTS= APPOINTMENT_PREFIX
    const val APPOINTMENT_CONFIRM = "${APPOINTMENT_PREFIX}/confirm"
    const val APPOINTMENT_DECLINE = "${APPOINTMENT_PREFIX}/decline"
    const val APPOINTMENT_CONFIRM_ALL = "${APPOINTMENT_PREFIX}/confirm/all"
    const val APPOINTMENT_RESERVE = "${APPOINTMENT_PREFIX}/reserve"
    const val APPOINTMENT_SEARCH = "${APPOINTMENT_PREFIX}/search"
    const val APPOINTMENT_ClASSROOM="${APPOINTMENT_PREFIX}/appointments"
    const val APPOINTMENT_AVAILABILITY="${APPOINTMENT_PREFIX}/available"
    const val APPOINTMENT_DATE = "${APPOINTMENT_PREFIX}/{date}"
    const val APPOINTMENT_UPDATE = APPOINTMENT_PREFIX


    //user
    private  const val USER_PREFIX="user"
    const val USER_DETAILS= USER_PREFIX
    const val USER_APPOINTMENTS="${USER_PREFIX}/appointments"
    const val PASSWORD_RESET="${USER_PREFIX}/password/reset"
    const val EMAIL_RESET="${USER_PREFIX}/email/reset"
    const val IS_USER_ADMIN="${USER_PREFIX}/admin"
    const val USER_REQUESTED_APPOINTMENTS = "${USER_PREFIX}/appointments-requested"
    const val USER_APPOINTMENTS_PENDING = "${USER_PREFIX}/appointments/pending"
    const val EMPLOYEE_PERMISSIONS_INFO = "${USER_PREFIX}/employee/permissions"
    const val USER_ROLE_CHANGE = "${USER_PREFIX}/update-role"


    private const val COMMON_PREFIX = "/common"
    const val COMMON_ALL_EMPLOYEE_TYPES = "${COMMON_PREFIX}/employee/types"
    const val COMMON_ALL_EDUCATION_TITLES = "${COMMON_PREFIX}/education/titles"
    const val COMMON_ALL_EMPLOYEE_DEPARTMENTS = "${COMMON_PREFIX}/employee/departments"
    const val COMMON_ALL_CLASSROOM_TYPES = "${COMMON_PREFIX}/classroom/types"
    const val COMMON_ALL_APPOINTMENT_TYPES = "${COMMON_PREFIX}/appointment/types"
    const val COMMON_ALL_USER_ROLES = "${COMMON_PREFIX}/user/roles"


}