package com.example.fon_classroommanagment_frontend.Components

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.fon_classroommanagment_frontend.CallendarPicker
import com.example.fon_classroommanagment_frontend.R
import com.example.fon_classroommanagment_frontend.data.Event
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.MainAxisAlignment
import java.time.LocalDateTime
import java.util.*
import kotlin.random.Random

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DetailsClassroom_Screen(){
    Scaffold(floatingActionButton = { FloatingActionButton(onClick = { /*TODO*/ }) {
        Icon(painter = painterResource(id = R.drawable.reserve), contentDescription = "Icon FAB", modifier = Modifier.size(24.dp))
    }}) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())

                .background(MaterialTheme.colorScheme.background),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "C001",
                    style = MaterialTheme.typography.displayMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    "Ucionica",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.surface
                )
            }
            Row(
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth().padding(15.dp,0.dp)
            ) {
                Statistics()
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                FlowRow(
                    modifier = Modifier
                        .fillMaxWidth(0.9f), mainAxisAlignment = MainAxisAlignment.Center
                ) {

                    CategoryChip_Outlined("Racunarksa")
                    CategoryChip_Outlined("Projektor")
                    CategoryChip_Outlined("30m2")
                    CategoryChip_Outlined("1.Sprat")
                    CategoryChip_Outlined("2 Table")
                }
            }

            Row(
                modifier = Modifier,
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CallendarPicker()
            }
            Column() {
                Schedule(
                    listOf(
                        Event(
                            type = "Predavanje",
                            subject = "Programiranje 2",
                            color = Color(0xFFAFBBF2),
                            start = LocalDateTime.parse("2021-05-18T15:00:00"),
                            end = LocalDateTime.parse("2021-05-18T17:00:00"),
                        ),
                        Event(
                            type = "Predavanje",
                            subject = "Napredna java",
                            color = Color(0xFFAFBBF2),
                            start = LocalDateTime.parse("2021-05-18T13:00:00"),
                            end = LocalDateTime.parse("2021-05-18T15:00:00"),
                        )
                    )
                )

            }

        }
    }
}

@Composable
fun Statistics(){
   val  months    = listOf("Jan","Feb","Mar","Apr","Maj","Jun","Jul","Avg","Sep","Okt","Nov","Dec")
   Row(modifier = Modifier
       .fillMaxSize()
       .clip(MaterialTheme.shapes.medium)
       .background(MaterialTheme.colorScheme.surface)
       .padding(5.dp, 5.dp), verticalAlignment=Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
        for ( mon in months){
            Row(modifier = Modifier

                .weight(1f)
                , horizontalArrangement = Arrangement.Center) {

                Statistics_Bar(Random.nextFloat(),mon)
            }
        }




    }

}

@Composable
fun Statistics_Bar(percent:Float,month:String){
Column(modifier = Modifier
    .fillMaxHeight()
 , horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Bottom) {
    Column(modifier = Modifier
        .background(MaterialTheme.colorScheme.onBackground)
        .fillMaxHeight(percent)
       ) {
        Text("b", color = Color.Transparent )
    }
    Column(modifier = Modifier) {
        Text(text =month, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onBackground)
    }
}
}

@Composable
fun CategoryChip_Outlined(
    category: String,



) {
    Surface(
        modifier = Modifier.padding(end = 8.dp, bottom = 8.dp),
       // shadowElevation = 8.d,
        shape = RoundedCornerShape(16.dp), color = Color.Transparent,
        border = BorderStroke(1.dp,MaterialTheme.colorScheme.onBackground)

    ) {
        Row()
            {
            Text(
                text = category,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}