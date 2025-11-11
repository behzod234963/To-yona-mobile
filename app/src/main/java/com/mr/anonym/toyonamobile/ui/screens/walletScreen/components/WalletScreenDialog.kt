package com.mr.anonym.toyonamobile.ui.screens.walletScreen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.mr.anonym.toyonamobile.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WalletScreenDialog(
    secondaryColor: Color,
    quaternaryColor: Color,
    fiveColor: Color,
    fontFamily: FontFamily,
    onConfirmClick:()-> Unit,
    onDismissClick:()-> Unit,
    onDismissRequest:()-> Unit
) {

    AlertDialog(
        title = {
            Text(
                text = stringResource(R.string.card_delete_alert),
                color = secondaryColor,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                fontFamily = fontFamily
            )
        },
        confirmButton = {
            Row (
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ){
                TextButton(
                    onClick = { onDismissClick() }
                ) {
                    Text(
                        text = stringResource(R.string.no),
                        color = quaternaryColor,
                        fontFamily = fontFamily,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Spacer(Modifier.width(15.dp))
                TextButton(
                    onClick = { onConfirmClick() }
                ) {
                    Text(
                        text = stringResource(R.string.yes),
                        color = fiveColor,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = fontFamily
                    )
                }
            }
        },
        onDismissRequest = { onDismissRequest() },
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true
        )
    )
}