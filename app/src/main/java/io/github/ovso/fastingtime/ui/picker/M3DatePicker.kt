package io.github.ovso.fastingtime.ui.picker

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import kotlinx.coroutines.launch
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun DatePickerSample() {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        // Pre-select a date for January 4, 2020
        val datePickerState = rememberDatePickerState(initialSelectedDateMillis = 1578096000000)
        DatePicker(state = datePickerState, modifier = Modifier.padding(16.dp))

        Text("Selected date timestamp: ${datePickerState.selectedDateMillis ?: "no selection"}")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun DatePickerDialogSample() {
    // Decoupled snackbar host state from scaffold state for demo purposes.
    val snackState = remember { SnackbarHostState() }
    val snackScope = rememberCoroutineScope()
    SnackbarHost(hostState = snackState, Modifier)
    val openDialog = remember { mutableStateOf(false) }
    // TODO demo how to read the selected date from the state.
    if (openDialog.value) {
        val datePickerState = rememberDatePickerState()
        val confirmEnabled = remember {
            derivedStateOf { datePickerState.selectedDateMillis != null }
        }
        DatePickerDialog(
            onDismissRequest = {
                // Dismiss the dialog when the user clicks outside the dialog or on the back
                // button. If you want to disable that functionality, simply use an empty
                // onDismissRequest.
                openDialog.value = false
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        openDialog.value = false
                        snackScope.launch {
                            snackState.showSnackbar(
                                "Selected date timestamp: ${datePickerState.selectedDateMillis}"
                            )
                        }
                    },
                    enabled = confirmEnabled.value
                ) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        openDialog.value = false
                    }
                ) {
                    Text("Cancel")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }

    Button(onClick = { openDialog.value = true }) {
        Text("Show dialog")
    }
}

@Suppress("ClassVerificationFailure")
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun DatePickerWithDateSelectableDatesSample() {
    val datePickerState = rememberDatePickerState()

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        DatePicker(state = datePickerState)
        Text("Selected date timestamp: ${datePickerState.selectedDateMillis ?: "no selection"}")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun DateInputSample() {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        val state = rememberDatePickerState(initialDisplayMode = DisplayMode.Input)
        DatePicker(state = state, modifier = Modifier.padding(16.dp))

        Text("Entered date timestamp: ${state.selectedDateMillis ?: "no input"}")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun DateRangePickerSample() {
    // Decoupled snackbar host state from scaffold state for demo purposes.
    val snackState = remember { SnackbarHostState() }
    val snackScope = rememberCoroutineScope()
    SnackbarHost(hostState = snackState, Modifier.zIndex(1f))

    val state = rememberDateRangePickerState()
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Top) {
        // Add a row with "Save" and dismiss actions.
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 12.dp, end = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = { /* dismiss the UI */ }) {
                Icon(Icons.Filled.Close, contentDescription = "Localized description")
            }
            TextButton(
                onClick = {
                    snackScope.launch {
                        snackState.showSnackbar(
                            "Saved range (timestamps): " +
                                    "${state.selectedStartDateMillis!!..state.selectedEndDateMillis!!}"
                        )
                    }
                },
                enabled = state.selectedEndDateMillis != null
            ) {
                Text(text = "Save")
            }
        }

        DateRangePicker(state = state, modifier = Modifier.weight(1f))
    }
}