package com.mr.anonym.toyonamobile.ui.screens.mainScreen.item

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
    sevenrdColor: Color,
    smallFontSize:Int,
    mediumFontSize:Int,
    partyModel: PartyModel,
    onItemClick:()-> Unit
) {

    Card (
        modifier = Modifier
            .padding(5.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = sevenrdColor,
            contentColor = sevenrdColor
        ),
        elevation = CardDefaults.cardElevation(7.dp)
    ){
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
                .clickable{ onItemClick() },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Row (
                modifier = Modifier
                    .fillMaxWidth(0.75f),
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
                Spacer(Modifier.width(2.dp))
                Text(
                    modifier = Modifier
                        .padding(vertical = 5.dp),
                    text = partyModel.type,
                    color = secondaryColor,
                    fontSize = mediumFontSize.sp,
                )
            }
            Row (
                modifier = Modifier
                    .fillMaxWidth()
            ){
                Spacer(Modifier.width(2.dp))
                Text(
                    modifier = Modifier
                        .padding(vertical = 5.dp),
                    text = partyModel.dateTime,
                    color = secondaryColor,
                    fontSize = smallFontSize.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}