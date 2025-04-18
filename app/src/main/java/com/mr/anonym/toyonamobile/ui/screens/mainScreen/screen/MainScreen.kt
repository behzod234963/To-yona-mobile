package com.mr.anonym.toyonamobile.ui.screens.mainScreen.screen

import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mr.anonym.data.instance.local.DataStoreInstance
import com.mr.anonym.domain.model.PartyModel
import com.mr.anonym.toyonamobile.R
import com.mr.anonym.toyonamobile.presentation.navigation.ScreensRouter
import com.mr.anonym.toyonamobile.presentation.utils.PermissionController
import com.mr.anonym.toyonamobile.ui.screens.mainScreen.components.MainScreenFAB
import com.mr.anonym.toyonamobile.ui.screens.mainScreen.components.MainScreenModalDrawerSheet
import com.mr.anonym.toyonamobile.ui.screens.mainScreen.components.MainScreenSearchField
import com.mr.anonym.toyonamobile.ui.screens.mainScreen.components.MainScreenTopBar
import com.mr.anonym.toyonamobile.ui.screens.mainScreen.item.MainScreenItem
import kotlinx.coroutines.launch

@Composable
fun MainScreen(
    navController: NavController
) {

    val context = LocalContext.current
    val activityContext = LocalActivity.current

    val dataStore = DataStoreInstance(context)
    val permissionController = PermissionController(context)
    val coroutineScope = rememberCoroutineScope()

    val primaryColor = if (isSystemInDarkTheme()) Color.Black else Color.White
    val secondaryColor = if (isSystemInDarkTheme()) Color.White else Color.Black
    val tertiaryColor = if (isSystemInDarkTheme()) Color.DarkGray else Color.LightGray
    val quaternaryColor = Color.Red
    val fiverdColor = Color.Green
    val sixrdColor = Color.Blue
    val sevenrdColor = if (isSystemInDarkTheme()) Color.Unspecified else primaryColor

    val smallFontSize = remember { mutableIntStateOf(14) }
    val mediumFontSize = remember { mutableIntStateOf(18) }
    val largeFontSize = remember { mutableIntStateOf(22) }

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    val profileAvatar = dataStore.getAvatar().collectAsState(R.drawable.ic_default_avatar)
    val firstName = dataStore.getFirstname().collectAsState("")
    val lastName = dataStore.getLastname().collectAsState("")
    val phoneNumber = dataStore.getPhoneNumber().collectAsState("")

    val searchValue = rememberSaveable { mutableStateOf("") }
    val showSearchResults = rememberSaveable { mutableStateOf(false) }

    val partyList = listOf<PartyModel>(
        PartyModel(
            id = 1,
            userID = 1,
            type = "123456789012345678901234567890",
            cardNumber = "9860030160619356",
            dateTime = "21-22-mart 2025,17:00"
        ),
        PartyModel(
            id = 1,
            userID = 1,
            type = "Худайкулов Ойбек Kelin to'y",
            cardNumber = "9860030160619356",
            dateTime = "21-22-mart 2025,17:00"
        ),
        PartyModel(
            id = 1,
            userID = 1,
            type = "Абдумадиярхуджаев Каландарбекжон Абдумаликсамандарович (Шагалкалла) Kelin to'y",
            cardNumber = "9860030160619356",
            dateTime = "21-22-mart 2025,17:00"
        ),
        PartyModel(
            id = 1,
            userID = 1,
            type = "Худайберганов Бехзод Sunnat to'y",
            cardNumber = "9860030160619356",
            dateTime = "21-mart 2025,12:00"
        ),
        PartyModel(
            id = 1,
            userID = 1,
            type = "To'liboyev Javohir Beshik to'y",
            cardNumber = "9860030160619356",
            dateTime = "22-mart 2025,16:00"
        ),
    )
    val partyLists = listOf<PartyModel>(
        PartyModel(
            id = 1,
            userID = 1,
            type = "123456789012345678901234567890",
            cardNumber = "9860030160619356",
            dateTime = "21-22-mart 2025,17:00"
        ),
        PartyModel(
            id = 1,
            userID = 1,
            type = "Абдумадиярхуджаев Каландарбекжон Абдумаликсамандарович (Шагалкалла) Kelin to'y",
            cardNumber = "9860030160619356",
            dateTime = "21-22-mart 2025,17:00"
        ),
        PartyModel(
            id = 1,
            userID = 1,
            type = "Худайберганов Бехзод Sunnat to'y",
            cardNumber = "9860030160619356",
            dateTime = "21-mart 2025,12:00"
        ),
        PartyModel(
            id = 1,
            userID = 1,
            type = "To'liboyev Javohir Beshik to'y",
            cardNumber = "9860030160619356",
            dateTime = "22-mart 2025,16:00"
        ),
    )

    permissionController.requestNotificationPermission(activityContext)
    permissionController.requestExternalStoragePermission(activityContext!!)

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            MainScreenModalDrawerSheet(
                smallFontSize = smallFontSize.value,
                mediumFontSize = mediumFontSize.value,
                largeFontSize = largeFontSize.value,
                primaryColor = primaryColor,
                secondaryColor = secondaryColor,
                tertiaryColor = tertiaryColor,
                profileTitle = "${firstName.value} ${lastName.value}",
                profileAvatar = profileAvatar.value,
                phoneNumber = phoneNumber.value,
                onContactsClick = {
                    if (drawerState.isOpen) coroutineScope.launch { drawerState.close() }
                    navController.navigate(ScreensRouter.ContactsScreen.route)
                },
                onMainClick = {
                    if (drawerState.isOpen) coroutineScope.launch { drawerState.close() }
                    navController.navigate(ScreensRouter.MainScreen.route)
                },
                onMyEventsClick = {
                    if (drawerState.isOpen) coroutineScope.launch { drawerState.close() }
                    navController.navigate(ScreensRouter.MyEventsScreen.route)
                },
                onMonitoringClick = {
                    if (drawerState.isOpen) coroutineScope.launch { drawerState.close() }
                    navController.navigate(ScreensRouter.MonitoringScreen.route)
                },
                onWalletClick = {
                    if (drawerState.isOpen) coroutineScope.launch { drawerState.close() }
                    navController.navigate(ScreensRouter.WalletScreen.route)
                },
                onSettingsClick = {
                    if (drawerState.isOpen) coroutineScope.launch { drawerState.close() }
                    navController.navigate(ScreensRouter.SettingsScreen.route)
                },
                onSupportClick = {
                    if (drawerState.isOpen) coroutineScope.launch { drawerState.close() }
                    navController.navigate(ScreensRouter.SupportScreen.route)
                }
            )
        }
    ) {
        Scaffold(
            containerColor = primaryColor,
            contentColor = primaryColor,
            floatingActionButton = {
                MainScreenFAB(
                    primaryColor = primaryColor,
                    quaternaryColor = quaternaryColor,
                    onFabClick = { navController.navigate(ScreensRouter.AddEventScreen.route) }
                )
            },
            topBar = {
                MainScreenTopBar(
                    primaryColor = primaryColor,
                    secondaryColor = secondaryColor,
                    title = stringResource(R.string.app_name),
                    navigationIcon = profileAvatar.value,
                    onActionsClick = {
                        navController.navigate(ScreensRouter.NotificationsScreen.route)
                    }
                ) {
                    coroutineScope.launch {
                        if (drawerState.isOpen) drawerState.close() else drawerState.open()
                    }
                }
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                MainScreenSearchField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 5.dp)
                        .clickable { showSearchResults.value = true },
                    primaryColor = primaryColor,
                    secondaryColor = secondaryColor,
                    tertiaryColor = tertiaryColor,
                    value = searchValue.value,
                    onValueChange = {
                        showSearchResults.value = true
                        searchValue.value = it
                    },
                    onSearch = {
                        TODO()
                    }
                )
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(if (showSearchResults.value) partyLists else partyList) { model ->
                        MainScreenItem(
                            primaryColor = primaryColor,
                            secondaryColor = secondaryColor,
                            tertiaryColor = tertiaryColor,
                            sevenrdColor = sevenrdColor,
                            smallFontSize = smallFontSize.value,
                            mediumFontSize = mediumFontSize.value,
                            partyModel = model,
                            onItemClick = { navController.navigate(ScreensRouter.DetailsScreen.route) }
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewMainScreen() {
    MainScreen(NavController(LocalContext.current))
}