package com.example.fon_classroommanagment_frontend

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.fon_classroommanagment_frontend.common.Constants
import com.example.fon_classroommanagment_frontend.domain.navigation.Screen
import com.example.fon_classroommanagment_frontend.domain.model.AppointmentStatus
import com.example.fon_classroommanagment_frontend.presentation.common.bars.Components.cards.AppointmentProfileCard
import com.example.fon_classroommanagment_frontend.presentation.common.bars.Components.input.AppointmentInput
import com.example.fon_classroommanagment_frontend.presentation.profile_screen.ProfileViewModel
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Profile_Screen(

    navHostController: NavHostController,
    profileViewModel: ProfileViewModel

) {
    val userDetails by profileViewModel.userDetails
    var shouldShowMyRequest by remember {
        mutableStateOf(false)
    }
    var shouldShowResetPassword by remember {
        mutableStateOf(false)
    }
    var shouldShowResetEmail by remember {
        mutableStateOf(false)
    }
    var shouldShowOptions by remember {
        mutableStateOf(false)
    }
    var shouldShowRequests by remember {
        mutableStateOf(false)
    }
    val userImage by remember {
        mutableStateOf(profileViewModel.byteArrayToBitmap())
    }
    val uiState = profileViewModel.networkState.value
    val coroutineScope = rememberCoroutineScope()
    val scaffoldState  = rememberScaffoldState()
    val deleteState = profileViewModel.deleteState
    val optionsState = profileViewModel.optionsState

    val animateheightMyRequests= animateDpAsState(targetValue = if(shouldShowMyRequest) (profileViewModel.appointmentsForUser.size*120).dp else 0.dp)
    val animatepaddingMyRequests= animateDpAsState(targetValue = if(shouldShowMyRequest) 10.dp else 0.dp)
    val animateheightRequests= animateDpAsState(targetValue = if(shouldShowRequests) (profileViewModel.appointmentsRequested.size*200).dp else 0.dp)
    val animateheightChangePassword= animateDpAsState(targetValue = if(shouldShowResetPassword) 100.dp else 0.dp)
    val animateheithtChangeEmail= animateDpAsState(targetValue = if(shouldShowResetEmail) 100.dp else 0.dp)
    val animateheightOptions= animateDpAsState(targetValue = if(shouldShowOptions)  200.dp else 0.dp)

   LaunchedEffect(key1 = true ){
       profileViewModel.getUserAppointments()
   }
    LaunchedEffect(key1 = deleteState.value) {
        if (deleteState.value.isError) {
            coroutineScope.launch {  scaffoldState.snackbarHostState.showSnackbar("Please check your internet")}
        } else if (deleteState.value.success) {
            coroutineScope.launch { scaffoldState.snackbarHostState.showSnackbar("Deleted successfully") }
        }
    }

    LaunchedEffect(key1 = optionsState.value){
        if(optionsState.value.isError){
            coroutineScope.launch { scaffoldState.snackbarHostState.showSnackbar("Something went wrong") }

        }
        else if(optionsState.value.success){
            coroutineScope.launch { scaffoldState.snackbarHostState.showSnackbar("Changed successfully") }

        }
        profileViewModel.restartChangedState()

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
                        style = MaterialTheme.typography.headlineMedium, color = MaterialTheme.colorScheme.onBackground
                    )
                    Text(userDetails.typeName, style = MaterialTheme.typography.headlineSmall, color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f))

                }
            }

            item {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    if (profileViewModel.isAdmin.value) {
                        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                            Box(modifier = Modifier.padding(10.dp, 0.dp)) {
                                Item(
                                    R.drawable.callendar,
                                    "Requests",
                                    true,
                                    profileViewModel.appointmentsRequested.size,
                                    R.drawable.refresh,
                                    null,
                                    { profileViewModel.getRequestedAppointments() },
                                    { shouldShowRequests = !shouldShowRequests })
                            }
                            Divider(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .border(1.dp, MaterialTheme.colorScheme.onBackground)
                            )

                        }
                        AppointmentList(profileViewModel, animateheightRequests.value) {

                                id ->
                            navHostController.navigate(Screen.AdminRequestsScreen.route + "/${id}")
                        }
                    }
                    Column(modifier = Modifier.clickable {
                        profileViewModel.getUserAppointments()
                    }) {


                        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {

                            Box(modifier = Modifier
                                .padding(10.dp, 0.dp)
                                .clickable {
                                    profileViewModel.getUserAppointments()
                                }) {
                                Item(
                                    R.drawable.callendar,
                                    "Appointments",
                                    true,
                                    profileViewModel.appointmentsForUser.size
                                )
                                {
                                    shouldShowMyRequest = !shouldShowMyRequest
                                }
                            }

                            Divider(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .border(1.dp, MaterialTheme.colorScheme.onBackground)
                            )

                        }
                        AppointmentListDissmisable(
                            profileViewModel,
                            animateheightMyRequests.value,
                            animatepaddingMyRequests.value
                        )
                    }
                }
                Column(verticalArrangement = Arrangement.spacedBy(0.dp)) {

                        Box(modifier = Modifier.padding(10.dp,10.dp)) {

                            Item(R.drawable.settings, "Settings", false, onClick = {

                                shouldShowOptions=!shouldShowOptions
                            })
                        }


                        Column(modifier=Modifier.height(animateheightOptions.value)) {
                                optionsField("Email",
                                    KeyboardType.Email,
                                    "Email",

                                    animateheithtChangeEmail.value,
                                trailingIcon = R.drawable.arrow_down_dropdown,
                                    {shouldShowResetEmail=!shouldShowResetEmail},
                                {email->profileViewModel.changeEmail(email)},shouldShowResetEmail)

                                optionsField(
                                    "Password",
                                    KeyboardType.Text,
                                    "Password",animateheightChangePassword.value
                                ,R.drawable.arrow_down_dropdown,{shouldShowResetPassword=!shouldShowResetPassword},
                                {password->profileViewModel.changePassword(password)},shouldShowResetPassword)
                            }

                        Divider(
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(1.dp, MaterialTheme.colorScheme.onBackground)
                        )
                    }
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {

                        Box(modifier = Modifier.padding(10.dp, 10.dp)) {

                            Item(R.drawable.logout, "Logout", false, onClick = {
                               profileViewModel.logout()
                                navHostController.navigate(Screen.LoginScreen.route)
                            })
                        }
                    }
                }
            }
        }
    }


