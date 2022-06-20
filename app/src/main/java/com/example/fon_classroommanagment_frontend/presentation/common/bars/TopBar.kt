package com.example.fon_classroommanagment_frontend.presentation.common.bars.Components.input

import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieConstants
import com.example.fon_classroommanagment_frontend.R
import com.example.fon_classroommanagment_frontend.presentation.common.bars.Components.LottieAnimation
import kotlinx.coroutines.Job


@Composable
fun TopBar(
    souldHide: Boolean,
    onFilterClick: () -> Job,
    search:(searchText:String)->Unit,
    searchText: String,
    changeSearchText:(String)->Unit,
    searchLoading:Boolean,
    onBackClicked:()->Unit={},
    displayBackIcon:Boolean=false
) {

    var widthOfSearchInput by remember {
        mutableStateOf(0f)
    }



    SmallTopAppBar(
        {
            if (!souldHide) {
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier.weight(1f),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = {
                            widthOfSearchInput = if (widthOfSearchInput == 1f) 0f
                            else 1f
                        }, modifier = Modifier) {
                            Icon(
                                painter = painterResource(id = R.drawable.search_icon),
                                contentDescription = "Search icon",
                                modifier = Modifier.size(24.dp),
                                tint = MaterialTheme.colorScheme.onSurface
                            )
                        }

                        //TextField(modifier = Modifier.width(0.dp),value = "", onValueChange ={}, )
                        BasicTextField(

                            textStyle = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier
                                .animateContentSize()
                                .height(40.dp)
                                .fillMaxWidth(widthOfSearchInput)
                                .clip(MaterialTheme.shapes.medium)
                                .border(
                                    1.dp,
                                    MaterialTheme.colorScheme.outline,
                                    MaterialTheme.shapes.medium
                                ),
                            //.background(Color.Gray)
                            // shape = MaterialTheme.shapes.medium,
                            value = searchText,
                            onValueChange = { changeSearchText(it) },
                            cursorBrush = SolidColor(MaterialTheme.colorScheme.onSurface),
                            decorationBox = { innerTextField ->
                                Row(
                                    Modifier

                                        .fillMaxSize()

                                        .padding(10.dp, 0.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween

                                ) {
                                    Box() {
                                        Row(modifier = Modifier) {
                                            if (searchText.isEmpty())
                                                Text(
                                                    "Search",
                                                    style = MaterialTheme.typography.bodyMedium
                                                )

                                        }

                                        innerTextField()  //<-- Add this
                                    }



                                    Row(Modifier) {
                                        if(searchLoading){
                                            Box( modifier = Modifier.size(15.dp)){
                                                LottieAnimation(lottieAnim = R.raw.loading_dialog, iterations = LottieConstants.IterateForever)
                                            }

                                        }else {

                                            IconButton(onClick = {
                                                if (searchText.isEmpty()) widthOfSearchInput =
                                                    0f else changeSearchText("")
                                            }) {
                                                Icon(
                                                    painter = painterResource(id = R.drawable.close),
                                                    contentDescription = "closeIcon",
                                                    modifier = Modifier.size(15.dp)
                                                )
                                            }
                                        }
                                    }
                                }

                            },

                            keyboardActions = KeyboardActions(onDone = {
                                    search(searchText)
                            }),
                            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)

                        )
                    }
                    Row(modifier = Modifier) {
                        IconButton(onClick = { onFilterClick() }) {
                            Icon(
                                painter = painterResource(id = R.drawable.filter_icon),
                                contentDescription = "Filter icon",
                                modifier = Modifier.size(24.dp),
                                tint = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }

                }
            }

            if(displayBackIcon)
                Row(modifier = Modifier){
                    IconButton(onClick = { onBackClicked() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.back),
                            contentDescription = "Back icon",
                            modifier = Modifier.size(24.dp),
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.surface)
    )

}
