package com.mr.anonym.toyonamobile.ui.screens.monitoringScreen.screen

import android.annotation.SuppressLint
import android.os.Build
import androidx.activity.compose.LocalActivity
import androidx.annotation.RequiresApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.mr.anonym.data.instance.local.SharedPreferencesInstance
import com.mr.anonym.domain.model.CardModel
import com.mr.anonym.domain.model.MonitoringModel
import com.mr.anonym.toyonamobile.R
import com.mr.anonym.toyonamobile.presentation.managers.pdfGenerator
import com.mr.anonym.toyonamobile.presentation.managers.shareTransfer
import com.mr.anonym.toyonamobile.ui.screens.monitoringScreen.components.FilterNavigationDrawer
import com.mr.anonym.toyonamobile.ui.screens.monitoringScreen.components.MonitoringBottomSheet
import com.mr.anonym.toyonamobile.ui.screens.monitoringScreen.components.MonitoringFAB
import com.mr.anonym.toyonamobile.ui.screens.monitoringScreen.components.MonitoringScrollableTabRow
import com.mr.anonym.toyonamobile.ui.screens.monitoringScreen.components.MonitoringTopBar
import com.mr.anonym.toyonamobile.ui.screens.monitoringScreen.items.MonitoringItem
import com.mr.anonym.toyonamobile.ui.screens.monitoringScreen.items.MonitoringViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.Q)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "AutoboxingStateCreation",
    "CoroutineCreationDuringComposition"
)
@Composable
fun MonitoringScreen(
    navController: NavController,
    viewModel: MonitoringViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    val activity = LocalActivity.current
    val coroutineScope = rememberCoroutineScope()

    val calendarInstance = Calendar.getInstance()
    val sharedPreferences = SharedPreferencesInstance(context)

    val monthIndex = calendarInstance.get(Calendar.MONTH)

    val isDarkTheme = sharedPreferences.getDarkThemeState()
    val isSystemTheme = sharedPreferences.getSystemThemeState()

    val systemPrimaryColor = if (isSystemInDarkTheme()) Color.Black else Color.White
    val primaryColor = when {
        isSystemTheme-> {
            systemPrimaryColor
        }
        isDarkTheme -> Color.Black
        else -> Color.White
    }
    val systemSecondaryColor = if (isSystemInDarkTheme()) Color.White else Color.Black
    val secondaryColor = when {
        isSystemTheme -> systemSecondaryColor
        isDarkTheme-> Color.White
        else -> Color.Black
    }
    val systemTertiaryColor = if (isSystemInDarkTheme()) Color.DarkGray else Color.LightGray
    val tertiaryColor = when {
        isSystemTheme -> systemTertiaryColor
        isDarkTheme -> Color.DarkGray
        else -> Color.LightGray
    }
    val quaternaryColor = Color.Red
    val fiveColor = Color(101, 163, 119, 255)
    val systemNineColor = if (isSystemInDarkTheme()) Color(0xFF222327) else Color(0xFFF1F2F4)
    val nineColor = when{
        isSystemTheme -> systemNineColor
        isDarkTheme -> Color(0xFF222327)
        else -> Color(0xFFF1F2F4)
    }
    val tenColor = Color(0xFF259BD6)

    val iosFont = FontFamily(Font(R.font.ios_font))

    val selectedTabIndex = rememberSaveable { mutableIntStateOf(monthIndex) }
    val filterDateIndex = remember { mutableIntStateOf( 0 ) }

    val showTransferDetails = rememberSaveable { mutableStateOf(false) }
    val bottomSheetState = rememberModalBottomSheetState( skipPartiallyExpanded = true )
    coroutineScope.launch { bottomSheetState.hide() }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

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

//    val cards = viewModel.cards
    val cards = listOf(
        CardModel(
            id = -1,
            userId = -1,
            number = "9860030160619356",
            date = "0925",
            createdAt = "01.11.2025"
        ),
        CardModel(
            id = -1,
            userId = -1,
            number = "9860030160619356",
            date = "0925",
            createdAt = "01.11.2025"
        )
    )
    val months = mapOf(
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
    val monitoringList = listOf(
        MonitoringModel(
            id = 1,
            monthIndex = 1,
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

    FilterNavigationDrawer(
        primaryColor = primaryColor,
        secondaryColor = secondaryColor,
        quaternaryColor = quaternaryColor,
        fiveColor = fiveColor,
        nineColor = nineColor,
        tenColor = tenColor,
        fontFamily = iosFont,
        cardList = cards,
        onCardClick = {
//            Need backend to realize next steps
        },
        onDateIndex = { filterDateIndex.intValue = it },
        onBackClick = {
            if ( drawerState.isOpen ){
                coroutineScope.launch {
                    delay(150)
                    drawerState.close()
                }
            }
        },
        onConfirmClick = {
//            Need backend to realize next steps
        },
        drawerState = drawerState,
        content = {
            Scaffold(
                containerColor = primaryColor,
                contentColor = primaryColor,
                floatingActionButton = {
                    MonitoringFAB(
                        secondaryColor = secondaryColor,
                        fiveColor = fiveColor
                    ) {
                        if (drawerState.isClosed){
                            coroutineScope.launch {
                                delay(100)
                                drawerState.open()
                            }
                        }
                    }
                },
                topBar = {
                    MonitoringTopBar(
                        primaryColor = primaryColor,
                        secondaryColor = secondaryColor,
                        fontFamily = iosFont,
                        onNavigationClick = { navController.navigateUp() }
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
                        fontFamily = iosFont,
                        selectedTabIndex = selectedTabIndex.intValue,
                        tabs = months.values,
                        onClick = { index ->
                            selectedTabIndex.intValue = index
                        },
                        content = {
                            LazyColumn(
                                modifier = Modifier
                                    .padding(10.dp)
                            ) {
                                when (selectedTabIndex.intValue) {
                                    0 -> {
                                        items(monitoringList) { item ->
                                            MonitoringItem(
                                                secondaryColor = secondaryColor,
                                                quaternaryColor = quaternaryColor,
                                                fiveColor = fiveColor,
                                                nineColor = nineColor,
                                                fontFamily = iosFont,
                                                model = item,
                                            ) {
                                                monitoringModel.value = item
                                                showTransferDetails.value = true
                                            }
                                        }
                                    }
                                    1 -> {
                                        items(monitoringList) { item ->
                                            MonitoringItem(
                                                secondaryColor = secondaryColor,
                                                quaternaryColor = quaternaryColor,
                                                fiveColor = fiveColor,
                                                nineColor = nineColor,
                                                fontFamily = iosFont,
                                                model = item,
                                            ) {
                                                monitoringModel.value = item
                                                showTransferDetails.value = true
                                            }
                                        }
                                    }
                                    2 -> {
                                        items(monitoringList) { item ->
                                            MonitoringItem(
                                                secondaryColor = secondaryColor,
                                                quaternaryColor = quaternaryColor,
                                                fiveColor = fiveColor,
                                                nineColor = nineColor,
                                                fontFamily = iosFont,
                                                model = item,
                                            ) {
                                                monitoringModel.value = item
                                                showTransferDetails.value = true
                                            }
                                        }
                                    }
                                    3 -> {
                                        items(monitoringList) { item ->
                                            MonitoringItem(
                                                secondaryColor = secondaryColor,
                                                quaternaryColor = quaternaryColor,
                                                fiveColor = fiveColor,
                                                nineColor = nineColor,
                                                fontFamily = iosFont,
                                                model = item,
                                            ) {
                                                monitoringModel.value = item
                                                showTransferDetails.value = true
                                            }
                                        }
                                    }
                                    4 -> {
                                        items(monitoringList) { item ->
                                            MonitoringItem(
                                                secondaryColor = secondaryColor,
                                                quaternaryColor = quaternaryColor,
                                                fiveColor = fiveColor,
                                                nineColor = nineColor,
                                                fontFamily = iosFont,
                                                model = item,
                                            ) {
                                                monitoringModel.value = item
                                                showTransferDetails.value = true
                                            }
                                        }
                                    }
                                    5 -> {
                                        items(monitoringList) { item ->
                                            MonitoringItem(
                                                secondaryColor = secondaryColor,
                                                quaternaryColor = quaternaryColor,
                                                fiveColor = fiveColor,
                                                nineColor = nineColor,
                                                fontFamily = iosFont,
                                                model = item,
                                            ) {
                                                monitoringModel.value = item
                                                showTransferDetails.value = true
                                            }
                                        }
                                    }
                                    6 -> {
                                        items(monitoringList) { item ->
                                            MonitoringItem(
                                                secondaryColor = secondaryColor,
                                                quaternaryColor = quaternaryColor,
                                                fiveColor = fiveColor,
                                                nineColor = nineColor,
                                                fontFamily = iosFont,
                                                model = item,
                                            ) {
                                                monitoringModel.value = item
                                                showTransferDetails.value = true
                                            }
                                        }
                                    }
                                    7 -> {
                                        items(monitoringList) { item ->
                                            MonitoringItem(
                                                secondaryColor = secondaryColor,
                                                quaternaryColor = quaternaryColor,
                                                fiveColor = fiveColor,
                                                nineColor = nineColor,
                                                fontFamily = iosFont,
                                                model = item,
                                            ) {
                                                monitoringModel.value = item
                                                showTransferDetails.value = true
                                            }
                                        }
                                    }
                                    8 -> {
                                        items(monitoringList) { item ->
                                            MonitoringItem(
                                                secondaryColor = secondaryColor,
                                                quaternaryColor = quaternaryColor,
                                                fiveColor = fiveColor,
                                                nineColor = nineColor,
                                                fontFamily = iosFont,
                                                model = item,
                                            ) {
                                                monitoringModel.value = item
                                                showTransferDetails.value = true
                                            }
                                        }
                                    }
                                    9 -> {
                                        items(monitoringList) { item ->
                                            MonitoringItem(
                                                secondaryColor = secondaryColor,
                                                quaternaryColor = quaternaryColor,
                                                fiveColor = fiveColor,
                                                nineColor = nineColor,
                                                fontFamily = iosFont,
                                                model = item,
                                            ) {
                                                monitoringModel.value = item
                                                showTransferDetails.value = true
                                            }
                                        }
                                    }
                                    10 -> {
                                        items(monitoringList) { item ->
                                            MonitoringItem(
                                                secondaryColor = secondaryColor,
                                                quaternaryColor = quaternaryColor,
                                                fiveColor = fiveColor,
                                                nineColor = nineColor,
                                                fontFamily = iosFont,
                                                model = item,
                                            ) {
                                                monitoringModel.value = item
                                                showTransferDetails.value = true
                                            }
                                        }
                                    }
                                    11 -> {
                                        items(monitoringList) { item ->
                                            MonitoringItem(
                                                secondaryColor = secondaryColor,
                                                quaternaryColor = quaternaryColor,
                                                fiveColor = fiveColor,
                                                nineColor = nineColor,
                                                fontFamily = iosFont,
                                                model = item,
                                            ) {
                                                monitoringModel.value = item
                                                showTransferDetails.value = true
                                            }
                                        }
                                    }
                                }
                            }
                            if (showTransferDetails.value) {
                                MonitoringBottomSheet(
                                    secondaryColor = secondaryColor,
                                    tertiaryColor = tertiaryColor,
                                    fontFamily = iosFont,
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
                                    }
                                ) { showTransferDetails.value = false }
                            }
                        }
                    )
                }
            }
        }
    )
}

@RequiresApi(Build.VERSION_CODES.Q)
@Preview
@Composable
private fun PreviewMonitoringScreen() {
    MonitoringScreen(
        NavController(LocalContext.current)
    )
}