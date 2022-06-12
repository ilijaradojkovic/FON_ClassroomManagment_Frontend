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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieConstants
import com.example.fon_classroommanagment_frontend.common.Screen
import com.example.fon_classroommanagment_frontend.presentation.common.bars.Components.LottieAnimation

@Composable
fun Splash_Screen(navController: NavHostController) {



    Box() {
        Image(
            painterResource(id = if (isSystemInDarkTheme()) R.drawable.bcg_splash_dark_1 else R.drawable.bcg_splash_light_1),
            "",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )


              LottieAnimation(lottieAnim = R.raw.custom_anim_logo_fona, onComplete = {navController.navigate(Screen.LoginScreen.route)})






    }
}