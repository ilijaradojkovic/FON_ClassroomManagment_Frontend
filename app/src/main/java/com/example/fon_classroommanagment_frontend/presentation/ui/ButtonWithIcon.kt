package com.example.fon_classroommanagment_frontend.presentation.common.bars.Components.buttons

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp


@Composable
fun ButtonWithIcon(text:String, @DrawableRes icon:Int, onClick :()->Unit){
    Button(onClick = {onClick() },modifier= Modifier
        .fillMaxWidth(0.5f)
        .height(50.dp)) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround, verticalAlignment = Alignment.CenterVertically){
            Text(text, style = MaterialTheme.typography.bodyLarge)
            Icon(painter = painterResource(id = icon), contentDescription = "", modifier = Modifier.size(24.dp))

        }
    }
}