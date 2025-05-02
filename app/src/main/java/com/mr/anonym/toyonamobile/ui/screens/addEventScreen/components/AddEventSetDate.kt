package com.mr.anonym.toyonamobile.ui.screens.addEventScreen.components

import android.icu.util.Calendar
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.mr.anonym.toyonamobile.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEventSetDate(
    showDialog: Boolean,
    onDismissRequest:()-> Unit,
    confirmButton:( startDate:Long?,endDate: Long? )-> Unit,
    dismissButton:()-> Unit
) {

    val context = LocalContext.current

    val calendarInstance = remember { mutableStateOf( Calendar.getInstance() ) }

    val year = calendarInstance.value.get(Calendar.YEAR)
    val month = calendarInstance.value.get(Calendar.MONTH)
    val day = calendarInstance.value.get(Calendar.DAY_OF_MONTH)

    val date = rememberSaveable { mutableStateOf( "" ) }

    val dateRangePickerState = rememberDateRangePickerState(
        initialDisplayMode = DisplayMode.Picker,
    )
    
    if (showDialog){
        DatePickerDialog(
            onDismissRequest = { onDismissRequest() },
            confirmButton = {
                TextButton(
                    onClick = {
                        val selectedStartDateMillis = dateRangePickerState.selectedStartDateMillis
                        val selectedEndDateMillis = dateRangePickerState.selectedEndDateMillis
                        confirmButton(selectedStartDateMillis,selectedEndDateMillis?:0L)
                    }
                ) {
                    Text( text = "Ok" )
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { dismissButton() }
                ) {
                    Text( text = stringResource(R.string.cancel))
                }
            },
        ) {
            DateRangePicker(
                state = dateRangePickerState,
            )
        }
    }
}