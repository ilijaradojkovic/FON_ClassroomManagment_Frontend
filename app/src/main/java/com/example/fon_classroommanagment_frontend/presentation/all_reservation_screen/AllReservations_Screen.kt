package com.example.fon_classroommanagment_frontend.screens

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.fon_classroommanagment_frontend.CallendarPicker
import com.example.fon_classroommanagment_frontend.presentation.common.bars.Components.Schedule
import com.example.fon_classroommanagment_frontend.presentation.all_reservation_screen.AllReservationViewModel
import com.foreverrafs.datepicker.state.rememberDatePickerState
import java.time.LocalDate

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AllReservations_Screen(
    navHostController: NavHostController,
    allReservationViewModel: AllReservationViewModel
) {

    val datePickerState =
        rememberDatePickerState(initialDate = LocalDate.now())

    val reservations =  allReservationViewModel.reservationForDay



    LaunchedEffect(key1 = true ){
        allReservationViewModel.getReservationsForData(datePickerState.initialDate)
    }
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
                CallendarPicker(datePickerState)
            }

            Row(modifier = Modifier) {
                Schedule(
                    reservations
                )

            }
        }
    }
