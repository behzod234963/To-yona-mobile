package com.mr.anonym.toyonamobile.ui.screens.walletScreen.screen

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.mr.anonym.data.instance.local.SharedPreferencesInstance
import com.mr.anonym.toyonamobile.R
import com.mr.anonym.toyonamobile.presentation.navigation.ScreensRouter
import com.mr.anonym.toyonamobile.ui.screens.walletScreen.components.WalletScreenDialog
import com.mr.anonym.toyonamobile.ui.screens.walletScreen.components.WalletTopBar
import com.mr.anonym.toyonamobile.ui.screens.walletScreen.item.WalletScreenItem
import com.mr.anonym.toyonamobile.ui.screens.walletScreen.viewModel.WalletViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@Composable
fun WalletScreen(
    navController: NavController,
    viewModel: WalletViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    val sharedPreferences = SharedPreferencesInstance(context)

    val isDarkTheme = sharedPreferences.getDarkThemeState()
    val isSystemTheme = sharedPreferences.getSystemThemeState()

    val addCardFromDetailsState = sharedPreferences.addCardFromDetailsState()
    val addCardFromAddEvent = sharedPreferences.addCardFromAddEventState()

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
    val cardID = remember { mutableIntStateOf(-1) }
    sharedPreferences.addCardProcess(false)

    val isLoading = remember { mutableStateOf(false) }
    val loadingAnimations = rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.anim_loading)
    )

    BackHandler {
        when {
            addCardFromDetailsState -> {
                sharedPreferences.addCardFromDetails(false)
                navController.navigate(ScreensRouter.DetailsScreen.route) {
                    popUpTo(ScreensRouter.DetailsScreen.route) {
                        inclusive = true
                    }
                    launchSingleTop = true
                }
            }
            addCardFromAddEvent -> {
                sharedPreferences.addCardFromAddEvent(false)
                navController.navigate(ScreensRouter.AddPartyScreen.route + "/-1") {
                    popUpTo(ScreensRouter.AddPartyScreen.route) {
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
                        addCardFromDetailsState -> {
                            sharedPreferences.addCardFromDetails(false)
                            navController.navigate(ScreensRouter.DetailsScreen.route) {
                                popUpTo(ScreensRouter.DetailsScreen.route) {
                                    inclusive = true
                                }
                                launchSingleTop = true
                            }
                        }
                        addCardFromAddEvent -> {
                            sharedPreferences.addCardFromAddEvent(false)
                            navController.navigate(ScreensRouter.AddPartyScreen.route + "/-1") {
                                popUpTo(ScreensRouter.AddPartyScreen.route) {
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
        if (!isLoading.value) {
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
                        onDeleteClick = {
                            cardID.intValue = model.id
                            showDeleteDialog.value = true
                        }
                    )
                }
            }
            if (showDeleteDialog.value) {
                WalletScreenDialog(
                    secondaryColor = secondaryColor,
                    quaternaryColor = quaternaryColor,
                    fiverdColor = fiverdColor,
                    onConfirmClick = {
                        showDeleteDialog.value = false
                        viewModel.deleteCard(
                            id = cardID.intValue
                        )
                        isLoading.value = true
                    },
                    onDismissClick = { showDeleteDialog.value = false },
                    onDismissRequest = { showDeleteDialog.value = false }
                )
            }
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                LottieAnimation(
                    modifier = Modifier
                        .size(150.dp),
                    composition = loadingAnimations.value,
                    restartOnPlay = true,
                    iterations = LottieConstants.IterateForever
                )
                coroutineScope.launch {
                    delay(1000)
                    viewModel.getUserCards()
                    isLoading.value = false
                }
            }
        }
    }
}