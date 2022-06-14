package com.example.fon_classroommanagment_frontend

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.fon_classroommanagment_frontend.common.Screen

@Composable
 fun No_Internet_Screen(navigateTo:()->Unit){
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
        Text("No Internet Connection", style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold), color = MaterialTheme.colorScheme.onBackground)
        Spacer(modifier = Modifier.fillMaxHeight(0.1f))
        
        Text("You are not connected to the internet." +
                "Make sure your Wi-Fi is on,Airplane mode is off " +
                "and try again",color=MaterialTheme.colorScheme.onBackground, style = MaterialTheme.typography.bodyLarge, textAlign = TextAlign.Center)
    }
    Column(modifier= Modifier
        .fillMaxWidth()
        .weight(1f)
        , horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick = {navigateTo()},modifier= Modifier
            .fillMaxWidth(0.7f)
            .height(50.dp)) {
            Text("Try Again", style = MaterialTheme.typography.bodyLarge)
        }
  }
}
}