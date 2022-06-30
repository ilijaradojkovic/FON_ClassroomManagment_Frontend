package com.example.fon_classroommanagment_frontend.presentation.common.bars.Components.input

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.fon_classroommanagment_frontend.R
import com.example.fon_classroommanagment_frontend.data.remote.dto.ClassroomChipDTO
import com.example.fon_classroommanagment_frontend.domain.model.AppointmentType
import com.example.fon_classroommanagment_frontend.presentation.appointment_insetion_screen.components.ErrorField

@Composable
fun AppointmetnComboBox(
    selectedName:String,
    dataList: SnapshotStateList<AppointmentType>,
    errorText: String,
    onSelect: (Int) -> Unit
) {
//ovde negde dolazi do greske prilikom rekompozicije
    var comboBoxOpened by remember {
        mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }

    var selectedText by remember { mutableStateOf(selectedName) }
    val animRotation = animateFloatAsState(targetValue = if(comboBoxOpened) 0f else 180f )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(interactionSource, indication = null) { comboBoxOpened = !comboBoxOpened },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = selectedText,
            enabled = false,
            modifier = Modifier
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
            trailingIcon = {
                Icon(
                    painterResource(id =R.drawable.arrow_down_dropdown),
                    "",
                    modifier = Modifier
                        .size(24.dp)
                        .rotate(animRotation.value)
                )
            },

            colors = TextFieldDefaults.textFieldColors(

                containerColor = Color.Transparent,
                disabledTextColor =MaterialTheme.colorScheme.onBackground,
                disabledTrailingIconColor = MaterialTheme.colorScheme.onBackground
            ),
            placeholder = {
                Text(
                    "Select",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
                    modifier = Modifier
                )
            },

            onValueChange = {})

        DropdownMenu(
            expanded = comboBoxOpened,
            onDismissRequest = { comboBoxOpened = false },
            modifier = Modifier
                .fillMaxWidth(0.8f)

        ) {

            dataList.forEach {  data ->
                DropdownMenuItem(text = { Text(data.name) }, onClick = {

                    selectedText = data.name
                    comboBoxOpened = !comboBoxOpened
                    onSelect(data.id)
                })
            }
        }
        if(!comboBoxOpened)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp, 0.dp),
            contentAlignment = Alignment.CenterStart
        ) {

            ErrorField(errorText, Modifier.fillMaxWidth())
        }

    }
}

@Composable
fun ClassroomComboBox(
    getMoreData :()->Unit,
    dataList: SnapshotStateList<ClassroomChipDTO>,
    selected:Long,
    onSelect: (Long) -> Unit,

) {

    val verticalscrollState= rememberScrollState()
    var comboBoxOpened by remember {
        mutableStateOf(false)
    }
    val interactionSource = remember { MutableInteractionSource() }

    var selectedText by remember { mutableStateOf("Selected")}
    val animRotation = animateFloatAsState(targetValue = if (comboBoxOpened) 0f else 180f)
LaunchedEffect(key1 = verticalscrollState.value==verticalscrollState.maxValue){
    getMoreData()

}

    Column(modifier = Modifier
        .fillMaxWidth()
        , horizontalAlignment = Alignment.CenterHorizontally) {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.8f)

                .clickable(interactionSource, indication = null) {
                    comboBoxOpened = !comboBoxOpened
                },
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            (if(selectedText=="Selected" && dataList.size!=0) dataList.firstOrNull{ x->x.id==selected}?.name else selectedText)?.let {
                TextField(
                    value = it,
                    enabled = false,
                    modifier = Modifier
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
                    trailingIcon = {
                        Icon(
                            painterResource(id = R.drawable.arrow_down_dropdown),
                            "",
                            modifier = Modifier
                                .size(24.dp)
                                .rotate(animRotation.value)
                        )
                    },

                    colors = TextFieldDefaults.textFieldColors(

                        containerColor = Color.Transparent,
                        disabledTextColor = MaterialTheme.colorScheme.onBackground,
                        disabledTrailingIconColor = MaterialTheme.colorScheme.onBackground
                    ),
                    placeholder = {
                        Text(
                            "Select",
                            style = MaterialTheme.typography.labelLarge,
                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
                            modifier = Modifier
                        )
                    },

                    onValueChange = {})
            }
            Row(modifier = Modifier
                .fillMaxWidth()
                , horizontalArrangement = Arrangement.Center) {
                DropdownMenu(
                    expanded = comboBoxOpened,
                    onDismissRequest = { comboBoxOpened = false },
                    modifier = Modifier
                        .verticalScroll(verticalscrollState)
                        .fillMaxWidth(0.8f)
                        .requiredSizeIn(maxHeight = 200.dp)


                ) {

                    dataList.forEach { data ->
                        DropdownMenuItem(text = { Text(data.name) }, onClick = {

                            selectedText = data.name
                            comboBoxOpened = !comboBoxOpened
                            onSelect(data.id)
                        })
                    }
                }
            }


        }
    }


}






