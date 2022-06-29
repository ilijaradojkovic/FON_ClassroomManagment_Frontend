package com.example.fon_classroommanagment_frontend.presentation.common.bars.Components

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.fon_classroommanagment_frontend.CallendarPicker
import com.example.fon_classroommanagment_frontend.No_Internet_Screen
import com.example.fon_classroommanagment_frontend.R
import com.example.fon_classroommanagment_frontend.domain.navigation.Screen
import com.example.fon_classroommanagment_frontend.presentation.appointments_screen.components.Schedule
import com.example.fon_classroommanagment_frontend.presentation.details_classroom_screen.DetailsClassromViewModel
import com.foreverrafs.datepicker.state.rememberDatePickerState
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.MainAxisAlignment
import kotlin.random.Random

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Details_Classroom_Screen(
    navController: NavHostController,
    classroomId:Long,
    detailsClassromViewModel: DetailsClassromViewModel = hiltViewModel()
) {

    val uiResponse = detailsClassromViewModel.uiResponseClassroomInfo
    val detailsClassroom by detailsClassromViewModel.classroom

    val datePickerState= rememberDatePickerState()


    LaunchedEffect(key1 = true){
        detailsClassromViewModel.classroomId.value=classroomId

        detailsClassromViewModel.getClassroom(classroomId)
        detailsClassromViewModel.getAppointmentsForClassroom(datePickerState.initialDate)
    }

    if(uiResponse.value.isError){
        No_Internet_Screen {
                navController.navigate(Screen.MainScreen.route)
        }
    }
    else
    Scaffold(floatingActionButton = { FloatingActionButton(onClick = {
        navController.navigate(Screen.AppointmentScreen.route+"?classroomId=$classroomId&name=${detailsClassroom.name}")
    }) {
        Icon(painter = painterResource(id = R.drawable.reserve), contentDescription = "Icon FAB", modifier = Modifier.size(24.dp))
    }}) {


        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())

                .background(MaterialTheme.colorScheme.background),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    detailsClassroom.name,
                    style = MaterialTheme.typography.displayMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    detailsClassroom.type.name,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.surface
                )
            }
            Row(
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
                    .padding(15.dp, 0.dp)
            ) {
                Statistics()
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                FlowRow(
                    modifier = Modifier
                        .fillMaxWidth(0.9f), mainAxisAlignment = MainAxisAlignment.Center
                ) {

                    CategoryChip_Outlined(detailsClassroom.type.name)
                    if(detailsClassroom.projector)
                        CategoryChip_Outlined("Projektor")

                    CategoryChip_Outlined(detailsClassroom.povrsina.toString()+" m2")
                    CategoryChip_Outlined(detailsClassroom.sprat.toString() + ".Sprat")
                    CategoryChip_Outlined(detailsClassroom.br_tabli.toString() +" ${if(detailsClassroom.br_tabli==1) "tabla" else "table"}")
                }
            }

            Row(
                modifier = Modifier,
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CallendarPicker(datePickerState){
                    detailsClassromViewModel.getAppointmentsForClassroom(it)
                }
            }
            Column() {
                Schedule(
                    detailsClassromViewModel.appointmentsForClassroom
                )

            }

        }
    }
}

@Composable
fun Statistics(){
   val  months    = listOf("Jan","Feb","Mar","Apr","Maj","Jun","Jul","Avg","Sep","Okt","Nov","Dec")
   Row(modifier = Modifier
       .fillMaxSize()
       .clip(MaterialTheme.shapes.medium)
       .background(MaterialTheme.colorScheme.surface)
       .padding(5.dp, 5.dp), verticalAlignment=Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
        for ( mon in months){
            Row(modifier = Modifier

                .weight(1f)
                , horizontalArrangement = Arrangement.Center) {

                Statistics_Bar(Random.nextFloat(),mon)
            }
        }




    }

}

@Composable
fun Statistics_Bar(percent:Float,month:String){
Column(modifier = Modifier
    .fillMaxHeight()
 , horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Bottom) {
    Column(modifier = Modifier
        .background(MaterialTheme.colorScheme.onBackground)
        .fillMaxHeight(percent)
       ) {
        Text("b", color = Color.Transparent )
    }
    Column(modifier = Modifier) {
        Text(text =month, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onBackground)
    }
}
}

@Composable
fun CategoryChip_Outlined(
    category: String,



) {
    Surface(
        modifier = Modifier.padding(end = 8.dp, bottom = 8.dp),
       // shadowElevation = 8.d,
        shape = RoundedCornerShape(16.dp), color = Color.Transparent,
        border = BorderStroke(1.dp,MaterialTheme.colorScheme.onBackground)

    ) {
        Row()
            {
            Text(
                text = category,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}