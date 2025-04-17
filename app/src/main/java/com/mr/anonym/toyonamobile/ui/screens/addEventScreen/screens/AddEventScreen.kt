package com.mr.anonym.toyonamobile.ui.screens.addEventScreen.screens

import android.annotation.SuppressLint
import android.app.TimePickerDialog
import android.widget.TimePicker
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.mr.anonym.toyonamobile.R
import com.mr.anonym.toyonamobile.presentation.navigation.ScreensRouter
import com.mr.anonym.toyonamobile.ui.screens.addEventScreen.components.AddEventCardField
import com.mr.anonym.toyonamobile.ui.screens.addEventScreen.components.AddEventDropDownMenu
import com.mr.anonym.toyonamobile.ui.screens.addEventScreen.components.AddEventFAB
import com.mr.anonym.toyonamobile.ui.screens.addEventScreen.components.AddEventOtherField
import com.mr.anonym.toyonamobile.ui.screens.addEventScreen.components.AddEventSetDate
import com.mr.anonym.toyonamobile.ui.screens.addEventScreen.components.AddEventTopBar
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddEventScreen(
    navController: NavController,
) {

    val context = LocalContext.current
    val calendarInstance = Calendar.getInstance()

    val primaryColor = if (isSystemInDarkTheme()) Color.Black else Color.White
    val secondaryColor = if (isSystemInDarkTheme()) Color.White else Color.Black
    val tertiaryColor = if (isSystemInDarkTheme()) Color.DarkGray else Color.LightGray
    val quaternaryColor = Color.Red
    val fiverdColor = Color.Green
    val sixrdColor = Color.Blue
    val sevenrdColor = if (isSystemInDarkTheme()) Color.Unspecified else primaryColor

    val selectedEventIndex = rememberSaveable { mutableStateOf(0) }
    val selectedEvent = rememberSaveable { mutableStateOf(0) }
    val anOwnEvent = rememberSaveable { mutableStateOf("") }
    val isExpanded = rememberSaveable { mutableStateOf(false) }

    val otherEventValue = rememberSaveable { mutableStateOf("") }
    val isValueConfirmed = rememberSaveable { mutableStateOf(false) }
    val isOtherClicked = rememberSaveable { mutableStateOf(false) }
    val isOtherEventError = rememberSaveable { mutableStateOf(false) }


    val showTimePicker = rememberSaveable { mutableStateOf(false) }
    val time = remember { mutableStateOf("") }
    val selectedTime = remember { mutableStateOf(System.currentTimeMillis()) }
    val pickedHour = calendarInstance.get(android.icu.util.Calendar.HOUR_OF_DAY)
    val pickedMinute = calendarInstance.get(android.icu.util.Calendar.MINUTE)
    val timePickerDialog = TimePickerDialog(
        context,
        { _: TimePicker, hourOfDay, minute ->
            time.value = if (minute == 0) "$hourOfDay:${minute}0" else "$hourOfDay:$minute"
            calendarInstance.set(Calendar.HOUR_OF_DAY, hourOfDay)
            calendarInstance.set(Calendar.MINUTE, minute)
            calendarInstance.set(Calendar.SECOND, 0)
            selectedTime.value = calendarInstance.time.time
        }, pickedHour, pickedMinute, true
    )

    val showDatePicker = rememberSaveable { mutableStateOf(false) }
    val isDateSet = rememberSaveable { mutableStateOf(false) }
    val startDate = rememberSaveable { mutableStateOf(context.getString(R.string.not_selected)) }
    val endDate = rememberSaveable { mutableStateOf(context.getString(R.string.not_selected)) }
    val formatter = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())

    val eventDate = rememberSaveable { mutableStateOf("") }

    val cards = listOf<String>(
        "9860030160619356",
        "9860030160619350"
    )
    val cardValue = rememberSaveable { mutableStateOf(cards[0]) }

    Scaffold(
        containerColor = primaryColor,
        contentColor = primaryColor,
        topBar = {
            AddEventTopBar(
                primaryColor = primaryColor,
                secondaryColor = secondaryColor,
                navigationIconClick = { navController.popBackStack() }
            ) { navController.navigate(ScreensRouter.MyEventsScreen.route) }
        },
        floatingActionButton = {
            AddEventFAB(
                secondaryColor = secondaryColor,
                quaternaryColor = quaternaryColor,
                onFabClick = { TODO() }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(10.dp)
        ) {
            Card(
                shape = RoundedCornerShape(10.dp),
                colors = CardDefaults.cardColors(
                    containerColor = sevenrdColor,
                    contentColor = sevenrdColor
                ),
                elevation = CardDefaults.cardElevation(7.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(0.5f),
                        text = stringResource(R.string.event),
                        fontSize = 18.sp,
                        color = secondaryColor,
                        fontWeight = FontWeight.SemiBold
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = when (selectedEventIndex.value) {
                                1 -> {
                                    stringResource(R.string.wedding)
                                }

                                2 -> {
                                    stringResource(R.string.sunnat_wedding)
                                }

                                3 -> {
                                    stringResource(R.string.birthday)
                                }

                                4 -> {
                                    stringResource(R.string.other)
                                }

                                5 -> {
                                    anOwnEvent.value
                                }

                                else -> {
                                    ""
                                }
                            },
                            textAlign = TextAlign.End,
                            color = secondaryColor,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                        IconButton(
                            onClick = { isExpanded.value = !isExpanded.value }
                        ) {
                            Icon(
                                imageVector = if (isExpanded.value) Icons.Default.KeyboardArrowDown else Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                                tint = secondaryColor,
                                contentDescription = ""
                            )
                        }
                        if (isExpanded.value) {
                            AddEventDropDownMenu(
                                secondaryColor = secondaryColor,
                                tertiaryColor = tertiaryColor,
                                isExpanded = isExpanded.value,
                                onDismissRequest = { isExpanded.value = false },
                                onWeddingClick = {
                                    selectedEventIndex.value = 1
                                    isExpanded.value = false
                                    selectedEvent.value = R.string.wedding
                                    isOtherClicked.value = false
                                },
                                onSunnatClick = {
                                    selectedEventIndex.value = 2
                                    isExpanded.value = false
                                    selectedEvent.value = R.string.sunnat_wedding
                                    isOtherClicked.value = false
                                },
                                onBirthdayClick = {
                                    selectedEventIndex.value = 3
                                    isExpanded.value = false
                                    selectedEvent.value = R.string.birthday
                                    isOtherClicked.value = false
                                },
                                onOtherClick = {
                                    selectedEventIndex.value = 4
                                    isExpanded.value = false
                                    selectedEvent.value = R.string.other
                                    isOtherClicked.value = true
                                }
                            )
                        }
                    }
                }
            }
            Spacer(Modifier.height(10.dp))
            if (isOtherClicked.value) {
                AddEventOtherField(
                    secondaryColor = secondaryColor,
                    tertiaryColor = tertiaryColor,
                    isEventError = isOtherEventError.value,
                    value = otherEventValue.value,
                    onValueChange = {
                        otherEventValue.value = it
                    },
                    isValueConfirmed = isValueConfirmed.value,
                    onConfirmClick = {
                        if (otherEventValue.value.isNotEmpty()) {
                            isValueConfirmed.value = true
                            isOtherEventError.value = false
                        } else {
                            isValueConfirmed.value = false
                            isOtherEventError.value = true
                        }
                    },
                    onEditClick = {
                        isOtherEventError.value = false
                        isValueConfirmed.value = false
                    }
                )
                Spacer(Modifier.height(10.dp))
            }
            Card(
                shape = if (isDateSet.value) {
                    RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp)
                } else {
                    RoundedCornerShape(10.dp)
                },
                colors = CardDefaults.cardColors(
                    containerColor = sevenrdColor,
                    contentColor = sevenrdColor
                ),
                elevation = CardDefaults.cardElevation(7.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.data_and_time),
                        fontSize = 18.sp,
                        color = secondaryColor,
                        fontWeight = FontWeight.SemiBold
                    )
                    Row {
                        IconButton(
                            onClick = { showDatePicker.value = !showDatePicker.value }
                        ) {
                            Icon(
                                imageVector = Icons.Default.DateRange,
                                tint = secondaryColor,
                                contentDescription = ""
                            )
                        }
                        IconButton(
                            onClick = { showTimePicker.value = !showTimePicker.value }
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.ic_clock),
                                tint = secondaryColor,
                                contentDescription = ""
                            )
                        }
                    }
                }
            }
            if (isDateSet.value) {
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = sevenrdColor,
                        contentColor = sevenrdColor
                    ),
                    shape = RoundedCornerShape(bottomEnd = 10.dp, bottomStart = 10.dp),
                    elevation = CardDefaults.cardElevation(7.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                    ) {
                        Text(
                            text = "${startDate.value} , ${endDate.value}",
                            modifier = Modifier
                                .padding(start = 10.dp),
                            color = secondaryColor,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                        Spacer(Modifier.width(15.dp))
                        Text(
                            text = time.value,
                            modifier = Modifier
                                .padding(end = 10.dp),
                            color = secondaryColor,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
            if (showDatePicker.value) {
                AddEventSetDate(
                    showDialog = showDatePicker.value,
                    onDismissRequest = {
                        showDatePicker.value = false
                    },
                    confirmButton = { start, end ->
                        startDate.value = formatter.format(start)
                        endDate.value = formatter.format(end)
                        showDatePicker.value = false
                        isDateSet.value = true
                    },
                    dismissButton = {
                        showDatePicker.value = false
                    }
                )
            }
            if (showTimePicker.value) {
                timePickerDialog.show()
                showTimePicker.value = false
            }
            Spacer(Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(R.string.requisites),
                    color = secondaryColor,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Spacer(Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(0.5f),
                    text = stringResource(R.string.card_holder_name),
                    fontSize = 16.sp,
                    color = secondaryColor,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "BEKHZOD KHUDAYBERGENOV",
                    fontSize = 16.sp,
                    color = secondaryColor,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.End
                )
            }
            Spacer(Modifier.height(5.dp))
            HorizontalDivider()
            Spacer(Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(0.5f),
                    text = stringResource(R.string.card_number),
                    fontSize = 16.sp,
                    color = secondaryColor,
                    fontWeight = FontWeight.SemiBold
                )
                AddEventCardField(
                    secondaryColor = secondaryColor,
                    tertiaryColor = tertiaryColor,
                    value = cardValue.value.drop(12),
                    values = cards,
                    onClick = { string ->
                        cardValue.value = string
                    }
                )
            }
            Spacer(Modifier.height(5.dp))
            HorizontalDivider()
        }
    }
}

@Preview
@Composable
private fun PreviewAddEventScreen() {
    AddEventScreen(
        NavController(LocalContext.current)
    )
}