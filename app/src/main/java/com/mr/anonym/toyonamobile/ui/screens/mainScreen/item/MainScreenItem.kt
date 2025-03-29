package com.mr.anonym.toyonamobile.ui.screens.mainScreen.item

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mr.anonym.domain.model.PartyModel

@Composable
fun MainScreenItem(
    primaryColor: Color,
    secondaryColor: Color,
    tertiaryColor: Color,
    smallFontSize:Int,
    mediumFontSize:Int,
    partyModel: PartyModel,
    onItemClick:()-> Unit
) {

    Row (
        modifier = Modifier
            .fillMaxWidth()
            .background(primaryColor)
            .padding(vertical = 5.dp, horizontal = 10.dp)
            .clickable{ onItemClick() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Row (
            modifier = Modifier
                .fillMaxWidth(0.5f),
            verticalAlignment = Alignment.CenterVertically
        ){
            Card(
                modifier = Modifier
                    .size(50.dp)
                    .padding(5.dp),
                shape = CircleShape
            ) {
                Icon(
                    modifier = Modifier
                        .fillMaxSize(),
                    imageVector = Icons.Default.Person,
                    tint = secondaryColor,
                    contentDescription = "profile icon"
                )
            }
            Spacer(Modifier.width(10.dp))
            Text(
                text = partyModel.type,
                color = secondaryColor,
                fontSize = mediumFontSize.sp,
                textAlign = TextAlign.Center
            )
        }
        Row (
            modifier = Modifier
                .fillMaxWidth(0.5f)
        ){
            Text(
                text = "21-22-mart 2025",
                color = secondaryColor,
                fontSize = smallFontSize.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview
@Composable
private fun PreviewMainScreenItem() {
    MainScreenItem(
        primaryColor = if (isSystemInDarkTheme()) Color.Black else Color.White,
        secondaryColor = if (isSystemInDarkTheme()) Color.White else Color.Black,
        tertiaryColor = if (isSystemInDarkTheme()) Color.DarkGray else Color.LightGray,
        smallFontSize = 16,
        mediumFontSize = 18,
        partyModel = PartyModel(
            id = 1,
            userID = 1,
            type = "Kelin to'y",
            cardNumber = "9860030160619356",
            dateTime = "21-22-mart 2025"
        ),
        onItemClick = {  },
    )
}