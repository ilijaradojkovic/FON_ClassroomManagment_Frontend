package com.example.fon_classroommanagment_frontend

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.fon_classroommanagment_frontend.common.Screen
import com.example.fon_classroommanagment_frontend.data.remote.dto.UserRegistrationDTO
import com.example.fon_classroommanagment_frontend.presentation.common.bars.Components.IconRoundBorder
import com.example.fon_classroommanagment_frontend.presentation.common.bars.Components.buttons.ButtonWithIcon
import com.example.fon_classroommanagment_frontend.presentation.common.bars.Components.input.RoundImage
import com.example.fon_classroommanagment_frontend.presentation.common.bars.Components.input.Text_Field
import com.example.fon_classroommanagment_frontend.presentation.signin_screen.RegisterViewModel

@Composable
 fun SignUp_Screen(
    navController: NavHostController,
    registerViewModel: RegisterViewModel = hiltViewModel(),
    ) {
    var emailText by remember{ mutableStateOf("") }
    var passwordText by remember{ mutableStateOf("") }
    var passwordRepeatText by remember{ mutableStateOf("")}
    var fullNameText by remember{ mutableStateOf("")}

    LaunchedEffect(key1 = registerViewModel.canNavigate){
        if(registerViewModel.canNavigate){
            registerViewModel.userRegistrationDTO.let {
                navController.currentBackStackEntry?.arguments?.putParcelable("registerObject", registerViewModel.userRegistrationDTO)
                navController.navigate(route = Screen.AditionalInfoScreen.route)
            }
        }
    }
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(modifier= Modifier
            .fillMaxWidth()
            .weight(1f)
            .background(MaterialTheme.colorScheme.secondaryContainer), verticalAlignment = Alignment.CenterVertically) {
            Row(modifier = Modifier
                .weight(1f)
              , ){
                IconButton(onClick = { navController.navigate(Screen.LoginScreen.route) }) {
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
                        Text_Field(emailText,{emailText=it},R.drawable.email,"Email", errorMessage = registerViewModel.errorMessageEmail)
                        Text_Field(passwordText,{passwordText=it},idIcon = R.drawable.padlock,"Password", PasswordVisualTransformation(), errorMessage = registerViewModel.errorMessagePassword)
                        Text_Field(passwordRepeatText,{passwordRepeatText=it},idIcon = R.drawable.padlock,"Password Repeat", PasswordVisualTransformation(), errorMessage = registerViewModel.errorMessagePassword)
                        Text_Field(fullNameText,{fullNameText=it},idIcon = R.drawable.avatar,"Full Name", errorMessage = registerViewModel.errorFullName)
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
                        ButtonWithIcon(text = "Advance", icon =R.drawable.advance ) {
                            registerViewModel.Register(emailText,passwordText,passwordRepeatText,fullNameText)
                            //navigateToLogin()
                        }
                    }
                }


            }


    }
}