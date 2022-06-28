package com.example.fon_classroommanagment_frontend.presentation.appointment_insetion_screen.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.fon_classroommanagment_frontend.R

@Composable
fun  InformationChip(title: String, onDelete: () -> Boolean) {
    Surface(
        modifier = Modifier
            .padding(end = 16.dp, bottom = 16.dp)
            .border(1.dp, MaterialTheme.colorScheme.outline, MaterialTheme.shapes.medium),
        shadowElevation = 3.dp,
        shape = MaterialTheme.shapes.medium,
        color =
           MaterialTheme.colorScheme.surface

        ,

        ) {
        Row(modifier = Modifier
            .height(32.dp)
          , verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
            Text(
                text = title,
                style = MaterialTheme.typography.labelLarge,
                color =  MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(12.dp,0.dp,0.dp,0.dp)
            )
            Box(modifier = Modifier.padding(8.dp,8.dp)){
            Icon(painter = painterResource(id = R.drawable.close),
                contentDescription ="",
                modifier = Modifier.size(15.dp).clickable{onDelete()},
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
                }
        }
    }
}