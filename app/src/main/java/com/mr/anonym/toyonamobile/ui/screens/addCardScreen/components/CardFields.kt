package com.mr.anonym.toyonamobile.ui.screens.addCardScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mr.anonym.toyonamobile.R.*
import com.mr.anonym.toyonamobile.presentation.utils.cardDateTrimmer
import com.mr.anonym.toyonamobile.presentation.utils.cardNumberFormatter

@Composable
fun CardFields(
    secondaryColor: Color,
    cardValue: String,
    isScanned : Boolean,
    columnModifier:Modifier,
    onCardValueChange:(String)-> Unit,
    cardFieldError: Boolean,
    cardFieldTrailingClick:()-> Unit,
    cardDateValue: String,
    onCardDateValueChange:(String)-> Unit,
    cardDateError: Boolean,
    cardHolderValue: String,
    onCardHolderValueChange:(String)-> Unit,
    cardHolderValueError: Boolean,
    cardHolderFieldTrailingIcon:()-> Unit
) {

    val cardFieldKeyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Number
    )
    val cardHolderFieldKeyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Text
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
                fontWeight = FontWeight.SemiBold
            ),
            label = {
                Text(
                    text = stringResource(string.card_number)
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
                    )
                }
            },
            placeholder = {
                Text(
                    text = "0000 0000 0000 0000"
                )
            },
            isError = cardFieldError,
            visualTransformation = if (isScanned) VisualTransformation.None else VisualTransformation{
                cardNumberFormatter(it)
            },
            keyboardOptions = cardFieldKeyboardOptions,
            singleLine = true,
            shape = RoundedCornerShape(10.dp),
        )

        Spacer(Modifier.height(10.dp))
//    Card date field
        OutlinedTextField(
            value = cardDateValue,
            onValueChange = { onCardDateValueChange(it) },
            enabled = true,
            textStyle = TextStyle(
                color = secondaryColor,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            ),
            isError = cardDateError,
            keyboardOptions = cardFieldKeyboardOptions,
            singleLine = true,
            visualTransformation = {
                cardDateTrimmer(it)
            },
            placeholder = {
                Text(
                    text = stringResource(string.mm_yy)
                )
            },
            shape = RoundedCornerShape(10.dp),
        )

        Spacer(Modifier.height(10.dp))
        //    Card holder field
        OutlinedTextField(
            value = cardHolderValue,
            onValueChange = { onCardHolderValueChange(it) },
            modifier = Modifier
                .fillMaxWidth(),
            enabled = true,
            textStyle = TextStyle(
                color = secondaryColor,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            ),
            label = {
                Text(
                    text = stringResource(string.card_holder_name)
                )
            },
            trailingIcon = {
                IconButton(
                    onClick = { cardHolderFieldTrailingIcon() }
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        tint = secondaryColor,
                        contentDescription = ""
                    )
                }
            },
            supportingText = {
                if (cardFieldError){
                    Text(
                        text = stringResource(string.please_check_validate_places),
                    )
                }
            },
            isError = cardHolderValueError,
            keyboardOptions = cardHolderFieldKeyboardOptions,
            singleLine = true,
            shape = RoundedCornerShape(10.dp),
        )
    }
}

@Preview
@Composable
private fun PreviewCardFields() {
    CardFields(
        secondaryColor = if (isSystemInDarkTheme()) Color.White else Color.Black,
        cardValue = "0000111122223333",
        isScanned = true,
        onCardValueChange = { },
        cardFieldError = true,
        cardFieldTrailingClick = { },
        cardDateValue = "",
        onCardDateValueChange = { },
        cardDateError = true,
        columnModifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
            .padding(16.dp),
        cardHolderValue = "BEKHZOD KHUDAYBERGENOV",
        onCardHolderValueChange = {  },
        cardHolderValueError = true,
    ){}
}