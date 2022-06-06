package com.example.fon_classroommanagment_frontend.screens

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.airbnb.lottie.LottieComposition
import com.airbnb.lottie.compose.*
import com.example.fon_classroommanagment_frontend.CallendarPicker
import com.example.fon_classroommanagment_frontend.Components.BasicEvent
import com.example.fon_classroommanagment_frontend.Components.Schedule
import com.example.fon_classroommanagment_frontend.Components.Screen
import com.example.fon_classroommanagment_frontend.R
import com.example.fon_classroommanagment_frontend.data.Event
import java.time.LocalDateTime

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AllReservations_Screen(navHostController: NavHostController) {

    Scaffold(floatingActionButton = { FloatingActionButton(onClick = { navHostController.navigate(Screen.AppointmentScreen.route+"/-1")}) {
        Icon(painter = painterResource(id = R.drawable.reserve), contentDescription = "Icon FAB", modifier = Modifier.size(24.dp))
    }
    }) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {

            Row(
                modifier = Modifier,
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CallendarPicker()
            }

            Row(modifier = Modifier) {
                Schedule(
                    listOf(
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
                    )
                )

            }
        }
    }
}