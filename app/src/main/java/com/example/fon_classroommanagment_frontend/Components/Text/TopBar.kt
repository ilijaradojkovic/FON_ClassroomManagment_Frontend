package com.example.fon_classroommanagment_frontend.Components.Text

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.fon_classroommanagment_frontend.R

@Composable
fun TopBar(souldHide:Boolean){

    SmallTopAppBar({
if(!souldHide) {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
        Row(modifier = Modifier.weight(1f), horizontalArrangement = Arrangement.End) {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(id = R.drawable.search_icon),
                    contentDescription = "Search icon",
                    modifier = Modifier.size(24.dp)
                )
            }
        }
        Row(modifier = Modifier) {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(id = R.drawable.filter_icon),
                    contentDescription = "Filter icon",
                    modifier = Modifier.size(24.dp)
                )
            }
        }

    }
}
    })

}
