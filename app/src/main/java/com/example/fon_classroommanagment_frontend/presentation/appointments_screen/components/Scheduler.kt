package com.example.fon_classroommanagment_frontend.presentation.appointments_screen.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.ParentDataModifier
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.fon_classroommanagment_frontend.data.Event
import com.example.fon_classroommanagment_frontend.presentation.common.bars.Components.BasicEvent
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import kotlin.math.roundToInt

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Schedule(
    events: List<Event>,
    modifier: Modifier = Modifier,
    eventContent: @Composable (event: Event) -> Unit = { BasicEvent(event = it) },
) {
    val dividerColor = MaterialTheme.colorScheme.onBackground

    val hourHeight = 64.dp

    Row(modifier = Modifier.fillMaxWidth()) {
        ScheduleSidebar(
            hourHeight = hourHeight,
            modifier = Modifier.background(Color.Transparent)

        )
        Layout(
            content = {
                events.sortedBy(Event::start).forEach { event ->
                    Box(modifier = Modifier.eventData(event)) {
                        eventContent(event)
                    }
                }
            },
            modifier = modifier

                .drawBehind {
                    repeat(13) {
                        drawLine(
                            dividerColor,
                            start = Offset(0f, (it + 1) * hourHeight.toPx()),
                            end = Offset(size.width, (it + 1) * hourHeight.toPx()),
                            strokeWidth = 1.dp.toPx()
                        )
                    }

                },
        ) { measureables, constraints ->

            val height = hourHeight.roundToPx() * 14
            val placeablesWithEvents = measureables.map { measurable ->
                val event = measurable.parentData as Event
                val eventDurationHours = ChronoUnit.HOURS.between(event.start, event.end)
                val eventHeight = ((eventDurationHours ) * hourHeight.toPx()).roundToInt()
                val placeable = measurable.measure(
                    constraints.copy(
                        minHeight = eventHeight,
                        maxHeight = eventHeight
                    )
                )
                Pair(placeable, event)
            }
            layout(constraints.maxWidth, height) {
                placeablesWithEvents.forEach { (placeable, event) ->
                    val eventOffsetMinutes =
                        ChronoUnit.HOURS.between(LocalTime.of(8,0), event.start.toLocalTime())
                    val eventY = ((eventOffsetMinutes ) * hourHeight.toPx()).roundToInt()
                    placeable.place(0, eventY)
                }
            }
        }
    }
}
private fun Modifier.eventData(event: Event) = this.then(EventDataModifier(event))
private class EventDataModifier(
    val event: Event,
) : ParentDataModifier {
    override fun Density.modifyParentData(parentData: Any?) = event


}
@RequiresApi(Build.VERSION_CODES.O)
private val HourFormatter = DateTimeFormatter.ofPattern("h a")

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BasicSidebarLabel(
    time: LocalTime,
    modifier: Modifier = Modifier,
) {
    Text(
        text = time.format(HourFormatter),
        modifier = modifier

            .padding(4.dp),
        color = MaterialTheme.colorScheme.onBackground,
        style = MaterialTheme.typography.bodyMedium
    )
}
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ScheduleSidebar(
    hourHeight: Dp,
    modifier: Modifier = Modifier,
    label: @Composable (time: LocalTime) -> Unit = { BasicSidebarLabel(time = it) },
) {
    Column(modifier =modifier ) {
        val startTime = LocalTime.of(8,0)
        repeat(14) { i ->
            Column(modifier = Modifier.height(hourHeight), verticalArrangement =Arrangement.Center) {
                label(startTime.plusHours(i.toLong()))
            }
        }
    }
}



