package com.example.fon_classroommanagment_frontend.presentation.appointment_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.fon_classroommanagment_frontend.R
import com.example.fon_classroommanagment_frontend.data.remote.dto.ClassroomCardDTO
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun ClassroomInputChip(
    errorText: String,
    selectedItems: SnapshotStateList<ChipItem>,
    classroomNames: SnapshotStateList<ClassroomCardDTO>,
    onTextChange: (String) -> Unit

){

    Column(modifier=Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {

        var text by remember {
            mutableStateOf("")
        }
        CreateChipsInputFiled(
            text,
            selectedItems = selectedItems,
            {
                text = it
                onTextChange(it)
            },
            suggestions = classroomNames,

        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp, 0.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            ErrorField(errorText, Modifier.fillMaxWidth())
        }
        FlowRow(
            Modifier.fillMaxWidth(),
            mainAxisAlignment = FlowMainAxisAlignment.Center
        ) {

            for (i in 0 until selectedItems.size) {
                InformationChip(selectedItems[i].value) { selectedItems.remove(selectedItems[i]) }

            }


        }
    }
}

@Composable
fun CreateChipsInputFiled(
    text: String,
    selectedItems: SnapshotStateList<ChipItem>,
    onTextChange: (String) -> Unit,
    suggestions: SnapshotStateList<ClassroomCardDTO>,

){



    AutocompleteText(
        modifier = Modifier.fillMaxWidth(),
        query = text,
        onQueryChanged = { updatedAddress ->

            onTextChange(updatedAddress)


            //Todo: call the view model method to update addressPlaceItemPredictions
        },
        predictions = suggestions.filter {x->x.name.startsWith(text.trim())  }     ,
        onDoneActionClick = {},
        onItemClick = {

            //Todo: call the view model method to update the UI with the selection
                      onTextChange(it.name)

        },
        selectedItems = selectedItems
    ) {

        //Define how the items need to be displayed
        Text(it.name, style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.onSurface)

    }
}

@Composable
fun <T> AutocompleteText(
    modifier: Modifier,
    query: String,
    onQueryChanged: (String) -> Unit = {},
    predictions: List<T>,
    onDoneActionClick: () -> Unit = {},
    onItemClick: (T) -> Unit = {},
    selectedItems: SnapshotStateList<ChipItem>,
    itemContent: @Composable (T) -> Unit = {}

){
    val view = LocalView.current
    val lazyListState = rememberLazyListState()
    LazyColumn(
        state = lazyListState,
        modifier = Modifier.heightIn(max = TextFieldDefaults.MinHeight * 6), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Box(contentAlignment = Alignment.Center) {
                QuerySearch(
                    query = query,
                    onQueryChanged = onQueryChanged,
                    onDoneActionClick = {
                        view.clearFocus()
                        onDoneActionClick()
                    },
                    selectedItems = selectedItems
                )
            }
        }


                if (query.isNotEmpty()&& predictions.isNotEmpty()) {
                    items(predictions) { prediction ->
                        Row(
                            Modifier

                                .fillMaxWidth()
                                .padding(10.dp,0.dp)
                                .background(MaterialTheme.colorScheme.surface)
                                .clickable {
                                    view.clearFocus()
                                    onItemClick(prediction)
                                }
                        ) {
                            itemContent(prediction)
                        }
                    }
                }



    }
}


@Composable
fun QuerySearch(
    modifier: Modifier = Modifier,
    query: String,
    onDoneActionClick: () -> Unit = {},
    onQueryChanged: (String) -> Unit,
    selectedItems: SnapshotStateList<ChipItem>
) {



    TextField(value =query,
        modifier= Modifier
            .padding(10.dp)
            .height(50.dp)
            .drawBehind {
                val strokeWidth = 10f
                val y = size.height - strokeWidth / 2

                drawLine(
                    Color.LightGray,
                    Offset(0f, y),
                    Offset(size.width, y),
                    strokeWidth
                )
            },
        textStyle = MaterialTheme.typography.labelLarge,
        trailingIcon={
            Icon(
                painterResource(id =  R.drawable.plus ),
                contentDescription = "Trailing icon",
                modifier = Modifier
                    .size(24.dp)
                    .clickable {
                        if (query
                                .trim()
                                .isNotEmpty() && !selectedItems.any { x -> x.value == query }
                        ) {
                            val trimmedText = query.trim()
                            selectedItems.add(ChipItem(trimmedText))
                            onQueryChanged("")
                        }
                    }
            )
        },
        singleLine = true,
        keyboardActions = KeyboardActions(onDone = {
            onDoneActionClick()
        }),

        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Text
        ),
        colors = TextFieldDefaults.textFieldColors(textColor = MaterialTheme.colorScheme.onBackground,containerColor = Color.Transparent, errorIndicatorColor = MaterialTheme.colorScheme.errorContainer),
        placeholder = {
            Text("Classroom",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier)},

        onValueChange =onQueryChanged)



}

