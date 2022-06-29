package com.example.fon_classroommanagment_frontend

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieConstants
import com.example.fon_classroommanagment_frontend.domain.navigation.Screen
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


              LottieAnimation(lottieAnim = R.raw.custom_anim_logo_fona, onComplete = {navController.navigate(
                  Screen.LoginScreen.route)})






    }
}