package com.example.fon_classroommanagment_frontend

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.*
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieConstants
import com.example.fon_classroommanagment_frontend.domain.navigation.Screen
import com.example.fon_classroommanagment_frontend.common.StoreUserEmail
import com.example.fon_classroommanagment_frontend.presentation.common.bars.Components.IconRoundBorder
import com.example.fon_classroommanagment_frontend.presentation.common.bars.Components.LottieAnimation
import com.example.fon_classroommanagment_frontend.presentation.common.bars.Components.input.Text_Field
import com.example.fon_classroommanagment_frontend.presentation.login_screen.LoginViewModel
import com.example.fon_classroommanagment_frontend.presentation.login_screen.components.Password_Text_Field


@Composable
 fun Login_Screen(
    navController: NavHostController,
    loginViewModel:  LoginViewModel= hiltViewModel(),
    ) {
    val context=LocalContext.current
    val colorBcg=MaterialTheme.colorScheme.background
    var emailText by remember{ mutableStateOf("")}
    var passwordText by remember{ mutableStateOf("")}
    val loginState by loginViewModel.state
    LaunchedEffect(key1 =loginState.success) {
        if(loginState.success){
            val dataStore = StoreUserEmail(context)
            dataStore.saveEmail(emailText)
            navController.navigate(route = Screen.MainScreen.route)
            loginViewModel.restart()

        }
    }
      
    Column(modifier = Modifier.animateContentSize(tween(500))) {
        //naslov
        Row(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.secondaryContainer),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(painter = painterResource(id = R.drawable.bcg_login), contentScale = ContentScale.Crop, alignment = Alignment.TopCenter, contentDescription = "", modifier = Modifier.fillMaxWidth())
        }
        Column(
            modifier = Modifier
                //=.clip(MaterialTheme.shapes.medium)

                .weight(3f)
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.secondaryContainer)
                .drawBehind {
                    val cornerRadius = CornerRadius(80f, 80f)
                    val path = Path().apply {
                        addRoundRect(
                            RoundRect(
                                rect = Rect(
                                    offset = Offset(0f, 0f),
                                    size = Size(size.width, size.height),
                                ),
                                topLeft = cornerRadius,
                                topRight = cornerRadius,
                            )
                        )
                    }
                    drawPath(path, color = colorBcg)
                }

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

                IconRoundBorder(R.drawable.user_default_light)


            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()

                    .weight(6f)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                       , horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    Text(
                        text = "Hello Again!",
                        style = MaterialTheme.typography.displaySmall,
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.offset(0.dp,-10.dp)
                    )

                    Text(
                        textAlign = TextAlign.Center,
                        text = "Login to your account",
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),

                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(3f)


                        , horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center
                ) {

                    Column(modifier = Modifier
                        .fillMaxWidth()

                        , horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(15.dp)) {

                        Text_Field(emailText,{emailText=it},R.drawable.email,hint="Email", errorMessage = loginViewModel.errorMessageEmail)
                        Password_Text_Field(passwordText,{passwordText=it},leadingIcon = R.drawable.padlock, trailingIcon = R.drawable.hide_password, trailingIconToggle = R.drawable.show_password,hint="Password", visualTransformation = PasswordVisualTransformation(), errorMessage =loginViewModel.errorMessagePassword)

                    }

                }


                        Column(
                           modifier= Modifier
                               .fillMaxWidth()
                               .weight(3f)

                               .padding(30.dp),
                           horizontalAlignment = Alignment.CenterHorizontally
                         ){

                            Column(modifier=Modifier.fillMaxHeight(0.7f), verticalArrangement = Arrangement.SpaceBetween) {
                                Button(onClick = { loginViewModel.Login(emailText,passwordText)},modifier= Modifier
                                    .fillMaxWidth(0.7f)
                                    .height(50.dp)) {
                                    if(loginState.isLoading)  LottieAnimation(lottieAnim = R.raw.loading_dialog, iterations = LottieConstants.IterateForever)
                                    else Text("Login", style = MaterialTheme.typography.bodyLarge)
                                }
                                OutlinedButton(onClick = {
                                    navController.navigate(
                                        route = Screen.RegisterScreen.route
                                    ) },modifier= Modifier
                                    .fillMaxWidth(0.7f)
                                    .height(50.dp)) {
                                    Text("Sign in", style = MaterialTheme.typography.bodyLarge)
                                }
                            }

                        }





                }

        }
    }



}