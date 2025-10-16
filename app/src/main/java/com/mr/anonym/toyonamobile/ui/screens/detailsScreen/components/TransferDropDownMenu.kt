package com.mr.anonym.toyonamobile.ui.screens.detailsScreen.components

import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.mr.anonym.domain.model.CardModel
import com.mr.anonym.toyonamobile.R

@Composable
fun TransferDropDownMenu(
    primaryColor: Color,
    secondaryColor: Color,
    isExpanded: Boolean,
    onDismissRequest: () -> Unit,
    onItemClick: (String) -> Unit,
    onAddCardClick: () -> Unit,
    userCards: List<CardModel>
) {
    DropdownMenu(
        expanded = isExpanded,
        onDismissRequest = {
            onDismissRequest()
        },
        containerColor = primaryColor,
    ) {
        if (userCards.isEmpty()) {
            DropdownMenuItem(
                text = {
                    Text(
                        text = stringResource(R.string.add_cart),
                        color = secondaryColor,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                },
                onClick = { onAddCardClick() },
                leadingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.ic_add_circle),
                        tint = secondaryColor,
                        contentDescription = ""
                    )
                },
            )
        } else {
            userCards.forEach { card ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = card.number,
                            color = secondaryColor,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    },
                    onClick = {
                        onItemClick(card.number)
                    },
                )
            }
        }
        if (userCards.isNotEmpty()){
            DropdownMenuItem(
                text = {
                    Text(
                        text = stringResource(R.string.add_card),
                        color = secondaryColor,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                },
                onClick = { onAddCardClick() },
                leadingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.ic_add_circle),
                        tint = secondaryColor,
                        contentDescription = ""
                    )
                },
            )
        }
    }
}