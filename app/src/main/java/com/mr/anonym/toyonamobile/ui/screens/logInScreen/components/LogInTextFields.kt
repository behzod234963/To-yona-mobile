package com.mr.anonym.toyonamobile.ui.screens.logInScreen.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
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
    passwordValue: String,
    onPasswordValueChange:(String)-> Unit,
    passwordValueTrailingIcon:()-> Unit,
    passwordValueError: Boolean,
    confirmPasswordValue: String,
    onConfirmPasswordValueChange:(String)-> Unit,
    confirmPasswordValueTrailingIcon:()->Unit,
    confirmPasswordValueError: Boolean,
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
        placeholder = {
            Text(
                modifier = Modifier
                    .padding(start = 10.dp),
                text = stringResource(R.string.enter_your_phone_number),
                fontSize = 14.sp
            )
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
//    Password field content
    OutlinedTextField(
        value = passwordValue,
        onValueChange = { onPasswordValueChange(it) },
        modifier = Modifier
            .fillMaxWidth(),
        textStyle = TextStyle(
            color = secondaryColor,
            fontSize = 16.sp
        ),
        placeholder = {
            Text(
                modifier = Modifier
                    .padding(start = 10.dp),
                text = stringResource(R.string.new_password),
                fontSize = 14.sp
            )
        },
        trailingIcon = {
            IconButton(
                onClick = {
                    passwordValueTrailingIcon()
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
            if (passwordValueError){
                Text(
                    text = stringResource(R.string.password_exeption)
                )
            }
        },
        isError = passwordValueError,
        singleLine = true,
        shape = RoundedCornerShape(10.dp),
    )
//    Confirm password field content
    OutlinedTextField(
        value = confirmPasswordValue,
        onValueChange = { onConfirmPasswordValueChange(it) },
        modifier = Modifier
            .fillMaxWidth(),
        textStyle = TextStyle(
            color = secondaryColor,
            fontSize = 16.sp
        ),
        placeholder = {
            Text(
                modifier = Modifier
                    .padding(start = 10.dp),
                text = stringResource(R.string.confirm_new_password),
                fontSize = 14.sp
            )
        },
        trailingIcon = {
            IconButton(
                onClick = {
                    confirmPasswordValueTrailingIcon()
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
            if (confirmPasswordValueError){
                Text(
                    text = stringResource(R.string.passwords_does_not_matches)
                )
            }
        },
        isError = confirmPasswordValueError,
        singleLine = true,
        shape = RoundedCornerShape(10.dp),
    )
}