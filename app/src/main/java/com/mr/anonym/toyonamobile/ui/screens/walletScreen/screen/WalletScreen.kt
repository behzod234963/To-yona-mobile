package com.mr.anonym.toyonamobile.ui.screens.walletScreen.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.mr.anonym.domain.model.CardModel
import com.mr.anonym.toyonamobile.presentation.navigation.ScreensRouter
import com.mr.anonym.toyonamobile.ui.screens.walletScreen.components.WalletScreenDialog
import com.mr.anonym.toyonamobile.ui.screens.walletScreen.components.WalletTopBar
import com.mr.anonym.toyonamobile.ui.screens.walletScreen.item.WalletScreenItem
import com.mr.anonym.toyonamobile.ui.screens.walletScreen.viewModel.WalletViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun WalletScreen(
    navController: NavController,
    viewModel: WalletViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    val dataStore = DataStoreInstance(context)
    val sharedPreferences = SharedPreferencesInstance(context)

    val isDarkTheme = dataStore.getDarkThemeState().collectAsState(false)
    val iSystemTheme = dataStore.getSystemThemeState().collectAsState(true)

    val systemPrimaryColor = if (isSystemInDarkTheme()) Color.Black else Color.White
    val primaryColor = when {
        iSystemTheme.value -> {
            systemPrimaryColor
        }

        isDarkTheme.value -> Color.Black
        else -> Color.White
    }
    val systemSecondaryColor = if (isSystemInDarkTheme()) Color.White else Color.Black
    val secondaryColor = when {
        iSystemTheme.value -> systemSecondaryColor
        isDarkTheme.value -> Color.White
        else -> Color.Black
    }
    val systemTertiaryColor = if (isSystemInDarkTheme()) Color.DarkGray else Color.LightGray
    val tertiaryColor = when {
        iSystemTheme.value -> systemTertiaryColor
        isDarkTheme.value -> Color.DarkGray
        else -> Color.LightGray
    }
    val quaternaryColor = Color.Red
    val fiverdColor = Color.Green
    val sixrdColor = Color.Blue
    val systemSevenrdColor = if (isSystemInDarkTheme()) Color.Unspecified else Color.White
    val sevenrdColor = when {
        iSystemTheme.value -> systemSevenrdColor
        isDarkTheme.value -> Color.Unspecified
        else -> Color.White
    }

    val whiteGreen = Color(5, 114, 5, 255)
    val cardBackgroundBrush = Brush.linearGradient(colors = listOf(whiteGreen,fiverdColor))

    val showDeleteDialog = rememberSaveable { mutableStateOf( false ) }

    val cards = viewModel.cards
    val cardModel = remember { mutableStateOf(CardModel() ) }
    sharedPreferences.addCardProcess(false)

    Scaffold(
        containerColor = primaryColor,
        contentColor = primaryColor,
        topBar = {
            WalletTopBar(
                primaryColor = primaryColor,
                secondaryColor = secondaryColor,
                navigationClick = { navController.popBackStack() },
                onActionsClick = { navController.navigate(ScreensRouter.AddCardScreen.route + "/-1") }
            ) 
        }
    ) { paddingValues ->
        if (showDeleteDialog.value){
            WalletScreenDialog(
                secondaryColor = secondaryColor,
                quaternaryColor = quaternaryColor,
                fiverdColor = fiverdColor,
                onConfirmClick = {
                    viewModel.deleteCard(cardModel.value)
                    showDeleteDialog.value = false
                },
                onDismissClick = { showDeleteDialog.value = false },
                onDismissRequest = { showDeleteDialog.value = false }
            )
        }
        LazyColumn (
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(10.dp)
        ){
            items(cards.value){model->
                WalletScreenItem(
                    secondaryColor = secondaryColor,
                    brush = cardBackgroundBrush,
                    model = model,
                    onChangeClick = { navController.navigate(ScreensRouter.AddCardScreen.route + "/${model.id}") },
                    onDeleteClick = { showDeleteDialog.value = true }
                )
                cardModel.value = model
            }
        }
    }
}