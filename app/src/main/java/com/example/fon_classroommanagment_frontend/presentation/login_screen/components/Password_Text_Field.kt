package com.example.fon_classroommanagment_frontend.presentation.login_screen.components

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun Password_Text_Field(text:String, changeText:(String)->Unit, @DrawableRes leadingIcon:Int, @DrawableRes trailingIconToggle:Int, @DrawableRes trailingIcon:Int, hint:String, errorMessage:String="", visualTransformation:VisualTransformation= PasswordVisualTransformation()){
    var _visualTransformation by remember {
        mutableStateOf(visualTransformation)
    }
    var showText by remember {
        mutableStateOf(false)
    }


    LaunchedEffect(key1 = showText){
        Log.i("cao",showText.toString())
        _visualTransformation = if(showText)
            VisualTransformation.None
        else PasswordVisualTransformation()

    }

    Column(modifier = Modifier.width(IntrinsicSize.Max), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(5.dp)) {
        Text(errorMessage, modifier = Modifier.fillMaxWidth(0.8f), style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.error)
        TextField(
            value = text,
            modifier = Modifier
                .border(1.dp,if(errorMessage.isEmpty()) Color.Transparent else MaterialTheme.colorScheme.error,
                    MaterialTheme.shapes.extraLarge),
            isError = errorMessage.isEmpty(),

            shape = MaterialTheme.shapes.extraLarge,
            onValueChange = { it -> changeText(it) },
            placeholder = { Text(hint, style = MaterialTheme.typography.bodyMedium) },
            leadingIcon = {
                Icon(
                    modifier = Modifier
                        .size(30.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.background)
                        .padding(5.dp),
                    painter = painterResource(id =  leadingIcon),
                    contentDescription = hint + "_Icon"
                )
            },
            trailingIcon={
                Icon(
                    modifier = Modifier

                        .size(24.dp)
                        .clickable {
                            showText = !showText
                        }
                            ,

                    painter = painterResource(id = if(showText) trailingIconToggle else trailingIcon),
                    contentDescription = hint + "_Icon",
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                disabledIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            maxLines = 1,
            keyboardOptions = KeyboardOptions(
                autoCorrect = true,
                keyboardType = KeyboardType.Email
            ),
            visualTransformation = _visualTransformation,
            textStyle = MaterialTheme.typography.bodyMedium

        )
    }

}