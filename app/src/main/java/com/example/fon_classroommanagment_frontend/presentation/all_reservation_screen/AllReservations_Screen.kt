package com.example.fon_classroommanagment_frontend.screens

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieConstants
import com.example.fon_classroommanagment_frontend.CallendarPicker
import com.example.fon_classroommanagment_frontend.R
import com.example.fon_classroommanagment_frontend.presentation.common.bars.Components.Schedule
import com.example.fon_classroommanagment_frontend.presentation.all_reservation_screen.AllReservationViewModel
import com.example.fon_classroommanagment_frontend.presentation.common.bars.Components.LottieAnimation
import com.example.fon_classroommanagment_frontend.presentation.common.bars.Components.input.ClassroomComboBox
import com.foreverrafs.datepicker.state.rememberDatePickerState

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
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

            Row(modifier = Modifier
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center  ,
                verticalAlignment = Alignment.CenterVertically) {

                ClassroomComboBox({allReservationViewModel.getAllClassrooms()},allReservationViewModel.classrooms) {
                     Log.i("cao", "selected $it")
                }
//            Row(modifier = Modifier
//                .fillMaxWidth(0.5f)
//
//                .background(Color.Red).height(50.dp) .padding(10.dp),
//                horizontalArrangement = Arrangement.SpaceBetween,
//                verticalAlignment = Alignment.CenterVertically) {
//
//                Row(modifier = Modifier.weight(4f)) {
//                    DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
//                        DropdownMenuItem(text = { Text("cao") }, onClick = { /*TODO*/ })
//                        DropdownMenuItem(text = { Text("cao1") }, onClick = { /*TODO*/ })
//
//                    }
//                }
//                Row(modifier = Modifier.background(Color.Green)) {
//                    Icon(
//                        painter = painterResource(id = R.drawable.arrow_down_dropdown),
//                        contentDescription = "",
//
//                        modifier = Modifier.size(24.dp).clickable { expanded=!expanded }
//                    )
//                }

//            }

            }
            Row(modifier = Modifier) {
                Schedule(
                    reservations
                )

            }
        }
}