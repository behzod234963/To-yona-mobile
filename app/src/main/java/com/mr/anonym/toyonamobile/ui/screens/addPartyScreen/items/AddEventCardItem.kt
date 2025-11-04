package com.mr.anonym.toyonamobile.ui.screens.addPartyScreen.items

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mr.anonym.domain.model.CardModel
import com.mr.anonym.toyonamobile.ui.screens.addPartyScreen.components.AddEventCardField

@Composable
fun AddEventCardItem(
    secondaryColor: Color,
    tertiaryColor: Color,
    fontFamily: FontFamily,
    value: String,
    cards: List<CardModel>,
    onClick:(CardModel)-> Unit,
    onAddCardClick:()-> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Top
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
//                text = "${cardModel.bankName} ${cardModel.paymentSystem}",
                text = "",
                fontSize = 16.sp,
                color = secondaryColor,
                fontWeight = FontWeight.SemiBold,
                fontFamily = fontFamily
            )
            AddEventCardField(
                secondaryColor = secondaryColor,
                tertiaryColor = tertiaryColor,
                fontFamily = fontFamily,
                value = value,
                values = cards,
                onClick = { onClick(it) }
            ) { onAddCardClick() }
        }
    }
}