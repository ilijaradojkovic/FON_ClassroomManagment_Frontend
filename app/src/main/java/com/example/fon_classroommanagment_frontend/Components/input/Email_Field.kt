package com.example.fon_classroommanagment_frontend.Components.input

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun Text_Field(idIcon:Int,hint:String,  visualTransformation: VisualTransformation= VisualTransformation.None){
    TextField(value = "",
        modifier= Modifier
            .height(50.dp)
            .border(
                1.dp,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                shape = MaterialTheme.shapes.extraLarge
            ),

        shape = MaterialTheme.shapes.extraLarge,
        onValueChange = {},
        placeholder = { Text(hint, style = MaterialTheme.typography.bodyMedium) },
        leadingIcon = {
            Icon( modifier = Modifier
                .size(30.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.background)
                .padding(5.dp)
                ,painter = painterResource(id = idIcon), contentDescription =hint+"_Icon" )
        },
        colors = TextFieldDefaults.textFieldColors(disabledIndicatorColor = Color.Transparent, errorIndicatorColor = Color.Transparent, focusedIndicatorColor = Color.Transparent, unfocusedIndicatorColor = Color.Transparent),
        maxLines = 1,
        keyboardOptions = KeyboardOptions(autoCorrect = true, keyboardType = KeyboardType.Email),
        visualTransformation =visualTransformation

    )
}
