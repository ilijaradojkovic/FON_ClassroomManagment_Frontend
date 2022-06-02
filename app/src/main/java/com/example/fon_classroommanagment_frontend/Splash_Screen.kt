package com.example.fon_classroommanagment_frontend

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource

@Composable
fun Splash_Screen(){

    Column(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background), horizontalAlignment = Alignment.CenterHorizontally) {
        Image(painter = painterResource(id = R.drawable.fonsplash), contentDescription ="Spalsh image", modifier = Modifier.fillMaxSize(0.6f) )
    }
}