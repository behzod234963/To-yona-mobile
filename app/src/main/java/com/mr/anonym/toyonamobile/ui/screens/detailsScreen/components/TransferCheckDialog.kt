package com.mr.anonym.toyonamobile.ui.screens.detailsScreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.window.DialogProperties
import com.mr.anonym.domain.model.FriendsModel
import com.mr.anonym.domain.model.TransactionsModel
import com.mr.anonym.domain.model.UserModel
import com.mr.anonym.domain.model.UserModelItem
import com.mr.anonym.toyonamobile.R
import com.mr.anonym.toyonamobile.presentation.extensions.cardNumberFormatter
import com.mr.anonym.toyonamobile.presentation.extensions.moneySeparator
import com.mr.anonym.toyonamobile.presentation.extensions.stringEqualizerForDetails

@Composable
fun TransferCheckDialog(
    secondaryColor: Color,
    quaternaryColor: Color,
    commission: String,
    userModel: UserModelItem,
    friendsModel: FriendsModel,
    transactionsModel: TransactionsModel,
    onDismissClick: () -> Unit,
    onConfirmClick: () -> Unit
) {
    val scrollState = rememberScrollState()
    AlertDialog(
        icon = {
            Image(
                modifier = Modifier
                    .size(60.dp),
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
                    .verticalScroll(state = scrollState),
                verticalArrangement = Arrangement.Center
            ) {
//                Date time
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(0.5f),
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
                HorizontalDivider()
                Spacer(Modifier.height(5.dp))
//                Amount
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(0.5f),
                        text = stringResource(R.string.transfer_amount),
                        fontSize = 16.sp,
                        color = secondaryColor,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = "${transactionsModel.price.moneySeparator()} ${stringResource(R.string.uzs)}",
                        fontSize = 16.sp,
                        color = secondaryColor,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                HorizontalDivider()
                Spacer(Modifier.height(5.dp))
//                Commission
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(0.5f),
                        text = stringResource(R.string.commission),
                        fontSize = 16.sp,
                        color = secondaryColor,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = "${commission.moneySeparator()} ${stringResource(R.string.uzs)}",
                        fontSize = 16.sp,
                        color = secondaryColor,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                HorizontalDivider()
                Spacer(Modifier.height(5.dp))
//                Receiver username
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(0.5f),
                        text = stringResource(R.string.receiver_name),
                        fontSize = 16.sp,
                        color = secondaryColor,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = "${friendsModel.name} ${if (friendsModel.surname.length > 10) friendsModel.surname.stringEqualizerForDetails() else friendsModel.surname }",
                        fontSize = 16.sp,
                        color = secondaryColor,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.End
                    )
                }
                HorizontalDivider()
                Spacer(Modifier.height(5.dp))
//                Receiver card
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(0.5f),
                        text = stringResource(R.string.receiver_card),
                        fontSize = 16.sp,
                        color = secondaryColor,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = transactionsModel.receiver.cardNumberFormatter(),
                        fontSize = 16.sp,
                        color = secondaryColor,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                HorizontalDivider()
                Spacer(Modifier.height(5.dp))
//                Sender username
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(0.5f),
                        text = stringResource(R.string.sender_name),
                        fontSize = 16.sp,
                        color = secondaryColor,
                        fontWeight = FontWeight.SemiBold
                    )
                    userModel.surname?.length?.let {
                        Text(
                            text = "${userModel.username} ${if (it > 10) userModel.surname!!.stringEqualizerForDetails() else userModel.surname}",
                            fontSize = 16.sp,
                            color = secondaryColor,
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.End
                        )
                    }
                }
                HorizontalDivider()
                Spacer(Modifier.height(5.dp))
//                Sender card
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(0.5f),
                        text = stringResource(R.string.sender_card),
                        fontSize = 16.sp,
                        color = secondaryColor,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = transactionsModel.sender.cardNumberFormatter(),
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
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        },
        onDismissRequest = { onDismissClick() },
        properties = DialogProperties(
            usePlatformDefaultWidth = true,
            dismissOnBackPress = true,
            dismissOnClickOutside = true
        )
    )
}