package com.mr.anonym.toyonamobile.ui.screens.logInScreen.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mr.anonym.toyonamobile.R

@Composable
fun LoginTextFields(
    secondaryColor: Color,
    phoneFieldValue: String,
    onPhoneValueChange:(String)-> Unit,
    phoneFieldTrailingFunction:()-> Unit,
    phoneFieldError: Boolean,
    phoneFieldModifier: Modifier,
    passwordValue: String,
    onPasswordValueChange:(String)-> Unit,
    passwordValueError: Boolean,
) {

    val focusManager = LocalFocusManager.current
    val keyBoardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Number,
        imeAction = if (!phoneFieldError) ImeAction.Next else ImeAction.Default
    )
    val passwordKeyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Password
    )
    val keyBoardActions = KeyboardActions (
        onNext = { focusManager.moveFocus(FocusDirection.Next) }
    )
    val isShowPassword = rememberSaveable { mutableStateOf( false ) }
    val isShowConfirmPassword = rememberSaveable { mutableStateOf( false ) }
    val visualTransformation = VisualTransformation.None

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
        label = {
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
        visualTransformation = if (isShowPassword.value) VisualTransformation.None else PasswordVisualTransformation(),
        modifier = Modifier
            .fillMaxWidth(),
        textStyle = TextStyle(
            color = secondaryColor,
            fontSize = 16.sp
        ),
        keyboardOptions = passwordKeyboardOptions,
        label = {
            Text(
                modifier = Modifier
                    .padding(start = 10.dp),
                text = stringResource(R.string.password),
                fontSize = 14.sp
            )
        },
        trailingIcon = {
            IconButton(
                onClick = {
                    isShowPassword.value = !isShowPassword.value
                }
            ) {
                Icon(
                    modifier = Modifier
                        .size(25.dp),
                    painter = if ( isShowPassword.value ) painterResource(R.drawable.ic_show) else painterResource(R.drawable.ic_hide),
                    tint = secondaryColor,
                    contentDescription = "null"
                )
            }
        },
        supportingText = {
            if (passwordValueError){
                Text(
                    text = stringResource(R.string.password_exception)
                )
            }
        },
        isError = passwordValueError,
        singleLine = true,
        shape = RoundedCornerShape(10.dp),
    )
}