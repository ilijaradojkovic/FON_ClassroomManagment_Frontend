package com.example.fon_classroommanagment_frontend

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.fon_classroommanagment_frontend.Components.IconRoundBorder
import com.example.fon_classroommanagment_frontend.Components.input.RoundImage
import com.example.fon_classroommanagment_frontend.Components.input.Text_Field

@Composable
 fun SignUp_Screen(navigateToLogin: () -> Unit) {
    var emailText by remember{ mutableStateOf("") }
    var passwordText by remember{ mutableStateOf("") }
    var passwordRepeatText by remember{ mutableStateOf("")}
    var fullNameText by remember{ mutableStateOf("")}

    Column(modifier = Modifier.fillMaxWidth()) {
        Row(modifier= Modifier
            .fillMaxWidth()
            .weight(1f)
            .background(MaterialTheme.colorScheme.secondaryContainer), verticalAlignment = Alignment.CenterVertically) {
            Row(modifier = Modifier
                .weight(1f)
              , ){
                IconButton(onClick = { navigateToLogin() }) {
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

             IconRoundBorder(icon = R.drawable.avatar)
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
                        Text_Field(emailText,{emailText=it},R.drawable.email,"Email")
                        Text_Field(passwordText,{passwordText=it},idIcon = R.drawable.padlock,"Password", PasswordVisualTransformation())
                        Text_Field(passwordRepeatText,{passwordRepeatText=it},idIcon = R.drawable.padlock,"Password Repeat", PasswordVisualTransformation())
                        Text_Field(fullNameText,{fullNameText=it},idIcon = R.drawable.avatar,"Full Name", PasswordVisualTransformation())
                        Row(
                            Modifier
                                .fillMaxWidth(0.7f)
                                , horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {

                               RoundImage(image = R.drawable.default_user_image)

                            Text("Choose Image", style = MaterialTheme.typography.bodyMedium,color=MaterialTheme.colorScheme.onBackground)
                        }
                    }
                    Row(modifier= Modifier
                        .fillMaxWidth()
                        .weight(1f)
                      , horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically){
                        Button(onClick = {navigateToLogin() },modifier= Modifier
                            .fillMaxWidth(0.5f)
                            .height(50.dp)) {
                            Text("Sign in", style = MaterialTheme.typography.bodyLarge)
                        }
                    }
                }


            }


    }
}