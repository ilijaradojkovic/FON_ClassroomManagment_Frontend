package com.example.fon_classroommanagment_frontend

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
import com.example.compose.FON_ClassroomManagment_FrontendTheme
import com.example.fon_classroommanagment_frontend.common.Screen
import com.example.fon_classroommanagment_frontend.presentation.common.bars.Components.Navigation
import com.example.fon_classroommanagment_frontend.presentation.common.bars.SuccessDialog
import com.example.fon_classroommanagment_frontend.presentation.signin_screen.Aditional_Info_Screen
import com.example.fon_classroommanagment_frontend.presentation.signin_screen.TypeEMP_EducationEMP_Screen
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
                   // RequestContentPermission()

                }
            }
    }
    @Composable
    fun RequestContentPermission() {
        var imageUri by remember {
            mutableStateOf<Uri?>(null)
        }
        val context = LocalContext.current
        val bitmap =  remember {
            mutableStateOf<Bitmap?>(null)
        }

        val launcher = rememberLauncherForActivityResult(contract =
        ActivityResultContracts.GetContent()) { uri: Uri? ->
            imageUri = uri
        }
        Column() {
            Button(onClick = {
                launcher.launch("image/*")
            }) {
                Text(text = "Pick image")
            }

            Spacer(modifier = Modifier.height(12.dp))



        }
    }
}



