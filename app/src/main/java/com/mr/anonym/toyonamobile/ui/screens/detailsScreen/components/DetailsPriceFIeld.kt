package com.mr.anonym.toyonamobile.ui.screens.detailsScreen.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mr.anonym.toyonamobile.R
import com.mr.anonym.toyonamobile.presentation.utils.UzsVisualTransformation

@Composable
fun DetailsPriceFields(
    modifier: Modifier,
    secondaryColor: Color,
    fiveColor: Color,
    fontFamily: FontFamily,
    value: String,
    priceFieldError: Boolean,
    onValueChange:(String)-> Unit,
    onTransferClick:()-> Unit
) {

    val keyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Number,
    )

    OutlinedTextField(
        value = value,
        onValueChange = {  onValueChange(it) },
        modifier = modifier,
        isError = priceFieldError,
        visualTransformation = UzsVisualTransformation(stringResource(R.string.uzs)),
        keyboardOptions = keyboardOptions,
        textStyle = TextStyle(
            color = secondaryColor,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = fontFamily
        ),
        label = {
            Text(
                text = stringResource(R.string.enter_the_price),
                fontFamily = fontFamily
            )
        },
        trailingIcon = {
            Button(
                modifier = Modifier
                    .height(50.dp)
                    .padding(end = 10.dp, top = 5.dp, bottom = 5.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = fiveColor,
                    contentColor = fiveColor
                ),
                shape = RoundedCornerShape(10.dp),
                onClick = { onTransferClick() }
            ) {
                Text(
                    text = stringResource(R.string.transfer),
                    color = secondaryColor,
                    fontSize = 15.sp,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.SemiBold
                )
            }
        },
        supportingText = {
            if (priceFieldError){
                Text(
                    text = stringResource(R.string.the_field_must_not_be_empty_or_can_contain_only_digits),
                    fontFamily = fontFamily
                )
            }
        },
        singleLine = true,
        shape = RoundedCornerShape(10.dp),
    )
}