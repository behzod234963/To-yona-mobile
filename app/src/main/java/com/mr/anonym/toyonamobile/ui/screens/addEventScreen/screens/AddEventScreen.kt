package com.mr.anonym.toyonamobile.ui.screens.addEventScreen.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.twotone.AddCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.mr.anonym.data.instance.local.DataStoreInstance
import com.mr.anonym.data.instance.local.SharedPreferencesInstance
import com.mr.anonym.domain.model.PartysItem
import com.mr.anonym.toyonamobile.R
import com.mr.anonym.toyonamobile.presentation.navigation.ScreensRouter
import com.mr.anonym.toyonamobile.presentation.utils.Arguments
import com.mr.anonym.toyonamobile.ui.screens.addEventScreen.components.AddEventDropDownMenu
import com.mr.anonym.toyonamobile.ui.screens.addEventScreen.components.AddEventFAB
import com.mr.anonym.toyonamobile.ui.screens.addEventScreen.components.AddEventOtherField
import com.mr.anonym.toyonamobile.ui.screens.addEventScreen.components.AddEventSetDate
import com.mr.anonym.toyonamobile.ui.screens.addEventScreen.components.AddEventTopBar
import com.mr.anonym.toyonamobile.ui.screens.addEventScreen.items.AddEventCardItem
import com.mr.anonym.toyonamobile.ui.screens.addEventScreen.viewModel.AddEventViewModel
import com.mr.anonym.toyonamobile.ui.screens.myEventsScreen.utils.AddEventState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Locale

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@Composable
fun AddEventScreen(
    arguments: Arguments,
    navController: NavController,
    viewModel: AddEventViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

//    val calendarInstance = Calendar.getInstance()
    val dataStore = DataStoreInstance(context)
    val sharedPreferences = SharedPreferencesInstance(context)

    val isDarkTheme = sharedPreferences.getDarkThemeState()
    val isSystemTheme = sharedPreferences.getSystemThemeState()

    val systemPrimaryColor = if (isSystemInDarkTheme()) Color.Black else Color.White
    val primaryColor = when {
        isSystemTheme -> {
            systemPrimaryColor
        }

        isDarkTheme -> Color.Black
        else -> Color.White
    }
    val systemSecondaryColor = if (isSystemInDarkTheme()) Color.White else Color.Black
    val secondaryColor = when {
        isSystemTheme -> systemSecondaryColor
        isDarkTheme -> Color.White
        else -> Color.Black
    }
    val systemTertiaryColor = if (isSystemInDarkTheme()) Color.DarkGray else Color.LightGray
    val tertiaryColor = when {
        isSystemTheme -> systemTertiaryColor
        isDarkTheme -> Color.DarkGray
        else -> Color.LightGray
    }
    val quaternaryColor = Color.Red
    val systemSevenrdColor = if (isSystemInDarkTheme()) Color.Unspecified else Color.White
    val sevenrdColor = when {
        isSystemTheme -> systemSevenrdColor
        isDarkTheme -> Color.Unspecified
        else -> Color.White
    }

    val isCardError = rememberSaveable { mutableStateOf(true) }
    val cards = viewModel.cards
    val cardValue = viewModel.cardValue
    val cardModel = viewModel.card
    val scaffoldState = remember { SnackbarHostState() }

    val selectedEventIndex = viewModel.selectedEventIndex
    val isExpanded = rememberSaveable { mutableStateOf(false) }

    val otherEventValue = viewModel.otherFieldValue
    val isValueConfirmed = rememberSaveable { mutableStateOf(false) }
    val isOtherClicked = rememberSaveable { mutableStateOf(false) }
    val isOtherEventError = rememberSaveable { mutableStateOf(false) }
    val isDateReEntered = rememberSaveable { mutableStateOf(false) }
    if (arguments.eventID != -1 && selectedEventIndex.value == 4) isOtherClicked.value = true

//    val showTimePicker = rememberSaveable { mutableStateOf(false) }
//    val pickedHour = calendarInstance.get(android.icu.util.Calendar.HOUR_OF_DAY)
//    val pickedMinute = calendarInstance.get(android.icu.util.Calendar.MINUTE)
    val isDateSet = rememberSaveable {
        mutableStateOf(
            arguments.eventID != -1
        )
    }
//    val timePickerDialog = TimePickerDialog(
//        context,
//        { _: TimePicker, hourOfDay, minute ->
//            isDateReEntered.value = true
//            calendarInstance.set(Calendar.HOUR_OF_DAY, hourOfDay)
//            calendarInstance.set(Calendar.MINUTE, minute)
//            calendarInstance.set(Calendar.SECOND, 0)
//            viewModel.onEvent(AddEventState.ChangeTime(if (minute == 0) "$hourOfDay:${0}0" else "$hourOfDay:$minute"))
//            isDateSet.value = true
//        }, pickedHour, pickedMinute, true
//    )

    val showDatePicker = rememberSaveable { mutableStateOf(false) }
    val startDate = viewModel.startDate
    val endDate = viewModel.endDate
    val formatter = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())

    val id = sharedPreferences.getID()
    val isLoading = remember { mutableStateOf(false) }
    val loadingAnimation = rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.ic_loading)
    )
    viewModel.getUserById()
