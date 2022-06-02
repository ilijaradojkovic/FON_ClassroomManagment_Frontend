package com.example.fon_classroommanagment_frontend.Components.Text

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.fon_classroommanagment_frontend.Profile_Screen


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Main_Screen(){
   Scaffold(topBar = { TopBar()}, bottomBar = { BottonBar()}) {
        Column(modifier = Modifier
            .fillMaxWidth()

            .padding(it)){
            Profile_Screen()
            //Main_Screen()
            //Text("c")
//            LazyColumn(){
//                items(count = 10){
//                    ClassroomCard()
//                    Spacer(modifier = Modifier.height(15.dp))
//                }
//            }

        }
   }
}