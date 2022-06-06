package com.example.fon_classroommanagment_frontend.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.fon_classroommanagment_frontend.Components.Screen
import com.example.fon_classroommanagment_frontend.Components.input.ClassroomCard

@Composable
fun All_Classrooms(navHostController: NavHostController) {

    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        LazyColumn(modifier=Modifier, contentPadding = PaddingValues(15.dp), verticalArrangement = Arrangement.spacedBy(15.dp)){
            items(count = 10){

                ClassroomCard { navHostController.navigate(Screen.DetailsClassroomScreen.route) }

            }

        }

    }
}