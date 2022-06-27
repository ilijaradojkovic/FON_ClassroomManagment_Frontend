package com.example.fon_classroommanagment_frontend

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.fon_classroommanagment_frontend.common.Screen
import com.example.fon_classroommanagment_frontend.domain.model.AppointmentStatus
import com.example.fon_classroommanagment_frontend.presentation.common.bars.Components.cards.AppointmentProfileCard
import com.example.fon_classroommanagment_frontend.presentation.profile_screen.ProfileViewModel
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun Profile_Screen(

    navHostController: NavHostController,
    profileViewModel: ProfileViewModel

) {
    val userDetails by profileViewModel.userDetails
    var shouldShow by remember {
        mutableStateOf(false)
    }
    val userImage by remember {
        mutableStateOf(profileViewModel.byteArrayToBitmap())
    }
    val uiState = profileViewModel.networkState.value
    val coroutineScope = rememberCoroutineScope()
    val scaffoldState  = rememberScaffoldState()
    val deleteState = profileViewModel.deleteState

val animateheightMyRequests= animateDpAsState(targetValue = if(shouldShow) 100.dp else 0.dp)
val animatepaddingMyRequests= animateDpAsState(targetValue = if(shouldShow) 10.dp else 0.dp)
    LaunchedEffect(key1 = deleteState.value) {
        if (deleteState.value.isError) {
            coroutineScope.launch {  scaffoldState.snackbarHostState.showSnackbar("Please check your internet")}
        } else if (deleteState.value.success) {
            coroutineScope.launch { scaffoldState.snackbarHostState.showSnackbar("Deleted successfull") }
        }
    }


    if (uiState.isError) {
        No_Internet_Screen() { navHostController.navigate(Screen.MainScreen.route) }
    }else
    Scaffold(scaffoldState=scaffoldState,modifier = Modifier.fillMaxSize(), backgroundColor = MaterialTheme.colorScheme.background) {


        LazyColumn(
            Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background), verticalArrangement = Arrangement.SpaceAround
        ) {

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(), horizontalArrangement = Arrangement.Center
                ) {
                    // if(userImage==null)
                    Image(
                        painter = painterResource(id = R.drawable.avatar),
                        contentDescription = "Profile_Image",
                        modifier = Modifier
                            .clip(CircleShape)
                            .border(2.dp, Color.White, shape = CircleShape)
                    )
//            else
//                Image(bitmap = userImage!!,
//               contentDescription = "Profile_Image",
//               modifier = Modifier
//                   .clip(CircleShape)
//                   .border(2.dp, Color.White, shape = CircleShape))

                }
            }
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp, 10.dp), horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        "${userDetails.firstName}  ${userDetails.lastName}",
                        style = MaterialTheme.typography.headlineMedium
                    )
                    Text(userDetails.workType, style = MaterialTheme.typography.bodyMedium)

                }
            }

            item {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    if (profileViewModel.isAdmin.value) {
                        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                            Box(modifier = Modifier.padding(10.dp, 0.dp)) {
                                Item(R.drawable.callendar, "Requests", true, 5, R.drawable.refresh){profileViewModel.getRequestedAppointments()}
                            }
                            Divider(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .border(1.dp, MaterialTheme.colorScheme.onBackground)
                            )

                        }
                    }
                    Column() {


                    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {

                        Box(modifier = Modifier.padding(10.dp, 0.dp)) {
                            Item(
                                R.drawable.callendar,
                                "Appointments",
                                true,
                                profileViewModel.appointmentsForUser.size
                            )
                            {
                                shouldShow = !shouldShow
                            }
                        }

                        Divider(
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(1.dp, MaterialTheme.colorScheme.onBackground)
                        )

                    }
                    profileViewModel.appointmentsForUser.forEach {
                        val dismissState = rememberDismissState()
                        LaunchedEffect(key1 = dismissState.isDismissed(DismissDirection.EndToStart)) {
                            if (dismissState.isDismissed(DismissDirection.EndToStart))
                                profileViewModel.deleteAppointment(it)
                        }
                        Box(
                            modifier = Modifier
                                .padding(animatepaddingMyRequests.value)
                                .height(animateheightMyRequests.value)

                        ) {

                            SwipeToDismiss(
                                state = dismissState,
                                background = {
                                    val color = when (dismissState.dismissDirection) {

                                        DismissDirection.EndToStart -> MaterialTheme.colorScheme.errorContainer
                                        DismissDirection.StartToEnd -> Color.Transparent

                                        else -> {
                                            Color.Transparent
                                        }
                                    }

                                    Card(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(100.dp),
                                        colors = CardDefaults.elevatedCardColors(containerColor = color)
                                    ) {
                                        Row(
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .padding(0.dp, 0.dp, 15.dp, 0.dp),
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.End
                                        ) {
                                            Icon(
                                                Icons.Default.Delete,
                                                "",

                                                modifier = Modifier.size(24.dp),
                                                tint = MaterialTheme.colorScheme.onErrorContainer
                                            )
                                        }

                                    }

                                }, directions = setOf(DismissDirection.EndToStart)
                            ) {
                                AppointmentProfileCard(
                                    AppointmentStatus.Accepted(isSystemInDarkTheme()),
                                    it
                                )
                            }

                        }
                    }
                }
                    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {

                        Box(modifier = Modifier.padding(10.dp, 0.dp)) {

                            Item(R.drawable.settings, "Settings", false)
                        }


                        Divider(
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(1.dp, MaterialTheme.colorScheme.onBackground)
                        )
                    }
                    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {

                        Box(modifier = Modifier.padding(10.dp, 0.dp)) {

                            Item(R.drawable.logout, "Logout", false)
                        }
                    }
                }
            }



        }
    }
}


@Composable
fun Item(
    icon: Int,
    name: String,
    hasCircleText: Boolean,
    circleTextNumber: Int? = null,
    refresh: Int?=null,
    onClick:()->Unit={}
){
    Row(modifier = Modifier
        .fillMaxWidth()
        .clickable { onClick() }, verticalAlignment =Alignment.CenterVertically) {

        Row(modifier = Modifier) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = "SettingsIcon",
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))

        }



        Row(modifier = Modifier, horizontalArrangement = Arrangement.Center) {
            Text(name, style = MaterialTheme.typography.bodyMedium)

        }
        if(hasCircleText && circleTextNumber!=null  )
            Row(modifier = Modifier
                .weight(1f)

                .width(IntrinsicSize.Min)
                .padding(10.dp, 0.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {



                if(refresh!=null) {
                    Text_Circle(
                        MaterialTheme.colorScheme.tertiary,
                        MaterialTheme.colorScheme.onTertiary,
                        circleTextNumber
                    )

                    Icon(
                        painter = painterResource(id = refresh),
                        contentDescription = "Refres Icon",
                        modifier = Modifier.size(24.dp),
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
    }

}
@Composable
fun Text_Circle(colorCircle:Color,colorText:Color,value:Int){
    Text(value.toString(),style=MaterialTheme.typography.bodyMedium, modifier = Modifier.drawBehind {
        drawCircle(colorCircle,radius = this.size.maxDimension/2)
    }, color = colorText)
}