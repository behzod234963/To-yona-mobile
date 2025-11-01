package com.mr.anonym.toyonamobile.ui.screens.notificationsScreen.items

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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mr.anonym.domain.model.NotificationsModel

@Composable
fun NotificationsItem(
    secondaryColor: Color,
    tertiaryColor: Color,
    nineColor: Color,
    fontFamily: FontFamily,
    notification: NotificationsModel
) {
    Card(
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = nineColor,
            contentColor = nineColor
        )
    ) {
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ){
            Row (
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(
                    modifier = Modifier
                        .fillMaxWidth(0.75f)
                        .padding(end = 5.dp),
                    text = notification.title,
                    color = secondaryColor,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = fontFamily
                )
                Text(
                    text = notification.date,
                    color = secondaryColor,
                    fontSize = 13.sp,
                    fontFamily = fontFamily
                )
            }
            HorizontalDivider(Modifier.fillMaxWidth(), thickness = 0.5.dp,color = tertiaryColor)
            Spacer(Modifier.height(5.dp))
            Text(
                text = notification.description,
                color = secondaryColor,
                fontSize = 16.sp,
                fontFamily = fontFamily
            )
        }
    }
    Spacer(Modifier.height(10.dp))
}