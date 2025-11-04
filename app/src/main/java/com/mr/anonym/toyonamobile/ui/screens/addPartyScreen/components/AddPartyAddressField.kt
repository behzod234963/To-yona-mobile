package com.mr.anonym.toyonamobile.ui.screens.addPartyScreen.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mr.anonym.toyonamobile.R

@Composable
fun AddPartyAddressField(
    secondaryColor: Color,
    tertiaryColor: Color,
    eightColor:Color,
    fontFamily: FontFamily,
    value:String,
    onValueChange:(String)-> Unit,
) {

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth(),
        value = value,
        onValueChange = { onValueChange(it) },
        shape = RoundedCornerShape(10.dp),
        textStyle = TextStyle(
            fontSize = 16.sp,
            color = secondaryColor,
            fontFamily = fontFamily,
            fontWeight = FontWeight.SemiBold
        ),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedContainerColor = eightColor,
            focusedContainerColor = eightColor,
            unfocusedBorderColor = eightColor,
            focusedBorderColor = eightColor,
            unfocusedLabelColor = tertiaryColor,
            focusedLabelColor = eightColor,
        ),
        leadingIcon = {
            Icon(
                painter = painterResource(R.drawable.ic_add_address),
                tint = secondaryColor,
                contentDescription = ""
            )
        },
        placeholder = {
            Text(
                text = stringResource(R.string.address),
                color = secondaryColor,
                fontFamily = fontFamily
            )
        }
    )
}