package com.example.fon_classroommanagment_frontend.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.fon_classroommanagment_frontend.CallendarPicker
import com.example.fon_classroommanagment_frontend.CategoryChip
import com.example.fon_classroommanagment_frontend.presentation.common.bars.Components.TextIconButton
import com.example.fon_classroommanagment_frontend.presentation.common.bars.Components.input.AppointmentInput
import com.example.fon_classroommanagment_frontend.presentation.common.bars.Components.input.AppointmentMultyLineInput
import com.example.fon_classroommanagment_frontend.presentation.common.bars.Components.input.AppointmetnComboBox
import com.example.fon_classroommanagment_frontend.R
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Appointment_Screen(classroom:Int) {
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
            AppointmentInput(hint = "Name")
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {

            AppointmentInput(hint = "Razlog")
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            AppointmentInput(hint = "Broj prisutnih", keyboardType = KeyboardType.Number)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            AppointmetnComboBox(com.google.android.material.R.drawable.mtrl_ic_arrow_drop_down)
        }
        if (classroom ==-1) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AppointmentInput("Classroom", trailingIcon = R.drawable.plus)
                    FlowRow(
                        Modifier.height(100.dp).fillMaxWidth(),
                        mainAxisAlignment = FlowMainAxisAlignment.Center
                    ) {
                        CategoryChip("C001", false, {}, {})
                        CategoryChip("B103", false, {}, {})
                        CategoryChip("101", false, {}, {})


                    }
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            CallendarPicker()
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
                        AppointmentInput(hint = "Od", keyboardType = KeyboardType.Number)
                    }

                }
                Row(
                    modifier = Modifier.weight(1f),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Row(modifier = Modifier.fillMaxWidth(0.7f)) {
                        AppointmentInput(hint = "Do", keyboardType = KeyboardType.Number)
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
            AppointmentMultyLineInput("Opis")
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Row(modifier = Modifier.fillMaxWidth(0.4f)) {
                TextIconButton("Reserve", R.drawable.advance)
            }

        }

    }
}





