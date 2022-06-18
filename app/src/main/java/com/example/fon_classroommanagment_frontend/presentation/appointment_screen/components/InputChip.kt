package com.example.fon_classroommanagment_frontend.presentation.appointment_screen.components

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.fon_classroommanagment_frontend.R
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun ClassroomInputChip(
    errorText: String,
    selectedItems: SnapshotStateList<ChipItem>

){

    var text by remember {
        mutableStateOf("")
    }
//
//    val items = remember {
//        mutableStateListOf(
//            ChipItem("Adam"),
//            ChipItem("Eve"),
//            ChipItem("Ken"),
//            ChipItem("William"),
//            ChipItem("Julien"),
//        )
//    }



        // Navigation()

        TextField(value =text,
            modifier= Modifier
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
            trailingIcon={
                    Icon(
                       painterResource(id =  R.drawable.plus ),
                        contentDescription = "Trailing icon",
                        modifier = Modifier
                            .size(24.dp).clickable {
                                if (text.trim()
                                        .isNotEmpty() && !selectedItems.any { x->x.value==text }
                                ) {
                                    val trimmedText = text.trim()
                                    selectedItems.add(ChipItem(trimmedText))
                                    text=""
                                }
                            }
                            )
                },


            colors = TextFieldDefaults.textFieldColors(textColor = MaterialTheme.colorScheme.onBackground,containerColor = Color.Transparent, errorIndicatorColor = MaterialTheme.colorScheme.errorContainer),
            placeholder = {
                Text("Classroom",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier)},

            onValueChange ={
                text =
                    it


            } )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp, 0.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        ErrorField(errorText, Modifier.fillMaxWidth())
    }
            FlowRow(
            Modifier.fillMaxWidth(),
            mainAxisAlignment = FlowMainAxisAlignment.Center
        ) {

            for (i in 0 until selectedItems.size){
                InformationChip(selectedItems[i].value){selectedItems.remove(selectedItems[i])}

            }


        }

}
