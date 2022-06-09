package com.example.fon_classroommanagment_frontend.Components.input

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.fon_classroommanagment_frontend.R
import kotlinx.coroutines.Job


@Composable
fun TopBar(
    souldHide: Boolean,
    onFilterClick: () -> Job,

) {

    var widthOfSearchInput by remember {
        mutableStateOf(0f)
    }
    var searchText by remember{ mutableStateOf("")}

    SmallTopAppBar({
        if (!souldHide) {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End, verticalAlignment = Alignment.CenterVertically) {
                Row(modifier = Modifier.weight(1f), horizontalArrangement = Arrangement.End, verticalAlignment = Alignment.CenterVertically) {
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
                    BasicTextField(textStyle = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier
                            .animateContentSize()
                            .height(40.dp)
                            .fillMaxWidth(widthOfSearchInput)
                            .clip(MaterialTheme.shapes.medium)
                            .background(Color.Gray),
                       // shape = MaterialTheme.shapes.medium,
                        value = searchText,
                        onValueChange = {searchText="tewt"},
                        decorationBox = { innerTextField ->
                            Row(
                                Modifier

                                    .fillMaxSize()
                                    .padding(10.dp, 0.dp), verticalAlignment = Alignment.CenterVertically

                            ) {

                                if (searchText.isEmpty()) {
                                    Text("Search", style = MaterialTheme.typography.bodyMedium)
                                }
                                innerTextField()  //<-- Add this
                            }
                        },

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
    }, colors =TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.surfaceVariant))

}
