package com.mr.anonym.toyonamobile.ui.screens.walletScreen.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController

@Composable
fun WalletScreen(
    navController: NavController
) {
    
}

@Preview
@Composable
private fun PreviewWalletScreen() {
    WalletScreen(
        NavController(LocalContext.current)
    )
}