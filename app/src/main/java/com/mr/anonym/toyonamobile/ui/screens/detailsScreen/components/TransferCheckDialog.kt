package com.mr.anonym.toyonamobile.ui.screens.detailsScreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.mr.anonym.domain.model.FriendsModel
import com.mr.anonym.domain.model.TransactionsModel
import com.mr.anonym.domain.model.UserModel
import com.mr.anonym.toyonamobile.R

@Composable
fun TransferCheckDialog(
    secondaryColor: Color,
    quaternaryColor: Color,
    userModel: UserModel,
    friendsModel: FriendsModel,
    transactionsModel: TransactionsModel,
    onDismissClick: () -> Unit,
    onConfirmClick: () -> Unit
) {
    AlertDialog(
        icon = {
            Image(
                painter = painterResource(R.drawable.ic_check),
                contentDescription = ""
            )
        },
        title = {
            Text(
                text = stringResource(R.string.successfully),
                color = secondaryColor,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )
        },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                verticalArrangement = Arrangement.Center
            ) {
//                Date time
                Row (
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Text(
                        text = stringResource(R.string.time_of_the_event),
                        fontSize = 16.sp,
                        color = secondaryColor,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = transactionsModel.dateTime,
                        fontSize = 16.sp,
                        color = secondaryColor,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Spacer(Modifier.height(5.dp))
//                Amount
                Row (
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Text(
                        text = stringResource(R.string.transfer_amount),
                        fontSize = 16.sp,
                        color = secondaryColor,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = transactionsModel.price.toString(),
                        fontSize = 16.sp,
                        color = secondaryColor,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Spacer(Modifier.height(5.dp))
//                Commission
                Row (
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Text(
                        text = stringResource(R.string.commission),
                        fontSize = 16.sp,
                        color = secondaryColor,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = "500 сум",
                        fontSize = 16.sp,
                        color = secondaryColor,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Spacer(Modifier.height(5.dp))
//                Receiver name
                Row (
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Text(
                        text = stringResource(R.string.receiver_name),
                        fontSize = 16.sp,
                        color = secondaryColor,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = "${friendsModel.name} ${friendsModel.surname}",
                        fontSize = 16.sp,
                        color = secondaryColor,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Spacer(Modifier.height(5.dp))
//                Receiver card
                Row (
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Text(
                        text = stringResource(R.string.receiver_card),
                        fontSize = 16.sp,
                        color = secondaryColor,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = transactionsModel.receiver,
                        fontSize = 16.sp,
                        color = secondaryColor,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Spacer(Modifier.height(5.dp))
//                Sender name
                Row (
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Text(
                        text = stringResource(R.string.sender_name),
                        fontSize = 16.sp,
                        color = secondaryColor,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = "${userModel.name} ${userModel.surName}",
                        fontSize = 16.sp,
                        color = secondaryColor,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Spacer(Modifier.height(5.dp))
//                Sender card
                Row (
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Text(
                        text = stringResource(R.string.sender_card),
                        fontSize = 16.sp,
                        color = secondaryColor,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = transactionsModel.sender,
                        fontSize = 16.sp,
                        color = secondaryColor,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        },
        confirmButton = {
            Button(
                modifier = Modifier
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = quaternaryColor,
                    contentColor = quaternaryColor
                ),
                shape = RoundedCornerShape(10.dp),
                onClick = { onConfirmClick() }
            ) {
                Text(
                    text = stringResource(R.string.continue_),
                    color = secondaryColor,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        },
        onDismissRequest = { onDismissClick() },
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true
        )
    )
}