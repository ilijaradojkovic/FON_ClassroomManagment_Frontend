package com.example.fon_classroommanagment_frontend

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieConstants
import com.example.fon_classroommanagment_frontend.domain.navigation.Screen
import com.example.fon_classroommanagment_frontend.presentation.admin_request_screen.AdminRequestsViewModel
import com.example.fon_classroommanagment_frontend.presentation.common.bars.Components.LottieAnimation

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AdminsRequestScreen(
    navHostController: NavHostController,
    id:Long,
    adminRequestsViewModel: AdminRequestsViewModel = hiltViewModel()

){

    val userDetails by adminRequestsViewModel.userDetails
    val userRequests = adminRequestsViewModel.userRequests
    val scaffoldState = rememberScaffoldState()
    val uiActionState by adminRequestsViewModel.uiStateActionPefromed
    LaunchedEffect(key1 = true){
        
        adminRequestsViewModel.getUserDetails(id)
        adminRequestsViewModel.getRequests(id)
    }
    LaunchedEffect(key1 = uiActionState){
        if(uiActionState.isError){
            scaffoldState.snackbarHostState.showSnackbar("Please check your internet connection")
        }
        else if(uiActionState.success){
            scaffoldState.snackbarHostState.showSnackbar("Success")

        }
    }

    if(adminRequestsViewModel.uiStateRequests.value.isError){
        No_Internet_Screen {
            navHostController.navigate(Screen.BottomBarScreens.UserProfileScreen.route)
        }
    }
    else
        androidx.compose.material.Scaffold(scaffoldState=scaffoldState) {


            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(2f)
                ) {
                    Row(
                        modifier = Modifier
                            .weight(2f)
                            .fillMaxHeight(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.avatar),
                            contentDescription = "Profile_Image",
                            modifier = Modifier
                                .clip(CircleShape)
                                .border(2.dp, Color.White, shape = CircleShape)
                        )


                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(2f)
                            .fillMaxHeight()
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(),
                            verticalArrangement = Arrangement.SpaceAround,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    "${userDetails.firstName} ${userDetails.lastName}",
                                    style = MaterialTheme.typography.titleLarge,
                                    color = MaterialTheme.colorScheme.onBackground
                                )
                                Text(
                                    userDetails.type,
                                    style = MaterialTheme.typography.titleSmall,
                                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
                                )

                            }
                            OutlinedButton(onClick = {
                                Log.i("cao", "clicked")
                                adminRequestsViewModel.confirmAllAppointments()
                            }) {
                                Text("Approve All")
                            }
                        }
                    }
                }
                Row(
                    modifier = Modifier

                        .fillMaxWidth()
                        .weight(5f)
                        .padding(10.dp)
                        .clip(MaterialTheme.shapes.extraLarge),
                    horizontalArrangement = Arrangement.Center
                ) {

                    if (adminRequestsViewModel.uiStateRequests.value.success)
                        LazyColumn() {
                            items(userRequests, key = { item -> item.hashCode() }) { request ->
                                Column(modifier = Modifier.height(200.dp)) {
                                    ApproveAppointmentCard(request,
                                        { adminRequestsViewModel.confirmAppointment(request.id) },
                                        { adminRequestsViewModel.declineAppointment(request.id) })
                                }
                                Spacer(modifier = Modifier.height(15.dp))
                            }
                        }
                    else if (adminRequestsViewModel.uiStateRequests.value.isLoading) {
                        Box(modifier = Modifier.fillMaxWidth(0.3f)) {
                            LottieAnimation(
                                lottieAnim = R.raw.loading_dialog,
                                iterations = LottieConstants.IterateForever
                            )
                        }
                    }


                }

            }
        }
}