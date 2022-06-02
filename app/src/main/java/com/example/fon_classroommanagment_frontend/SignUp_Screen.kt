package com.example.fon_classroommanagment_frontend

import android.widget.ImageButton
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
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
import com.example.fon_classroommanagment_frontend.Components.Text.Text_Field

@Composable
 fun SignUp_Screen(){
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(modifier= Modifier
            .fillMaxWidth()
            .weight(1f), verticalAlignment = Alignment.CenterVertically) {
            Row(modifier = Modifier
                .weight(1f)
              , ){
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(painter = painterResource(id = R.drawable.back), contentDescription ="Back", modifier = Modifier.size(24.dp))
                }
            }

        }
        Column(modifier= Modifier
            .fillMaxWidth()
            .weight(9f)
            .background(MaterialTheme.colorScheme.background)
        ) {
            Row(
                modifier = Modifier
                    .zIndex(10f)
                    .absoluteOffset(0.dp, -45.dp)
                    .fillMaxWidth()
                    .weight(1f)
                    .clip(MaterialTheme.shapes.medium),
                horizontalArrangement = Arrangement.Center
            ) {

                Icon(
                    painter = painterResource(id = R.drawable.avatar),
                    contentDescription = "Avatar",
                    tint = Color.White,
                    modifier = Modifier

                        .size(90.dp)

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
                        .absoluteOffset(0.dp, -45.dp)
                        .fillMaxWidth()
                        .weight(7f)
                    , horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceAround
                ) {

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(10f)


                            .padding(0.dp, 30.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceAround
                    ) {
                        Text_Field(R.drawable.email,"Email")
                        Text_Field(idIcon = R.drawable.padlock,"Password", PasswordVisualTransformation())
                        Text_Field(idIcon = R.drawable.padlock,"Password Repeat", PasswordVisualTransformation())
                        Text_Field(idIcon = R.drawable.avatar,"Full Name", PasswordVisualTransformation())
                        Row(
                            Modifier
                                .fillMaxWidth(0.7f)
                                , horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {

                                Icon(painter = painterResource(id = R.drawable.avatar), contentDescription ="" ,
                                    tint = Color.White,
                                    modifier = Modifier
                                        .size(80.dp)
                                        .clip(CircleShape)
                                        .background(MaterialTheme.colorScheme.secondaryContainer)
                                        .border(
                                            BorderStroke(1.dp, Color.White), shape = CircleShape
                                        )

                                        .shadow(0.dp))

                            Text("Choose Image", style = MaterialTheme.typography.bodyMedium,color=MaterialTheme.colorScheme.onBackground)
                        }
                    }
                    Row(modifier= Modifier
                        .fillMaxWidth()
                        .weight(1f)
                      , horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically){
                            Button(onClick = { /*TODO*/ }, modifier = Modifier.fillMaxWidth(0.5f)) {
                                Text("Sign up", style = MaterialTheme.typography.bodyLarge)
                            }
                    }
                }


            }


    }
}