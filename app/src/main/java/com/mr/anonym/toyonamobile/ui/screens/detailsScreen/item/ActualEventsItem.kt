package com.mr.anonym.toyonamobile.ui.screens.detailsScreen.item

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mr.anonym.domain.model.FriendsModel
import com.mr.anonym.domain.model.PartyModel
import com.mr.anonym.toyonamobile.R
import com.mr.anonym.toyonamobile.presentation.extensions.cardNumberFormatter
import com.mr.anonym.toyonamobile.ui.screens.detailsScreen.components.DetailsPriceFields

@Composable
fun ActualEventsItem(
    secondaryColor: Color,
    fiverdColor: Color,
    sevenrdColor: Color,
    partyModel: PartyModel,
    friendsModel: FriendsModel,
    priceFieldError: Boolean,
    onTransferClick:(String)-> Unit
) {

    val isExpanded = rememberSaveable { mutableStateOf( true ) }
    val priceValue = remember { mutableStateOf("" ) }

    Card(
        modifier = Modifier
            .padding(5.dp),
        colors = CardDefaults.cardColors(
            containerColor = sevenrdColor,
            contentColor = sevenrdColor
        ),
        elevation = CardDefaults.cardElevation( 10.dp ),
        shape = RoundedCornerShape(10.dp),
        onClick = {
            isExpanded.value = !isExpanded.value
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = partyModel.type,
                    color = secondaryColor,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.more_details),
                        color = secondaryColor,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(Modifier.width(5.dp))
                    Icon(
                        imageVector = if (isExpanded.value) Icons.Default.KeyboardArrowDown else Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                        tint = secondaryColor,
                        contentDescription = ""
                    )
                }
            }
            if (isExpanded.value) {
                Spacer(Modifier.height(5.dp))
                Column(
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                ) {
//                    Event type
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = stringResource(R.string.event),
                            color = secondaryColor,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            text = partyModel.type,
                            color = secondaryColor,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                    Spacer(Modifier.height(5.dp))
                    HorizontalDivider()
                    Spacer(Modifier.height(10.dp))
//                    Event date time
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth(0.5f),
                            text = stringResource(R.string.event_date_and_time),
                            color = secondaryColor,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            text = partyModel.dateTime,
                            color = secondaryColor,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.End
                        )
                    }
                    Spacer(Modifier.height(5.dp))
                    HorizontalDivider()
                    Spacer(Modifier.height(10.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = stringResource(R.string.requisites),
                            color = secondaryColor,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                    Spacer(Modifier.height(10.dp))
//                    Receiver name
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth(0.5f),
                            text = stringResource(R.string.card_holder_name),
                            color = secondaryColor,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            text = "${friendsModel.name} ${friendsModel.surname}",
                            color = secondaryColor,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.End
                        )
                    }
                    Spacer(Modifier.height(5.dp))
                    HorizontalDivider()
                    Spacer(Modifier.height(10.dp))
//                    Receiver card number
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth(0.5f),
                            text = stringResource(R.string.card_number),
                            color = secondaryColor,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            text = partyModel.cardNumber.cardNumberFormatter(),
                            color = secondaryColor,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.End
                        )
                    }
                    Spacer(Modifier.height(5.dp))
                    HorizontalDivider()
                    Spacer(Modifier.height(10.dp))
                    DetailsPriceFields(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp),
                        secondaryColor = secondaryColor,
                        fiverdColor = fiverdColor,
                        value = priceValue.value,
                        priceFieldError = priceFieldError,
                        onValueChange = { newValue ->
                            priceValue.value = newValue
                        }
                    ) { onTransferClick(priceValue.value) }
                }
            }
        }
    }
}
