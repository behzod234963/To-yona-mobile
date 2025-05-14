package com.mr.anonym.toyonamobile.ui.screens.profileScreen.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mr.anonym.toyonamobile.R

@Composable
fun NameField(
    secondaryColor: Color,
//    Name field properties
    nameValue: String,
    onNameValueChange: (String) -> Unit,
    onNameValueEnabledTrailingIconClick: () -> Unit,
    nameValueError: Boolean,
//    Lastname field properties
    surnameValue: String,
    onSurnameValueChange: (String) -> Unit,
    surnameValueError: Boolean,
    onSurnameEnabledTrailingIconClick: () -> Unit,
) {

    //    username field content
    OutlinedTextField(
        value = nameValue,
        onValueChange = { onNameValueChange(it) },
        modifier = Modifier
            .fillMaxWidth(),
        textStyle = TextStyle(
            color = secondaryColor,
            fontSize = 16.sp
        ),
        trailingIcon = {
            IconButton(
                onClick = {
                    onNameValueEnabledTrailingIconClick()
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    tint = secondaryColor,
                    contentDescription = "null"
                )
            }
        },
        supportingText = {
            if (nameValueError) {
                Text(
                    text = stringResource(R.string.phone_number_error)
                )
            }
        },
        label = {
            Text(
                text = stringResource(R.string.firstname),
                fontSize = 16.sp
            )
        },
        isError = nameValueError,
        singleLine = true,
        shape = RoundedCornerShape(10.dp),
    )
//    Surname field content
    OutlinedTextField(
        value = surnameValue,
        onValueChange = { onSurnameValueChange(it) },
        modifier = Modifier
            .fillMaxWidth(),
        textStyle = TextStyle(
            color = secondaryColor,
            fontSize = 16.sp
        ),
        trailingIcon = {
            IconButton(
                onClick = {
                    onSurnameEnabledTrailingIconClick()
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    tint = secondaryColor,
                    contentDescription = "null"
                )
            }
        },
        supportingText = {
            if (surnameValueError) {
                Text(
                    text = stringResource(R.string.phone_number_error)
                )
            }
        },
        label = {
            Text(
                text = stringResource(R.string.lastname),
                fontSize = 16.sp
            )
        },
        isError = surnameValueError,
        singleLine = true,
        shape = RoundedCornerShape(10.dp),
    )
}