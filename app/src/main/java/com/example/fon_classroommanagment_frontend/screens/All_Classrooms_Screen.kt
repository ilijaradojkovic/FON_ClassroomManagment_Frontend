package com.example.fon_classroommanagment_frontend.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.fon_classroommanagment_frontend.Components.input.ClassroomCard

@Composable
fun All_Classrooms(){

    Column(modifier = Modifier.fillMaxWidth(0.9f), horizontalAlignment = Alignment.CenterHorizontally) {
        LazyColumn(modifier=Modifier.background(Color.White).background(Color.Red)){
            items(count = 10){
                if(it==0)  Spacer(modifier = Modifier.height(15.dp))
                ClassroomCard()
                Spacer(modifier = Modifier.height(15.dp))
            }

        }

    }
}