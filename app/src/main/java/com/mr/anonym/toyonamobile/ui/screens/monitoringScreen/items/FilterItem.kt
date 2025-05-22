package com.mr.anonym.toyonamobile.ui.screens.monitoringScreen.items

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mr.anonym.domain.model.CardModel
import com.mr.anonym.toyonamobile.R
import com.mr.anonym.toyonamobile.presentation.extensions.cardNumberFormatter

@Composable
fun FilterItem(
    secondaryColor: Color,
    quaternaryColor: Color,
    fiverdColor: Color,
    sevenrdColor: Color,
    isExpanded: Boolean,
    isSelected: Int,
    selectedCard: String,
    onFilterClick:()-> Unit,
    onCardClick:(String,Int)-> Unit,
    cards: List<CardModel>
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = sevenrdColor,
            contentColor = sevenrdColor
        ),
        elevation = CardDefaults.cardElevation(7.dp),
        shape = RoundedCornerShape(10.dp),
        onClick = { onFilterClick() }
    ){
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalArrangement = Arrangement.Center
        ){
            Row (
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Row (
                    modifier = Modifier
                        .fillMaxWidth(0.7f),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        text = stringResource(R.string.by_card),
                        color = secondaryColor,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(Modifier.width(10.dp))
                    Text(
                        text = if (selectedCard.isNotEmpty() || selectedCard.isNotBlank())selectedCard.cardNumberFormatter() else "",
                        color = secondaryColor,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Icon(
                    painter = if (isExpanded) painterResource(R.drawable.ic_minus) else painterResource(R.drawable.ic_add),
                    tint = secondaryColor,
                    contentDescription = ""
                )
            }
            if (isExpanded){
                if (cards.isNotEmpty()){
                    LazyColumn (
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    ){
                        itemsIndexed(cards){ index,model->
                            FilterByCardItem(
                                secondaryColor = secondaryColor,
                                quaternaryColor = quaternaryColor,
                                fiverdColor = fiverdColor,
                                model = model,
                                isSelected = isSelected == index,
                                onClick = { onCardClick( model.number,index ) }
                            )
                        }
                    }
                }else{
                    Row (
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 5.dp, horizontal = 10.dp),
                        verticalAlignment = Alignment.Bottom,
                        horizontalArrangement = Arrangement.Center
                    ){
                        Text(
                            text = stringResource(R.string.you_have_not_active_card),
                            color = secondaryColor,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewFilterItem() {
    FilterItem(
        secondaryColor = Color.Black,
        sevenrdColor = Color.White,
        quaternaryColor = Color.Red,
        fiverdColor = Color.Green,
        isExpanded = true,
        onCardClick = { card,index->

        },
        onFilterClick = {},
        isSelected = 0,
        selectedCard = "9860030160619356",
        cards = listOf(
            CardModel(
                number = "9860030160619356"
            )
        )
    )
}