package com.example.fon_classroommanagment_frontend

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.DismissDirection
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.rememberDismissState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fon_classroommanagment_frontend.domain.model.AppointmentStatus
import com.example.fon_classroommanagment_frontend.presentation.common.bars.Components.cards.AppointmentProfileCard
import com.example.fon_classroommanagment_frontend.presentation.profile_screen.ProfileViewModel

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun Profile_Screen(

    profileViewModel: ProfileViewModel

) {
    val userDetails by profileViewModel.userDetails
    var shouldShow by remember {
        mutableStateOf(false)
    }
    val userImage by remember {
        mutableStateOf(profileViewModel.byteArrayToBitmap())
    }

    LazyColumn(
        Modifier
            .fillMaxHeight()
            .fillMaxWidth(), verticalArrangement = Arrangement.SpaceAround
    ) {

        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    , horizontalArrangement = Arrangement.Center
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
            if (true) {

                Item(R.drawable.callendar, "Requests", true, 5, R.drawable.refresh)
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, MaterialTheme.colorScheme.onBackground)
                )
            }
        }
        item {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {

                Box(modifier = Modifier.padding(10.dp, 0.dp)) {
                    Item(
                        R.drawable.callendar,
                        "Appointments",
                        true,
                        profileViewModel.appointmentsForUser.size
                    )
                    {
                        shouldShow=!shouldShow
                    }
                }

                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, MaterialTheme.colorScheme.onBackground)
                )
            }
        }
        if(shouldShow)
            items(profileViewModel.appointmentsForUser, key = {k->k.id}) {
                val dismissState = rememberDismissState()
                LaunchedEffect(key1 = dismissState.isDismissed(DismissDirection.EndToStart)) {
                    if (dismissState.isDismissed(DismissDirection.EndToStart))
                        profileViewModel.deleteAppointment(it)
                }
                Box(modifier = Modifier.padding(10.dp)) {

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




        item {

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
        }

        item {

            Box(modifier = Modifier.padding(10.dp, 0.dp)) {

                Item(R.drawable.logout, "Logout", false)
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
    Row(modifier = Modifier.fillMaxWidth().clickable { onClick() }, verticalAlignment =Alignment.CenterVertically) {

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