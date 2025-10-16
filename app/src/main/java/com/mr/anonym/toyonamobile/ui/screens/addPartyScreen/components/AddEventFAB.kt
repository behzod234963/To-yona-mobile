package com.mr.anonym.toyonamobile.ui.screens.addPartyScreen.components

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.mr.anonym.toyonamobile.R

@Composable
fun AddEventFAB(
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
            painter = painterResource(R.drawable.ic_check_two),
            tint = Color.White,
            contentDescription = ""
        )
    }
}