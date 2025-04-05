package com.mr.anonym.toyonamobile.ui.screens.addEventScreen.components

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun AddEventFAB(
    secondaryColor: Color,
    quaternaryColor: Color,
    onFabClick:()-> Unit
) {

    FloatingActionButton(
        containerColor = quaternaryColor,
        contentColor = quaternaryColor,
        shape = CircleShape,
        onClick = { onFabClick() }
    ) {
        Icon(
            imageVector = Icons.Default.Check,
            tint = secondaryColor,
            contentDescription = ""
        )
    }
}