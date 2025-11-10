package com.mr.anonym.toyonamobile.ui.screens.monitoringScreen.items

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
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
import com.mr.anonym.toyonamobile.presentation.extensions.cardNumberFormatter

@Composable
fun FilterByCardItem(
    secondaryColor: Color,
    quaternaryColor: Color,
    fiveColor: Color,
    fontFamily: FontFamily,
    model: CardModel,
    isSelected: Boolean,
    onClick:()-> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable{
                onClick()
            },
        verticalArrangement = Arrangement.Center
    ) {
        HorizontalDivider()
        Row (
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Row (
                modifier = Modifier
                    .fillMaxWidth(0.5f),
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
//                    text = model.bankName,
                    text = "",
                    fontSize = 16.sp,
                    color = secondaryColor,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = fontFamily
                )
                Spacer(Modifier.width(5.dp))
                Text(
//                    text = model.paymentSystem,
                    text = "",
                    fontSize = 16.sp,
                    color = secondaryColor,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = fontFamily
                )
            }
            Row (
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = model.number.cardNumberFormatter(),
                    fontSize = 16.sp,
                    color = secondaryColor,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = fontFamily
                )
                Spacer(Modifier.width(5.dp))
                RadioButton(
                    selected = isSelected,
                    onClick = { onClick() },
                    colors = RadioButtonDefaults.colors(
                        unselectedColor = quaternaryColor,
                        selectedColor = fiveColor
                    ),
                )
            }
        }
    }
}