//    val user = viewModel.user
    val party = viewModel.party

    val isTitleError = remember { mutableStateOf(false) }
    val titleValue = viewModel.titleValue
    /*
    * Selected index index values
    * 0-> nothing was selected
    * 1-> Wedding
    * 2-> Sunnat
    * 3-> Birthday
    * 4-> Other (when 4 was selected , index type will be select from textField value
    * */

    BackHandler {
        navController.navigateUp()
    }
    Scaffold(
        modifier = Modifier
            .imePadding(),
        containerColor = primaryColor,
        contentColor = primaryColor,
        topBar = {
            AddEventTopBar(
                primaryColor = primaryColor,
                secondaryColor = secondaryColor,
                navigationIconClick = {
                    navController.navigateUp()
                }
            ) { navController.navigate(ScreensRouter.MyEventsScreen.route) }
        },
        floatingActionButton = {
            AddEventFAB(
                secondaryColor = secondaryColor,
                quaternaryColor = quaternaryColor,
                onFabClick = {
                    viewModel.getUserById()
                    val endDateResult =
                        if (!endDate.value.contains("2025")) "" else {
                            " , ${endDate.value}"
                        }
                    if (arguments.eventID == -1) {
                        if (
                            selectedEventIndex.value > 0 &&
                            isDateSet.value &&
                            !isCardError.value
                        ) {
                            isLoading.value = true
                            val eventType = when (selectedEventIndex.value) {
                                0 -> "0"
                                1 -> "1"
                                2 -> "2"
                                3 -> "3"
                                4 -> otherEventValue.value
                                else -> ""
                            }
                            viewModel.addParty(
                                userId = id,
                                partyModel = PartysItem(
                                    name = titleValue.value,
                                    type = eventType,
                                    address = party.value.address,
                                    cardNumber = cardValue.value,
                                    startTime = startDate.value,
                                    endTime = endDateResult,
                                    status = true
                                )
                            )
                        } else {
                            coroutineScope.launch {
                                scaffoldState.showSnackbar(
                                    context.getString(R.string.please_check_validate_places)
                                )
                            }
                        }
                    } else {
                        if (
                            !isCardError.value
                        ) {
                            val eventType = when (selectedEventIndex.value) {
                                0 -> "0"
                                1 -> "1"
                                2 -> "2"
                                3 -> "3"
                                4 -> otherEventValue.value
                                else -> ""
                            }
                            isLoading.value = true
                            viewModel.updateParty(
                                partyID = arguments.eventID,
                                partyModel = PartysItem(
                                    name = titleValue.value,
                                    type = eventType,
                                    address = party.value.address,
                                    cardNumber = cardValue.value,
                                    startTime = startDate.value,
                                    endTime = endDateResult,
                                    status = true
                                )
                            )
                        } else {
                            coroutineScope.launch {
                                scaffoldState.showSnackbar(
                                    context.getString(R.string.please_check_validate_places)
                                )
                            }
                        }
                    }
                }
            )
        },
        snackbarHost = { SnackbarHost(scaffoldState) }
    ) { paddingValues ->
        if (!isLoading.value) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(10.dp)
            ) {
//                Title
                AddEventOtherField(
                    secondaryColor = secondaryColor,
                    tertiaryColor = tertiaryColor,
                    isEventError = isTitleError.value,
                    value = titleValue.value,
                    isTitle = true,
                    onValueChange = {
                        viewModel.onEvent(AddEventState.ChangeTitle(it))
                    },
                    isValueConfirmed = false,
                    onConfirmClick = { },
                    onEditClick = { }
                )
//                Event
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
                                    0 -> ""
                                    1 -> stringResource(R.string.wedding)
                                    2 -> stringResource(R.string.sunnat_wedding)
                                    3 -> stringResource(R.string.birthday)
                                    4 -> stringResource(R.string.other)
                                    else -> ""
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
                                        viewModel.onEvent(AddEventState.ChangeEventIndex(1))
                                        isExpanded.value = false
                                        isOtherClicked.value = false
                                    },
                                    onSunnatClick = {
                                        viewModel.onEvent(AddEventState.ChangeEventIndex(2))
                                        isExpanded.value = false
                                        isOtherClicked.value = false
                                    },
                                    onBirthdayClick = {
                                        viewModel.onEvent(AddEventState.ChangeEventIndex(3))
                                        isExpanded.value = false
                                        isOtherClicked.value = false
                                    },
                                    onOtherClick = {
                                        viewModel.onEvent(AddEventState.ChangeEventIndex(4))
                                        isExpanded.value = false
                                        isOtherClicked.value = true
                                    }
                                )
                            }
                        }
                    }
                }
                Spacer(Modifier.height(10.dp))
