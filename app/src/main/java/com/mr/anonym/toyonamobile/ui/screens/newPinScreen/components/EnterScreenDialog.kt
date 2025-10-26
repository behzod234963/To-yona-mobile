package com.mr.anonym.toyonamobile.ui.screens.newPinScreen.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.mr.anonym.toyonamobile.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EnterScreenDialog(
    secondaryColor: Color,
    tertiaryColor: Color,
    quaternaryColor: Color,
    sixColor:Color,
    title: String,
    fontFamily: FontFamily,
    confirmButton:()-> Unit,
    dismissButton:()-> Unit,
    onDismissRequest:()-> Unit
) {

    AlertDialog(
        containerColor = tertiaryColor,
        icon = {
            Icon(
                modifier = Modifier
                    .size(70.dp),
                painter = painterResource(R.drawable.ic_fingerprint),
                tint = secondaryColor,
                contentDescription = "null"
            )
        },
        title = {
            Row (
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = title,
                    color = secondaryColor,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                    fontFamily = fontFamily
                )
            }
        },
        confirmButton = {
            TextButton(
                onClick = { confirmButton() }
            ) {
                Text(
                    text = stringResource(R.string.Confirm),
                    color = sixColor,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = fontFamily
                )
            }
        },
        dismissButton = {
            TextButton(
                onClick = { dismissButton() }
            ) {
                Text(
                    text = stringResource(R.string.Later),
                    color = quaternaryColor,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = fontFamily
                )
            }
        },
        onDismissRequest = { onDismissRequest() },
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false
        )
    )
}

@Preview
@Composable
private fun PreviewEnterScreenDialog() {
    EnterScreenDialog (
        secondaryColor = if (isSystemInDarkTheme()) Color.White else Color.Black,
        tertiaryColor = if (isSystemInDarkTheme()) Color.DarkGray else Color.LightGray,
        quaternaryColor = Color.Red,
        sixColor = Color.Blue,
        title = "Barmoq izi orqali kirish yoqilsinmi?",
        confirmButton = {},
        dismissButton = {},
        fontFamily = FontFamily(Font(R.font.ios_font))
    ) {}
}