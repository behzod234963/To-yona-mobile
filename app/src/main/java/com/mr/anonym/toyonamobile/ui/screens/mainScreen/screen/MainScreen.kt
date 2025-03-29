package com.mr.anonym.toyonamobile.ui.screens.mainScreen.screen

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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mr.anonym.domain.model.PartyModel
import com.mr.anonym.toyonamobile.R
import com.mr.anonym.toyonamobile.presentation.navigation.ScreensRouter
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
    val coroutineScope = rememberCoroutineScope()
    val primaryColor = if (isSystemInDarkTheme()) Color.Black else Color.White
    val secondaryColor = if (isSystemInDarkTheme()) Color.White else Color.Black
    val tertiaryColor = if (isSystemInDarkTheme()) Color.DarkGray else Color.LightGray
    val quaternaryColor = Color.Red
    val fiverdColor = Color.Green
    val sixrdColor = Color.Blue

    val smallFontSize = remember { mutableIntStateOf(16) }
    val mediumFontSize = remember { mutableIntStateOf(18) }
    val largeFontSize = remember { mutableIntStateOf(22) }

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    val partyList = listOf<PartyModel>(
        PartyModel(
            id = 1,
            userID = 1,
            type = "Kelin to'y",
            cardNumber = "9860030160619356",
            dateTime = "21-22-mart 2025"
        ),
        PartyModel(
            id = 1,
            userID = 1,
            type = "Sunnat to'y",
            cardNumber = "9860030160619356",
            dateTime = "21-22-mart 2025"
        ),
        PartyModel(
            id = 1,
            userID = 1,
            type = "Beshik to'y",
            cardNumber = "9860030160619356",
            dateTime = "21-22-mart 2025"
        ),
    )

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
                onMainClick = {
                    navController.navigate(ScreensRouter.MainScreen.route)
                },
                onContactsClick = {},
                onMonitoringClick = { },
                onWalletClick = { },
                onSettingsClick = { },
                onSupportClick = { }
            )
        }
    ) {
        Scaffold(
            topBar = {
                MainScreenTopBar(
                    secondaryColor = secondaryColor,
                    title = stringResource(R.string.app_name),
                    onNavigationIconClick = {
                        coroutineScope.launch {
                            if (drawerState.isOpen) drawerState.close() else drawerState.open()
                        }
                    },
                    onActionsClick = { }
                )
            }
        ) { paddingValues ->
            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                MainScreenSearchField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 5.dp),
                    secondaryColor = secondaryColor,
                    tertiaryColor = tertiaryColor
                )
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items( partyList ){model->
                        MainScreenItem(
                            primaryColor = primaryColor,
                            secondaryColor = secondaryColor,
                            tertiaryColor = tertiaryColor,
                            smallFontSize = smallFontSize.value,
                            mediumFontSize = mediumFontSize.value,
                            partyModel = model,
                            onItemClick = {  }
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