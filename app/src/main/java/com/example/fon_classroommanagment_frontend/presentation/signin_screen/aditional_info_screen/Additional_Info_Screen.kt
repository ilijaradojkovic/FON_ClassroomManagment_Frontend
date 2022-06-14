package com.example.fon_classroommanagment_frontend.presentation.signin_screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieConstants
import com.example.fon_classroommanagment_frontend.No_Internet_Screen
import com.example.fon_classroommanagment_frontend.R
import com.example.fon_classroommanagment_frontend.common.Screen
import com.example.fon_classroommanagment_frontend.data.remote.dto.UserRegistrationDTO
import com.example.fon_classroommanagment_frontend.presentation.common.bars.Components.LottieAnimation
import com.example.fon_classroommanagment_frontend.presentation.common.bars.Components.buttons.ButtonWithIcon
import com.example.fon_classroommanagment_frontend.presentation.signin_screen.aditional_info_screen.AditionalInfoViewModel
import com.example.fon_classroommanagment_frontend.presentation.signin_screen.components.ChoiseItem

@Composable
fun  Aditional_Info_Screen(

    navController: NavHostController,
    registerObject: UserRegistrationDTO,
    aditionalInfoViewModel: AditionalInfoViewModel = hiltViewModel()
) {

    val registerState by aditionalInfoViewModel.registerState
    val selectedValue = remember { mutableStateOf(1) }
    val isSelectedItem: (Int) -> Boolean = { selectedValue.value == it }
    val onChangeState: (Int) -> Unit = { selectedValue.value = it }
    val departments = aditionalInfoViewModel.departments
    LaunchedEffect(true){
        aditionalInfoViewModel.registerObject=registerObject
        aditionalInfoViewModel.restart()
   }





    Column(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)
        .padding(10.dp), ) {
        if(registerState.isLoading)   Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Box(modifier = Modifier.fillMaxWidth(0.5f)){      LottieAnimation(lottieAnim = R.raw.loading_dialog, iterations =  LottieConstants.IterateForever)}

        }     else if(registerState.success) {
            Text(
                "Department",
                style = MaterialTheme.typography.displaySmall,
                color = MaterialTheme.colorScheme.onBackground,
            )
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier
                    .weight(8f)

            ) {
                itemsIndexed(departments) { index, item ->
                    ChoiseItem(item.name, isSelectedItem(item.id)) { onChangeState(item.id) }
                }


            }
        }
        else{
            No_Internet_Screen(){navController.navigate(Screen.RegisterScreen.route)}
        }
        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp), contentAlignment = Alignment.Center){
            ButtonWithIcon(text = "Advance", icon =R.drawable.advance ) {
                    aditionalInfoViewModel.registerObject.department =departments[selectedValue.value-1]

                    aditionalInfoViewModel.registerObject.let {
                        navController.currentBackStackEntry?.arguments?.putParcelable("registerObject", it)
                        navController.navigate(route = Screen.TypeEMPEducationEMPScreen.route)
                    }
                }
            }
        }

    }










