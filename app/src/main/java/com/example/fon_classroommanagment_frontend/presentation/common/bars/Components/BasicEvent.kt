package com.example.fon_classroommanagment_frontend.presentation.common.bars.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fon_classroommanagment_frontend.data.Event
import java.time.format.DateTimeFormatter

val EventTimeFormatter = DateTimeFormatter.ofPattern("h:mm a")

@Composable
fun BasicEvent(
    event: Event,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(10.dp)
            .background(event.color, shape = MaterialTheme.shapes.medium)
            .padding(10.dp), verticalArrangement = Arrangement.Center

    ) {

        Text(
            text = "${event.start.format(EventTimeFormatter)} - ${event.end.format(EventTimeFormatter)}",
            style = MaterialTheme.typography.bodyMedium,
        )


        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically){
            Text(
                text = event.type,
                style = MaterialTheme.typography.bodyLarge,

                )

            Text(
                text = "C001",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.secondary

            )
        }
        Text(
            text = event.subject,
            style = MaterialTheme.typography.bodyMedium,

            )


    }
}