@Composable
fun optionsField(
    title: String,
    keyboardType: KeyboardType,
    hint: String,
    animateheithtChangeEmail: Dp,
    trailingIcon:Int?=null,
    changeVisiblity:()->Unit,
    onChangeClicked:(String)->Unit,
    show:Boolean

) {
    var toChange by remember {
        mutableStateOf("")
    }

    Column(modifier = Modifier) {


        Row(
            Modifier
                .fillMaxWidth()

                .padding(10.dp, 0.dp)
                .clickable {
                    changeVisiblity()
                }
       ,
        verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
            Text(title, style = MaterialTheme.typography.bodyMedium, )
            if(trailingIcon!=null)
            Icon(painter = painterResource(id = trailingIcon), contentDescription ="", modifier = Modifier.size(24.dp) )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(animateheithtChangeEmail), horizontalAlignment = Alignment.CenterHorizontally
        ) {


            //AppointmentInput(text = "", onTextChange = {}, hint = hint, keyboardType = keyboardType)
            if(show) {
                androidx.compose.material3.TextField(
                    value = toChange,
                    onValueChange = { toChange = it })
                androidx.compose.material3.TextButton(onClick = { onChangeClicked(toChange) }) {
                    Text("Change")
                }
            }
        }
    }


}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun AppointmentListDissmisable(profileViewModel: ProfileViewModel, animateheightMyRequests: Dp, animatepaddingMyRequests: Dp) {
    Box(modifier=Modifier.height(animateheightMyRequests)) {
        LazyColumn(userScrollEnabled = false) {
            items(profileViewModel.appointmentsForUser, key = { it -> it.id }) {

                val dismissState = rememberDismissState()
                LaunchedEffect(key1 = dismissState.isDismissed(DismissDirection.EndToStart)) {
                    if (dismissState.isDismissed(DismissDirection.EndToStart))
                        profileViewModel.deleteAppointment(it)
                }
                Box(
                    modifier = Modifier
                        .padding(animatepaddingMyRequests)


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

                            when (it.state) {
                                Constants.APPROVED_KEY -> AppointmentStatus.Accepted(isSystemInDarkTheme())
                                Constants.PENDING_KEY -> AppointmentStatus.Pending(
                                    isSystemInDarkTheme())
                                else -> AppointmentStatus.Declined(
                                    isSystemInDarkTheme())
                            },
                            it
                        )
                    }

                }
            }
        }
    }
}



@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppointmentList(profileViewModel: ProfileViewModel,
                    animateheightMyRequests: Dp,
                    onItemClick :(Long)->Unit) {
    val widthDp = LocalContext.current.resources.displayMetrics.run { widthPixels / density }
    Box(modifier=Modifier.height(animateheightMyRequests)) {
        LazyVerticalGrid(GridCells.Adaptive(widthDp.dp/2),userScrollEnabled = false) {
            items(profileViewModel.appointmentsRequested) {

                      AdminRequestCard("${it.firstName} ${it.lastName}",it.number_of_requests){onItemClick(it.id)}

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
    requets: Int?=null,
    trailingIcon:Int?=null,
    onIconClicked :()->Unit={},
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



                if(requets!=null) {
                    Text_Circle(
                        MaterialTheme.colorScheme.tertiary,
                        MaterialTheme.colorScheme.onTertiary,
                        circleTextNumber
                    )
                }
                if(trailingIcon!=null)
                    Icon(
                        painter = painterResource(id = trailingIcon),
                        contentDescription = "Refres Icon",
                        modifier = Modifier
                            .size(24.dp)
                            .clickable { onIconClicked() },
                        tint = MaterialTheme.colorScheme.onBackground,

                    )
                }
            }
    }


@Composable
fun Text_Circle(colorCircle:Color,colorText:Color,value:Int){
    Text(value.toString(),style=MaterialTheme.typography.bodyMedium, modifier = Modifier.drawBehind {
        drawCircle(colorCircle,radius = this.size.maxDimension/2)
    }, color = colorText)
}