package com.example.fon_classroommanagment_frontend.presentation.common.bars

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.airbnb.lottie.compose.LottieConstants
import com.example.fon_classroommanagment_frontend.R
import com.example.fon_classroommanagment_frontend.common.Screen
import com.example.fon_classroommanagment_frontend.common.UIRequestResponse
import com.example.fon_classroommanagment_frontend.presentation.common.bars.Components.LottieAnimation
import com.example.fon_classroommanagment_frontend.presentation.common.bars.Components.input.RoundIconButton

@Composable
fun SuccessRegistrationDialog(isLoading:Boolean, toNavigate :()->Unit, title:String, body:String, confirmButton:()->Unit={}, dismissButton: @Composable  ()->Unit={}){
    AlertDialog(
        onDismissRequest = {
            //openDialog.value = false
        },
        title = {
            if(isLoading) Text("Loading")
            else Text(text = title)
        },
        text = {
            Column(modifier = Modifier.height(100.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
               if(isLoading)  LottieAnimation(lottieAnim = R.raw.loading_dialog, iterations =  LottieConstants.IterateForever)
               else Text(body, textAlign = TextAlign.Center)

            }
        },

        dismissButton = {
            if(!isLoading)
                 dismissButton()
        }
        , confirmButton = {toNavigate()})
}
@Composable
fun ErrorRegistrationDialog(title:String, body:String, navigation:()->Unit){

    AlertDialog(
        containerColor = MaterialTheme.colorScheme.errorContainer,
        onDismissRequest = {

         },
        title = {

          Text(text = title)
        },
        text = {
            Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                   Text(body, textAlign = TextAlign.Center)

            }
        },

        dismissButton = {

               Button(onClick = {  navigation() },colors=ButtonDefaults.buttonColors(MaterialTheme.colorScheme.onSecondaryContainer),modifier = Modifier.fillMaxWidth(),) {
                   Text("Try Again")
               }
        }
        , confirmButton = {})
}




@Composable
fun SuccessReservationDialog(onDissmis:()->Unit){
    Column(modifier = Modifier
        .fillMaxSize()
        ,horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {

        Column(
            modifier = Modifier
                .clip(MaterialTheme.shapes.medium)
                .fillMaxWidth(0.9f)
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Your reservation has been requested, please be patient we will confirm it soon.",
               textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Button(
                onClick = { onDissmis()}, modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Text(
                    "Continue",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}
@Composable

fun ErrorReservationDialog(onDissmis:()->Unit){
    Column(modifier = Modifier
        .fillMaxSize()
        ,horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {

            Column(
                modifier = Modifier
                    .clip(MaterialTheme.shapes.medium)
                    .fillMaxWidth(0.9f)
                    .background(MaterialTheme.colorScheme.errorContainer)
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "Please check appointment information,or some appointments are already reserved",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onErrorContainer
                )
                Button(
                    onClick = { onDissmis()}, modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                ) {
                    Text(
                        "Continue",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onError
                    )
                }
            }
        }

}