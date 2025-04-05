package com.mr.anonym.toyonamobile.ui.screens.myEventsScreen.components

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
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mr.anonym.domain.model.MyEventsModel
import com.mr.anonym.toyonamobile.R

@Composable
fun MyEventsItem(
    secondaryColor: Color,
    quaternaryColor: Color,
    fiverdColor:Color,
    sevenrdColor: Color,
    myEventsModel: MyEventsModel,
    onEditClick:()->Unit,
    onCheckedChange:(Boolean)-> Unit
) {

    val eventsStatus = rememberSaveable { mutableStateOf( false ) }

    Card(
        modifier = Modifier
            .padding(7.dp),
        colors = CardDefaults.cardColors(
            contentColor = sevenrdColor,
            containerColor = sevenrdColor
        ),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(7.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 10.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.wedding),
                    fontSize = 18.sp,
                    color = secondaryColor,
                    fontWeight = FontWeight.SemiBold
                )
                Row {
                    IconButton(
                        onClick = { onEditClick() }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            tint = secondaryColor,
                            contentDescription = ""
                        )
                    }
                    Spacer(Modifier.width(10.dp))
                    MyEventSwitch(
                        secondaryColor = secondaryColor,
                        quaternaryColor = quaternaryColor,
                        fiverdColor = fiverdColor,
                        isChecked = eventsStatus.value,
                        onCheckedChange = {
                            eventsStatus.value = it
                            onCheckedChange(it)
                        }
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(R.string.status),
                    color = secondaryColor,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                )
                Text(
                    text = if (eventsStatus.value)
                        stringResource(R.string.is_on)
                    else
                        stringResource(R.string.is_off),
                    color = secondaryColor,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                )
            }
            HorizontalDivider()
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(R.string.event),
                    color = secondaryColor,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                )
                Text(
                    text = myEventsModel.eventType,
                    color = secondaryColor,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                )
            }
            HorizontalDivider()
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(0.5f),
                    text = stringResource(R.string.event_date_and_time),
                    color = secondaryColor,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                )
                Text(
                    text = myEventsModel.eventDateTime,
                    color = secondaryColor,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.End
                )
            }
            HorizontalDivider()
            Spacer(Modifier.height(10.dp))
            Row (
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ){
                Text(
                    text = stringResource(R.string.requisites),
                    color = secondaryColor,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(0.5f),
                    text = stringResource(R.string.card_holder_name),
                    fontSize = 16.sp,
                    color = secondaryColor,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = myEventsModel.cardHolder,
                    fontSize = 16.sp,
                    color = secondaryColor,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.End
                )
            }
            Spacer(Modifier.height(5.dp))
            HorizontalDivider()
            Spacer(Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(0.5f),
                    text = stringResource(R.string.card_number),
                    fontSize = 16.sp,
                    color = secondaryColor,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = myEventsModel.cardNumber,
                    color = secondaryColor,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Spacer(Modifier.height(5.dp))
            HorizontalDivider()
        }
    }
}

@Preview
@Composable
private fun PreviewMyEventsItem() {
    MyEventsItem(
        secondaryColor = Color.Black,
        quaternaryColor = Color.Red,
        fiverdColor = Color.Green,
        sevenrdColor = Color.White,
        myEventsModel = MyEventsModel(
            eventStatus = false,
            eventType = "Wedding",
            eventDateTime = "05.04.2025 , 06.04.2025  12:00",
            cardHolder = "BEKHZOD KHUDAYBERGENOV",
            cardNumber = "9860030160619356"
        ),
        onCheckedChange = {  },
        onEditClick = {  }
    )
}