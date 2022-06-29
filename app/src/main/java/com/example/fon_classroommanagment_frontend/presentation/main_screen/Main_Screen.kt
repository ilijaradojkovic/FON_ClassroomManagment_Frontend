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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.fon_classroommanagment_frontend.*
import com.example.fon_classroommanagment_frontend.R
import com.example.fon_classroommanagment_frontend.domain.navigation.Screen
import com.example.fon_classroommanagment_frontend.presentation.classrooms_screen.ClassroomsViewModel
import com.example.fon_classroommanagment_frontend.presentation.appointments_screen.AppointmentViewModel
import com.example.fon_classroommanagment_frontend.presentation.common.bars.Components.input.BottonBar
import com.example.fon_classroommanagment_frontend.presentation.common.bars.Components.input.TopBar
import com.example.fon_classroommanagment_frontend.presentation.classrooms_screen.FilterViewModel
import com.example.fon_classroommanagment_frontend.presentation.profile_screen.ProfileViewModel
import com.example.fon_classroommanagment_frontend.screens.Appointments_Screen
import com.example.fon_classroommanagment_frontend.screens.Classrooms_Screen
import kotlinx.coroutines.launch


@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun Main_Screen(
    navHostController: NavHostController,
    Title: String,
    filterViewModel: FilterViewModel = hiltViewModel(),
    allClassroomsViewModel: ClassroomsViewModel= hiltViewModel(),
    allReservationViewModel: AppointmentViewModel = hiltViewModel(),
    profileViewModel: ProfileViewModel= hiltViewModel(),

    ){

    val searchText by allClassroomsViewModel.searchText
    val coroutineScope = rememberCoroutineScope()
    val modalBottomSheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    var displayTopBarBackElement by remember{mutableStateOf(false)}
    var displayTopBarIconsElements by remember{mutableStateOf(false)}
    val filterDTO = allClassroomsViewModel.filterDto.value
    val allClassroomTypesState by allClassroomsViewModel.allClassroomsTypeState
    LaunchedEffect(key1 = allClassroomTypesState){
        if(allClassroomTypesState.success)
         filterViewModel.setAllClassrooms(allClassroomsViewModel.classroomTypes.toList())
    }
    ModalBottomSheetLayout(sheetState = modalBottomSheetState,

        sheetContent =  {
         Bottom_Sheet_Content(
             {_filterDTO->

                allClassroomsViewModel.filter(_filterDTO)
             },
             filterViewModel,
             {
                 allClassroomsViewModel.restartFilter()
                 filterViewModel.setFilterDTO(filterDTO)
         }
         ){ coroutineScope.launch{ modalBottomSheetState.hide()} }
        },

       sheetBackgroundColor = Color.Transparent,

    ) {
        Scaffold(topBar = { TopBar(
            displayTopBarIconsElements,
            {coroutineScope.launch {
                if(modalBottomSheetState.targetValue==ModalBottomSheetValue.Expanded)
                    modalBottomSheetState.hide()
                else{
                    filterViewModel.setFilterDTO(filterDTO)
                    modalBottomSheetState.show()}}
            },
            {  allClassroomsViewModel.changeSearchText(it)
                allClassroomsViewModel.searchClassrooms()},
            searchText,
            {allClassroomsViewModel.changeSearchText(it)}
            ,allClassroomsViewModel.networkStateSearch.value.isLoading,
            {},
            displayTopBarBackElement) },
            bottomBar = { BottonBar(navHostController) },
            floatingActionButton = {
               if(navHostController.currentDestination!!.route== Screen.BottomBarScreens.ReservationScreen.route)
                    androidx.compose.material3.FloatingActionButton(
                        onClick = {
                            navHostController.currentBackStackEntry?.arguments?.putParcelable("registerObject",null)

                            navHostController.navigate(Screen.MyClassroomRequestsScreen.route)
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
                        displayTopBarBackElement=false
                        displayTopBarIconsElements=true
                        Classrooms_Screen(navHostController,allClassroomsViewModel)
                    }
                    Screen.BottomBarScreens.ReservationScreen.title->{
                        displayTopBarBackElement=false
                        displayTopBarIconsElements=false
                        Appointments_Screen(allReservationViewModel)
                    }
                    Screen.BottomBarScreens.UserProfileScreen.title->{
                        displayTopBarBackElement=false
                        displayTopBarIconsElements=false
                        Profile_Screen(navHostController,profileViewModel )
                    }
                }


            }
        }
    }
}