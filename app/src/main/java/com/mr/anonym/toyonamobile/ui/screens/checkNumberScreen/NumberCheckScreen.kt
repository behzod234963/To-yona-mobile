package com.mr.anonym.toyonamobile.ui.screens.checkNumberScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.mr.anonym.toyonamobile.presentation.utils.Arguments

@Composable
fun NumberCheckScreen(
    arguments: Arguments,
    navController: NavController
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Text(
            text = arguments.number
        )
    }
}

@Preview
@Composable
private fun PreviewNumberCheckScreen() {
    NumberCheckScreen( navController = NavController(LocalContext.current), arguments = Arguments("+998973570498"))
}