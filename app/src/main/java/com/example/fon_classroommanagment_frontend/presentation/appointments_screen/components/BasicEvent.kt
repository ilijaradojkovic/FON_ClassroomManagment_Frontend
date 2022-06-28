package com.example.fon_classroommanagment_frontend.presentation.common.bars.Components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fon_classroommanagment_frontend.data.Event
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
val EventTimeFormatter = DateTimeFormatter.ofPattern("h:mm a")

@RequiresApi(Build.VERSION_CODES.O)
@Composable

fun BasicEvent(
    event: Event,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(10.dp)
            .background(MaterialTheme.colorScheme.secondary, shape = MaterialTheme.shapes.medium)
            .padding(10.dp), verticalArrangement = Arrangement.Top

    ) {

        Text(
            text = "${event.start.format(EventTimeFormatter)} - ${event.end.format(EventTimeFormatter)}",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSecondary
        )


        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically){
            Text(
                text = event.type,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSecondary

            )

            Text(
                text = event.classroomName,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primaryContainer


            )
        }



    }
}