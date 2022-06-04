package com.example.fon_classroommanagment_frontend.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import com.airbnb.lottie.LottieComposition
import com.airbnb.lottie.compose.*
import com.example.fon_classroommanagment_frontend.CallendarPicker
import com.example.fon_classroommanagment_frontend.Components.BasicEvent
import com.example.fon_classroommanagment_frontend.Components.Schedule
import com.example.fon_classroommanagment_frontend.R
import com.example.fon_classroommanagment_frontend.data.Event
import java.time.LocalDateTime

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AllReservations_Screen(){



    Column(modifier=Modifier.fillMaxSize()) {

        Row(modifier = Modifier
            .weight(1f)
         , horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically){
            CallendarPicker()
        }

        Row(modifier = Modifier.weight(3f)){
            Schedule(listOf(
                Event(
                    type = "Predavanje",
                    subject = "Programiranje 2",
                    color = Color(0xFFAFBBF2),
                    start = LocalDateTime.parse("2021-05-18T15:00:00"),
                    end = LocalDateTime.parse("2021-05-18T17:00:00"),
                ),
                Event(
                    type = "Predavanje",
                    subject = "Napredna java",
                    color = Color(0xFFAFBBF2),
                    start = LocalDateTime.parse("2021-05-18T13:00:00"),
                    end = LocalDateTime.parse("2021-05-18T15:00:00"),
                )
            ))

        }
    }
}