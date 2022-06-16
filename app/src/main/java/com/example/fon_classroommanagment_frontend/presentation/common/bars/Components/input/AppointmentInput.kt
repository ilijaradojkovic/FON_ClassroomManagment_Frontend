package com.example.fon_classroommanagment_frontend.presentation.common.bars.Components.input

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.fon_classroommanagment_frontend.R
import com.example.fon_classroommanagment_frontend.presentation.appointment_screen.components.ErrorField
import com.github.skgmn.composetooltip.AnchorEdge
import com.github.skgmn.composetooltip.Tooltip
import com.github.skgmn.composetooltip.TooltipStyle
import com.github.skgmn.composetooltip.rememberTooltipStyle

@Composable
fun AppointmentInput(
    text:String,
    onTextChange:(String)->Unit,
    hint:String,
    modifier:Modifier=Modifier,
    keyboardType:KeyboardType = KeyboardType.Text,
    trailingIcon:Int?=null,
    enabled:Boolean=true,
    errorText:String="",
    isScrolling:Boolean,
    explainedError:String
    ){

    var showTooltip by remember{ mutableStateOf(false)}

    LaunchedEffect(key1 = isScrolling){
        showTooltip=false
    }
    Column(modifier=Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {

        TextField(value =text,
            enabled=enabled,
            modifier= modifier
                .padding(10.dp)
                .height(50.dp)
                .drawBehind {
                    val strokeWidth = 10f
                    val y = size.height - strokeWidth / 2

                    drawLine(
                        Color.LightGray,
                        Offset(0f, y),
                        Offset(size.width, y),
                        strokeWidth
                    )
                },
            textStyle = MaterialTheme.typography.labelLarge,
            trailingIcon={if(trailingIcon!=null)
                        Icon(painter = painterResource(
                            id = trailingIcon),
                            tint = MaterialTheme.colorScheme.onBackground,
                            contentDescription = "Trailing icon",
                            modifier = Modifier.size(24.dp))
                if(errorText.isNotEmpty()){
                 if(showTooltip) {
                     val tooltipstyple = rememberTooltipStyle()
                     tooltipstyple.color=MaterialTheme.colorScheme.onSurface
                     Tooltip(


                         anchorEdge = AnchorEdge.Top,
                         tooltipStyle = tooltipstyple

                     ) {
                         Text(
                             explainedError,
                             style = MaterialTheme.typography.labelMedium,
                             color = MaterialTheme.colorScheme.surface
                         )
                     }

                 }
                    Icon(
                        Icons.Filled.Error ,
                        contentDescription = "Trailing icon",
                        modifier = Modifier
                            .size(24.dp)
                            .clickable { showTooltip = !showTooltip })
                }
                             },
            isError = errorText.isNotEmpty(),
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            colors = TextFieldDefaults.textFieldColors(textColor = MaterialTheme.colorScheme.onBackground,containerColor = Color.Transparent, errorIndicatorColor = MaterialTheme.colorScheme.errorContainer),
            placeholder = {
                Text(hint,
                style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier)},

            onValueChange ={onTextChange(it)} )

            ErrorField(errorText,Modifier.fillMaxWidth(0.7f))


    }
}