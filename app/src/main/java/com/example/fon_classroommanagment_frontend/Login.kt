package com.example.fon_classroommanagment_frontend

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.fon_classroommanagment_frontend.ui.theme.FON_ClassroomManagment_FrontendTheme
import com.example.fon_classroommanagment_frontend.ui.theme.Shapes

class Login {


    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        FON_ClassroomManagment_FrontendTheme {


            Column {
                //naslov
                Row(

                    modifier = Modifier
                        .weight(1f)

                        .fillMaxWidth(), horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Login", style = MaterialTheme.typography.displayLarge)
                }
                Column(

                    modifier = Modifier
                        .background(Color.White)

                        .weight(3f)
                        //  .clip(RoundedCornerShape(10.dp,10.dp,0.dp,0.dp))
                        .fillMaxWidth()

                        .background(MaterialTheme.colorScheme.inverseSurface)
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .offset(0.dp, -40.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {

                        Icon(
                            painter = painterResource(id = R.drawable.avatar),
                            contentDescription = "Login",
                            tint = Color.White,

                            modifier = Modifier
                                .size(90.dp)
                                .clip(CircleShape)
                                .background(MaterialTheme.colorScheme.background)// clip to the circle shape
                                .border(8.dp, Color.White, CircleShape)
                                .padding(10.dp)

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
                                .fillMaxWidth(), horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "Welcome!",
                                style = MaterialTheme.typography.displayMedium,
                                color = MaterialTheme.colorScheme.onBackground
                            )
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(40.dp, 0.dp)
                                .clip(RoundedCornerShape(30.dp))

                                .background(Color.Gray)
                                .border(2.dp, Color.White, CircleShape)

                                .padding(5.dp, 5.dp), horizontalArrangement = Arrangement.Center
                        ) {

                            Row(
                                modifier = Modifier
                                    .weight(1f), horizontalArrangement = Arrangement.Start
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.email),
                                    contentDescription = "Login",
                                    tint = MaterialTheme.colorScheme.secondary,

                                    modifier = Modifier
                                        .size(36.dp)
                                        .clip(CircleShape)
                                        .background(MaterialTheme.colorScheme.background)// clip to the circle shape
                                        .padding(7.dp)


                                )
                            }

                            Row(
                                modifier = Modifier
                                    .height(IntrinsicSize.Max)
                                    .weight(6f), horizontalArrangement = Arrangement.Start
                            ) {

                                Text("cao")
                            }


                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(40.dp, 0.dp)
                                .clip(RoundedCornerShape(30.dp))

                                .background(Color.Gray)
                                .border(2.dp, Color.White, CircleShape)

                                .padding(5.dp, 5.dp), horizontalArrangement = Arrangement.Center
                        ) {

                            Row(
                                modifier = Modifier
                                    .weight(1f), horizontalArrangement = Arrangement.Start
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.email),
                                    contentDescription = "Login",
                                    tint = MaterialTheme.colorScheme.secondary,

                                    modifier = Modifier
                                        .size(36.dp)
                                        .clip(CircleShape)
                                        .background(MaterialTheme.colorScheme.background)// clip to the circle shape
                                        .padding(7.dp)


                                )
                            }

                            Row(
                                modifier = Modifier
                                    .height(IntrinsicSize.Max)
                                    .weight(6f), horizontalArrangement = Arrangement.Start
                            ) {

                                Text("cao")
                            }


                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth(), horizontalArrangement = Arrangement.Center
                        ) {
                            Button(onClick = {}, modifier = Modifier.fillMaxWidth(0.5f)) {
                                Text("Login", style = MaterialTheme.typography.bodyLarge)
                            }
                        }


                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(), horizontalArrangement = Arrangement.Center
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

    }
    }
