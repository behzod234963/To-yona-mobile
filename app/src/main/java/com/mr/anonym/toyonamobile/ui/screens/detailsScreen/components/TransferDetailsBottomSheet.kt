package com.mr.anonym.toyonamobile.ui.screens.detailsScreen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalBottomSheetProperties
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mr.anonym.domain.model.CardModel
import com.mr.anonym.domain.model.PartysItem
import com.mr.anonym.domain.model.UserModelItem
import com.mr.anonym.toyonamobile.R
import com.mr.anonym.toyonamobile.presentation.extensions.cardNumberFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransferDetailsBottomSheet(
    primaryColor: Color,
    secondaryColor:Color,
    tertiaryColor:Color,
    quaternaryColor: Color,
    state: SheetState,
    senderName:String,
    userModel: UserModelItem,
    partyModel: PartysItem,

//    Price field
    isFieldEnabled: Boolean,
    priceValue: String,
    onValueChange:(String)-> Unit,
    onTrailingIconClick:()-> Unit,
    senderCardNumber: String,
    onConfirmButtonClick:()-> Unit,
    onDismissRequest:()-> Unit,
    onSelectCardClick:()-> Unit,

//    DropDown field
    userCards: List<CardModel>,
    onDropDownDismissRequest:()-> Unit,
    onItemClick:(String)-> Unit,
    onAddCardClick:()-> Unit,
    isExpanded: Boolean,
) {
    ModalBottomSheet(
        onDismissRequest = { onDismissRequest() },
        sheetState = state,
        shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp),
        containerColor = tertiaryColor,
        contentColor = tertiaryColor,
        properties = ModalBottomSheetProperties(
            shouldDismissOnBackPress = true,
        ),
        dragHandle = {
            BottomSheetDefaults.DragHandle(
                height = 2.dp,
                shape = RoundedCornerShape(10.dp),
                color = secondaryColor
            )
        },
    ) {
        Column (
            modifier = Modifier
                .padding(10.dp)
        ){
            TransferDetailField(
                secondaryColor = secondaryColor,
                priceValue = priceValue,
                onValueChange = { onValueChange(it) },
                onTrailingIconClick = { onTrailingIconClick() },
                isFieldEnabled = isFieldEnabled
            )
            Spacer(Modifier.height(10.dp))
            Row (
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ){
                Column (
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                ){
                    Text(
                        text = stringResource(R.string.receiver),
                        color = secondaryColor,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = "${userModel.username} ${userModel.surname}",
                        color = secondaryColor,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                partyModel.cardNumber?.cardNumberFormatter()?.let {
                    Text(
                        text = it,
                        color = secondaryColor,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
            Spacer(Modifier.height(5.dp))
            HorizontalDivider(color = secondaryColor)
            Spacer(Modifier.height(5.dp))
            Row (
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ){
                Column (
                    modifier = Modifier
                        .fillMaxWidth(0.5f),
                ){
                    Text(
                        text = stringResource(R.string.sender),
                        color = secondaryColor,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = senderName,
                        color = secondaryColor,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Row (
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        text = senderCardNumber.cardNumberFormatter(),
                        color = secondaryColor,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Box{
                        IconButton(
                            onClick = { onSelectCardClick() }
                        ) {
                            Icon(
                                imageVector = if (isExpanded) Icons.Default.KeyboardArrowDown else Icons.AutoMirrored.Filled.KeyboardArrowRight,
                                tint = secondaryColor,
                                contentDescription = ""
                            )
                        }
                        TransferDropDownMenu(
                            primaryColor = primaryColor,
                            secondaryColor = secondaryColor,
                            isExpanded = isExpanded,
                            onDismissRequest = { onDropDownDismissRequest() },
                            onItemClick = { cardNumber->
                                onItemClick(cardNumber) },
                            userCards = userCards,
                            onAddCardClick = { onAddCardClick() }
                        )
                    }
                }
            }
            Spacer(Modifier.height(10.dp))
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(10.dp),
                onClick = { onConfirmButtonClick() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = quaternaryColor,
                    contentColor = quaternaryColor
                )
            ) {
                Text(
                    text = stringResource(R.string.transfer),
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}