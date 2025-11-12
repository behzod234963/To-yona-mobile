package com.mr.anonym.toyonamobile.ui.screens.addCardScreen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mr.anonym.toyonamobile.R.drawable
import com.mr.anonym.toyonamobile.R.string
import com.mr.anonym.toyonamobile.presentation.utils.cardDateTrimmer
import com.mr.anonym.toyonamobile.presentation.utils.cardNumberFormatter

@Composable
fun CardFields(
    secondaryColor: Color,
    tertiaryColor: Color,
    eightColor:Color,
    fontFamily: FontFamily,
    cardValue: String,
    isScanned : Boolean,
    columnModifier:Modifier,
    onCardValueChange:(String)-> Unit,
    cardFieldError: Boolean,
    cardFieldTrailingClick:()-> Unit,
    cardDateValue: String,
    onCardDateValueChange:(String)-> Unit,
    cardDateError: Boolean,
) {

    val cardFieldKeyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Number
    )

    Column (
        modifier = columnModifier
    ){
        //    Card number field
        OutlinedTextField(
            value = cardValue,
            onValueChange = { onCardValueChange(it) },
            modifier = Modifier
                .fillMaxWidth(),
            enabled = true,
            textStyle = TextStyle(
                color = secondaryColor,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = fontFamily
            ),
            placeholder = {
                Text(
                    text = stringResource(string.card_number),
                    fontFamily = fontFamily
                )
            },
            trailingIcon = {
                IconButton(
                    onClick = { cardFieldTrailingClick() }
                ) {
                    Icon(
                        painter = painterResource(drawable.ic_card),
                        tint = secondaryColor,
                        contentDescription = ""
                    )
                }
            },
            supportingText = {
                if (cardFieldError){
                    Text(
                        text = stringResource(string.card_field_must_contains_16_digit),
                        fontFamily = fontFamily
                    )
                }
            },
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = eightColor,
                focusedContainerColor = eightColor,
                unfocusedBorderColor = eightColor,
                focusedBorderColor = eightColor,
                unfocusedLabelColor = tertiaryColor,
                focusedLabelColor = eightColor,
            ),
            isError = cardFieldError,
            visualTransformation = if (isScanned) VisualTransformation.None else VisualTransformation{
                cardNumberFormatter(it)
            },
            keyboardOptions = cardFieldKeyboardOptions,
            singleLine = true,
            shape = RoundedCornerShape(10.dp),
        )
//    Card date field
        OutlinedTextField(
            modifier = Modifier
                .width(100.dp),
            value = cardDateValue,
            onValueChange = { onCardDateValueChange(it) },
            enabled = true,
            textStyle = TextStyle(
                color = secondaryColor,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = fontFamily
            ),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = eightColor,
                focusedContainerColor = eightColor,
                unfocusedBorderColor = eightColor,
                focusedBorderColor = eightColor,
                unfocusedLabelColor = tertiaryColor,
                focusedLabelColor = eightColor,
            ),
            isError = cardDateError,
            keyboardOptions = cardFieldKeyboardOptions,
            singleLine = true,
            visualTransformation = {
                cardDateTrimmer(it)
            },
            placeholder = {
                Text(
                    text = stringResource(string.mm_yy),
                    fontFamily = fontFamily
                )
            },
            shape = RoundedCornerShape(10.dp),
        )

        Spacer(Modifier.height(10.dp))
    }
}