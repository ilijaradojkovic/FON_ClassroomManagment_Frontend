package com.example.fon_classroommanagment_frontend.presentation.common.bars.Components.input

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.fon_classroommanagment_frontend.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun ClassroomCard(navigateToDetailsScreen: () -> Unit,

) {
    Card(modifier = Modifier
        .fillMaxWidth()

        .height(150.dp)
        .clickable { navigateToDetailsScreen() }
       ) {
        Row(Modifier.fillMaxSize()){
            Row(modifier= Modifier
                .weight(1f)
                .fillMaxHeight()) {
                Image(painter = painterResource(id = R.drawable.classroomimage),
                    contentDescription = "Classroom image", contentScale = ContentScale.Crop)
            }
            Column(modifier= Modifier
                .weight(1f)
                .fillMaxHeight(0.8f)
                .padding(10.dp)) {
                Row(modifier= Modifier
                    .weight(1f)
                    .fillMaxWidth()
                   , horizontalArrangement = Arrangement.Center){
                  Text(text = "Title", style = MaterialTheme.typography.headlineMedium)
                }
                Row(modifier= Modifier
                    .weight(2f)
                    .fillMaxHeight()
                    .fillMaxWidth()){

                    Row(modifier = Modifier

                        .weight(1f).fillMaxHeight(), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically){
                        Ui(R.drawable.monitoricon)
                    }

                    Row(modifier = Modifier

                        .weight(1f).fillMaxHeight(), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically){
                        Ui(R.drawable.projector)
                    }
                    Row(modifier = Modifier

                        .weight(1f).fillMaxHeight(), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically){
                        Ui(R.drawable.people)
                    }

                }
            }
        }

    }
}
@Composable
fun Ui(icon:Int){
    Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceAround, modifier = Modifier.fillMaxHeight()) {
        Icon(painter = painterResource(id =icon), contentDescription ="MonitorIcon" , modifier = Modifier.size(24.dp))
        Canvas(modifier = Modifier ){
            drawCircle(Color.Green, radius = 20f)
        }
    }
}