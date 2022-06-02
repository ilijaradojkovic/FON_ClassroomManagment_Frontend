package com.example.fon_classroommanagment_frontend

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun Profile_Screen(){
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .weight(2f), horizontalArrangement = Arrangement.Center) {
            Image(painter = painterResource(id = R.drawable.avatar),
                contentDescription = "Profile_Image",
                modifier = Modifier
                    .clip(CircleShape)
                    .border(2.dp, Color.White, shape = CircleShape))
        }

        Column(modifier = Modifier
            .fillMaxWidth()
            .weight(1f), horizontalAlignment = Alignment.CenterHorizontally){

            Text("Adam Smith", style = MaterialTheme.typography.headlineMedium)
            Text("Professor", style = MaterialTheme.typography.bodyMedium)

        }

        Column(modifier = Modifier
            .fillMaxWidth()
            .weight(4f)
            , verticalArrangement = Arrangement.Bottom){

           Column(
               Modifier
                   .fillMaxHeight(0.35f)
                   .fillMaxWidth()
                   , verticalArrangement = Arrangement.SpaceAround){
               Item(R.drawable.callendar,"Appointments",true,5)

               Divider(modifier = Modifier
                   .fillMaxWidth()
                   .border(1.dp, MaterialTheme.colorScheme.onBackground))
               Item(R.drawable.settings,"Settings",false)

               Divider(modifier = Modifier
                   .fillMaxWidth()
                   .border(1.dp, MaterialTheme.colorScheme.onBackground))
               Item(R.drawable.logout,"Logout",false)


            }
        }

    }
}
@Composable
fun Item(icon:Int,name:String,hasCircleText:Boolean,circleTextNumber:Int?=null){
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
        if(hasCircleText && circleTextNumber!=null)
            Row(modifier = Modifier
                .weight(1f)
                .padding(10.dp, 0.dp)) {

                Text_Circle(MaterialTheme.colorScheme.tertiary,circleTextNumber)


            }
    }

}
@Composable
fun Text_Circle(colorCircle:Color,value:Int){
    Text(value.toString(),style=MaterialTheme.typography.bodyMedium, modifier = Modifier.drawBehind {
        drawCircle(colorCircle,radius = this.size.maxDimension/2)
    })
}