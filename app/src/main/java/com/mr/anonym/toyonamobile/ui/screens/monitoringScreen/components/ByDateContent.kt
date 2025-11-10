package com.mr.anonym.toyonamobile.ui.screens.monitoringScreen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ByDateContent(
    text: String,
    secondaryColor: Color,
    quaternaryColor: Color,
    fiveColor: Color,
    nineColor: Color,
    fontFamily: FontFamily,
    isChecked: Boolean,
    onCheckedChange:(Boolean)-> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = secondaryColor,
            fontFamily = fontFamily
        )
        Switch(
            checked = isChecked,
            onCheckedChange = { onCheckedChange(it) },
            enabled = true,
            colors = SwitchDefaults.colors(
                checkedThumbColor = fiveColor,
                uncheckedThumbColor = quaternaryColor,
                checkedTrackColor = nineColor,
                uncheckedTrackColor = nineColor,
                checkedBorderColor = fiveColor,
                uncheckedBorderColor = quaternaryColor
            ),
        )
    }
}