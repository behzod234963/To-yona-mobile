package com.mr.anonym.toyonamobile.ui.screens.walletScreen.screen

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mr.anonym.data.instance.local.DataStoreInstance
import com.mr.anonym.data.instance.local.SharedPreferencesInstance
import com.mr.anonym.toyonamobile.presentation.navigation.ScreensRouter
import com.mr.anonym.toyonamobile.ui.screens.walletScreen.components.WalletScreenDialog
import com.mr.anonym.toyonamobile.ui.screens.walletScreen.components.WalletTopBar
import com.mr.anonym.toyonamobile.ui.screens.walletScreen.item.WalletScreenItem
import com.mr.anonym.toyonamobile.ui.screens.walletScreen.viewModel.WalletViewModel
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun WalletScreen(
    navController: NavController,
    viewModel: WalletViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    val dataStore = DataStoreInstance(context)
    val sharedPreferences = SharedPreferencesInstance(context)

    val isDarkTheme = sharedPreferences.getDarkThemeState()
    val isSystemTheme = sharedPreferences.getSystemThemeState()

    val addCardFromDetailsState = dataStore.addCardFromDetailsState().collectAsState(false)
    val addCardFromAddEvent = dataStore.addCardFromAddEventState().collectAsState(false)

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
    val quaternaryColor = Color.Red
    val fiverdColor = Color.Green

    val whiteGreen = Color(5, 114, 5, 255)
    val cardBackgroundBrush = Brush.linearGradient(colors = listOf(whiteGreen, fiverdColor))

    val showDeleteDialog = rememberSaveable { mutableStateOf(false) }

    val cards = viewModel.cards
    sharedPreferences.addCardProcess(false)

    BackHandler {
        when {
            addCardFromDetailsState.value -> {
                coroutineScope.launch {
                    dataStore.addCardFromDetails(false)
                }
                navController.navigate(ScreensRouter.DetailsScreen.route) {
                    popUpTo(ScreensRouter.DetailsScreen.route) {
                        inclusive = true
                    }
                    launchSingleTop = true
                }
            }
            addCardFromAddEvent.value->{
                coroutineScope.launch {
                    dataStore.addCardFromAddEvent(false)
                }
                navController.navigate(ScreensRouter.AddEventScreen.route + "/-1") {
                    popUpTo(ScreensRouter.AddEventScreen.route) {
                        inclusive = true
                    }
                    launchSingleTop = true
                }
            }
            else -> {
                navController.navigateUp()
            }
        }
    }
    Scaffold(
        containerColor = primaryColor,
        contentColor = primaryColor,
        topBar = {
            WalletTopBar(
                primaryColor = primaryColor,
                secondaryColor = secondaryColor,
                navigationClick = {
                    when {
                        addCardFromDetailsState.value -> {
                            coroutineScope.launch {
                                dataStore.addCardFromDetails(false)
                            }
                            navController.navigate(ScreensRouter.DetailsScreen.route) {
                                popUpTo(ScreensRouter.DetailsScreen.route) {
                                    inclusive = true
                                }
                                launchSingleTop = true
                            }
                        }
                        addCardFromAddEvent.value->{
                            coroutineScope.launch {
                                dataStore.addCardFromAddEvent(false)
                            }
                            navController.navigate(ScreensRouter.AddEventScreen.route + "/-1") {
                                popUpTo(ScreensRouter.AddEventScreen.route) {
                                    inclusive = true
                                }
                                launchSingleTop = true
                            }
                        }
                        else -> {
                            navController.navigateUp()
                        }
                    }
                },
                onActionsClick = { navController.navigate(ScreensRouter.AddCardScreen.route + "/-1") }
            )
        }
    ) { paddingValues ->
        if (showDeleteDialog.value) {
            WalletScreenDialog(
                secondaryColor = secondaryColor,
                quaternaryColor = quaternaryColor,
                fiverdColor = fiverdColor,
                onConfirmClick = {
                    showDeleteDialog.value = false
                },
                onDismissClick = { showDeleteDialog.value = false },
                onDismissRequest = { showDeleteDialog.value = false }
            )
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(10.dp)
        ) {
            items(cards.value) { model ->
                WalletScreenItem(
                    secondaryColor = secondaryColor,
                    brush = cardBackgroundBrush,
                    model = model,
                    onChangeClick = { navController.navigate(ScreensRouter.AddCardScreen.route + "/${model.id}") },
                    onDeleteClick = { showDeleteDialog.value = true }
                )
            }
        }
    }
}