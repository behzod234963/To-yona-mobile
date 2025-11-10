package com.mr.anonym.toyonamobile.ui.screens.monitoringScreen.items

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mr.anonym.domain.model.MonitoringModel

@Composable
fun MonitoringItem(
    secondaryColor: Color,
    quaternaryColor: Color,
    fiveColor: Color,
    nineColor: Color,
    fontFamily: FontFamily,
    model: MonitoringModel,
    onClick:()-> Unit
) {

    Card (
        modifier = Modifier
            .padding(vertical = 5.dp),
        colors = CardDefaults.cardColors(
            containerColor = nineColor,
            contentColor = nineColor
        ),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation( 7.dp ),
        onClick = { onClick() }
    ){
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp, horizontal = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Column {
                Text(
                    text = model.eventName,
                    color = secondaryColor,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = fontFamily
                )
                Spacer(Modifier.height(5.dp))
                Text(
                    text = "${model.eventOwnerName} ${model.eventOwnerLastName}",
                    color = secondaryColor,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = fontFamily
                )
            }
            Text(
                text = model.amount,
                color = if (model.amount.contains("-")) quaternaryColor else fiveColor,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = fontFamily
            )
        }
    }
}