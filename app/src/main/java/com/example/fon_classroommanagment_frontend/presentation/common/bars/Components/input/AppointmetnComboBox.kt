package com.example.fon_classroommanagment_frontend.presentation.common.bars.Components.input

import androidx.annotation.DrawableRes
import androidx.compose.material3.*
import androidx.compose.runtime.Composable

@Composable
fun AppointmetnComboBox( @DrawableRes trailingIcon: Int) {

    AppointmentInput(hint = "Select",trailingIcon=trailingIcon, enabled = false)
    DropdownMenu(expanded = false, onDismissRequest = { }) {

    }




    }
