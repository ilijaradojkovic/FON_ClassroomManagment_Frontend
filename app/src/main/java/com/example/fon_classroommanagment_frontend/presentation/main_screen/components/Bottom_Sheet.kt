package com.example.fon_classroommanagment_frontend

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.fon_classroommanagment_frontend.common.Constants
import com.example.fon_classroommanagment_frontend.data.remote.dto.FilterDTO
import com.example.fon_classroommanagment_frontend.presentation.main_screen.FilterViewModel
import com.google.accompanist.flowlayout.FlowRow


@Composable
fun Bottom_Sheet_Content(
    onApplyFilters:(FilterDTO)->Unit,
    filterViewModel: FilterViewModel,
    onRestart:() ->Unit,
    onCloseFilter: () -> Unit,

    ) {



    val classroomTypes = filterViewModel.classroomTypes
    val scrollstate = rememberScrollState()


    Box(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.8f)
            .clip(RoundedCornerShape(20.dp, 20.dp, 0.dp, 0.dp))
            .background(MaterialTheme.colorScheme.surface)
            .scrollable(scrollstate, orientation = Orientation.Vertical)
            .padding(20.dp)
    ) {
        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .height(IntrinsicSize.Max)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(IntrinsicSize.Max)
                ) {

                    Row(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.close),
                            contentDescription = "Close icon",
                            modifier = Modifier
                                .size(20.dp)
                                .clickable { onCloseFilter() },
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }
                    Row(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            "Filter",
                            style = MaterialTheme.typography.displaySmall,
                            color = MaterialTheme.colorScheme.onSurface
                        )

                    }
                    Row(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight(), horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(painter = painterResource(id = R.drawable.refresh),
                            contentDescription = "",
                            modifier = Modifier.size(24.dp).clickable {
                                onRestart()
                        }, tint = MaterialTheme.colorScheme.secondary)


                    }
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ,
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        "Kapacitet",
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        Row(
                            modifier = Modifier.weight(1f),
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Text(
                                (filterViewModel.sliderPosition.start* Constants.MAX_CAPACITY).toInt().toString(),
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.secondary
                            )
                        }
                        Row(
                            modifier = Modifier.weight(1f),
                            horizontalArrangement = Arrangement.End
                        ) {
                            Text(
                                (filterViewModel.sliderPosition.endInclusive* Constants.MAX_CAPACITY).toInt().toString(),
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.secondary
                            )
                        }
                    }
                    DoubleSlider(
                        filterViewModel.sliderPosition
                    ) {
                        filterViewModel.changeRangeCapacity(it)

                    }
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Column(modifier = Modifier.fillMaxWidth().weight(1f)) {
                    Text(
                        "Tip",
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.onSurface

                    )
                }
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(), verticalArrangement = Arrangement.Center
                ) {
                    FlowRow() {
                        for (i in 0 until classroomTypes.size) {
                            CategoryChip(
                                classroomTypes[i].name,

                                filterViewModel.shouldShowCoosenType(classroomTypes[i])
                            ) { filterViewModel.handleClassroomTypeChoosen(classroomTypes[i]) }
                        }

                    }
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        "Oprema",
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(), verticalArrangement = Arrangement.Center
                ) {
                    FlowRow() {
                        CategoryChip(
                            "Klima",
                            filterViewModel.aircontition
                        ) { filterViewModel.changeAircondition(it) }
                        CategoryChip(
                            "Projektor",
                            filterViewModel.projector
                        ) { filterViewModel.changeProjector(it) }
                    }


                }
            }


            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        "Sort by",
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(), verticalArrangement = Arrangement.Center
                ) {
                    FlowRow() {
                        CategoryChip(
                            "Kapacitet",
                            filterViewModel.sortByCapacity
                        ) { filterViewModel.changeSortByCapacity(it) }


                    }
                }
            }
            Column(modifier= Modifier
                .fillMaxWidth()

                ) {
                Button(onClick = {
                    onApplyFilters(filterViewModel.filter())
                    onCloseFilter()},
                    modifier=Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.small,
                    colors = ButtonDefaults.buttonColors()) {
                    Text("Done")
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DoubleSlider(sliderPosition:ClosedFloatingPointRange<Float>,changeRange:(ClosedFloatingPointRange<Float>)->Unit) {
    RangeSlider(values =sliderPosition ,
        onValueChange = { changeRange(it)
                },
           // changeRange(it.start*200f,it.endInclusive*200f)},
        colors =  SliderDefaults.colors(thumbColor = MaterialTheme.colorScheme.secondary, activeTrackColor = MaterialTheme.colorScheme.secondary))
}

@Composable
fun CategoryChip(
    category: String,
    checked:Boolean,
    changeChecked:(Boolean)->Unit

) {


    Surface(
        modifier = Modifier
            .padding(end = 16.dp, bottom = 16.dp)
            .border(1.dp, MaterialTheme.colorScheme.outline, MaterialTheme.shapes.medium),
        shadowElevation = 8.dp,
        shape = MaterialTheme.shapes.medium,
        color = when {
            checked -> MaterialTheme.colorScheme.secondary
            else ->MaterialTheme.colorScheme.surface
        },

    ) {
        Row(modifier = Modifier
            .height(32.dp)
            .toggleable(
                value = checked,
                onValueChange = {
                    changeChecked(!checked)
                    //onExecuteSearch()
                }
            ), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
            Text(
                text = category,
                style = MaterialTheme.typography.bodyMedium,
                color = if(checked) MaterialTheme.colorScheme.onSecondary else MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(16.dp,0.dp)
            )
        }
    }
}