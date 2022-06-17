package com.example.fon_classroommanagment_frontend

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.foreverrafs.datepicker.DatePickerTimeline
import com.foreverrafs.datepicker.Orientation
import com.foreverrafs.datepicker.state.DatePickerState
import com.foreverrafs.datepicker.state.rememberDatePickerState
import java.time.LocalDate
@OptIn(ExperimentalComposeUiApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CallendarPicker(
    datePickerState:DatePickerState,
    onDateChaned :(LocalDate)->Unit
                    ) {



    DatePickerTimeline(
        modifier = Modifier
            .wrapContentSize()
            .shadow(0.dp),

        onDateSelected = { selectedDate: LocalDate ->
            onDateChaned(selectedDate)
        },
        backgroundColor = MaterialTheme.colorScheme.background,
        state = datePickerState,
        orientation = Orientation.Horizontal,
        selectedBackgroundColor = MaterialTheme.colorScheme.secondary ,
        selectedTextColor = MaterialTheme.colorScheme.onSecondary,
        dateTextColor = MaterialTheme.colorScheme.onBackground,

        todayLabel = {
            Text(
                modifier = Modifier
                    .padding(10.dp)
                    .clickable {
                        datePickerState.smoothScrollToDate(LocalDate.now())
                        onDateChaned(datePickerState.initialDate)
                    },
                text = "Today",
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.headlineMedium
            )
        },
        pastDaysCount = 5,
    )
}