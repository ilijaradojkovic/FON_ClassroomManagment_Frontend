package com.example.fon_classroommanagment_frontend

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import com.example.compose.FON_ClassroomManagment_FrontendTheme
import com.example.fon_classroommanagment_frontend.presentation.common.bars.Components.Navigation
import dagger.hilt.android.AndroidEntryPoint

//import com.example.fon_classroommanagment_frontend.ui.theme.FON_ClassroomManagment_FrontendTheme


@AndroidEntryPoint
class MainActivity :  ComponentActivity() {





    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            setContent {
                FON_ClassroomManagment_FrontendTheme() {
                    Navigation()

                }
            }
    }
}



