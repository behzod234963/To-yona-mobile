package com.mr.anonym.toyonamobile.ui.screens.monitoringScreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mr.anonym.domain.model.MonitoringModel
import com.mr.anonym.toyonamobile.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MonitoringBottomSheet(
    secondaryColor:Color,
    tertiaryColor: Color,
    state: SheetState,
    model: MonitoringModel,
    onDownloadClick:()-> Unit,
    onShareClick:()-> Unit,
    onDismissRequest:()-> Unit
) {

    ModalBottomSheet(
        onDismissRequest = { onDismissRequest() },
        sheetState = state,
        shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp),
        containerColor = tertiaryColor,
        contentColor = tertiaryColor,
        dragHandle = {
            BottomSheetDefaults.DragHandle(
                height = 2.dp,
                shape = RoundedCornerShape(10.dp),
                color = secondaryColor
            )
        },
    ){
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Image(
                modifier = Modifier
                    .size(30.dp),
                painter = painterResource(R.drawable.ic_check),
                contentDescription = ""
            )
            Spacer(Modifier.height(20.dp))
            Text(
                text = "${model.eventOwnerName} ${model.eventOwnerLastName} ${model.eventName}",
                color = secondaryColor,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(10.dp))
            Column (
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ){
//                Date time
                Row {
                    Text(
                        text = "${stringResource(R.string.time_of_the_event)} :",
                        color = secondaryColor,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                    )
                    Text(
                        text = " ${model.dateTime}",
                        color = secondaryColor,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                    )
                }
                Spacer(Modifier.height(5.dp))
//                Amount
                Row {
                    Text(
                        text = "${stringResource(R.string.amount)} :",
                        color = secondaryColor,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                    )
                    Text(
                        text = " ${model.amount}",
                        color = secondaryColor,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                    )
                }
                Spacer(Modifier.height(5.dp))
//                Commission
                Row {
                    Text(
                        text = "${stringResource(R.string.commission)} :",
                        color = secondaryColor,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                    )
                    Text(
                        text = " 500",
                        color = secondaryColor,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                    )
                }
                Spacer(Modifier.height(5.dp))
//                Sender card
                Row {
                    Text(
                        text = "${stringResource(R.string.sender_card)} :",
                        color = secondaryColor,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                    )
                    Text(
                        text = " ${model.senderCardNumber}",
                        color = secondaryColor,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                    )
                }
                Spacer(Modifier.height(5.dp))
//                Sender cardholdr
                Row {
                    Text(
                        text = "${stringResource(R.string.sender_name)} :",
                        color = secondaryColor,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                    )
                    Text(
                        text = " ${model.senderCardHolder}",
                        color = secondaryColor,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                    )
                }
                Spacer(Modifier.height(5.dp))
//                Receiver card
                Row {
                    Text(
                        text = "${stringResource(R.string.receiver_card)} :",
                        color = secondaryColor,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                    )
                    Text(
                        text = " ${model.receiverCardNumber}",
                        color = secondaryColor,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                    )
                }
                Spacer(Modifier.height(5.dp))
//                Receiver cardholder
                Row {
                    Text(
                        text = "${stringResource(R.string.receiver_name)} :",
                        color = secondaryColor,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                    )
                    Text(
                        text = " ${model.receiverCardHolder}",
                        color = secondaryColor,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                    )
                }
                Spacer(Modifier.height(5.dp))
//                Transfer status
                Row {
                    Text(
                        text = "${stringResource(R.string.status)} :",
                        color = secondaryColor,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                    )
                    Text(
                        text = " ${model.transferStatus}",
                        color = secondaryColor,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                    )
                }
                Spacer(Modifier.height(5.dp))
            }
            Spacer(Modifier.height(10.dp))
            Row (
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ){
                IconButton(
                    onClick = { onDownloadClick() }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_download),
                        tint = secondaryColor,
                        contentDescription = "button download"
                    )
                }
                IconButton(

                    onClick = { onShareClick() }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_share),
                        tint = secondaryColor,
                        contentDescription = "button share"
                    )
                }
            }
            Spacer(Modifier.height(15.dp))
        }
    }
}