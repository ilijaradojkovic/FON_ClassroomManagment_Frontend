package com.example.fon_classroommanagment_frontend

import android.media.Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminRequestCard(){

    Card() {
       Column(modifier = Modifier.fillMaxWidth()) {
           Box(modifier = Modifier
               .fillMaxWidth()
               .weight(3f),  ){
               Image(
                   painter = painterResource(R.drawable.classroomimage),
                   contentDescription = "avatar",
                   contentScale = ContentScale.Crop,            // crop the image if it's not a square
                   modifier = Modifier
                       .align(Alignment.Center)
                       .drawBehind {
                           drawCircle(
                               Color.Yellow,
                               radius = 40f,
                               center = Offset(2 * this.center.x, this.center.y / 2f)
                           )

                       }
                       .size(100.dp)
                       .clip(CircleShape)
                       // clip to the circle shape
                       .border(2.dp, MaterialTheme.colorScheme.tertiary, CircleShape)
                       // add a border (optional)
               )
               Text("2", style = MaterialTheme.typography.bodyMedium, modifier = Modifier .align(Alignment.Center).absoluteOffset(50.dp,-27.dp))
           }
           Row(modifier = Modifier
               .fillMaxWidth()
               .weight(1f), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.Top){
               Text("Ilija Radojkovic", style = MaterialTheme.typography.bodyMedium,color=MaterialTheme.colorScheme.onBackground)
           }
       }
    }

}