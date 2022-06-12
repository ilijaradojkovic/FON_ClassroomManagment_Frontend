package com.example.fon_classroommanagment_frontend

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.fon_classroommanagment_frontend.common.Screen
import com.example.fon_classroommanagment_frontend.presentation.common.bars.Components.LottieAnimation

@Composable
fun Splash_Screen(navController: NavHostController) {

    var animate by remember{ mutableStateOf(false)}
    val animateF = animateFloatAsState(targetValue = if(animate) 0f else 1f, animationSpec = tween(durationMillis = 3000))
    LaunchedEffect(key1 = true){
        animate=true
    }
    LaunchedEffect(key1 = animateF.value){
        if(animateF.value==0f){
            navController.navigate(Screen.LoginScreen.route)
        }
    }
    Box() {
        Image(
            painterResource(id = if (isSystemInDarkTheme()) R.drawable.bcg_spash_dark else R.drawable.bch_splash_light),
            "",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                //.background(MaterialTheme.colorScheme.background)
                .padding(10.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(contentAlignment = Alignment.TopCenter, modifier = Modifier.weight(1f)) {
                Image(
                    painter = painterResource(id = R.drawable.fonsplash),
                    contentDescription = "Spalsh image",
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .scale(animateF.value)
                )
            }



            //Image(painter = painterResource(id = R.drawable.splash_img), contentDescription = "")

            //LottieAnimation(lottieAnim = if(isSystemInDarkTheme()) R.raw.splash_animated_dark else R.raw.splash_animated_light)
        }
    }
}