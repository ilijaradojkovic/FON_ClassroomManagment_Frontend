package com.example.fon_classroommanagment_frontend.Components.input

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.fon_classroommanagment_frontend.*
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun Main_Screen(){

    val coroutineScope = rememberCoroutineScope()
    val modalBottomSheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)

    ModalBottomSheetLayout(sheetState = modalBottomSheetState,

        sheetContent =  {
         Bottom_Sheet_Content()
        },

       sheetBackgroundColor = Color.Transparent,

    ) {
        Scaffold(topBar = { TopBar(false,{coroutineScope.launch { if(modalBottomSheetState.targetValue==ModalBottomSheetValue.Expanded) modalBottomSheetState.hide() else modalBottomSheetState.show()}}) }, bottomBar = { BottonBar() }) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()

                    .padding(it), horizontalAlignment = Alignment.CenterHorizontally
            ) {
              AdminsRequestScreen()


            }
        }
    }
}