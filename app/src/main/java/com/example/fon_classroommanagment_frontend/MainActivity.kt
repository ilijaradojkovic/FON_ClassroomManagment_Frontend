package com.example.fon_classroommanagment_frontend

import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.compose.FON_ClassroomManagment_FrontendTheme
import com.example.compose.LockScreenOrientation
import com.example.fon_classroommanagment_frontend.presentation.all_reservation_screen.AllReservationViewModel
import com.example.fon_classroommanagment_frontend.presentation.common.bars.Components.Navigation
import com.example.fon_classroommanagment_frontend.presentation.common.bars.Components.cards.AppointmentProfileCard
import com.example.fon_classroommanagment_frontend.screens.AllReservations_Screen
import dagger.hilt.android.AndroidEntryPoint

//import com.example.fon_classroommanagment_frontend.ui.theme.FON_ClassroomManagment_FrontendTheme


@AndroidEntryPoint
class MainActivity :  ComponentActivity() {


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LockScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_USER_PORTRAIT)
            FON_ClassroomManagment_FrontendTheme() {
               Navigation()


            }
        }
    }
}







