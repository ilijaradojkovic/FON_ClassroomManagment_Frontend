package com.example.fon_classroommanagment_frontend.presentation.common.bars.Components.cards

import android.util.Log
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.DismissDirection
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.rememberDismissState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fon_classroommanagment_frontend.common.Constants
import com.example.fon_classroommanagment_frontend.data.remote.dto.AppointmentsForUserDTO
import com.example.fon_classroommanagment_frontend.domain.model.AppointmentStatus
import java.text.SimpleDateFormat

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun AppointmentProfileCard(
    state:AppointmentStatus,
    appointmentsForUserDTO: AppointmentsForUserDTO,


    ) {

        Card(modifier = Modifier.fillMaxWidth()) {


            Row(modifier = Modifier.height(100.dp)) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(0.1f)
                        .height(100.dp)
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = listOf(
                                    state.color,
                                    MaterialTheme.colorScheme.surfaceVariant
                                )
                            )
                        )
                ) {

                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .height(100.dp), verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(1f),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Column(
                            modifier = Modifier.fillMaxHeight(0.6f),
                            verticalArrangement = Arrangement.SpaceAround,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                if(appointmentsForUserDTO.start_timeInHours<10) "0${appointmentsForUserDTO.start_timeInHours}:00" else "${appointmentsForUserDTO.start_timeInHours}:00",
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Text(
                                if(appointmentsForUserDTO.end_timeInHours<10) "0${appointmentsForUserDTO.end_timeInHours}:00" else "${appointmentsForUserDTO.end_timeInHours}:00",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.alpha(0.5f)

                            )
                        }
                    }


                    Column(
                        modifier = Modifier
                            .weight(2f)
                            .fillMaxHeight()
                            .padding(10.dp),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.Start,

                        ) {
                        Text(
                            appointmentsForUserDTO.appointmentName,
                            style = MaterialTheme.typography.headlineSmall.copy(fontSize = 20.sp),
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            SimpleDateFormat("yyyy-MM-dd").format(appointmentsForUserDTO.date),
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,

                            )
                        Text(
                            appointmentsForUserDTO.classroomName,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.alpha(0.5f)
                        )

                    }
                }
            }
        }

    }

