package com.example.fon_classroommanagment_frontend

import android.util.Log
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fon_classroommanagment_frontend.common.Constants.MAX_CAPACITY
import com.example.fon_classroommanagment_frontend.common.Constants.MIN_CAPACITY
import com.example.fon_classroommanagment_frontend.data.remote.dto.FilterDTO
import com.example.fon_classroommanagment_frontend.domain.model.ClassroomType
import com.example.fon_classroommanagment_frontend.presentation.common.bars.FilterViewModel
import com.google.accompanist.flowlayout.FlowRow


@Composable
fun Bottom_Sheet_Content(
    _filterDTO:FilterDTO,
    applyFilters:(FilterDTO)->Unit,
    filterViewModel: FilterViewModel = hiltViewModel(),
    closeFilter: () -> Unit,

) {
    //kada izadjemo treba da ne save obj
    var filterDTO by remember {

        mutableStateOf(_filterDTO.copy())
    }
    val classroomTypes = filterViewModel.classroomTypes
    val scrollstate= rememberScrollState()
    var sliderPosition by remember { mutableStateOf(filterDTO.minCapacity.toFloat() ..filterDTO.maxCapacity.toFloat()) }

    Box(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.8f)
            .clip(RoundedCornerShape(20.dp, 20.dp, 0.dp, 0.dp))
            .background(MaterialTheme.colorScheme.surface)
            .scrollable(scrollstate, orientation = Orientation.Vertical)
            .padding(20.dp)) {
        Column(Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Row(modifier= Modifier
                .fillMaxWidth()
                .weight(1f)
                .height(IntrinsicSize.Max)
               ){
                Row(modifier= Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Max)
                   ) {

                    Row(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight(),
                        verticalAlignment = Alignment.Bottom
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.close),
                            contentDescription = "Close icon",
                            modifier = Modifier
                                .size(20.dp)
                                .clickable { closeFilter() },
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
                            .fillMaxHeight()
                    ,   horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            "Done",
                            style = MaterialTheme.typography.headlineSmall,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.clickable {
                                filterDTO.minCapacity= (sliderPosition.start * MAX_CAPACITY).toInt()
                                filterDTO.maxCapacity= (sliderPosition.endInclusive * MAX_CAPACITY).toInt()
                                applyFilters(filterDTO)
                                closeFilter()
                            }
                        )

                    }
                }
            }

            Column(modifier= Modifier
                .fillMaxWidth()
                .weight(1f)) {
                Column(modifier=Modifier.fillMaxWidth()) {
                    Text("Kapacitet",
                        style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onSurface
                        )
                }
                Column(modifier=Modifier.weight(1f), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                    Row(modifier=Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.SpaceAround){
                        Row(modifier = Modifier.weight(1f), horizontalArrangement = Arrangement.Start) {
                            Text(
                                (sliderPosition.start* MAX_CAPACITY).toInt().toString(),
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.secondary
                            )
                        }
                        Row(modifier = Modifier.weight(1f), horizontalArrangement = Arrangement.End) {
                            Text(
                                (sliderPosition.endInclusive * MAX_CAPACITY).toInt().toString(),
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.secondary
                            )
                        }
                    }
                               DoubleSlider(sliderPosition
                               ) { sliderPosition=it
                                       //start, end ->
////                                   if((start*200f).toInt()!=filterDTO.minCapacity)
////                                         filterDTO.minCapacity = (start*200f).toInt()
////
////                                   if((end*200f).toInt()!=filterDTO.maxCapacity)
////                                        filterDTO.maxCapacity = (end*200f).toInt()
//                                   Log.i("cao","Start "+start.toString())
//                                   Log.i("cao","End"+end.toString())
                               }
                }
            }
            Column(modifier= Modifier
                .fillMaxWidth()
                .weight(2f)) {
                Column(modifier=Modifier.fillMaxWidth()) {
                    Text("Tip",
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.onSurface

                    )
                }
                Column(modifier= Modifier
                    .weight(5f)
                    .fillMaxWidth(), verticalArrangement = Arrangement.Center) {
                    FlowRow() {
                        for (i in 0 until classroomTypes.size){
                            CategoryChip(classroomTypes[i].name,false)  {}
                        }

                }
                }
            }
            Column(modifier= Modifier
                .fillMaxWidth()
                .weight(1f)) {
                Column(modifier=Modifier.fillMaxWidth()) {
                    Text("Oprema",
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
                Column(modifier= Modifier
                    .weight(1f)
                    .fillMaxWidth(), verticalArrangement = Arrangement.Center) {
                    FlowRow() {
                        CategoryChip("Klima",filterDTO.aircondition) { filterDTO=filterDTO.copy(aircondition =  it)
                        Log.i("cao",filterDTO.aircondition.toString())
                        }
                        CategoryChip("Projektor",filterDTO.projeector){ filterDTO=filterDTO.copy(projeector = it) }

                    }
                }
            }
            Column(modifier= Modifier
                .fillMaxWidth()
                .weight(1f)) {
                Column(modifier=Modifier.fillMaxWidth()) {
                    Text("Sort by",
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
                Column(modifier= Modifier
                    .weight(1f)
                    .fillMaxWidth(), verticalArrangement = Arrangement.Center) {
                    FlowRow() {
                        CategoryChip("Kapacitet",filterDTO.sortByCapacity){filterDTO=filterDTO.copy(sortByCapacity=it)}


                    }
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
            else -> Color.Transparent
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
                color = Color.White,
                modifier = Modifier.padding(16.dp,0.dp)
            )
        }
    }
}