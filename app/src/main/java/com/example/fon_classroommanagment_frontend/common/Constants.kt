package com.example.fon_classroommanagment_frontend.common

object Constants {

    const val ADMIN_ROLE_ID: String="ADMIN"
    const val  EMAIL_REGEX="(?:[a-z0-9!#\$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#\$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])"
    const val URL="http://192.168.1.110:8000/"

    var VALIDATION_TOKEN_KEY="VALIDATION_TOKEN"
    var REFRESH_TOKEN_KEY="REFRESH_TOKEN"
    var EMAIL_KEY="EMAIL_USER"
    var ROLE_KEY="ROLE_USER"

    const val MIN_CAPACITY=0
    const val MAX_CAPACITY=200
    const val MAX_WORK_TIME=20
    const val MIN_WORK_TIME=8


    const val APPROVED_KEY=1L
    const val PENDING_KEY=3L
    const val DECLINED_KEY=2L



}