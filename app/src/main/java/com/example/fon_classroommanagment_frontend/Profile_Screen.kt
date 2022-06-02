package com.example.fon_classroommanagment_frontend

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun Profile_Screen(){
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .weight(2f), horizontalArrangement = Arrangement.Center) {
            Image(painter = painterResource(id = R.drawable.avatar),
                contentDescription = "Profile_Image",
                modifier = Modifier
                    .clip(CircleShape)
                    .border(2.dp, Color.White, shape = CircleShape))
        }

        Column(modifier = Modifier
            .fillMaxWidth()
            .weight(1f), horizontalAlignment = Alignment.CenterHorizontally){

            Text("Adam Smith", style = MaterialTheme.typography.headlineMedium)
            Text("Professor", style = MaterialTheme.typography.bodyMedium)

        }

        Column(modifier = Modifier
            .fillMaxWidth()
            .weight(4f)
            , verticalArrangement = Arrangement.Bottom){

           Column(
               Modifier
                   .fillMaxHeight(0.35f)
                   .fillMaxWidth()
                   , verticalArrangement = Arrangement.SpaceAround){
            Row(modifier = Modifier.fillMaxWidth()){

                Icon(painter = painterResource(id = R.drawable.callendar) , contentDescription = "AppointmentsIcon", modifier = Modifier.size(24.dp))
                Spacer(modifier = Modifier.width(10.dp))
                Text("Appointments", style = MaterialTheme.typography.bodyMedium)

            }
               Divider(modifier = Modifier.fillMaxWidth().border(1.dp,MaterialTheme.colorScheme.onBackground))
            Row(modifier = Modifier.fillMaxWidth()){

                Icon(painter = painterResource(id = R.drawable.settings) , contentDescription = "SettingsIcon", modifier = Modifier.size(24.dp))
                Spacer(modifier = Modifier.width(10.dp))
                Text("Settings", style = MaterialTheme.typography.bodyMedium)

            }
               Divider(modifier = Modifier.fillMaxWidth().border(1.dp,MaterialTheme.colorScheme.onBackground))

               Row(modifier = Modifier.fillMaxWidth()){

                Icon(painter = painterResource(id = R.drawable.logout) , contentDescription = "SettingsIcon", modifier = Modifier
                    .size(24.dp)
                    .rotate(180f))
                Spacer(modifier = Modifier.width(10.dp))
                Text("Logout", style = MaterialTheme.typography.bodyMedium)

            }
            }
        }

    }
}