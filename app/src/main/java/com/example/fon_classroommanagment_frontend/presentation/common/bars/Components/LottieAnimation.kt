package com.example.fon_classroommanagment_frontend.presentation.common.bars.Components

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun LottieAnimation(@DrawableRes lottieAnim:Int, opacity:Float=0f){
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(lottieAnim))

    LottieAnimation(
        composition,

        modifier = Modifier.alpha(opacity),
        false,
    )
}