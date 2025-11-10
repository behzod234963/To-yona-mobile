package com.mr.anonym.toyonamobile.ui.screens.monitoringScreen.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mr.anonym.toyonamobile.R

@Composable
fun FilteredContent(
    secondaryColor: Color,
    text: String,
    fontFamily: FontFamily,
    onClear:()-> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(
            text = text,
            fontFamily = fontFamily,
            fontSize = 16.sp,
            color = secondaryColor
        )
        Spacer(Modifier.height(10.dp))
        IconButton(
            onClick = { onClear() }
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_close),
                tint = secondaryColor,
                contentDescription = ""
            )
        }
    }
}