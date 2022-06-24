package com.example.fon_classroommanagment_frontend.presentation.common.bars.Components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.airbnb.lottie.compose.*

@Composable
fun LottieAnimation( modifier:Modifier=Modifier,lottieAnim:Int, opacity:Float=1f,isPlating:Boolean=true,restartOnPlay:Boolean=true,iterations:Int=1,onComplete:()->Unit={}){
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(lottieAnim))
    val progress by animateLottieCompositionAsState(composition)
    LaunchedEffect(key1 = progress){
        if(progress==1f){
            onComplete()
        }
    }

    LottieAnimation(
        composition,

        modifier = modifier.alpha(opacity).zIndex(10f),
        isPlating,
        restartOnPlay = restartOnPlay,
        iterations =iterations
    )
}