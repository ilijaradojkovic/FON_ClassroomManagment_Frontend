package com.example.fon_classroommanagment_frontend.Components.input

import android.util.Size
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius.Companion.Zero
import androidx.compose.ui.geometry.Rect.Companion.Zero
import androidx.compose.ui.geometry.Size.Companion.Zero
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize

@Composable
fun AppointmetnComboBox(trailingIcon: Int) {

    AppointmentInput(hint = "Select",trailingIcon=trailingIcon, enabled = false)
    DropdownMenu(expanded = false, onDismissRequest = { }) {

    }




    }
