package com.example.fon_classroommanagment_frontend.presentation.common.bars.Components

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
fun TextIconButton(actionName:String,@DrawableRes icon:Int,onClick:()->Unit){
    Button(onClick = {onClick() }, modifier = Modifier.height(50.dp)) {
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceAround) {
            Text(text = actionName, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.background)
            Icon(painter = painterResource(id =icon ), contentDescription ="Icon button", modifier = Modifier.size(24.dp))
        }
    }
}