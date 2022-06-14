package com.example.fon_classroommanagment_frontend.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.fon_classroommanagment_frontend.common.Screen
import com.example.fon_classroommanagment_frontend.presentation.all_classrooms_screen.AllClassroomsViewModel
import com.example.fon_classroommanagment_frontend.presentation.common.bars.Components.input.ClassroomCard

@Composable
fun All_Classrooms(
    navHostController: NavHostController,
    searchText: String,
    allClassroomsViewModel: AllClassroomsViewModel,

    ) {
    val classrooms = allClassroomsViewModel.classrooms

    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        LazyColumn(modifier=Modifier, contentPadding = PaddingValues(15.dp), verticalArrangement = Arrangement.spacedBy(15.dp)){
            items(classrooms){

                ClassroomCard(it.name) { navHostController.navigate(Screen.DetailsClassroomScreen.route) }

            }

        }

    }
}