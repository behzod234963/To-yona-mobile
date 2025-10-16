package com.mr.anonym.toyonamobile.ui.screens.friendsScreen.components

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.mr.anonym.toyonamobile.R

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
            painter = painterResource(R.drawable.ic_add),
            tint = Color.White,
            contentDescription = ""
        )
    }
}