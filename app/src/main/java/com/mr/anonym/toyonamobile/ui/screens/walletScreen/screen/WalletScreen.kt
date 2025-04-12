package com.mr.anonym.toyonamobile.ui.screens.walletScreen.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.mr.anonym.toyonamobile.presentation.navigation.ScreensRouter
import com.mr.anonym.toyonamobile.ui.screens.walletScreen.screen.components.WalletTopBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun WalletScreen(
    navController: NavController
) {

    val primaryColor = if (isSystemInDarkTheme()) Color.Black else Color.White
    val secondaryColor = if (isSystemInDarkTheme()) Color.White else Color.Black
    val tertiaryColor = if (isSystemInDarkTheme()) Color.DarkGray else Color.LightGray
    val quaternaryColor = Color.Red
    val fiverdColor = Color.Green
    val sixrdColor = Color.Blue
    val sevenrdColor = if (isSystemInDarkTheme()) Color.Unspecified else primaryColor

    Scaffold(
        containerColor = primaryColor,
        contentColor = primaryColor,
        topBar = {
            WalletTopBar(
                primaryColor = primaryColor,
                secondaryColor = secondaryColor,
                navigationClick = { navController.popBackStack() },
                onActionsClick = { navController.navigate(ScreensRouter.AddCardScreen.route) }
            ) 
        }
    ) { paddingValues ->
        LazyColumn (
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ){

        }
    }
}

@Preview
@Composable
private fun PreviewWalletScreen() {
    WalletScreen(
        NavController(LocalContext.current)
    )
}