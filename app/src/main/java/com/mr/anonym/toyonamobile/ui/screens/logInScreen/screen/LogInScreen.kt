package com.mr.anonym.toyonamobile.ui.screens.logInScreen.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController

@Composable
fun LogInScreen(
    navController: NavController
) {

}

@Preview
@Composable
private fun PreviewLogInScreen() {
    LogInScreen(NavController(LocalContext.current))
}