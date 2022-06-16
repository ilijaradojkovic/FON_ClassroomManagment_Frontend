package com.example.fon_classroommanagment_frontend

import android.content.pm.ActivityInfo
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.compose.FON_ClassroomManagment_FrontendTheme
import com.example.compose.LockScreenOrientation
import com.example.fon_classroommanagment_frontend.common.Screen
import com.example.fon_classroommanagment_frontend.data.remote.dto.UserRegistrationDTO
import com.example.fon_classroommanagment_frontend.presentation.common.bars.Components.Navigation
import com.example.fon_classroommanagment_frontend.presentation.common.bars.SuccessDialog
import com.example.fon_classroommanagment_frontend.presentation.signin_screen.Aditional_Info_Screen
import com.example.fon_classroommanagment_frontend.presentation.signin_screen.TypeEMP_EducationEMP_Screen
import com.example.fon_classroommanagment_frontend.screens.AllReservations_Screen
import com.example.fon_classroommanagment_frontend.screens.Appointment_Screen
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
                  //  Navigation()
                    Appointment_Screen(-1, rememberNavController())
                  // RequestContentPermission()
                    //TypeEMP_EducationEMP_Screen(UserRegistrationDTO(), navHostController = rememberNavController(
                   // ))
                    //AllReservations_Screen(rememberNavController())
                }
            }
    }

}



