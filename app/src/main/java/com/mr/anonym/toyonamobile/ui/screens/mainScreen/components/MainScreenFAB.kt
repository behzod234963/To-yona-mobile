package com.mr.anonym.toyonamobile.ui.screens.mainScreen.components

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MainScreenFAB(
    secondaryColor: Color,
    quaternaryColor:Color,
    onFabClick:()-> Unit
) {

    FloatingActionButton(
        shape = CircleShape,
        containerColor = quaternaryColor,
        contentColor = quaternaryColor,
        elevation = FloatingActionButtonDefaults.elevation(10.dp),
        onClick = { onFabClick() }
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            tint = Color.White,
            contentDescription = "new index button"
        )
    }
}