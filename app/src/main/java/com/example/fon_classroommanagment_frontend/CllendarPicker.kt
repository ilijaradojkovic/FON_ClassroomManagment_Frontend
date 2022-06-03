package com.example.fon_classroommanagment_frontend

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.foreverrafs.datepicker.DatePickerTimeline
import com.foreverrafs.datepicker.Orientation
import com.foreverrafs.datepicker.state.rememberDatePickerState
import java.time.LocalDate
@OptIn(ExperimentalComposeUiApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CallendarPicker(){

    val datePickerState =
        rememberDatePickerState(initialDate = LocalDate.now())
    DatePickerTimeline(
        modifier = Modifier.wrapContentSize().shadow(0.dp),

        onDateSelected = { selectedDate: LocalDate ->
            // do something with the selected date
        },
        backgroundColor = MaterialTheme.colorScheme.background,
        state = datePickerState,
        orientation = Orientation.Horizontal,
        selectedBackgroundColor = MaterialTheme.colorScheme.secondary ,
        selectedTextColor = MaterialTheme.colorScheme.onSecondary,
        dateTextColor = MaterialTheme.colorScheme.onBackground,

        todayLabel = {
            Text(
                modifier = Modifier.padding(10.dp),
                text = "Today",
                color = Color.White,
                style = MaterialTheme.typography.bodyLarge
            )
        },
        pastDaysCount = 5,
    )
}