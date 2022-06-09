package com.example.fon_classroommanagment_frontend.presentation.main_screen.components


import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.example.fon_classroommanagment_frontend.*
import com.example.fon_classroommanagment_frontend.presentation.common.bars.Components.input.BottonBar
import com.example.fon_classroommanagment_frontend.presentation.common.bars.Components.input.TopBar
import com.example.fon_classroommanagment_frontend.screens.AllReservations_Screen
import com.example.fon_classroommanagment_frontend.screens.All_Classrooms
import kotlinx.coroutines.launch


@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun Main_Screen(navHostController: NavHostController, Title: String){

    val coroutineScope = rememberCoroutineScope()
    val modalBottomSheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)

    var displayTopBarElements by remember{mutableStateOf(false)}
    ModalBottomSheetLayout(sheetState = modalBottomSheetState,

        sheetContent =  {
         Bottom_Sheet_Content()
        },

       sheetBackgroundColor = Color.Transparent,

    ) {
        Scaffold(topBar = { TopBar(displayTopBarElements,{coroutineScope.launch { if(modalBottomSheetState.targetValue==ModalBottomSheetValue.Expanded) modalBottomSheetState.hide() else modalBottomSheetState.show()}}) },
            bottomBar = { BottonBar(navHostController) }) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()

                    .padding(it), horizontalAlignment = Alignment.CenterHorizontally
            ) {

                when(Title){
                    "Classrooms"->{
                        displayTopBarElements=false
                        All_Classrooms(navHostController)
                    }
                    "Reservations"->{
                        displayTopBarElements=true
                        AllReservations_Screen(navHostController)
                    }
                    "Profile"->{
                        displayTopBarElements=true
                        Profile_Screen(isAdmin = false, fullName ="Ilija Radojkovic" )
                    }
                }


            }
        }
    }
}