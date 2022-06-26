package com.example.fon_classroommanagment_frontend

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fon_classroommanagment_frontend.presentation.profile_screen.ProfileViewModel

@Composable
fun Profile_Screen(
    isAdmin:Boolean,
    profileViewModel: ProfileViewModel= hiltViewModel()

){
    val userDetails  by profileViewModel.userDetails
    val userImage by remember{
        mutableStateOf(profileViewModel.byteArrayToBitmap())}
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(0.dp, 10.dp, 0.dp, 0.dp)) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .weight(2f), horizontalArrangement = Arrangement.Center) {
          // if(userImage==null)
            Image(painter = painterResource(id = R.drawable.avatar) ,
                contentDescription = "Profile_Image",
                modifier = Modifier
                    .clip(CircleShape)
                    .border(2.dp, Color.White, shape = CircleShape))
//            else
//                Image(bitmap = userImage!!,
//               contentDescription = "Profile_Image",
//               modifier = Modifier
//                   .clip(CircleShape)
//                   .border(2.dp, Color.White, shape = CircleShape))

        }

        Column(modifier = Modifier
            .fillMaxWidth()
            .weight(1f)
            .padding(0.dp, 10.dp), horizontalAlignment = Alignment.CenterHorizontally){

            Text("${userDetails.firstName}  ${userDetails.lastName}", style = MaterialTheme.typography.headlineMedium)
            Text(userDetails.workType, style = MaterialTheme.typography.bodyMedium)

        }

        Column(modifier = Modifier
            .fillMaxWidth()
            .weight(4f)
            .padding(5.dp)
            , verticalArrangement = Arrangement.Bottom){

           Column(
               Modifier
                   .fillMaxHeight(0.5f)
                   .fillMaxWidth()
                   , verticalArrangement = Arrangement.SpaceAround){
               if(isAdmin){
                   Item(R.drawable.callendar,"Requests",true,5,R.drawable.refresh)
                   Divider(modifier = Modifier
                       .fillMaxWidth()
                       .border(1.dp, MaterialTheme.colorScheme.onBackground))
               }
               Item(R.drawable.callendar, "Appointments", true, 5)

               Divider(modifier = Modifier
                   .fillMaxWidth()
                   .border(1.dp, MaterialTheme.colorScheme.onBackground))
               Item(R.drawable.settings, "Settings", false )

               Divider(modifier = Modifier
                   .fillMaxWidth()
                   .border(1.dp, MaterialTheme.colorScheme.onBackground))
               Item(R.drawable.logout, "Logout", false )


            }
        }

    }
}
@Composable
fun Item(
    icon: Int,
    name: String,
    hasCircleText: Boolean,
    circleTextNumber: Int? = null,
    refresh: Int?=null
){
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment =Alignment.CenterVertically) {

        Row(modifier = Modifier) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = "SettingsIcon",
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))

        }



        Row(modifier = Modifier, horizontalArrangement = Arrangement.Center) {
            Text(name, style = MaterialTheme.typography.bodyMedium)

        }
        if(hasCircleText && circleTextNumber!=null  )
            Row(modifier = Modifier
                .weight(1f)

                .width(IntrinsicSize.Min)
                .padding(10.dp, 0.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {

                Text_Circle(MaterialTheme.colorScheme.tertiary,circleTextNumber)

                if(refresh!=null)

                    Icon(painter = painterResource(id = refresh), contentDescription = "Refres Icon", modifier = Modifier.size(24.dp))

            }
    }

}
@Composable
fun Text_Circle(colorCircle:Color,value:Int){
    Text(value.toString(),style=MaterialTheme.typography.bodyMedium, modifier = Modifier.drawBehind {
        drawCircle(colorCircle,radius = this.size.maxDimension/2)
    })
}