//                Other field
                if (isOtherClicked.value) {
                    AddEventOtherField(
                        secondaryColor = secondaryColor,
                        tertiaryColor = tertiaryColor,
                        isEventError = isOtherEventError.value,
                        value = otherEventValue.value,
                        onValueChange = {
                            viewModel.onEvent(AddEventState.ChangeOtherField(it))
                        },
                        isValueConfirmed = isValueConfirmed.value,
                        onConfirmClick = {
                            if (
                                otherEventValue.value.isNotEmpty() &&
                                otherEventValue.value.isNotBlank()
                            ) {
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
                        },
                        isTitle = false
                    )
                    Spacer(Modifier.height(10.dp))
                }
//                Date and time value
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
                        Row(
                            horizontalArrangement = Arrangement.End,
                        ) {
                            IconButton(
                                onClick = { showDatePicker.value = !showDatePicker.value }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.DateRange,
                                    tint = secondaryColor,
                                    contentDescription = ""
                                )
                            }
//                            IconButton(
//                                onClick = { showTimePicker.value = !showTimePicker.value }
//                            ) {
//                                Icon(
//                                    painter = painterResource(R.drawable.ic_clock),
//                                    tint = secondaryColor,
//                                    contentDescription = ""
//                                )
//                            }
                        }
                    }
                }
//                Date and time value
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
                                text = "${startDate.value}${if (!endDate.value.contains("2025")) "" else " , ${endDate.value}"}",
                                modifier = Modifier
                                    .padding(start = 10.dp),
                                color = secondaryColor,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                            Spacer(Modifier.width(15.dp))
//                            Text(
//                                text = time.value,
//                                modifier = Modifier
//                                    .padding(end = 10.dp),
//                                color = secondaryColor,
//                                fontSize = 14.sp,
//                                fontWeight = FontWeight.SemiBold
//                            )
                        }
                    }
                }
//                Date picker
                if (showDatePicker.value) {
                    AddEventSetDate(
                        showDialog = showDatePicker.value,
                        onDismissRequest = {
                            isDateReEntered.value = it
                            showDatePicker.value = false
                        },
                        confirmButton = { start, end ->
                            isDateSet.value = true
                            viewModel.onEvent(AddEventState.ChangeStartDate(formatter.format(start)))
                            viewModel.onEvent(AddEventState.ChangeEndDate(formatter.format(end)))
                            showDatePicker.value = false
                        },
                        dismissButton = {
                            showDatePicker.value = false
                        }
                    )
                }
