package com.example.fon_classroommanagment_frontend

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.fon_classroommanagment_frontend.common.Screen
import com.example.fon_classroommanagment_frontend.presentation.common.bars.Components.input.RoundIconButton

@Composable
fun MyClassroomRequests_Screen(navController: NavHostController) {

    Column(modifier= Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                ,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Button(onClick = { /*TODO*/ }, shape = MaterialTheme.shapes.medium) {
                Text(
                    "Complete",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onPrimary
                )


            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                ,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            RoundIconButton(R.drawable.plus,24.dp){navController.navigate(Screen.AppointmentScreen.route)}
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(5f)
                .padding(10.dp)
                ,
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Center
        ) {
            ClassromRequestCard()
        }

    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClassromRequestCard(){
    Card(modifier=Modifier.padding(10.dp).height(100.dp)){

        Column(modifier = Modifier
            .fillMaxSize()
        ) {
            Row(modifier = Modifier.weight(1f).padding(10.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                Row(modifier = Modifier.weight(1f), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center){
                    Text(text = "C001", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.tertiary)
                }
                Row(modifier = Modifier.weight(4f), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center){
                    Text("Predavanje iz programiranja 2",style = MaterialTheme.typography.bodyLarge , color = MaterialTheme.colorScheme.onBackground)
                }
            }
            Row(modifier = Modifier.weight(1f).padding(10.dp),verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                Row(modifier = Modifier.weight(1f), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceAround){
                    Row(modifier = Modifier.fillMaxWidth(0.8f),verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceAround){
                        Icon(painter = painterResource(id = R.drawable.callendar), contentDescription = "Calednar icon", modifier = Modifier.size(24.dp))
                        Text("2020.2.2",style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onBackground)

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
                            "14h-16h",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }

            }
    }
    }
}