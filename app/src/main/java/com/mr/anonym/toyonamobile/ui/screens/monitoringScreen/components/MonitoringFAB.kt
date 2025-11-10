package com.mr.anonym.toyonamobile.ui.screens.monitoringScreen.components

import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.mr.anonym.toyonamobile.R

@Composable
fun MonitoringFAB(
    secondaryColor: Color,
    fiveColor: Color,
    onClick:()-> Unit
) {
    FloatingActionButton(
        containerColor = fiveColor,
        contentColor = fiveColor,
        onClick = { onClick() }
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_filter),
            tint = secondaryColor,
            contentDescription = ""
        )
    }
}