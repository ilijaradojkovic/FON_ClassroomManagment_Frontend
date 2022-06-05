package com.example.fon_classroommanagment_frontend.Components.input

import androidx.annotation.DrawableRes
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
@Composable
fun RoundIconButton(@DrawableRes icon:Int, size:Dp){

    IconButton(onClick = { /*TODO*/ }) {
        Icon(painter = painterResource(id = icon),
            contentDescription = "Profile_Image",

            modifier = Modifier

                .clip(CircleShape)

                .border(2.dp, MaterialTheme.colorScheme.tertiary, CircleShape)
                .padding(10.dp)
                .size(size)
            ,

        )
    }

}