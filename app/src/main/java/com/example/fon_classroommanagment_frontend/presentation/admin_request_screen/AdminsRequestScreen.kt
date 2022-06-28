package com.example.fon_classroommanagment_frontend

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fon_classroommanagment_frontend.presentation.admin_request_screen.AdminRequestsViewModel

@Composable
fun AdminsRequestScreen(
    id:Long,
    adminRequestsViewModel: AdminRequestsViewModel = hiltViewModel()

){

    val userDetails by adminRequestsViewModel.userDetails
    val userRequests = adminRequestsViewModel.userRequests

    LaunchedEffect(key1 = true){
        adminRequestsViewModel.getUserDetails(id)
        adminRequestsViewModel.getRequests(id)
    }
Column(modifier= Modifier
    .fillMaxSize()
    .background(MaterialTheme.colorScheme.background)) {

    Row(modifier= Modifier
        .fillMaxWidth()
        .weight(2f)
      ) {
        Row(modifier= Modifier
            .weight(2f)
            .fillMaxHeight()
           , verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center){
            Image(
                painter = painterResource(id = R.drawable.avatar),
                contentDescription = "Profile_Image",
                modifier = Modifier
                    .clip(CircleShape)
                    .border(2.dp, Color.White, shape = CircleShape)
            )


        }
        Row(modifier= Modifier
            .fillMaxWidth()
            .weight(2f)
            .fillMaxHeight()){
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(), verticalArrangement = Arrangement.SpaceAround, horizontalAlignment = Alignment.CenterHorizontally) {
                  Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                      Text("${userDetails.firstName} ${userDetails.lastName}", style = MaterialTheme.typography.titleLarge,color=MaterialTheme.colorScheme.onBackground)
                      Text(userDetails.type, style = MaterialTheme.typography.titleSmall,color=MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f))

                  }
                  OutlinedButton(onClick = { /*TODO*/ }) {
                        Text("Approve All")
                    }
                }
        }
    }
    Row(modifier= Modifier

        .fillMaxWidth()
        .weight(5f)
        .padding(10.dp)
        .clip(MaterialTheme.shapes.extraLarge)) {

        LazyColumn(){
                items(userRequests, key = {item->item.hashCode()}){
                    item->
                    Column(modifier = Modifier.height(200.dp)) {
                        ApproveAppointmentCard(item)
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                }
            }


    }

}
}