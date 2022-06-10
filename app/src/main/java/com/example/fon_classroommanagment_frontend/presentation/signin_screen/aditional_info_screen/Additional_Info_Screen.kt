package com.example.fon_classroommanagment_frontend.presentation.signin_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.fon_classroommanagment_frontend.R
import com.example.fon_classroommanagment_frontend.common.Screen
import com.example.fon_classroommanagment_frontend.presentation.common.bars.Components.buttons.ButtonWithIcon
import com.example.fon_classroommanagment_frontend.presentation.signin_screen.aditional_info_screen.AditionalInfoViewModel
import com.example.fon_classroommanagment_frontend.presentation.signin_screen.components.ChoiseItem

@Composable
fun  Aditional_Info_Screen(

    navController: NavHostController, registerObject: Int?,
    aditionalInfoViewModel: AditionalInfoViewModel = hiltViewModel()
) {

    val selectedValue = remember { mutableStateOf(-1) }

    val isSelectedItem: (Int) -> Boolean = { selectedValue.value == it }
    val onChangeState: (Int) -> Unit = { selectedValue.value = it }

    val items = aditionalInfoViewModel.departments
    LaunchedEffect( true  ){
        aditionalInfoViewModel.getAllData()
    }


    Column(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)
        .padding(10.dp)) {
        LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier
            .weight(8f)

            ){
            itemsIndexed(items){index,item->
                ChoiseItem(item.name, isSelectedItem(item.id)) { onChangeState(item.id) }
            }


        }
        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp), contentAlignment = Alignment.Center){
            ButtonWithIcon(text = "Advance", icon =R.drawable.advance ) {
                navController.navigate(route = Screen.TypeEMPEducationEMPScreen.route)
            }
        }

    }






}

