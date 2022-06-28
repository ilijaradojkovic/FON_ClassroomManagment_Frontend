package com.example.fon_classroommanagment_frontend

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fon_classroommanagment_frontend.data.remote.dto.AppointmentRequestedUserDTO
import com.example.fon_classroommanagment_frontend.data.remote.dto.RequestedAppointmentsDTO
import java.text.SimpleDateFormat

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ApproveAppointmentCard(request: AppointmentRequestedUserDTO,onAcceptClicked:()->Unit,onDeclinedClicked:()->Unit) {
        Card(shape = RoundedCornerShape(32.dp,32.dp,0.dp,0.dp), modifier = Modifier){
                Column(modifier = Modifier
                    .fillMaxSize()
                  ) {
                    Row(modifier = Modifier.weight(1f).padding(10.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                            Row(modifier = Modifier.weight(1f), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center){
                                Text(text = request.classroomName, style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.tertiary)
                            }
                            Row(modifier = Modifier.weight(2f), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center){
                                Text(request.title,style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onBackground)
                            }
                    }
                    Row(modifier = Modifier.weight(1f).padding(10.dp),verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                        Row(modifier = Modifier.weight(1f), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceAround){
                           Icon(painter = painterResource(id = R.drawable.callendar), contentDescription = "Calednar icon", modifier = Modifier.size(24.dp))
                            Text(SimpleDateFormat("yyyy-MM-dd").format(request.date!!),style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.onBackground)
                        }
                        Row(modifier = Modifier.weight(1f),verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceAround){
                            Icon(painter = painterResource(id = R.drawable.clock), contentDescription = "Clock icon", modifier = Modifier.size(24.dp))

                            Text(
                                (if(request.startTime>9) "${request.startTime}h" else "0${request.startTime}h") +"-"+ if(request.endTime>9) "${request.endTime}h" else "0${request.endTime}h"

                            ,style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.onBackground)
                        }

                    }
                    Row(modifier = Modifier.weight(1f), verticalAlignment = Alignment.Bottom) {
                        Row(modifier = Modifier.weight(1f)){

                            TextButton(onClick = { onDeclinedClicked()}, modifier =Modifier.fillMaxWidth().fillMaxHeight().background(Brush.verticalGradient(listOf(Color.Transparent,Color.Red))) ) {
                                Text("Decline", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onBackground)
                            }
                        }
                        Row(modifier = Modifier.weight(1f)){
                            //GradientButton("Accept",Brush.linearGradient(listOf(Color.Gray,Color.Transparent)),Modifier.fillMaxWidth().fillMaxHeight(),{})
                            TextButton(onClick = {onAcceptClicked() }, modifier =Modifier.fillMaxWidth().fillMaxHeight().background(Brush.verticalGradient(listOf(Color.Transparent,Color.Green))) ) {
                                Text("Accept", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onBackground)
                            }
                        }

                    }
                }
        }
}
@Composable
fun GradientButton(
    text: String,
    gradient : Brush,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = { },
) {
    TextButton(
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
        contentPadding = PaddingValues(),
        onClick = { onClick() },
    ) {
        Box(
            modifier = Modifier
                .background(gradient)
                .then(modifier),
            contentAlignment = Alignment.Center,
        ) {
            Text(text = text)
        }
    }
}