package com.example.fon_classroommanagment_frontend

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.flowlayout.FlowRow


@Composable
fun Bottom_Sheet_Content(){
    val scrollstate= rememberScrollState()
    Box(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.8f)
            .clip(RoundedCornerShape(20.dp, 20.dp, 0.dp, 0.dp))
            .background(Color(0XFF0F9D58))
            .scrollable(scrollstate, orientation = Orientation.Vertical)
            .padding(20.dp)) {
        Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
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

                            .fillMaxHeight(), verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.close),
                            contentDescription = "Close icon",
                            modifier = Modifier.size(24.dp)
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
                            style = MaterialTheme.typography.headlineLarge,
                            color = MaterialTheme.colorScheme.onBackground
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
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.tertiary
                        )

                    }
                }
            }

            Column(modifier= Modifier
                .fillMaxWidth()
                .weight(1f)) {
                Column(modifier=Modifier.fillMaxWidth()) {
                    Text("Kapacitet", style = MaterialTheme.typography.bodyMedium)
                }
                Column(modifier=Modifier.weight(1f), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                                Slider(value = 0.5f, onValueChange ={} )
                    }
            }
            Column(modifier= Modifier
                .fillMaxWidth()
                .weight(2f)) {
                Column(modifier=Modifier.fillMaxWidth()) {
                    Text("Tip", style = MaterialTheme.typography.bodyMedium)
                }
                Column(modifier= Modifier
                    .weight(5f)
                    .fillMaxWidth(), verticalArrangement = Arrangement.Center) {
                    FlowRow() {
                        CategoryChip("Racunarska",false,{},{})
                        CategoryChip("Kombinovana",false,{},{})
                        CategoryChip("Amfiteatar",false,{},{})
                        CategoryChip("Kabinet",false,{},{})
                        CategoryChip("Sala",false,{},{})
                        CategoryChip("Kabinet",false,{},{})
                        CategoryChip("Laboratorija",false,{},{})
                }
                }
            }
            Column(modifier= Modifier
                .fillMaxWidth()
                .weight(1f)) {
                Column(modifier=Modifier.fillMaxWidth()) {
                    Text("Oprema", style = MaterialTheme.typography.bodyMedium)
                }
                Column(modifier= Modifier
                    .weight(1f)
                    .fillMaxWidth(), verticalArrangement = Arrangement.Center) {
                    FlowRow() {
                        CategoryChip("Klima",false,{},{})
                        CategoryChip("Projektor",false,{},{})

                    }
                }
            }
            Column(modifier= Modifier
                .fillMaxWidth()
                .weight(1f)) {
                Column(modifier=Modifier.fillMaxWidth()) {
                    Text("Sort by", style = MaterialTheme.typography.bodyMedium)
                }
                Column(modifier= Modifier
                    .weight(1f)
                    .fillMaxWidth(), verticalArrangement = Arrangement.Center) {
                    FlowRow() {
                        CategoryChip("Kapacitet",false,{},{})


                    }
                }
            }
        }
    }
}
@Composable
fun CategoryChip(
    category: String,
    isSelected: Boolean = false,
    onSelectedCategoryChanged: (String) -> Unit,
    onExecuteSearch: () -> Unit
) {
    Surface(
        modifier = Modifier.padding(end = 8.dp, bottom = 8.dp),
        shadowElevation = 8.dp,
        shape = RoundedCornerShape(16.dp),
        color = when {
            isSelected -> colorResource(R.color.teal_200)
            else -> colorResource(R.color.purple_500)
        }
    ) {
        Row(modifier = Modifier
            .toggleable(
                value = isSelected,
                onValueChange = {
                    onSelectedCategoryChanged(category)
                    onExecuteSearch()
                }
            )) {
            Text(
                text = category,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}