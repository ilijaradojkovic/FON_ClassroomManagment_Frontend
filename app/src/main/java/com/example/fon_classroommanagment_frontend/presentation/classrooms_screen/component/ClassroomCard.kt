package com.example.fon_classroommanagment_frontend.presentation.common.bars.Components.input

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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

fun ClassroomCard(
    name: String,
    isRC:Boolean,
    isProjector:Boolean,
    numOfPeopleCapacity:Int,
    navigateToDetailsScreen: () -> Unit,

    ) {

    Card(modifier = Modifier
        .fillMaxWidth()

        .height(150.dp)
        .clickable { navigateToDetailsScreen() }, colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant), elevation = CardDefaults.elevatedCardElevation(15.dp)
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
                  Text(text = name, style = MaterialTheme.typography.headlineMedium)
                }
                Row(modifier= Modifier
                    .weight(2f)
                    .fillMaxHeight()
                    .fillMaxWidth()){

                    Row(modifier = Modifier

                        .weight(1f)
                        .fillMaxHeight(), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically){
                        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceAround, modifier = Modifier.fillMaxHeight()) {
                            Icon(painter = painterResource(id =R.drawable.monitoricon), contentDescription ="MonitorIcon" , modifier = Modifier.size(24.dp))
                            Canvas(modifier = Modifier ){
                                if(isRC)
                                    drawCircle(Color.Green, radius = 20f)
                                else     drawCircle(Color.Red, radius = 20f)

                            }
                        }
                    }

                    Row(modifier = Modifier

                        .weight(1f)
                        .fillMaxHeight(), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically){
                        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceAround, modifier = Modifier.fillMaxHeight()) {
                            Icon(painter = painterResource(id =R.drawable.projector), contentDescription ="MonitorIcon" , modifier = Modifier.size(24.dp))
                            Canvas(modifier = Modifier ){
                                if(isProjector)
                                    drawCircle(Color.Green, radius = 20f)
                                else  drawCircle(Color.Red, radius = 20f)
                            }
                        }
                    }
                    Row(modifier = Modifier

                        .weight(1f)
                        .fillMaxHeight(), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically){
                        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceAround, modifier = Modifier.fillMaxHeight()) {
                            Icon(painter = painterResource(id =R.drawable.people), contentDescription ="MonitorIcon" , modifier = Modifier.size(24.dp))
                            Text(text = numOfPeopleCapacity.toString(), style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurface)
                        }
                    }

                }
            }
        }

    }
}
