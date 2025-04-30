package com.mr.anonym.toyonamobile.ui.screens.changePasswordScreen.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mr.anonym.toyonamobile.R

@Composable
fun ChangePasswordFields(
    secondaryColor: Color,
//    Old password properties
    oldPasswordValue: String,
    onOldPasswordValueChange:(String)-> Unit,
    isOldPasswordError: Boolean,
//    New password properties
    newPasswordValue: String,
    onNewPasswordValueChange:(String)-> Unit,
    isNewPasswordError: Boolean,
//    Confirm new password properties
    confirmPasswordValue: String,
    onConfirmPasswordValueChange:(String)-> Unit,
    isConfirmPasswordError: Boolean
) {
    val focusManager = LocalFocusManager.current
    val passwordKeyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Password
    )
    val keyBoardActions = KeyboardActions(
        onNext = { focusManager.moveFocus(FocusDirection.Next) }
    )
    val isOldPasswordShow = rememberSaveable { mutableStateOf(false) }
    val isShowNewPassword = rememberSaveable { mutableStateOf( false ) }
    val isShowConfirmPassword = rememberSaveable { mutableStateOf(false) }
    val visualTransformation = VisualTransformation.None

//    Old password field
    OutlinedTextField(
        value = oldPasswordValue,
        onValueChange = { onOldPasswordValueChange(it) },
        visualTransformation = if (isOldPasswordShow.value) VisualTransformation.None else PasswordVisualTransformation(),
        modifier = Modifier
            .fillMaxWidth(),
        textStyle = TextStyle(
            color = secondaryColor,
            fontSize = 16.sp
        ),
        keyboardOptions = passwordKeyboardOptions,
        label = {
            Text(
                text = stringResource(R.string.old_password),
            )
        },
        trailingIcon = {
            IconButton(
                onClick = {
                    isOldPasswordShow.value = !isOldPasswordShow.value
                }
            ) {
                Icon(
                    modifier = Modifier
                        .size(25.dp),
                    painter = if (isOldPasswordShow.value) painterResource(R.drawable.ic_show) else painterResource(
                        R.drawable.ic_hide
                    ),
                    tint = secondaryColor,
                    contentDescription = "null"
                )
            }
        },
        supportingText = {
            if (isOldPasswordError) {
                Text(
                    text = stringResource(R.string.password_exception)
                )
            }
        },
        isError = isOldPasswordError,
        singleLine = true,
        shape = RoundedCornerShape(10.dp),
    )
//    New password field
    OutlinedTextField(
        value = newPasswordValue,
        onValueChange = { onNewPasswordValueChange(it) },
        visualTransformation = if (isShowNewPassword.value) VisualTransformation.None else PasswordVisualTransformation(),
        modifier = Modifier
            .fillMaxWidth(),
        textStyle = TextStyle(
            color = secondaryColor,
            fontSize = 16.sp
        ),
        keyboardOptions = passwordKeyboardOptions,
        label = {
            Text(
                text = stringResource(R.string.new_password),
            )
        },
        trailingIcon = {
            IconButton(
                onClick = {
                    isShowNewPassword.value = !isShowNewPassword.value
                }
            ) {
                Icon(
                    modifier = Modifier
                        .size(25.dp),
                    painter = if (isShowNewPassword.value) painterResource(R.drawable.ic_show) else painterResource(
                        R.drawable.ic_hide
                    ),
                    tint = secondaryColor,
                    contentDescription = "null"
                )
            }
        },
        supportingText = {
            if (isNewPasswordError) {
                Text(
                    text = stringResource(R.string.password_exception)
                )
            }
        },
        isError = isNewPasswordError,
        singleLine = true,
        shape = RoundedCornerShape(10.dp),
    )
//    Confirm new password field
    OutlinedTextField(
        value = confirmPasswordValue,
        onValueChange = { onConfirmPasswordValueChange(it) },
        visualTransformation = if (isShowConfirmPassword.value) VisualTransformation.None else PasswordVisualTransformation(),
        modifier = Modifier
            .fillMaxWidth(),
        textStyle = TextStyle(
            color = secondaryColor,
            fontSize = 16.sp
        ),
        keyboardOptions = passwordKeyboardOptions,
        label = {
            Text(
                text = stringResource(R.string.confirm_new_password),
            )
        },
        trailingIcon = {
            IconButton(
                onClick = {
                    isShowConfirmPassword.value = !isShowConfirmPassword.value
                }
            ) {
                Icon(
                    modifier = Modifier
                        .size(25.dp),
                    painter = if (isShowConfirmPassword.value) painterResource(R.drawable.ic_show) else painterResource(
                        R.drawable.ic_hide
                    ),
                    tint = secondaryColor,
                    contentDescription = "null"
                )
            }
        },
        supportingText = {
            if (isConfirmPasswordError) {
                Text(
                    text = stringResource(R.string.password_exception)
                )
            }
        },
        isError = isConfirmPasswordError,
        singleLine = true,
        shape = RoundedCornerShape(10.dp),
    )
}