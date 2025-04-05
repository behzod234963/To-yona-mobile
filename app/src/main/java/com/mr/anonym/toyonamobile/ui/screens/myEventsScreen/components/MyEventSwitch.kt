package com.mr.anonym.toyonamobile.ui.screens.myEventsScreen.components

import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun MyEventSwitch(
    secondaryColor: Color,
    quaternaryColor: Color,
    fiverdColor: Color,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
) {
    Switch(
        checked = isChecked,
        onCheckedChange = { onCheckedChange(it) },
        enabled = true,
        colors = SwitchDefaults.colors(
            checkedThumbColor = secondaryColor,
            checkedTrackColor = fiverdColor,
            checkedBorderColor = secondaryColor,
            checkedIconColor = fiverdColor,
            uncheckedThumbColor = secondaryColor,
            uncheckedTrackColor = quaternaryColor,
            uncheckedBorderColor = secondaryColor,
            uncheckedIconColor = quaternaryColor
        ),
    )
}