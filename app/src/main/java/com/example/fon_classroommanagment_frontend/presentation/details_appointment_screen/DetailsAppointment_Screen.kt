package com.example.fon_classroommanagment_frontend.presentation.details_appointment_screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieConstants
import com.example.fon_classroommanagment_frontend.CallendarPicker
import com.example.fon_classroommanagment_frontend.No_Internet_Screen
import com.example.fon_classroommanagment_frontend.R
import com.example.fon_classroommanagment_frontend.common.Constants
import com.example.fon_classroommanagment_frontend.domain.navigation.Screen
import com.example.fon_classroommanagment_frontend.presentation.appointment_insetion_screen.components.ClassroomInputChip
import com.example.fon_classroommanagment_frontend.presentation.common.bars.Components.LottieAnimation
import com.example.fon_classroommanagment_frontend.presentation.common.bars.Components.TextIconButton
import com.example.fon_classroommanagment_frontend.presentation.common.bars.Components.input.AppointmentInput
import com.example.fon_classroommanagment_frontend.presentation.common.bars.Components.input.AppointmentMultyLineInput
import com.example.fon_classroommanagment_frontend.presentation.common.bars.Components.input.AppointmetnComboBox
import com.example.fon_classroommanagment_frontend.presentation.common.bars.ErrorRegistrationDialog
import com.example.fon_classroommanagment_frontend.presentation.common.bars.SuccessRegistrationDialog
import com.foreverrafs.datepicker.state.rememberDatePickerState
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DetailsAppointment_Screen(
    navController:NavHostController,
    id:UUID,
    detailsAppointmentViewModel: DetailsAppointmentViewModel = hiltViewModel()
){
    val scrollableState = rememberScrollState()
    val datePickerState= rememberDatePickerState(detailsAppointmentViewModel.forDate)
val dialog = remember {
    mutableStateOf(false)
}
    val uiResponse by detailsAppointmentViewModel.uiResponseSave
    LaunchedEffect(key1 = uiResponse){
        if(uiResponse.isLoading || uiResponse.isError||uiResponse.success)
            dialog.value=true
    }
    LaunchedEffect(key1 = true ){
        detailsAppointmentViewModel.getAppointmentData(id)

    }
    var typeName= remember{ mutableStateOf("")}
    LaunchedEffect(key1 = detailsAppointmentViewModel.typeClass){
        typeName=detailsAppointmentViewModel.getTextSelected

    }
    val detailsReponse by detailsAppointmentViewModel.uiResponseDetails
    if(dialog.value){

            if (uiResponse.success) {

                SuccessRegistrationDialog(
                    uiResponse.isLoading,
                    toNavigate = {},
                    title = "Appointment Updated!",
                    body = "You successfuly updated your appointment,please wait for confirmation",
                    dismissButton = {
                        Row(
                            modifier = Modifier.padding(all = 8.dp),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Button(
                                modifier = Modifier.fillMaxWidth(),
                                onClick = {
                                    dialog.value=false
                                    detailsAppointmentViewModel.restart()
                                    navController.navigate(Screen.MainScreen.route)
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

            }
            else if (uiResponse.isLoading) {
                SuccessRegistrationDialog(
                    uiResponse.isLoading,
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
                                    dialog.value=false
                                    detailsAppointmentViewModel.restart()
                                    navController.navigate(Screen.MainScreen.route)
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
            }

            else {


                ErrorRegistrationDialog("Error", "Error occured please try again") {
                    dialog.value=false
                    detailsAppointmentViewModel.restart()
                    navController.navigate(Screen.MainScreen.route)
                }
            }
        }


    if(detailsReponse.success){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(scrollableState)
            .padding(0.dp, 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(0.8f)
        ) {
            AppointmentInput(detailsAppointmentViewModel.nameText,{detailsAppointmentViewModel.nameText=it},hint = "Name", errorText = detailsAppointmentViewModel.nameTextError, isScrolling = scrollableState.isScrollInProgress, explainedError = detailsAppointmentViewModel.nameTextErrorExplained)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(0.8f)
        ) {

            AppointmentInput(detailsAppointmentViewModel.reasonText,{detailsAppointmentViewModel.reasonText=it},hint = "Razlog",errorText = detailsAppointmentViewModel.reasonTextError,isScrolling = scrollableState.isScrollInProgress, explainedError = detailsAppointmentViewModel.reasonTextErrorExplained)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(0.8f)
        ) {
            AppointmentInput(detailsAppointmentViewModel.numAttendeesText,{detailsAppointmentViewModel.numAttendeesText=it},hint = "Broj prisutnih", keyboardType = KeyboardType.Number,errorText = detailsAppointmentViewModel.numAttendeesTextError,isScrolling = scrollableState.isScrollInProgress, explainedError =detailsAppointmentViewModel.numAttendeesTextErrorExplained)
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(0.8f)
        ) {
            AppointmetnComboBox(typeName.value,detailsAppointmentViewModel.appointmentTypes,detailsAppointmentViewModel.typeClassError) {
                detailsAppointmentViewModel.typeClass = it
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(0.8f)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ClassroomInputChip(detailsAppointmentViewModel.classroomsError,detailsAppointmentViewModel.classrooms,detailsAppointmentViewModel.classroomNames,onTextChange={detailsAppointmentViewModel.getAllClassroomNamesSearched(it)},true)


            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            CallendarPicker(datePickerState){detailsAppointmentViewModel.forDate=it}
        }

        Column(
            modifier = Modifier//date picker ima senku pa sam stavio samo pozadinu za ovaj ele prekrice je,nisam otkio jos kako se iskljucje senka
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
        ) {

            Column(
                modifier = Modifier

                    .fillMaxWidth(),

                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.clock),
                    contentDescription = "Clock icon",
                    modifier = Modifier.size(24.dp),
                    tint = MaterialTheme.colorScheme.onBackground
                )

                Text("${if (Constants.MIN_WORK_TIME.toString().length>1) "" else "0"}${Constants.MIN_WORK_TIME}h-${Constants.MAX_WORK_TIME}h", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onBackground)
            }
            Row(
                modifier = Modifier

                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.weight(1f),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Row(modifier = Modifier.fillMaxWidth(0.7f)) {
                        AppointmentInput(detailsAppointmentViewModel.startTime,{detailsAppointmentViewModel.startTime=it},hint = "Od", keyboardType = KeyboardType.Number,errorText = detailsAppointmentViewModel.startTimeError,isScrolling = scrollableState.isScrollInProgress, explainedError = detailsAppointmentViewModel.startTimeErrorExplained)
                    }

                }
                Row(
                    modifier = Modifier.weight(1f),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Row(modifier = Modifier.fillMaxWidth(0.7f)) {
                        AppointmentInput(detailsAppointmentViewModel.endTime,{detailsAppointmentViewModel.endTime=it},hint = "Do", keyboardType = KeyboardType.Number,errorText = detailsAppointmentViewModel.endTimeError,isScrolling = scrollableState.isScrollInProgress  , explainedError = detailsAppointmentViewModel.endTimeErrorExplained)
                    }
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            AppointmentMultyLineInput(detailsAppointmentViewModel.descriptionText,{detailsAppointmentViewModel.descriptionText=it},"Opis",errorText = detailsAppointmentViewModel.descriptionTextError, isScrolling= scrollableState.isScrollInProgress, explainedError = detailsAppointmentViewModel.descriptionTextErrorExplained)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Row(modifier = Modifier.fillMaxWidth(0.4f)) {


                    TextIconButton("Save", R.drawable.save){
                        detailsAppointmentViewModel.saveAppointment()


                    }
            }

        }

    }
    }else if(detailsReponse.isError){
        No_Internet_Screen {
            navController.navigate(Screen.MainScreen.route)
        }
    }
    else {
        LottieAnimation(lottieAnim = R.raw.loading_dialog, iterations = LottieConstants.IterateForever)
    }
}