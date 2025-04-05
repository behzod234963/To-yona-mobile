package com.mr.anonym.toyonamobile.ui.screens.settingsScreen.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController

@Composable
fun SettingsScreen(
    navController: NavController
) {
    
}

@Preview
@Composable
private fun PreviewSettingsScreen() {
    SettingsScreen(
        NavController(LocalContext.current)
    )
}