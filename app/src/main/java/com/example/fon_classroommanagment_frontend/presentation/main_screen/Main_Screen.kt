package com.example.fon_classroommanagment_frontend.presentation.main_screen.components


import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.fon_classroommanagment_frontend.*
import com.example.fon_classroommanagment_frontend.R
import com.example.fon_classroommanagment_frontend.common.Screen
import com.example.fon_classroommanagment_frontend.presentation.all_classrooms_screen.AllClassroomsViewModel
import com.example.fon_classroommanagment_frontend.presentation.all_reservation_screen.AllReservationViewModel
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
    allClassroomsViewModel: AllClassroomsViewModel= hiltViewModel(),
    allReservationViewModel: AllReservationViewModel = hiltViewModel()
    ){

    val searchText by allClassroomsViewModel.searchText
    val coroutineScope = rememberCoroutineScope()
    val modalBottomSheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
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
        Scaffold(topBar = { TopBar(displayTopBarElements,{coroutineScope.launch {
            if(modalBottomSheetState.targetValue==ModalBottomSheetValue.Expanded)
                modalBottomSheetState.hide() else modalBottomSheetState.show()}},
            {  allClassroomsViewModel.changeSearchText(it)
                allClassroomsViewModel.searchClassrooms()},searchText,{allClassroomsViewModel.changeSearchText(it)},allClassroomsViewModel.networkStateSearch.value.isLoading) },
            bottomBar = { BottonBar(navHostController) },
            floatingActionButton = {
               if(navHostController.currentDestination!!.route==Screen.BottomBarScreens.ReservationScreen.route)
                    androidx.compose.material3.FloatingActionButton(
                        onClick = {
                            navHostController.currentBackStackEntry?.arguments?.putParcelable("registerObject",null)

                            navHostController.navigate(Screen.MyClassroomRequests_Screen.route)
                        }) {
                    Icon(painter = painterResource(id = R.drawable.arrow_right), contentDescription ="" , modifier = Modifier.size(24.dp))
            }}
            ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()

                    .padding(it), horizontalAlignment = Alignment.CenterHorizontally
            ) {

                when(Title){
                    Screen.BottomBarScreens.AllClassroomsScreen.title->{
                        displayTopBarElements=false
                        All_Classrooms(navHostController,allClassroomsViewModel)
                    }
                    Screen.BottomBarScreens.ReservationScreen.title->{
                        displayTopBarElements=false
                        AllReservations_Screen(allReservationViewModel)
                    }
                    Screen.BottomBarScreens.UserProfileScreen.title->{
                        displayTopBarElements=false
                        Profile_Screen(isAdmin = false, fullName ="Ilija Radojkovic" )
                    }
                }


            }
        }
    }
}