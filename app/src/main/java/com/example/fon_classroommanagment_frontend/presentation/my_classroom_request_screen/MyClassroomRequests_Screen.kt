package com.example.fon_classroommanagment_frontend

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.*
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieConstants
import com.example.fon_classroommanagment_frontend.common.RequestReservastion
import com.example.fon_classroommanagment_frontend.common.Screen
import com.example.fon_classroommanagment_frontend.common.UIRequestResponse
import com.example.fon_classroommanagment_frontend.presentation.common.bars.Components.LottieAnimation
import com.example.fon_classroommanagment_frontend.presentation.common.bars.Components.input.RoundIconButton
import com.example.fon_classroommanagment_frontend.presentation.common.bars.ErrorReservationDialog
import com.example.fon_classroommanagment_frontend.presentation.common.bars.SuccessReservationDialog
import com.example.fon_classroommanagment_frontend.presentation.my_classroom_request_screen.RequestViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MyClassroomRequests_Screen(
    navController: NavHostController,
    requestReservation: RequestReservastion?,
    requestViewMode: RequestViewModel

) {
val scaffoldState = rememberScaffoldState()
    val coroutine= rememberCoroutineScope()
    var appointmentLoading by remember{ mutableStateOf(false)}
    LaunchedEffect(key1 = true) {
        if (requestReservation != null) {
            requestViewMode.addRequest(requestReservation)
            coroutine.launch { scaffoldState.snackbarHostState.showSnackbar("Appointment added",)
            }
        }
    }
    val reservationState=requestViewMode.reservationState

    Scaffold(scaffoldState=scaffoldState, modifier = Modifier.fillMaxSize()) {

        Box(Modifier
          .fillMaxSize()
            ,
            Alignment.Center) {

           if( reservationState.value.isError || reservationState.value.success) {
               Dialog(onDismissRequest = { /*TODO*/ }) {
                   Box(modifier = Modifier.fillMaxSize()) {
                       if(!appointmentLoading)
                           LottieAnimation(
                               lottieAnim = R.raw.loading_appointment,
                               modifier = Modifier.scale(1.5f)
                           ) {
                               appointmentLoading = true
                           }
                       else{
                           if(reservationState.value.isError)  ErrorReservationDialog {
                               requestViewMode.restart()
                               appointmentLoading = false
                           }
                           if(reservationState.value.success)SuccessReservationDialog(){
                               requestViewMode.restart()
                               appointmentLoading = false}
                       }
                   }

               }

           }


            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.2f),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {

                    Button(
                        onClick = { requestViewMode.sendAppointments() },
                        shape = MaterialTheme.shapes.medium
                    ) {
                        Text(
                            "Complete",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onPrimary
                        )


                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    RoundIconButton(
                        R.drawable.plus,
                        24.dp
                    ) {
                        navController.navigate(route = Screen.AppointmentScreen.route) }
                }

                LazyColumn() {
                    items(requestViewMode.reservations) {

                        val dismissState = rememberDismissState()
                        LaunchedEffect(key1 = dismissState.isDismissed(DismissDirection.EndToStart)) {
                            if (dismissState.isDismissed(DismissDirection.EndToStart))
                                requestViewMode.deleteRequest(it.reserveDTO)
                        }
                        SwipeToDismiss(
                            state = dismissState,
                            background = {
                                val color = when (dismissState.dismissDirection) {

                                    DismissDirection.EndToStart -> MaterialTheme.colorScheme.errorContainer
                                    DismissDirection.StartToEnd -> Color.Transparent

                                    else -> {
                                        Color.Transparent
                                    }
                                }

                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(10.dp)
                                        .height(100.dp),
                                    colors = CardDefaults.elevatedCardColors(containerColor = color)
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .padding(0.dp, 0.dp, 15.dp, 0.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.End
                                    ) {
                                        Icon(
                                            Icons.Default.Delete,
                                            "",

                                            modifier = Modifier.size(24.dp),
                                            tint = MaterialTheme.colorScheme.onErrorContainer
                                        )
                                    }

                                }

                            }, directions = setOf(DismissDirection.EndToStart)
                        ) {
                            ClassromRequestCard(
                                it.reserveDTO.date_appointment,
                                it.reserveDTO.start_timeInHours,
                                it.reserveDTO.end_timeInHours,
                                it.reserveDTO.name,
                                it.reserveDTO.classroomName,
                                it.uiRequestResponse
                            )

                        }

                    }
                }
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .weight(5f)
//                .padding(10.dp)
//                ,
//            verticalAlignment = Alignment.Top,
//            horizontalArrangement = Arrangement.Center
//        ) {
//        }

            }
        }
    }


    }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClassromRequestCard(
    date: Date,
    startTimeinhours: Int,
    endTimeinhours: Int,
    name: String,
    classroomName: String,
    uiRequestResponse: UIRequestResponse
) {
    Card(modifier= Modifier
        .padding(10.dp)
        .height(100.dp)){

        Column(modifier = Modifier
            .fillMaxSize()
        ) {
            Row(modifier = Modifier
                .weight(1f)
                .padding(10.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                Row(modifier = Modifier.weight(1f), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center){
                    Text(text =classroomName, style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.tertiary)
                }
                Row(modifier = Modifier.weight(4f), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center){
                    Text(name,style = MaterialTheme.typography.bodyLarge , color = MaterialTheme.colorScheme.onBackground)
                }
                Row(modifier = Modifier.weight(1f),verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center){
                    if(uiRequestResponse.isLoading) LottieAnimation(lottieAnim = R.raw.loading_dialog, iterations = LottieConstants.IterateForever)
                }
            }
            Row(modifier = Modifier
                .weight(1f)
                .padding(10.dp),verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                Row(modifier = Modifier.weight(1f), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceAround){
                    Row(modifier = Modifier.fillMaxWidth(0.8f),verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceAround){
                       Box(contentAlignment = Alignment.BottomCenter){
                           Icon(painter = painterResource(id = R.drawable.callendar), contentDescription = "Calednar icon", modifier = Modifier.size(24.dp))

                           if(uiRequestResponse.isError )Icon(Icons.Filled.Error, contentDescription = "",modifier = Modifier.size(15.dp), tint = MaterialTheme.colorScheme.error)
                            else if(uiRequestResponse.success) Icon(painter = painterResource(id = R.drawable.success), contentDescription = "",modifier = Modifier.size(15.dp), tint=Color.Green)
                       }
                        Text(SimpleDateFormat("yyyy-MM-dd").format(date),style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onBackground)

                    }
             }
                Row(modifier = Modifier.weight(1f), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceAround) {
                    Row(
                        modifier = Modifier.fillMaxWidth(0.8f),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {

                            Icon(
                                painter = painterResource(id = R.drawable.clock),
                                contentDescription = "Calednar icon",
                                modifier = Modifier.size(24.dp)
                            )


                        Text(
                            "${startTimeinhours}h - ${endTimeinhours}h",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }

            }
    }
    }
}