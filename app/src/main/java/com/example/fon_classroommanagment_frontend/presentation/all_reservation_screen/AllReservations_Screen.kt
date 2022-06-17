package com.example.fon_classroommanagment_frontend.screens

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieConstants
import com.example.fon_classroommanagment_frontend.CallendarPicker
import com.example.fon_classroommanagment_frontend.R
import com.example.fon_classroommanagment_frontend.presentation.common.bars.Components.Schedule
import com.example.fon_classroommanagment_frontend.presentation.all_reservation_screen.AllReservationViewModel
import com.example.fon_classroommanagment_frontend.presentation.common.bars.Components.LottieAnimation
import com.foreverrafs.datepicker.state.rememberDatePickerState
import java.time.LocalDate

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AllReservations_Screen(
    allReservationViewModel: AllReservationViewModel
) {



    val reservations =  allReservationViewModel.reservationForDay

    val state by allReservationViewModel.uiState
    val datePicked = rememberDatePickerState()

    val heightAnim = animateDpAsState(targetValue = if(state.isError) 100.dp else 0.dp)
    LaunchedEffect(key1 = true){
        allReservationViewModel.getReservationsForData(datePicked.initialDate)
    }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .animateContentSize(tween(1000))
        ) {

            Row(
                modifier = Modifier,
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CallendarPicker(datePicked){allReservationViewModel.getReservationsForData(it)}
            }



                Row(modifier = Modifier
                    .fillMaxWidth()

                    .height(heightAnim.value),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically){
                    LottieAnimation(lottieAnim = R.raw.no_wifi, iterations = LottieConstants.IterateForever)

                }


            Row(modifier = Modifier) {
                Schedule(
                    reservations
                )

            }
        }
    }
