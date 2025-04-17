package com.mr.anonym.toyonamobile.ui.screens.walletScreen.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun WalletScreenItem(
    secondaryColor: Color,
    sevenrdColor: Color
) {

    Card (
        colors = CardDefaults.cardColors(
            contentColor = sevenrdColor,
            containerColor = sevenrdColor
        ),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(7.dp)
    ){
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
        ){

        }
    }
}

@Preview
@Composable
private fun PreviewWalletScreenItem() {
    WalletScreenItem(
        secondaryColor = if (isSystemInDarkTheme()) Color.White else Color.Black,
        sevenrdColor = Color.Unspecified
    )
}