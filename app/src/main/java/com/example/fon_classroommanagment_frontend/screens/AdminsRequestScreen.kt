package com.example.fon_classroommanagment_frontend

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.fon_classroommanagment_frontend.Components.input.RoundImage

@Composable
fun AdminsRequestScreen(){
Column(modifier=Modifier.fillMaxSize()) {

    Row(modifier= Modifier
        .fillMaxWidth()
        .weight(2f)
      ) {
        Row(modifier= Modifier


            .weight(2f)

            .fillMaxHeight()
           , verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center){
            RoundImage(R.drawable.user_img)

        }
        Row(modifier= Modifier
            .fillMaxWidth()

            .weight(3f)
            .fillMaxHeight()){
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(), verticalArrangement = Arrangement.SpaceAround, horizontalAlignment = Alignment.CenterHorizontally) {
                  Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                      Text("Ilija Radojkovic", style = MaterialTheme.typography.bodyLarge,color=MaterialTheme.colorScheme.onBackground)
                      Text("Asistent", style = MaterialTheme.typography.bodyMedium,color=MaterialTheme.colorScheme.onBackground)

                  }
                  OutlinedButton(onClick = { /*TODO*/ }) {
                        Text("Approve All")
                    }
                }
        }
    }
    Row(modifier= Modifier

        .fillMaxWidth()
        .weight(5f).padding(10.dp)    .clip(MaterialTheme.shapes.extraLarge)) {

        LazyColumn(){
                items(count = 4){
                    Column(modifier = Modifier.height(200.dp)) {
                        ApproveAppointmentCard()
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                }
            }


    }

}
}