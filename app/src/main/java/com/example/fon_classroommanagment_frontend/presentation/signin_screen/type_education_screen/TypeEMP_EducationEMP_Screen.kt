package com.example.fon_classroommanagment_frontend.presentation.signin_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.fon_classroommanagment_frontend.R
import com.example.fon_classroommanagment_frontend.presentation.common.bars.Components.buttons.ButtonWithIcon
import com.example.fon_classroommanagment_frontend.presentation.signin_screen.components.ChoiseItem


@Composable
fun TypeEMP_EducationEMP_Screen(navigateToLogin: () -> Unit) {
    val selectedValue = remember { mutableStateOf(-1) }

    val isSelectedItem: (Int) -> Boolean = { selectedValue.value == it }
    val onChangeState: (Int) -> Unit = { selectedValue.value = it }

    val items = listOf("Srednja skola", "Visoka skola", "Osnovne studije", "Master studije","Doktroske studije")
    val items1 = listOf("Student", "Profesor", "Asistent", "Radnik","Docent")

    Column(modifier= Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background).padding(10.dp)) {
        Column(verticalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier
            .weight(1f)

        ){
            Text("Education", style = MaterialTheme.typography.displaySmall, color = MaterialTheme.colorScheme.onBackground)
       for (i in 1 until items.size) {
           ChoiseItem(items[i],isSelectedItem(i)) { onChangeState(i) }
       }


        }
        Column(verticalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier
            .weight(1f)

        ){
            Text("Type", style = MaterialTheme.typography.displaySmall, color = MaterialTheme.colorScheme.onBackground)
            for (i in 1 until items1.size) {
                ChoiseItem(items[i],isSelectedItem(i)) { onChangeState(i) }
            }


        }
        Box(modifier = Modifier.fillMaxWidth().padding(10.dp), contentAlignment = Alignment.Center){
            Button(onClick = { navigateToLogin()},modifier= Modifier
                .fillMaxWidth(0.5f)
                .height(50.dp)) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround, verticalAlignment = Alignment.CenterVertically){
                    Text("Sign in", style = MaterialTheme.typography.bodyLarge)

                }
            }
        }
    }
}