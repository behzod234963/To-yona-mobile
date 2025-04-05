package com.mr.anonym.toyonamobile.ui.screens.notificationsScreen.items

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mr.anonym.domain.model.NotificationsModel

@Composable
fun NotificationsItem(
    tertiaryColor: Color,
    secondaryColor: Color,
    notification: NotificationsModel
) {

    Card(
        modifier = Modifier
            .padding(5.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = tertiaryColor,
            contentColor = tertiaryColor
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
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = notification.date,
                    color = secondaryColor,
                    fontSize = 13.sp,
                )
            }
            Spacer(Modifier.height(5.dp))
            Text(
                text = notification.description,
                color = secondaryColor,
                fontSize = 16.sp,
            )
        }
    }
}

@Preview
@Composable
private fun PreviewNotificationsItem() {
    NotificationsItem(
        tertiaryColor = if (isSystemInDarkTheme()) Color.DarkGray else Color.LightGray,
        secondaryColor = if (isSystemInDarkTheme()) Color.White else Color.Black,
        notification = NotificationsModel(
            id = 1,
            title = "Худайберганов Бехзод Sunnat to'y",
            description = "Худайберганов Бехзод Sunnat to'y \n Orzu to'yxonasi \n 22-mart 2025",
            date = "31.03.2025"
        )
    )
}