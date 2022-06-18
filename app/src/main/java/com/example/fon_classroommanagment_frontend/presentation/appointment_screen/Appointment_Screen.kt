package com.example.fon_classroommanagment_frontend.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.fon_classroommanagment_frontend.CallendarPicker
import com.example.fon_classroommanagment_frontend.presentation.common.bars.Components.TextIconButton
import com.example.fon_classroommanagment_frontend.presentation.common.bars.Components.input.AppointmentInput
import com.example.fon_classroommanagment_frontend.presentation.common.bars.Components.input.AppointmentMultyLineInput
import com.example.fon_classroommanagment_frontend.presentation.common.bars.Components.input.AppointmetnComboBox
import com.example.fon_classroommanagment_frontend.R
import com.example.fon_classroommanagment_frontend.common.Constants.MAX_WORK_TIME
import com.example.fon_classroommanagment_frontend.common.Constants.MIN_WORK_TIME
import com.example.fon_classroommanagment_frontend.presentation.appointment_screen.AppointmentCreationViewModel
import com.example.fon_classroommanagment_frontend.presentation.appointment_screen.components.ClassroomInputChip
import com.foreverrafs.datepicker.state.rememberDatePickerState

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Appointment_Screen(
    classroom:Long,
    navHostController: NavHostController,
    appointmentCreationViewModel: AppointmentCreationViewModel = hiltViewModel()
) {
    val scrollableState = rememberScrollState()
    val datePickerState= rememberDatePickerState()

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
            AppointmentInput(appointmentCreationViewModel.nameText,{appointmentCreationViewModel.nameText=it},hint = "Name", errorText = appointmentCreationViewModel.nameTextError, isScrolling = scrollableState.isScrollInProgress, explainedError = appointmentCreationViewModel.nameTextErrorExplained)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(0.8f)
        ) {

            AppointmentInput(appointmentCreationViewModel.reasonText,{appointmentCreationViewModel.reasonText=it},hint = "Razlog",errorText = appointmentCreationViewModel.reasonTextError,isScrolling = scrollableState.isScrollInProgress, explainedError = appointmentCreationViewModel.reasonTextErrorExplained)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(0.8f)
        ) {
            AppointmentInput(appointmentCreationViewModel.numAttendeesText,{appointmentCreationViewModel.numAttendeesText=it},hint = "Broj prisutnih", keyboardType = KeyboardType.Number,errorText = appointmentCreationViewModel.numAttendeesTextError,isScrolling = scrollableState.isScrollInProgress, explainedError =appointmentCreationViewModel.numAttendeesTextErrorExplained)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(0.8f)
        ) {
            AppointmetnComboBox(appointmentCreationViewModel.appointmentTypes,appointmentCreationViewModel.typeClassError) {
                appointmentCreationViewModel.typeClass = it
            }
        }
        if (classroom ==-1L) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ClassroomInputChip(appointmentCreationViewModel.classroomsError,appointmentCreationViewModel.classrooms,appointmentCreationViewModel.classroomNames,onTextChange={appointmentCreationViewModel.getAllClassroomNamesSearched(it)})


                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            CallendarPicker(datePickerState){appointmentCreationViewModel.forDate=it}
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

                Text("${if (MIN_WORK_TIME.toString().length>1) "" else "0"}${MIN_WORK_TIME}h-${MAX_WORK_TIME}h", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onBackground)
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
                        AppointmentInput(appointmentCreationViewModel.startTime,{appointmentCreationViewModel.startTime=it},hint = "Od", keyboardType = KeyboardType.Number,errorText = appointmentCreationViewModel.startTimeError,isScrolling = scrollableState.isScrollInProgress, explainedError = appointmentCreationViewModel.startTimeErrorExplained)
                    }

                }
                Row(
                    modifier = Modifier.weight(1f),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Row(modifier = Modifier.fillMaxWidth(0.7f)) {
                        AppointmentInput(appointmentCreationViewModel.endTime,{appointmentCreationViewModel.endTime=it},hint = "Do", keyboardType = KeyboardType.Number,errorText = appointmentCreationViewModel.endTimeError,isScrolling = scrollableState.isScrollInProgress  , explainedError = appointmentCreationViewModel.endTimeErrorExplained)
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
            AppointmentMultyLineInput(appointmentCreationViewModel.descriptionText,{appointmentCreationViewModel.descriptionText=it},"Opis",errorText = appointmentCreationViewModel.descriptionTextError)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Row(modifier = Modifier.fillMaxWidth(0.4f)) {
                TextIconButton("Reserve", R.drawable.advance){
                    appointmentCreationViewModel.createAppointment()


                }
            }

        }

    }
}





