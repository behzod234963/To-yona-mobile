package com.mr.anonym.toyonamobile.ui.screens.detailsScreen.components

import android.content.Context
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mr.anonym.toyonamobile.presentation.utils.UzsVisualTransformation
import com.mr.anonym.toyonamobile.R

@Composable
fun TransferDetailField(
    secondaryColor: Color,
    priceValue: String,
    onValueChange:(String)-> Unit,
    onTrailingIconClick:()-> Unit,
    isFieldEnabled: Boolean,
) {

    val keyBoardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Number
    )

    OutlinedTextField(
        value = priceValue,
        onValueChange = {
            onValueChange(it)
        },
        modifier = Modifier.fillMaxWidth(),
        textStyle = TextStyle(
            color = secondaryColor,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        ),
        keyboardOptions = keyBoardOptions,
        enabled = isFieldEnabled,
        visualTransformation = UzsVisualTransformation(stringResource(R.string.uzs)),
        trailingIcon = {
            if (!isFieldEnabled){
                IconButton(
                    onClick = { onTrailingIconClick() }
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        tint = secondaryColor,
                        contentDescription = ""
                    )
                }
            }
        },
        label = {
            Text(
                text = stringResource(R.string.transfer_amount)
            )
        },
        singleLine = true,
        shape = RoundedCornerShape(10.dp),
    )
}