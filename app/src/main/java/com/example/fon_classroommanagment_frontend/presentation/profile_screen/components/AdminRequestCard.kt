package com.example.fon_classroommanagment_frontend

import android.graphics.Bitmap
import android.media.Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fon_classroommanagment_frontend.domain.model.UserRole
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun AdminRequestCard(
    fillName: String,
    image:Bitmap?,
    numberOfRequests: Long?,
    onClick:()->Unit
) {

  val  circleBorderColor=MaterialTheme.colorScheme.tertiary
    Column(Modifier.padding(10.dp)) {


        Card(modifier = Modifier
            .padding(10.dp)
            .clickable { onClick() }) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Box() {
                    if(image==null)
                    Image(
                        painter = painterResource(R.drawable.classroomimage),
                        contentDescription = "avatar",
                        contentScale = ContentScale.Crop,            // crop the image if it's not a square
                        modifier = Modifier
                            .drawBehind {
                                drawCircle(
                                    circleBorderColor,
                                    radius = 40f,
                                    center = Offset(
                                        2 * this.center.x,
                                        this.center.y / 2f
                                    )
                                )

                            }
                            .size(100.dp)
                            .clip(CircleShape)

                            .border(
                                2.dp,
                                MaterialTheme.colorScheme.tertiary,
                                CircleShape
                            )

                    )
                    else
                        Image(
                        bitmap = image.asImageBitmap(),
                        contentDescription = "avatar",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .drawBehind {
                                drawCircle(
                                    circleBorderColor,
                                    radius = 40f,
                                    center = Offset(
                                        2 * this.center.x,
                                        this.center.y / 2f
                                    )
                                )

                            }
                            .size(100.dp)
                            .clip(CircleShape)

                            .border(
                                2.dp,
                                MaterialTheme.colorScheme.tertiary,
                                CircleShape
                            )

                    )

                    Text(
                        numberOfRequests.toString(),
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .absoluteOffset(50.dp, -27.dp),
                        color = MaterialTheme.colorScheme.onTertiary
                    )

                }

                Text(
                    fillName,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )

            }
        }
    }
    }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmployeeCard(
    fillName:String,
    image: Bitmap?,
    premissionText:String,
    onClick: () -> Unit
    ){
    Card(modifier = Modifier
        .padding(10.dp)
        .clickable {
            onClick()
        }) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Box() {
                if(image==null)
                    Image(
                        painter = painterResource(R.drawable.classroomimage),
                        contentDescription = "avatar",
                        contentScale = ContentScale.Crop,            // crop the image if it's not a square
                        modifier = Modifier
                            .drawBehind {


                            }
                            .size(100.dp)
                            .clip(CircleShape)
                            // clip to the circle shape
                            .border(
                                2.dp,
                                MaterialTheme.colorScheme.tertiary,
                                CircleShape
                            )
                        // add a border (optional)
                    )
                else
                    Image(
                        bitmap = image.asImageBitmap(),
                        contentDescription = "avatar",
                        contentScale = ContentScale.Crop,            // crop the image if it's not a square
                        modifier = Modifier
                            .drawBehind {


                            }
                            .size(100.dp)
                            .clip(CircleShape)
                            // clip to the circle shape
                            .border(
                                2.dp,
                                MaterialTheme.colorScheme.tertiary,
                                CircleShape
                            )
                        // add a border (optional)
                    )
            }

            Text(
                fillName,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                premissionText,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
            )

        }
    }


}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmployeeCardRoles(onClick: () -> Unit,
                      idUser:String,
                      userRoles: List<UserRole>,
                      changeRoleTo:(Long,UUID)->Unit

) {
    Card(modifier = Modifier
        .padding(10.dp)
        .clickable {
            onClick()
        }) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Change Role")

            userRoles.forEach {
                OutlinedButton(modifier = Modifier.fillMaxWidth(),onClick = { changeRoleTo(it.id, UUID.fromString(idUser)) }) {
                    Text(it.name)
                }
            }

        }

    }
}


