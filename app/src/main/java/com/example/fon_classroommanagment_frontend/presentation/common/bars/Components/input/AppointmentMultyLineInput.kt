package com.example.fon_classroommanagment_frontend.presentation.common.bars.Components.input

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fon_classroommanagment_frontend.presentation.appointment_screen.components.ErrorField

@Composable
fun AppointmentMultyLineInput(
    text:String,
    ontextChange:(String)->Unit,
    nameField:String,
    errorText:String=""
){
        Column(modifier= Modifier
            .fillMaxSize()
            .padding(20.dp)
            .height(160.dp), verticalArrangement = Arrangement.SpaceBetween) {


            Text(
                nameField,
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)

                    .border(
                        3.dp,
                        MaterialTheme.colorScheme.onBackground,
                        MaterialTheme.shapes.small
                    ),
                shape = MaterialTheme.shapes.medium,
                value = text,
                onValueChange = {ontextChange(it)},
                keyboardOptions = KeyboardOptions(imeAction = androidx.compose.ui.text.input.ImeAction.Done),
                maxLines = 3,
                isError = !errorText.isEmpty(),
                textStyle = MaterialTheme.typography.bodyMedium
            )
            ErrorField(text = errorText, modifier = Modifier.fillMaxWidth())
        }
}