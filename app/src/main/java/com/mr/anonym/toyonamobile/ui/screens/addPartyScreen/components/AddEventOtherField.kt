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
fun AddEventOtherField(
    secondaryColor: Color,
    tertiaryColor: Color,
    eightColor:Color,
    fontFamily: FontFamily,
    isEventError: Boolean,
    isTitle: Boolean,
    value: String,
    onValueChange: (String) -> Unit,
    isValueConfirmed: Boolean,
) {

    OutlinedTextField(
        value = value,
        onValueChange = { onValueChange(it) },
        modifier = Modifier
            .fillMaxWidth(),
        enabled = if (isTitle) true else !isValueConfirmed,
        textStyle = TextStyle(
            color = secondaryColor,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = fontFamily
        ),
        placeholder = {
            Text(
                text = if (isTitle) stringResource(R.string.title) else stringResource(R.string.enter_the_event),
                color = secondaryColor,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = fontFamily
            )
        },
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
                painter = painterResource(R.drawable.ic_edit),
                tint = secondaryColor,
                contentDescription = ""
            )
        },
        supportingText = {
            if (isEventError) {
                Text(
                    text = stringResource(R.string.the_place_must_not_be_empty),
                    fontFamily = fontFamily
                )
            }
        },
        isError = isEventError,
        shape = RoundedCornerShape(10.dp),
    )
}