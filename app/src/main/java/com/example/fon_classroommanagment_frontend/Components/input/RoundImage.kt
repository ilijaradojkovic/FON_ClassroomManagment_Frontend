package com.example.fon_classroommanagment_frontend.Components.input

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
@Composable
fun RoundImage(@DrawableRes image:Int){
    Image(painter = painterResource(id = image),
        contentDescription = "Profile_Image",


        modifier = Modifier

            .clip(CircleShape)
            .border(2.dp, MaterialTheme.colorScheme.outline, CircleShape)
            .size(120.dp)
        ,
        contentScale = ContentScale.Crop

    )
}