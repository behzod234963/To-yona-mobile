package com.mr.anonym.toyonamobile.ui.screens.changePasswordScreen.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.mr.anonym.data.instance.local.DataStoreInstance
import com.mr.anonym.data.instance.local.SharedPreferencesInstance
import com.mr.anonym.toyonamobile.R
import com.mr.anonym.toyonamobile.presentation.extensions.passwordChecker
import com.mr.anonym.toyonamobile.presentation.navigation.ScreensRouter
import com.mr.anonym.toyonamobile.ui.screens.changePasswordScreen.components.ChangePasswordFields
import com.mr.anonym.toyonamobile.ui.screens.changePasswordScreen.components.ChangePasswordTopBar
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ChangePasswordScreen(
    navController: NavController
) {

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    val dataStore = DataStoreInstance(context)
    val sharedPreferences = SharedPreferencesInstance(context)

    val isDarkTheme = sharedPreferences.getDarkThemeState()
    val isSystemTheme = sharedPreferences.getSystemThemeState()

    val systemPrimaryColor = if (isSystemInDarkTheme()) Color.Black else Color.White
    val primaryColor = when {
        isSystemTheme -> {
            systemPrimaryColor
        }
        isDarkTheme -> Color.Black
        else -> Color.White
    }
    val systemSecondaryColor = if (isSystemInDarkTheme()) Color.White else Color.Black
    val secondaryColor = when {
        isSystemTheme -> systemSecondaryColor
        isDarkTheme -> Color.White
        else -> Color.Black
    }
    val quaternaryColor = Color.Red

    val oldPassword = dataStore.getPassword().collectAsState("")
    val oldPasswordValue = rememberSaveable { mutableStateOf("") }
    val oldPasswordError = rememberSaveable { mutableStateOf(false) }
    val newPasswordValue = rememberSaveable { mutableStateOf("") }
    val newPasswordError = rememberSaveable { mutableStateOf(false) }
    val confirmNewPasswordValue = rememberSaveable { mutableStateOf("") }
    val confirmNewPasswordError = rememberSaveable { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier
            .imePadding(),
        containerColor = primaryColor,
        contentColor = primaryColor,
        topBar = {
            ChangePasswordTopBar(
                primaryColor = primaryColor,
                secondaryColor = secondaryColor,
                onNavigationClick = { navController.navigateUp() }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.85f)
                    .padding(10.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.to_change_password_you_need_to_enter_an_old_password_and_new_password_then_confirm_new_password),
                        fontSize = 18.sp,
                        color = secondaryColor,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center
                    )
                }
                Spacer(Modifier.height(10.dp))
                ChangePasswordFields(
                    secondaryColor = secondaryColor,
//                Old password properties
                    oldPasswordValue = oldPasswordValue.value,
                    onOldPasswordValueChange = {
                        oldPasswordValue.value = it
                        oldPasswordError.value = !it.passwordChecker()
                    },
                    isOldPasswordError = oldPasswordError.value,
//                New password properties
                    newPasswordValue = newPasswordValue.value,
                    onNewPasswordValueChange = {
                        newPasswordValue.value = it
                        newPasswordError.value = !it.passwordChecker()
                    },
                    isNewPasswordError = newPasswordError.value,
//                Confirm new password properties
                    confirmPasswordValue = confirmNewPasswordValue.value,
                    onConfirmPasswordValueChange = {
                        confirmNewPasswordValue.value = it
                    },
                    isConfirmPasswordError = confirmNewPasswordError.value
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.7f)
                    .padding(horizontal = 10.dp),
                verticalArrangement = Arrangement.Bottom
            ) {
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    onClick = {
                        when {
                            oldPasswordValue.value.isNotBlank() &&
                                    oldPasswordValue.value.isNotEmpty() &&
                                    !oldPasswordError.value &&
                                    newPasswordValue.value.isNotEmpty() &&
                                    newPasswordValue.value.isNotBlank() &&
                                    !newPasswordError.value -> {
                                if (
                                    oldPasswordValue.value == oldPassword.value &&
                                    newPasswordValue.value == confirmNewPasswordValue.value
                                    ) {
                                    coroutineScope.launch {
                                        dataStore.savePassword(confirmNewPasswordValue.value)
                                    }
                                    navController.navigate(ScreensRouter.SecurityScreen.route) {
                                        popUpTo(ScreensRouter.ChangePasswordScreen.route) {
                                            inclusive = true
                                        }
                                    }
                                } else {
                                    confirmNewPasswordError.value = true
                                }
                            }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = quaternaryColor,
                        contentColor = quaternaryColor
                    ),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(
                        text = stringResource(R.string.Confirm),
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewChangePasswordScreen() {
    ChangePasswordScreen(
        navController = NavController(LocalContext.current)
    )
}