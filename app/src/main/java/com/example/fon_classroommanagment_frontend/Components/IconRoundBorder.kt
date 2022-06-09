package com.example.fon_classroommanagment_frontend.Components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.fon_classroommanagment_frontend.R

@Composable
fun IconRoundBorder(@DrawableRes icon:Int){
    Icon(
        painter = painterResource(id = icon),
        contentDescription = "Avatar",
        tint = MaterialTheme.colorScheme.onBackground,
        modifier = Modifier

            .clip(CircleShape)
            .border(
                BorderStroke(7.dp, MaterialTheme.colorScheme.secondaryContainer),
                shape = CircleShape
            )
            .background(MaterialTheme.colorScheme.background)

            .padding(23.dp)
            .shadow(0.dp)

    )
}