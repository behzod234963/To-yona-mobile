package com.mr.anonym.toyonamobile.ui.screens.monitoringScreen.items

import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mr.anonym.domain.model.MonitoringModel

@Composable
fun MonitoringItem(
    secondaryColor: Color,
    quaternaryColor: Color,
    fiverdColor: Color,
    nineColor: Color,
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
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(Modifier.height(5.dp))
                Text(
                    text = "${model.eventOwnerName} ${model.eventOwnerLastName}",
                    color = secondaryColor,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Text(
                text = model.amount,
                color = if (model.amount.contains("-")) quaternaryColor else fiverdColor,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Preview
@Composable
private fun PreviewMonitoringModel() {
    MonitoringItem(
        secondaryColor = if (isSystemInDarkTheme()) Color.White else Color.Black,
        quaternaryColor = Color.Red,
        fiverdColor = Color.Green,
        nineColor = if (isSystemInDarkTheme()) Color.Unspecified else Color.White,
        model = MonitoringModel(
            id = 1,
            eventName = "Wedding",
            eventOwnerName = "",
            eventOwnerLastName = "",
            dateTime = "",
            amount = "",
            senderCardNumber = "",
            senderCardHolder = "",
            receiverCardNumber = "",
            receiverCardHolder = "",
            transferStatus = "",
            monthIndex = 3
        )
    ) { }
}