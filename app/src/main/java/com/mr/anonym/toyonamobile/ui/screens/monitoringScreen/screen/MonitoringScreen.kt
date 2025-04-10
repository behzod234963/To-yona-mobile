package com.mr.anonym.toyonamobile.ui.screens.monitoringScreen.screen

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.activity.compose.LocalActivity
import androidx.annotation.RequiresApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mr.anonym.domain.model.MonitoringModel
import com.mr.anonym.toyonamobile.R
import com.mr.anonym.toyonamobile.presentation.utils.pdfGenerator
import com.mr.anonym.toyonamobile.presentation.utils.shareTransfer
import com.mr.anonym.toyonamobile.ui.screens.monitoringScreen.components.MonitoringBottomSheet
import com.mr.anonym.toyonamobile.ui.screens.monitoringScreen.components.MonitoringItem
import com.mr.anonym.toyonamobile.ui.screens.monitoringScreen.components.MonitoringScrollableTabRow
import com.mr.anonym.toyonamobile.ui.screens.monitoringScreen.components.MonitoringTopBar
import kotlinx.coroutines.launch
import java.text.DateFormatSymbols
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.Q)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MonitoringScreen(
    navController: NavController
) {

    val context = LocalContext.current
    val activity = LocalActivity.current
    val coroutineScope = rememberCoroutineScope()

    val calendarInstance = Calendar.getInstance()

    val monthIndex = calendarInstance.get(Calendar.MONTH)

    val primaryColor = if (isSystemInDarkTheme()) Color.Black else Color.White
    val secondaryColor = if (isSystemInDarkTheme()) Color.White else Color.Black
    val tertiaryColor = if (isSystemInDarkTheme()) Color.DarkGray else Color.LightGray
    val quaternaryColor = Color.Red
    val fiverdColor = Color.Green
    val sixrdColor = Color.Blue
    val sevenrdColor = if (isSystemInDarkTheme()) Color.Unspecified else primaryColor

    val selectedTabIndex = rememberSaveable { mutableStateOf(monthIndex) }

    val showTransferDetails = rememberSaveable { mutableStateOf(false) }
    val bottomSheetState = rememberModalBottomSheetState( skipPartiallyExpanded = true )
    coroutineScope.launch { bottomSheetState.hide() }

    val monitoringModel = remember {
        mutableStateOf(
            MonitoringModel(
                id = 1,
                monthIndex = 3,
                eventName = "Wedding",
                eventOwnerName = "Bekhzod",
                eventOwnerLastName = "Khudaybergenov",
                receiverCardHolder = "BEKHZOD KHUDAYBERGENOV",
                receiverCardNumber = "9860030160619356",
                senderCardHolder = "Javokhir Tulibayev",
                senderCardNumber = "0000111122223333",
                amount = "+ 50 000",
                dateTime = "05.04.2025",
                transferStatus = "Success"
            )
        )
    }

    val months = mapOf<Int, String>(
        0 to stringResource(R.string.january),
        1 to stringResource(R.string.february),
        2 to stringResource(R.string.march),
        3 to stringResource(R.string.april),
        4 to stringResource(R.string.may),
        5 to stringResource(R.string.june),
        6 to stringResource(R.string.july),
        7 to stringResource(R.string.august),
        8 to stringResource(R.string.september),
        9 to stringResource(R.string.october),
        10 to stringResource(R.string.november),
        11 to stringResource(R.string.december)
    ).mapKeys { it.key }

    val monitoringList = listOf<MonitoringModel>(
        MonitoringModel(
            id = 1,
            monthIndex = 3,
            eventName = "Wedding",
            eventOwnerName = "Bekhzod",
            eventOwnerLastName = "Khudaybergenov",
            receiverCardHolder = "BEKHZOD KHUDAYBERGENOV",
            receiverCardNumber = "9860030160619356",
            senderCardHolder = "Javokhir Tulibayev",
            senderCardNumber = "0000111122223333",
            amount = "+ 50 000",
            dateTime = "05.04.2025",
            transferStatus = "Success"
        ),
        MonitoringModel(
            id = 1,
            monthIndex = 3,
            eventName = "Sunnat",
            eventOwnerName = "Bekhzod",
            eventOwnerLastName = "Khudaybergenov",
            receiverCardHolder = "BEKHZOD KHUDAYBERGENOV",
            receiverCardNumber = "9860030160619356",
            senderCardHolder = "DAVRANBEK RADJAPOV",
            senderCardNumber = "1111222233334444",
            amount = "+ 550 000",
            dateTime = "05.04.2025",
            transferStatus = "Success"
        )
    )

    Scaffold(
        containerColor = primaryColor,
        contentColor = primaryColor,
        topBar = {
            MonitoringTopBar(
                secondaryColor = secondaryColor,
                onNavigationClick = { navController.popBackStack() },
                onActionsClick = {
                    val calendar = Calendar.getInstance()
                    val monthIndex = calendar.get(Calendar.MONTH)
                    val month = DateFormatSymbols().months[monthIndex]
                    Log.e("UtilsLogging", "MonitoringScreen: $month $monthIndex")
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            MonitoringScrollableTabRow(
                primaryColor = primaryColor,
                secondaryColor = secondaryColor,
                selectedTabIndex = selectedTabIndex.value,
                tabs = months.values,
                onClick = { index ->
                    selectedTabIndex.value = index
                },
                content = {
                    LazyColumn(
                        modifier = Modifier
                            .padding(10.dp)
                    ) {
                        when (selectedTabIndex.value) {
                            0 -> {
                                items(monitoringList) { item ->
                                    MonitoringItem(
                                        secondaryColor = secondaryColor,
                                        quaternaryColor = quaternaryColor,
                                        fiverdColor = fiverdColor,
                                        sevenrdColor = sevenrdColor,
                                        model = item,
                                        onClick = {
                                            monitoringModel.value = item
                                            showTransferDetails.value = true
                                        },
                                    )
                                }
                            }

                            1 -> {
                                items(monitoringList) { item ->
                                    MonitoringItem(
                                        secondaryColor = secondaryColor,
                                        quaternaryColor = quaternaryColor,
                                        fiverdColor = fiverdColor,
                                        sevenrdColor = sevenrdColor,
                                        model = item,
                                        onClick = {
                                            monitoringModel.value = item
                                            showTransferDetails.value = true
                                        },
                                    )
                                }
                            }
                            2 -> {
                                items(monitoringList) { item ->
                                    MonitoringItem(
                                        secondaryColor = secondaryColor,
                                        quaternaryColor = quaternaryColor,
                                        fiverdColor = fiverdColor,
                                        sevenrdColor = sevenrdColor,
                                        model = item,
                                        onClick = {
                                            monitoringModel.value = item
                                            showTransferDetails.value = true
                                        },
                                    )
                                }
                            }
                            3 -> {
                                items(monitoringList) { item ->
                                    MonitoringItem(
                                        secondaryColor = secondaryColor,
                                        quaternaryColor = quaternaryColor,
                                        fiverdColor = fiverdColor,
                                        sevenrdColor = sevenrdColor,
                                        model = item,
                                        onClick = {
                                            monitoringModel.value = item
                                            showTransferDetails.value = true
                                        },
                                    )
                                }
                            }

                            4 -> {
                                items(monitoringList) { item ->
                                    MonitoringItem(
                                        secondaryColor = secondaryColor,
                                        quaternaryColor = quaternaryColor,
                                        fiverdColor = fiverdColor,
                                        sevenrdColor = sevenrdColor,
                                        model = item,
                                        onClick = {
                                            monitoringModel.value = item
                                            showTransferDetails.value = true
                                        },
                                    )
                                }
                            }
                            5 -> {
                                items(monitoringList) { item ->
                                    MonitoringItem(
                                        secondaryColor = secondaryColor,
                                        quaternaryColor = quaternaryColor,
                                        fiverdColor = fiverdColor,
                                        sevenrdColor = sevenrdColor,
                                        model = item,
                                        onClick = {
                                            monitoringModel.value = item
                                            showTransferDetails.value = true
                                        },
                                    )
                                }
                            }
                            6 -> {
                                items(monitoringList) { item ->
                                    MonitoringItem(
                                        secondaryColor = secondaryColor,
                                        quaternaryColor = quaternaryColor,
                                        fiverdColor = fiverdColor,
                                        sevenrdColor = sevenrdColor,
                                        model = item,
                                        onClick = {
                                            monitoringModel.value = item
                                            showTransferDetails.value = true
                                        },
                                    )
                                }
                            }
                            7 -> {
                                items(monitoringList) { item ->
                                    MonitoringItem(
                                        secondaryColor = secondaryColor,
                                        quaternaryColor = quaternaryColor,
                                        fiverdColor = fiverdColor,
                                        sevenrdColor = sevenrdColor,
                                        model = item,
                                        onClick = {
                                            monitoringModel.value = item
                                            showTransferDetails.value = true
                                        },
                                    )
                                }
                            }
                            8 -> {
                                items(monitoringList) { item ->
                                    MonitoringItem(
                                        secondaryColor = secondaryColor,
                                        quaternaryColor = quaternaryColor,
                                        fiverdColor = fiverdColor,
                                        sevenrdColor = sevenrdColor,
                                        model = item,
                                        onClick = {
                                            monitoringModel.value = item
                                            showTransferDetails.value = true
                                        },
                                    )
                                }
                            }
                            9 -> {
                                items(monitoringList) { item ->
                                    MonitoringItem(
                                        secondaryColor = secondaryColor,
                                        quaternaryColor = quaternaryColor,
                                        fiverdColor = fiverdColor,
                                        sevenrdColor = sevenrdColor,
                                        model = item,
                                        onClick = {
                                            monitoringModel.value = item
                                            showTransferDetails.value = true
                                        },
                                    )
                                }
                            }
                            10 -> {
                                items(monitoringList) { item ->
                                    MonitoringItem(
                                        secondaryColor = secondaryColor,
                                        quaternaryColor = quaternaryColor,
                                        fiverdColor = fiverdColor,
                                        sevenrdColor = sevenrdColor,
                                        model = item,
                                        onClick = {
                                            monitoringModel.value = item
                                            showTransferDetails.value = true
                                        },
                                    )
                                }
                            }
                            11 -> {
                                items(monitoringList) { item ->
                                    MonitoringItem(
                                        secondaryColor = secondaryColor,
                                        quaternaryColor = quaternaryColor,
                                        fiverdColor = fiverdColor,
                                        sevenrdColor = sevenrdColor,
                                        model = item,
                                        onClick = {
                                            monitoringModel.value = item
                                            showTransferDetails.value = true
                                        },
                                    )
                                }
                            }
                        }
                    }
                    if (showTransferDetails.value) {
                        MonitoringBottomSheet(
                            secondaryColor = secondaryColor,
                            tertiaryColor = tertiaryColor,
                            state = bottomSheetState,
                            model = monitoringModel.value,
                            onDownloadClick = {
                                activity?.let {
                                    pdfGenerator(
                                        context = context,
                                        activity = it,
                                        fileName = "${System.currentTimeMillis()}",
                                        model = monitoringModel.value
                                    )
                                }
                                showTransferDetails.value = false
                            },
                            onShareClick = {
                                val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
                                val currentDate = dateFormat.format( calendarInstance.time )
                                shareTransfer(
                                    context = context,
                                    fileName = "Document_$currentDate _${System.currentTimeMillis()}",
                                    model = monitoringModel.value
                                )
                                showTransferDetails.value = false
                            },
                            onDismissRequest = { showTransferDetails.value = false }
                        )
                    }
                }
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.Q)
@Preview
@Composable
private fun PreviewMonitoringScreen() {
    MonitoringScreen(
        NavController(LocalContext.current)
    )
}