//                if (showTimePicker.value) {
//                    timePickerDialog.show()
//                    showTimePicker.value = false
//                }
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
//                Requisites
//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(horizontal = 10.dp),
//                    verticalAlignment = Alignment.CenterVertically,
//                    horizontalArrangement = Arrangement.SpaceBetween
//                ) {
//                    Text(
//                        modifier = Modifier
//                            .fillMaxWidth(0.5f),
//                        text = stringResource(R.string.card_holder_name),
//                        fontSize = 16.sp,
//                        color = secondaryColor,
//                        fontWeight = FontWeight.SemiBold
//                    )
//                    Text(
//                        text = cardHolderValue.value,
//                        fontSize = 16.sp,
//                        color = secondaryColor,
//                        fontWeight = FontWeight.SemiBold,
//                        textAlign = TextAlign.End
//                    )
//                }
                Spacer(Modifier.height(5.dp))
                HorizontalDivider()
                Spacer(Modifier.height(10.dp))
//                Cards
                if (cards.value.isNotEmpty()) {
                    isCardError.value = false
                    AddEventCardItem(
                        secondaryColor = secondaryColor,
                        tertiaryColor = tertiaryColor,
                        value = cardValue.value,
                        cards = cards.value,
                        onClick = {
                            viewModel.onEvent(AddEventState.ChangeCardModel(it))
                            viewModel.onEvent(AddEventState.ChangeCardNumber(it.number))
                        },
                        onAddCardClick = {
                            coroutineScope.launch {
                                dataStore.addCardFromAddEvent(true)
                            }
                            navController.navigate(ScreensRouter.AddCardScreen.route + "/-1")
                        },
                        cardModel = cardModel.value
                    )
                    Spacer(Modifier.height(5.dp))
                    HorizontalDivider()
                    Spacer(Modifier.height(10.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = stringResource(R.string.add_other_payment_methods),
                            color = tertiaryColor,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                    Spacer(Modifier.height(5.dp))
//                    Add card
                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = tertiaryColor,
                            contentColor = tertiaryColor
                        ),
                        shape = RoundedCornerShape(10.dp),
                        onClick = {
                            coroutineScope.launch {
                                dataStore.addCardFromAddEvent(true)
                            }
                            navController.navigate(ScreensRouter.AddCardScreen.route + "/-1")
                        }
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.TwoTone.AddCircle,
                                tint = secondaryColor,
                                contentDescription = ""
                            )
                            Spacer(Modifier.width(5.dp))
                            Text(
                                text = stringResource(R.string.add_cart),
                                fontSize = 16.sp,
                                color = secondaryColor,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                } else {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = stringResource(R.string.you_have_not_active_card),
                            color = tertiaryColor,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                    Spacer(Modifier.height(5.dp))
//                    Add card
                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = tertiaryColor,
                            contentColor = tertiaryColor
                        ),
                        shape = RoundedCornerShape(10.dp),
                        onClick = {
                            coroutineScope.launch {
                                dataStore.addCardFromAddEvent(true)
                            }
                            navController.navigate(ScreensRouter.AddCardScreen.route + "/-1")
                        }
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.TwoTone.AddCircle,
                                tint = secondaryColor,
                                contentDescription = ""
                            )
                            Spacer(Modifier.width(5.dp))
                            Text(
                                text = stringResource(R.string.add_cart),
                                fontSize = 16.sp,
                                color = secondaryColor,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }
            }
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                LottieAnimation(
                    modifier = Modifier
                        .size(150.dp),
                    composition = loadingAnimation.value,
                    restartOnPlay = true,
                    iterations = LottieConstants.IterateForever
                )
                if (arguments.eventID == -1) {
                    coroutineScope.launch {
                        if (viewModel.isPartyAdded.value) {
                            delay(1500)
                            isLoading.value = false
                            withContext(Dispatchers.Main) {
                                navController.navigate(ScreensRouter.MainScreen.route) {
                                    popUpTo(ScreensRouter.AddEventScreen.route) { inclusive = true }
                                    launchSingleTop = true
                                }
                            }
                        }
                    }
                } else {
                    Log.d("UtilsLogging", "AddEventScreen: ${viewModel.isPartyUpdated.value}")
                    coroutineScope.launch {
                        if (viewModel.isPartyUpdated.value) {
                            delay(1500)
                            isLoading.value = false
                            withContext(Dispatchers.Main) {
                                navController.navigate(ScreensRouter.MyEventsScreen.route) {
                                    popUpTo(ScreensRouter.AddEventScreen.route) { inclusive = true }
                                    launchSingleTop = true
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}