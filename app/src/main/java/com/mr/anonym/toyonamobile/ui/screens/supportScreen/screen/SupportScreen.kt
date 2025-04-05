package com.mr.anonym.toyonamobile.ui.screens.supportScreen.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController

@Composable
fun SupportScreen(
    navController: NavController
) {
    
}

@Preview
@Composable
private fun PreviewSupportScreen() {
    SupportScreen(NavController(LocalContext.current))
}