package com.mr.anonym.toyonamobile.ui.screens.friendsScreen.components

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun ContactsFAB(
    quaternaryColor: Color,
    onClick:()-> Unit
) {
    FloatingActionButton(
        containerColor = quaternaryColor,
        contentColor = quaternaryColor,
        onClick = { onClick() },
        shape = CircleShape
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            tint = Color.White,
            contentDescription = ""
        )
    }
}