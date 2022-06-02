package com.example.fon_classroommanagment_frontend.Components.Text

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.fon_classroommanagment_frontend.R

@Composable
fun BottonBar(){

    BottomAppBar(modifier=Modifier.background(Color.Red)) {
        Row(modifier = Modifier.weight(1f), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(painter = painterResource(id = R.drawable.classrooms), contentDescription = "all  classrooms", modifier = Modifier.size(24.dp))
            }
        }
        Row(modifier = Modifier.weight(1f), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {

            IconButton(onClick = { /*TODO*/ }) {
                Icon(painter = painterResource(id = R.drawable.callendar), contentDescription = "all  classrooms", modifier = Modifier.size(24.dp))
            }
        }
        Row(modifier = Modifier.weight(1f), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(painter = painterResource(id = R.drawable.avatar), contentDescription = "all  classrooms", modifier = Modifier.size(24.dp))
            }
        }
    }
}