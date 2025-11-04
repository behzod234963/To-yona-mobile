package com.mr.anonym.toyonamobile.ui.screens.addPartyScreen.components

import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import com.mr.anonym.toyonamobile.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEventSetDate(
    showDialog: Boolean,
    fontFamily: FontFamily,
    onDismissRequest:(Boolean)-> Unit,
    confirmButton:(startDate:Long?, endDate: Long? )-> Unit,
    dismissButton:(Boolean)-> Unit
) {

    val dateRangePickerState = rememberDateRangePickerState(
        initialDisplayMode = DisplayMode.Picker,
    )
    
    if (showDialog){
        DatePickerDialog(
            onDismissRequest = { onDismissRequest(false) },
            confirmButton = {
                TextButton(
                    onClick = {
                        val selectedStartDateMillis = dateRangePickerState.selectedStartDateMillis
                        val selectedEndDateMillis = dateRangePickerState.selectedEndDateMillis

                        confirmButton(selectedStartDateMillis,selectedEndDateMillis?:0L)
                    }
                ) {
                    Text( text = "Ok", fontFamily = fontFamily )
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { dismissButton(false) }
                ) {
                    Text( text = stringResource(R.string.cancel), fontFamily = fontFamily)
                }
            },
        ) {
            DateRangePicker(
                state = dateRangePickerState,
            )
        }
    }
}