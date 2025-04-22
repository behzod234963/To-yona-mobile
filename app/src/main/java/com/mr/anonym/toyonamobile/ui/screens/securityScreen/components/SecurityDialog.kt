package com.mr.anonym.toyonamobile.ui.screens.securityScreen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.mr.anonym.toyonamobile.R

@Composable
fun SecurityDialog(
    secondaryColor: Color,
    quaternaryColor: Color,
    fiverdColor:Color,
    title: Int,
    onDismissClick:()-> Unit,
    onConfirmClick:()-> Unit,
    onDismissRequest:()-> Unit
) {

    AlertDialog(
        title = {
            Text(
                text = stringResource(title),
                color = secondaryColor,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
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
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Spacer(Modifier.width(15.dp))
                TextButton(
                    onClick = { onConfirmClick() }
                ) {
                    Text(
                        text = stringResource(R.string.yes),
                        color = fiverdColor,
                        fontWeight = FontWeight.SemiBold
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