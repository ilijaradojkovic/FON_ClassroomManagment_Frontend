package com.example.fon_classroommanagment_frontend.presentation.common.bars.Components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun LottieAnimation( lottieAnim:Int, opacity:Float=1f,isPlating:Boolean=true,restartOnPlay:Boolean=true,iterations:Int=1){
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(lottieAnim))

    LottieAnimation(
        composition,

        modifier = Modifier.alpha(opacity),
        isPlating,
        restartOnPlay = restartOnPlay,
        iterations =iterations
    )
}