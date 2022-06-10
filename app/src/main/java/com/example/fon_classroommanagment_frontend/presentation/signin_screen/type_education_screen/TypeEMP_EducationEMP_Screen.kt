package com.example.fon_classroommanagment_frontend.presentation.signin_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.fon_classroommanagment_frontend.R
import com.example.fon_classroommanagment_frontend.common.Screen
import com.example.fon_classroommanagment_frontend.data.remote.dto.UserRegistrationDTO
import com.example.fon_classroommanagment_frontend.presentation.common.bars.Components.buttons.ButtonWithIcon
import com.example.fon_classroommanagment_frontend.presentation.signin_screen.components.ChoiseItem
import com.example.fon_classroommanagment_frontend.presentation.signin_screen.type_education_screen.TypeEducationViewModel


@Composable
fun TypeEMP_EducationEMP_Screen(
    registerObject: UserRegistrationDTO,

    typeEducationViewModel: TypeEducationViewModel= hiltViewModel(),
    navHostController: NavHostController
) {
    val selectedValueTitle = remember { mutableStateOf(1) }
    val isSelectedItemTitle: (Int) -> Boolean = { selectedValueTitle.value == it }
    val onChangeStateTitle: (Int) -> Unit = { selectedValueTitle.value = it }


    val selectedValueType = remember { mutableStateOf(1) }
    val isSelectedItemType: (Int) -> Boolean = { selectedValueType.value == it }
    val onChangeStateType: (Int) -> Unit = { selectedValueType.value = it }


    val titles = typeEducationViewModel.titles
    val types = typeEducationViewModel.types

    val dialog by typeEducationViewModel.dialog

    LaunchedEffect(true){
        typeEducationViewModel.registerObject=registerObject
    }


    Column(modifier= Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)
        .padding(10.dp)) {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier
                .weight(1f)

        ) {
            if (dialog) {
                AlertDialog(
                    onDismissRequest = {
                        //openDialog.value = false
                    },
                    title = {
                        Text(text = "Registration Successful!")
                    },
                    text = {
                        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {

                            Text("Confirmation email has been send,please check your email and complete registration", textAlign = TextAlign.Center)

                        }
                    },

                dismissButton = {
                        Row(
                            modifier = Modifier.padding(all = 8.dp),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Button(
                                modifier = Modifier.fillMaxWidth(),
                                onClick = { navHostController.navigate(Screen.LoginScreen.route) }
                            ) {
                                Text("Sure!", style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.onPrimary)
                            }
                        }
                    }
                , confirmButton = {})
            }
                Text(
                    "Education",
                    style = MaterialTheme.typography.displaySmall,
                    color = MaterialTheme.colorScheme.onBackground
                )
                for (i in 0 until titles.size) {
                    ChoiseItem(
                        titles[i].name,
                        isSelectedItemTitle(titles[i].id)
                    ) { onChangeStateTitle(titles[i].id) }
                }


            }
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier
                    .weight(1f)

            ) {
                Text(
                    "Type",
                    style = MaterialTheme.typography.displaySmall,
                    color = MaterialTheme.colorScheme.onBackground
                )
                for (i in 0 until types.size) {
                    ChoiseItem(types[i].name, isSelectedItemType(types[i].id)) {
                        onChangeStateType(
                            types[i].id
                        )
                    }
                }


            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp), contentAlignment = Alignment.Center
            ) {
                Button(
                    onClick = {
                        typeEducationViewModel.registerObject.type =
                            types[selectedValueType.value - 1]
                        typeEducationViewModel.registerObject.title =
                            titles[selectedValueTitle.value - 1]
                        typeEducationViewModel.Register()


                    }, modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .height(50.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Sign in", style = MaterialTheme.typography.bodyLarge)

                    }
                }
            }
        }
    }
