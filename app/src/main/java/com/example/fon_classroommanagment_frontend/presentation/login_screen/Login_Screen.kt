package com.example.fon_classroommanagment_frontend

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.fon_classroommanagment_frontend.common.Response
import com.example.fon_classroommanagment_frontend.common.Screen
import com.example.fon_classroommanagment_frontend.data.repository.AuthRepositoryImpl
import com.example.fon_classroommanagment_frontend.presentation.common.bars.Components.IconRoundBorder
import com.example.fon_classroommanagment_frontend.presentation.common.bars.Components.input.Text_Field
import com.example.fon_classroommanagment_frontend.presentation.login_screen.LoginViewModel


@Composable
 fun Login_Screen(
    navController: NavHostController,
    loginViewModel:  LoginViewModel= hiltViewModel(),


    ) {

    var emailText by remember{ mutableStateOf("")}
    var passwordText by remember{ mutableStateOf("")}
    val state= loginViewModel.state
    LaunchedEffect(loginViewModel){
        loginViewModel.authResult.collect{result->
            when(result){
                is Response.Success->{navController.navigate(route = Screen.MainScreen.route) }
                is Response.Loading->{
                    Log.i("cao","Loading")}

                else -> { }
            }
        }
    }
    Column() {
        //naslov
        Row(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.secondaryContainer),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Login", style = MaterialTheme.typography.displayLarge, color = MaterialTheme.colorScheme.onSecondaryContainer)
        }
        Column(
            modifier = Modifier
                //=.clip(MaterialTheme.shapes.medium)
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

                IconRoundBorder(R.drawable.avatar)



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

                        Text_Field(emailText,{emailText=it},R.drawable.email,"Email", errorMessage = loginViewModel.errorMessageEmail)
                        Text_Field(passwordText,{passwordText=it},idIcon = R.drawable.padlock,"Password", PasswordVisualTransformation(), errorMessage =loginViewModel.errorMessagePassword)
                       
                    }
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically){
                        Button(onClick = { loginViewModel.Login(emailText,passwordText)},modifier= Modifier
                            .fillMaxWidth(0.5f)
                            .height(50.dp)) {
                            Text("Login", style = MaterialTheme.typography.bodyLarge)
                        }
                    }
                }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(2f)
                            .padding(0.dp, 20.dp)
                            , horizontalArrangement = Arrangement.Center
                    ) {

                        OutlinedButton(onClick = {
                            navController.navigate(
                                route = Screen.RegisterScreen.route
                            ) },modifier= Modifier
                            .fillMaxWidth(0.5f)
                            .height(50.dp)) {
                            Text("Sign ip", style = MaterialTheme.typography.bodyLarge)
                        }
                    }


                }

        }
    }



}