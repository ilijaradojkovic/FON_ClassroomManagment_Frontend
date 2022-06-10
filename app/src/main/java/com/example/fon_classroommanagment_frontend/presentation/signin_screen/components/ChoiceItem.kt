package com.example.fon_classroommanagment_frontend.presentation.signin_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChoiseItem(text: String, selectedItem: Boolean, onChangeState: () -> Unit) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .border(1.dp, MaterialTheme.colorScheme.outline)
        , horizontalArrangement = Arrangement.SpaceAround, verticalAlignment = Alignment.CenterVertically){
        Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center){
            Text(text, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onBackground)

        }
        Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {

            RadioButton(selected = selectedItem, onClick = { onChangeState() })
        }
    }
}