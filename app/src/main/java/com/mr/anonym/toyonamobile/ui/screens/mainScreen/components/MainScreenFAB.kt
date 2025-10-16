package com.mr.anonym.toyonamobile.ui.screens.mainScreen.components

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.mr.anonym.toyonamobile.R

@Composable
fun MainScreenFAB(
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
            painter = painterResource(R.drawable.ic_add),
            tint = Color.White,
            contentDescription = "new index button"
        )
    }
}