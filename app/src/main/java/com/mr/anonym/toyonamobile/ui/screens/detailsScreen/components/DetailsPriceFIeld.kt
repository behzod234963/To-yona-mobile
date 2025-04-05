package com.mr.anonym.toyonamobile.ui.screens.detailsScreen.components

import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.PlatformImeOptions
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mr.anonym.toyonamobile.R

@Composable
fun DetailsPriceFields(
    modifier: Modifier,
    secondaryColor: Color,
    tertiaryColor:Color,
    fiverdColor: Color,
    value: String,
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
        keyboardOptions = keyboardOptions,
        textStyle = TextStyle(
            color = secondaryColor,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold
        ),
        placeholder = {
            Text(
                text = stringResource(R.string.enter_the_price),
                color = tertiaryColor,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
        },
        trailingIcon = {
            Button(
                modifier = Modifier
                    .height(50.dp)
                    .padding(end = 10.dp, top = 5.dp, bottom = 5.dp,),
                colors = ButtonDefaults.buttonColors(
                    containerColor = fiverdColor,
                    contentColor = fiverdColor
                ),
                shape = RoundedCornerShape(10.dp),
                onClick = { onTransferClick() }
            ) {
                Text(
                    text = stringResource(R.string.transfer),
                    color = secondaryColor,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        },
        singleLine = true,
        shape = RoundedCornerShape(10.dp,),
    )
}