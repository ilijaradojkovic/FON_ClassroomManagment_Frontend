package com.example.fon_classroommanagment_frontend.presentation.common.bars.Components.input

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fon_classroommanagment_frontend.presentation.appointment_insetion_screen.components.ErrorField
import com.github.skgmn.composetooltip.AnchorEdge
import com.github.skgmn.composetooltip.Tooltip
import com.github.skgmn.composetooltip.rememberTooltipStyle

@Composable
fun AppointmentMultyLineInput(
    text:String,
    ontextChange:(String)->Unit,
    nameField:String,
    isScrolling:Boolean=false,
    errorText:String="",
    explainedError:String=""
){
    var showTooltip by remember{ mutableStateOf(false) }

    LaunchedEffect(key1 = isScrolling){
        showTooltip=false
    }
        Column(modifier= Modifier
            .fillMaxSize()
            .padding(20.dp)
            .height(160.dp), verticalArrangement = Arrangement.SpaceBetween) {
            Row(modifier = Modifier.height(IntrinsicSize.Max), verticalAlignment = Alignment.CenterVertically) {
                Text(
                    nameField,
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onBackground
                )

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
                                .clickable { showTooltip = !showTooltip },
                        tint = MaterialTheme.colorScheme.error)
                    }

            }
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)

                    .border(
                        3.dp,
                        MaterialTheme.colorScheme.outline,
                        MaterialTheme.shapes.small
                    ),
                shape = MaterialTheme.shapes.medium,
                value = text,
                onValueChange = {ontextChange(it)},
                keyboardOptions = KeyboardOptions(imeAction = androidx.compose.ui.text.input.ImeAction.Done),
                maxLines = 3,
                isError = errorText.isNotEmpty(),
                textStyle = MaterialTheme.typography.bodyMedium,

            )
            ErrorField(text = errorText, modifier = Modifier.fillMaxWidth())
        }
}