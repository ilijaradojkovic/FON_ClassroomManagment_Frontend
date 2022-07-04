package com.example.fon_classroommanagment_frontend

import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.fon_classroommanagment_frontend.domain.navigation.Screen
import com.example.fon_classroommanagment_frontend.presentation.common.bars.Components.IconRoundBorder
import com.example.fon_classroommanagment_frontend.presentation.common.bars.Components.buttons.ButtonWithIcon
import com.example.fon_classroommanagment_frontend.presentation.common.bars.Components.input.RoundImage
import com.example.fon_classroommanagment_frontend.presentation.common.bars.Components.input.Text_Field
import com.example.fon_classroommanagment_frontend.presentation.common.bars.ErrorRegistrationDialog
import com.example.fon_classroommanagment_frontend.presentation.common.bars.SuccessRegistrationDialog
import com.example.fon_classroommanagment_frontend.presentation.login_screen.components.Password_Text_Field
import com.example.fon_classroommanagment_frontend.presentation.signin_screen.RegisterViewModel


@Composable
 fun SignUp_Screen(
    navController: NavHostController,
    registerViewModel: RegisterViewModel = hiltViewModel(),
    ) {
    val colorBcg=MaterialTheme.colorScheme.background

    var emailText by registerViewModel.emailText
    var passwordText by registerViewModel.passwordText
    var passwordRepeatText by registerViewModel.passwordRepeatText
    var fullNameText by registerViewModel.fullNameText

    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }

    val launcher = rememberLauncherForActivityResult(contract =
    ActivityResultContracts.GetContent()) { uri: Uri? ->
        imageUri = uri
    }
    val context = LocalContext.current
    val bitmap = registerViewModel.image
    val dialog by registerViewModel.dialog

    val registerState by registerViewModel.state
    LaunchedEffect(key1 = true ){
        registerViewModel.restart()

    }
//    LaunchedEffect(key1 = registerViewModel.canNavigate){
//        if(registerViewModel.canNavigate){
//            registerViewModel.userRegistrationDTO.let {
//                navController.currentBackStackEntry?.arguments?.putParcelable("registerObject", registerViewModel.userRegistrationDTO)
//
//                }
//        }
//    }
    if (dialog) {
        if (registerState.success) {

            SuccessRegistrationDialog(
                registerState.isLoading,
                toNavigate = {},
                title = "Registration Successful!",
                body = "Confirmation email has been send,please check your email and complete registration",
                dismissButton = {
                    Row(
                        modifier = Modifier.padding(all = 8.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Button(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = {
                                registerViewModel.restart()
                                navController.navigate(Screen.LoginScreen.route)
                            }
                        ) {
                            Text(
                                "Sure!",
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    }
                })

        } else if (registerState.isLoading) {
            SuccessRegistrationDialog(
                registerState.isLoading,
                toNavigate = {},
                title = "Registration Successful!",
                body = "Confirmation email has been send,please check your email and complete registration",
                dismissButton = {
                    Row(
                        modifier = Modifier.padding(all = 8.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Button(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = {
                                registerViewModel.restart()
                                navController.navigate(Screen.LoginScreen.route)
                            }
                        ) {
                            Text(
                                "Sure!",
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    }
                })
        } else {


            ErrorRegistrationDialog("Error", "Error occured please try again") {
                registerViewModel.restart()
                navController.navigate(
                    Screen.LoginScreen.route
                )
            }
        }
    }
    Column(modifier = Modifier.fillMaxWidth()

    ) {
        Row(modifier= Modifier
            .fillMaxWidth()
            .weight(1f)
            .background(MaterialTheme.colorScheme.secondaryContainer )


            , verticalAlignment = Alignment.CenterVertically) {
            Row(
                modifier = Modifier
                    .weight(1f)
                ,
            ){
                IconButton(onClick = { navController.navigate(Screen.LoginScreen.route) }) {
                    Icon(painter = painterResource(id = R.drawable.back), contentDescription ="Back", modifier = Modifier.size(24.dp))
                }
            }

        }
        Column(modifier= Modifier
            .fillMaxWidth()
            .weight(9f)
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
                        Text_Field(emailText,{emailText=it},R.drawable.email,hint="Email", errorMessage = registerViewModel.errorMessageEmail)
                        Password_Text_Field(passwordText,{passwordText=it},leadingIcon = R.drawable.padlock, trailingIcon = R.drawable.hide_password, trailingIconToggle = R.drawable.show_password,hint="Password", errorMessage = registerViewModel.errorMessagePassword)
                        Password_Text_Field(passwordRepeatText,{passwordRepeatText=it},leadingIcon = R.drawable.padlock,trailingIcon = R.drawable.hide_password,trailingIconToggle=R.drawable.show_password,hint="Password Repeat", errorMessage = registerViewModel.errorMessagePassword)
                        Text_Field(fullNameText,{fullNameText=it},leadingIcon = R.drawable.avatar,hint="Full Name", errorMessage = registerViewModel.errorFullName)
                        Box(
                            Modifier
                                //.fillMaxWidth(0.7f)
                                , contentAlignment = Alignment.Center) {


                            if(imageUri!=null){

                                if (Build.VERSION.SDK_INT < 28) {
                                    bitmap.value = MediaStore.Images
                                        .Media.getBitmap(context.contentResolver,imageUri)

                                } else {
                                    val source = ImageDecoder
                                        .createSource(context.contentResolver, imageUri!!)
                                    bitmap.value = ImageDecoder.decodeBitmap(source)
                                }

                                bitmap.value?.let {  btm ->
                                    RoundImage(image = btm.asImageBitmap())

                                }
                            }else{
                                val bm =
                                    BitmapFactory.decodeResource( context.resources,R.drawable.default_user_image)

                                RoundImage(image = bm.asImageBitmap())
                            }


                            IconButton(onClick = {  launcher.launch("image/*")}) {
                                Icon(painter = painterResource(id = R.drawable.camera), modifier = Modifier.size(24.dp), contentDescription = "")
                            }

                        }
                    }
                    Row(modifier= Modifier
                        .fillMaxWidth()
                        .weight(1f)
                      , horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically){
                        ButtonWithIcon(text = "Advance", icon =R.drawable.advance ) {
                            registerViewModel.Register()

                        }
                    }
                }


            }


    }
}