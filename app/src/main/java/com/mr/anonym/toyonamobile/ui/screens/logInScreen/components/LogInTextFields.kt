package com.mr.anonym.toyonamobile.ui.screens.logInScreen.components

import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mr.anonym.toyonamobile.R

@Composable
fun LogInTextFields(
    secondaryColor: Color,
    tertiaryColor:Color,
    phoneFieldModifier: Modifier,
    phoneFieldError: Boolean,
    phoneFieldValue:String,
    phoneFieldTrailingFunction:()-> Unit,
    onPhoneValueChange:(String)->Unit,
    nameFieldModifier: Modifier,
    nameFieldError: Boolean,
    nameFieldValue: String,
    nameFieldTrailingFunction:()->Unit,
    onNameFieldValueChange:(String)->Unit
) {

    val focusManager = LocalFocusManager.current
    val keyBoardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Number,
        imeAction = if (!phoneFieldError) ImeAction.Next else ImeAction.Default
    )
    val keyBoardActions = KeyboardActions (
        onNext = { focusManager.moveFocus(FocusDirection.Next) }
    )

//    Phone field content
    OutlinedTextField(
        value = phoneFieldValue,
        onValueChange = { onPhoneValueChange(it) },
        modifier = phoneFieldModifier,
        textStyle = TextStyle(
            color = secondaryColor,
            fontSize = 16.sp
        ),
        prefix = {
            Text(
                text = "+998"
            )
        },
        trailingIcon = {
            IconButton(
                onClick = {
                    phoneFieldTrailingFunction()
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
            if (phoneFieldError){
                Text(
                    text = stringResource(R.string.phone_number_error)
                )
            }
        },
        isError = phoneFieldError,
        keyboardOptions = keyBoardOptions,
        keyboardActions = keyBoardActions,
        singleLine = true,
        shape = RoundedCornerShape(10.dp),
    )
//    Firstname and lastname field content
    OutlinedTextField(
        value = nameFieldValue,
        onValueChange = { onNameFieldValueChange(it) },
        modifier = nameFieldModifier,
        textStyle = TextStyle(
            color = secondaryColor,
            fontSize = 16.sp
        ),
        trailingIcon = {
            IconButton(
                onClick = {
                    nameFieldTrailingFunction()
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
            if (nameFieldError){
                Text(
                    text = stringResource(R.string.phone_number_error)
                )
            }
        },
        isError = nameFieldError,
        singleLine = true,
        shape = RoundedCornerShape(10.dp),
    )
}