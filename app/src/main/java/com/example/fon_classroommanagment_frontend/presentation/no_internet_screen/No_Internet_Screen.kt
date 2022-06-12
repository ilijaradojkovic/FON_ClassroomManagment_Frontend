package com.example.fon_classroommanagment_frontend

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign

@Composable
 fun No_Internet_Screen(){
Column(modifier = Modifier
    .fillMaxSize()
    .background(MaterialTheme.colorScheme.background)) {

    Row(modifier= Modifier
        .fillMaxWidth()
        .weight(2f)
       , horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
        Image(painter = painterResource(id = R.drawable.sad), contentDescription ="No internet",modifier=Modifier.fillMaxSize(0.6f))
    }
    Column(modifier= Modifier
        .fillMaxWidth()
        .weight(3f)
        , horizontalAlignment = Alignment.CenterHorizontally) {
        Text("No Internet Connection", style = MaterialTheme.typography.headlineMedium, color = MaterialTheme.colorScheme.onBackground)
        Spacer(modifier = Modifier.fillMaxHeight(0.1f))
        
        Text("You are not connected to the internet." +
                "Make sure your Wi-Fi is on,Airplane mode is off " +
                "and try again",color=MaterialTheme.colorScheme.onBackground, style = MaterialTheme.typography.bodyMedium, textAlign = TextAlign.Center)
    }
}
}