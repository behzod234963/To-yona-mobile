package com.mr.anonym.toyonamobile.ui.screens.numberCheckScreen.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OTPField(
    textColor: Color,
    borderColor:Color,
    value: String,
    fontFamily: FontFamily,
    onSend:()->Unit,
    onValueChange: (String) -> Unit
) {

    val keyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Number,
        imeAction = ImeAction.Done
    )
    val keyboardController = LocalSoftwareKeyboardController.current

    BasicTextField(
        value = value,
        onValueChange = { onValueChange(it) },
        cursorBrush = SolidColor(textColor),
        keyboardOptions = keyboardOptions,
        textStyle = TextStyle(
            fontFamily = fontFamily
        ),
        keyboardActions = KeyboardActions{
            onSend()
            keyboardController?.hide()
        }
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            repeat(5) { index ->
                val number = when {
                    index >= value.length -> ""
                    else -> value[index]
                }
                Column(
                    modifier = Modifier
                        .size(50.dp)
                        .border(
                            width = 1.dp,
                            color = borderColor,
                            shape = RoundedCornerShape(10.dp)
                        ),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = number.toString(),
                        color = textColor,
                        fontSize = 16.sp,
                        fontFamily = fontFamily
                    )
                }
            }
        }
    }
}