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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.fon_classroommanagment_frontend.*
import com.example.fon_classroommanagment_frontend.common.Screen
import com.example.fon_classroommanagment_frontend.data.remote.dto.FilterDTO
import com.example.fon_classroommanagment_frontend.domain.model.ClassroomType
import com.example.fon_classroommanagment_frontend.presentation.all_classrooms_screen.AllClassroomsViewModel
import com.example.fon_classroommanagment_frontend.presentation.common.bars.Components.input.BottonBar
import com.example.fon_classroommanagment_frontend.presentation.common.bars.Components.input.TopBar
import com.example.fon_classroommanagment_frontend.screens.AllReservations_Screen
import com.example.fon_classroommanagment_frontend.screens.All_Classrooms
import kotlinx.coroutines.launch


@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun Main_Screen(
    navHostController: NavHostController,
    Title: String,
    allClassroomsViewModel: AllClassroomsViewModel= hiltViewModel()
    ){

    val coroutineScope = rememberCoroutineScope()
    val modalBottomSheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    var searchText by remember{ mutableStateOf("")}
    var displayTopBarElements by remember{mutableStateOf(false)}
    var filterDTO = allClassroomsViewModel.filterDto.value


    ModalBottomSheetLayout(sheetState = modalBottomSheetState,

        sheetContent =  {
         Bottom_Sheet_Content(filterDTO,{_filterDTO->
             filterDTO=_filterDTO
             allClassroomsViewModel.filter()
         }){ coroutineScope.launch{ modalBottomSheetState.hide()} }
        },

       sheetBackgroundColor = Color.Transparent,

    ) {
        Scaffold(topBar = { TopBar(displayTopBarElements,{coroutineScope.launch { if(modalBottomSheetState.targetValue==ModalBottomSheetValue.Expanded) modalBottomSheetState.hide() else modalBottomSheetState.show()}},searchText,{searchText=it}) },
            bottomBar = { BottonBar(navHostController) }) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()

                    .padding(it), horizontalAlignment = Alignment.CenterHorizontally
            ) {

                when(Title){
                    Screen.BottomBarScreens.AllClassroomsScreen.title->{
                        displayTopBarElements=false
                        All_Classrooms(navHostController,searchText)
                    }
                    Screen.BottomBarScreens.ReservationScreen.title->{
                        displayTopBarElements=true
                        AllReservations_Screen(navHostController)
                    }
                    Screen.BottomBarScreens.UserProfileScreen.title->{
                        displayTopBarElements=true
                        Profile_Screen(isAdmin = false, fullName ="Ilija Radojkovic" )
                    }
                }


            }
        }
    }
}