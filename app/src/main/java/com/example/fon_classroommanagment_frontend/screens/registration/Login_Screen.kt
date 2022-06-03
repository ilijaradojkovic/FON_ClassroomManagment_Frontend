package com.example.fon_classroommanagment_frontend

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.fon_classroommanagment_frontend.Components.input.Text_Field




@Composable
 fun Login_Screen(){
    Column() {
        //naslov
        Row(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Login", style = MaterialTheme.typography.displayLarge)
        }
        Column(
            modifier = Modifier
                //.clip(MaterialTheme.shapes.medium)
                .weight(3f)
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)


        ) {

            Row(
                modifier = Modifier
                    .zIndex(10f)
                    .absoluteOffset(0.dp, -45.dp)
                    .fillMaxWidth()
                    .weight(1f)
                    .clip(MaterialTheme.shapes.medium)



                   ,
                horizontalArrangement = Arrangement.Center
            ) {

                    Icon(painter = painterResource(id = R.drawable.avatar) ,
                        contentDescription ="Avatar",
                        tint = Color.White,
                        modifier = Modifier

                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.background)
                            .border(
                                BorderStroke(7.dp, Color.White), shape = CircleShape
                            )
                            .padding(7.dp)
                            .shadow(0.dp)
                        )


            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()

                    .weight(6f)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                       , horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Welcome!",
                        style = MaterialTheme.typography.displayMedium,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(5f)
                        , horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceAround
                ) {

                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .weight(4f)
                        .padding(0.dp, 30.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceAround) {

                        Text_Field(R.drawable.email,"Email")
                        Text_Field(idIcon = R.drawable.padlock,"Password", PasswordVisualTransformation())
                       
                    }
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically){
                        Button(onClick = { /*TODO*/ },modifier= Modifier
                            .fillMaxWidth(0.5f)
                            .height(50.dp)) {
                            Text("Login", style = MaterialTheme.typography.bodyMedium)
                        }
                    }
                }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(2f)
                            , horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            "Dont have an account?",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        Text(
                            "Sign in",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.secondary
                        )

                    }


                }

        }
    }



}