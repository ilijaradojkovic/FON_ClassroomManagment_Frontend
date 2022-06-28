package com.example.fon_classroommanagment_frontend.presentation.appointment_insetion_screen.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ErrorField(text:String,modifier: Modifier=Modifier){
    Text(text, modifier = modifier, style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.error)

}