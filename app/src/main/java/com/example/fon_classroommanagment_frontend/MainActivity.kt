package com.example.fon_classroommanagment_frontend

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.fon_classroommanagment_frontend.ui.theme.FON_ClassroomManagment_FrontendTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FON_ClassroomManagment_FrontendTheme {

            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FON_ClassroomManagment_FrontendTheme {

    }
}