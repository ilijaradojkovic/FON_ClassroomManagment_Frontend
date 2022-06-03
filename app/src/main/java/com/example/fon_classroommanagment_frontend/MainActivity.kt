package com.example.fon_classroommanagment_frontend

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*

import com.example.fon_classroommanagment_frontend.Components.input.Main_Screen
import com.example.fon_classroommanagment_frontend.ui.theme.FON_ClassroomManagment_FrontendTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FON_ClassroomManagment_FrontendTheme {



              Main_Screen()
            }
        }
    }
}



