package com.example.fon_classroommanagment_frontend.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import com.example.fon_classroommanagment_frontend.data.remote.dto.ReserveDTO
import com.example.fon_classroommanagment_frontend.domain.model.ClassroomType
import com.example.fon_classroommanagment_frontend.presentation.appointment_screen.AppointmentCreationViewModel
import com.example.fon_classroommanagment_frontend.presentation.appointment_screen.components.InformationChip
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import java.time.LocalDate
import java.time.ZoneId
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Appointment_Screen(
    classroom:Long,
    navHostController: NavHostController,
    appointmentCreationViewModel: AppointmentCreationViewModel = hiltViewModel()
) {
    val scrollableState = rememberScrollState()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(scrollableState)
            .padding(0.dp, 20.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            AppointmentInput(appointmentCreationViewModel.nameText,{appointmentCreationViewModel.nameText=it},hint = "Name", errorText = appointmentCreationViewModel.nameTextError)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {

            AppointmentInput(appointmentCreationViewModel.reasonText,{appointmentCreationViewModel.reasonText=it},hint = "Razlog",errorText = appointmentCreationViewModel.reasonTextError)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            AppointmentInput(appointmentCreationViewModel.numAttendeesText.toString(),{appointmentCreationViewModel.numAttendeesText=it},hint = "Broj prisutnih", keyboardType = KeyboardType.Number,errorText = appointmentCreationViewModel.numAttendeesTextError)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            AppointmetnComboBox(com.google.android.material.R.drawable.mtrl_ic_arrow_drop_down)
        }
        if (classroom ==-1L) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AppointmentInput("",{},"Classroom", trailingIcon = R.drawable.plus)
                    FlowRow(
                        Modifier.fillMaxWidth(),
                        mainAxisAlignment = FlowMainAxisAlignment.Center
                    ) {
                        InformationChip("C001")
                        InformationChip("B103")



                    }
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            CallendarPicker(){appointmentCreationViewModel.forDate=it}
        }

        Column(
            modifier = Modifier//date picker ima senku pa sam stavio samo pozadinu za ovaj ele prekrice je,nisam otkio jos kako se iskljucje senka
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
        ) {

            Row(
                modifier = Modifier

                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.clock),
                    contentDescription = "Clocl icon",
                    modifier = Modifier.size(24.dp),
                    tint = MaterialTheme.colorScheme.onBackground
                )
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
                        AppointmentInput(appointmentCreationViewModel.startTime,{appointmentCreationViewModel.startTime=it},hint = "Od", keyboardType = KeyboardType.Number,errorText = appointmentCreationViewModel.startTimeError)
                    }

                }
                Row(
                    modifier = Modifier.weight(1f),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Row(modifier = Modifier.fillMaxWidth(0.7f)) {
                        AppointmentInput(appointmentCreationViewModel.endTime,{appointmentCreationViewModel.endTime=it},hint = "Do", keyboardType = KeyboardType.Number,errorText = appointmentCreationViewModel.endTimeError)
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
                    appointmentCreationViewModel.CreateAppointment()


                }
            }

        }

    }
}





