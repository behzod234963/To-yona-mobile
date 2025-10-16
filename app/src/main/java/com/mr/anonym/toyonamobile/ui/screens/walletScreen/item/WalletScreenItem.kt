package com.mr.anonym.toyonamobile.ui.screens.walletScreen.item

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mr.anonym.domain.model.CardModel
import com.mr.anonym.toyonamobile.R

@Composable
fun WalletScreenItem(
    secondaryColor: Color,
    brush: Brush,
    model: CardModel,
    onChangeClick:()-> Unit,
    onDeleteClick:()-> Unit
) {

    Card(
        shape = RoundedCornerShape(10.dp),
    ){
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .background(brush)
                .height(200.dp)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ){
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.5f),
                verticalArrangement = Arrangement.Bottom
            ){
                Row (
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Text(
                        text = model.number,
                        color = secondaryColor,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = model.date,
                        color = secondaryColor,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Bottom
            ){
                Row (
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.Center
                ){
                    IconButton(
                        onClick = { onChangeClick() }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_edit),
                            tint = secondaryColor,
                            contentDescription = ""
                        )
                    }
                    Spacer(Modifier.width(30.dp))
                    IconButton(
                        onClick = { onDeleteClick() }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_delete),
                            tint = secondaryColor,
                            contentDescription = ""
                        )
                    }
                }
            }
        }
    }
    Spacer(Modifier.height(15.dp))
}

@Preview
@Composable
private fun PreviewWalletScreenItem() {
    val whiteGreen = Color(150, 180, 150, 255)
    val darkerGreen = Color(120, 150, 100, 255)
    WalletScreenItem(
        secondaryColor = Color.Black,
        brush = Brush.linearGradient(colors = listOf(whiteGreen,darkerGreen,Color.Green)),
        model = CardModel(
            id = 1,
            number = "9860 0301 6061 9356",
            date = "08/25",
        ),
        onChangeClick = {  }
    ) { }
}