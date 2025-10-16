package com.mr.anonym.toyonamobile.ui.screens.profileScreen.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mr.anonym.toyonamobile.R

@Composable
fun NameField(
    secondaryColor: Color,
    tertiaryColor: Color,
    eightrdColor: Color,
    focusedRequester: FocusRequester,
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
            .fillMaxWidth()
            .focusRequester(focusedRequester),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedContainerColor = eightrdColor,
            focusedContainerColor = eightrdColor,
            unfocusedBorderColor = eightrdColor,
            focusedBorderColor = eightrdColor,
            unfocusedLabelColor = tertiaryColor,
            focusedLabelColor = eightrdColor,
        ),
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
                    painter = painterResource(R.drawable.ic_close),
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
        placeholder = {
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
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedContainerColor = eightrdColor,
            focusedContainerColor = eightrdColor,
            unfocusedBorderColor = eightrdColor,
            focusedBorderColor = eightrdColor,
            unfocusedLabelColor = tertiaryColor,
            focusedLabelColor = eightrdColor,
        ),
        trailingIcon = {
            IconButton(
                onClick = {
                    onSurnameEnabledTrailingIconClick()
                }
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_close),
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
        placeholder = {
            Text(
                text = stringResource(R.string.lastname),
                fontSize = 16.sp,
//                color = secondaryColor
            )
        },
        isError = surnameValueError,
        singleLine = true,
        shape = RoundedCornerShape(10.dp),
    )
}