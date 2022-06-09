package com.example.fon_classroommanagment_frontend

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import com.example.compose.FON_ClassroomManagment_FrontendTheme
import com.example.fon_classroommanagment_frontend.Components.Navigation
import com.example.fon_classroommanagment_frontend.viewModels.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

//import com.example.fon_classroommanagment_frontend.ui.theme.FON_ClassroomManagment_FrontendTheme


@AndroidEntryPoint
class MainActivity :  ComponentActivity() {



    val authViewModel:AuthViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            setContent {
                FON_ClassroomManagment_FrontendTheme() {
                    Navigation()
                    authViewModel.Login("cao")
                }
            }
    }
}



