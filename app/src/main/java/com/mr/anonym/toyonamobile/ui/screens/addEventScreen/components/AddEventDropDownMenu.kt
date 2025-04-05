package com.mr.anonym.toyonamobile.ui.screens.addEventScreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mr.anonym.toyonamobile.R

@Composable
fun AddEventDropDownMenu(
    secondaryColor: Color,
    tertiaryColor: Color,
    isExpanded: Boolean,
    onDismissRequest:()-> Unit,
    onWeddingClick:()-> Unit,
    onSunnatClick:()-> Unit,
    onBirthdayClick:()-> Unit,
    onOtherClick:(Boolean)-> Unit
) {

    DropdownMenu(
        expanded = isExpanded,
        onDismissRequest = { onDismissRequest() },
        shape = RoundedCornerShape(10.dp),
        containerColor = tertiaryColor,
        tonalElevation = 7.dp,
        shadowElevation = 7.dp
    ) {
//        Wedding menu
        DropdownMenuItem(
            text = {
                Text(
                    text = stringResource(R.string.wedding),
                    color = secondaryColor,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            },
            onClick = { onWeddingClick() },
            leadingIcon = {
                Image(
                    modifier = Modifier
                        .size(30.dp),
                    painter = painterResource(R.drawable.ic_rings),
                    contentDescription = ""
                )
            },
            colors = MenuDefaults.itemColors(
                textColor = secondaryColor
            ),
        )
//        Sunnat menu
        DropdownMenuItem(
            text = {
                Text(
                    text = stringResource(R.string.sunnat_wedding),
                    color = secondaryColor,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            },
            onClick = { onSunnatClick() },
            leadingIcon = {
                Image(
                    modifier = Modifier
                        .size(30.dp),
                    painter = painterResource(R.drawable.ic_sunnat),
                    contentDescription = ""
                )
            },
            colors = MenuDefaults.itemColors(
                textColor = secondaryColor
            ),
        )
//        Birthday menu
        DropdownMenuItem(
            text = {
                Text(
                    text = stringResource(R.string.birthday),
                    color = secondaryColor,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            },
            onClick = { onBirthdayClick() },
            leadingIcon = {
                Image(
                    modifier = Modifier
                        .size(30.dp),
                    painter = painterResource(R.drawable.ic_birthday),
                    contentDescription = ""
                )
            },
            colors = MenuDefaults.itemColors(
                textColor = secondaryColor
            ),
        )
//        Other
        DropdownMenuItem(
            text = {
                Text(
                    text = stringResource(R.string.other),
                    color = secondaryColor,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            },
            onClick = { onOtherClick(true) },
            leadingIcon = {
                Image(
                    modifier = Modifier
                        .size(30.dp),
                    painter = painterResource(R.drawable.ic_more),
                    contentDescription = ""
                )
            },
            colors = MenuDefaults.itemColors(
                textColor = secondaryColor
            ),
        )
    }
}