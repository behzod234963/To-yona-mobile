package com.mr.anonym.toyonamobile.ui.screens.securityScreen.screens

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mr.anonym.data.instance.local.SharedPreferencesInstance
import com.mr.anonym.toyonamobile.R
import com.mr.anonym.toyonamobile.presentation.navigation.ScreensRouter
import com.mr.anonym.toyonamobile.ui.screens.securityScreen.components.SecurityDialog
import com.mr.anonym.toyonamobile.ui.screens.securityScreen.components.SecurityFields
import com.mr.anonym.toyonamobile.ui.screens.securityScreen.components.SecurityTopBar
import com.mr.anonym.toyonamobile.ui.screens.securityScreen.viewModel.SecurityViewModel
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SecurityScreen(
    navController: NavController,
    viewModel: SecurityViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

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
    val fiverdColor = Color.Green
    val systemSevenrdColor = if (isSystemInDarkTheme()) Color.Unspecified else Color.White
    val sevenrdColor = when {
        isSystemTheme -> systemSevenrdColor
        isDarkTheme -> Color.Unspecified
        else -> Color.White
    }
    val snackbarHostState = remember { SnackbarHostState() }

    val isChangePinProcess = remember { mutableStateOf(false) }
    val isChangePasswordProcess = remember { mutableStateOf(false) }
    val isExitProcess = remember { mutableStateOf(false) }

    val isBiometricAuthOn = sharedPreferences.getIsBiometricAuthOn()
    val isFingerprintChecked = remember { mutableStateOf(isBiometricAuthOn) }

    val responseMessage = viewModel.message

    BackHandler(
        enabled = true
    ) {
        sharedPreferences.saveIsBiometricAuthOn(isFingerprintChecked.value)
        navController.navigateUp()
    }

    Scaffold(
        containerColor = primaryColor,
        contentColor = primaryColor,
        topBar = {
            SecurityTopBar(
                primaryColor = primaryColor,
                secondaryColor = secondaryColor,
                navigationClick = {
                    sharedPreferences.saveIsBiometricAuthOn(isFingerprintChecked.value)
                    navController.navigateUp()
                }
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(10.dp)
        ) {
//            Change pin code content
            SecurityFields(
                secondaryColor = secondaryColor,
                quaternaryColor = quaternaryColor,
                fiverdColor = fiverdColor,
                sevenrdColor = sevenrdColor,
                contentIcon = R.drawable.ic_change_pin_code,
                contentTitle = stringResource(R.string.change_pin_code),
                isHaveSwitcher = false,
                isChecked = false,
                onCheckedChange = { },
                onContentClick = { isChangePinProcess.value = true }
            )
//            Change password content
            SecurityFields(
                secondaryColor = secondaryColor,
                quaternaryColor = quaternaryColor,
                fiverdColor = fiverdColor,
                sevenrdColor = sevenrdColor,
                contentIcon = R.drawable.ic_change_password,
                contentTitle = stringResource(R.string.change_password),
                isHaveSwitcher = false,
                isChecked = false,
                onCheckedChange = { },
                onContentClick = { isChangePasswordProcess.value = true }
            )
//            Enter with fingerprint content
            SecurityFields(
                secondaryColor = secondaryColor,
                quaternaryColor = quaternaryColor,
                fiverdColor = fiverdColor,
                sevenrdColor = sevenrdColor,
                contentIcon = R.drawable.ic_allow_fingerprint,
                contentTitle = stringResource(R.string.enter_with_fingerprint),
                isHaveSwitcher = true,
                isChecked = isFingerprintChecked.value,
                onCheckedChange = {
                    isFingerprintChecked.value = it
                },
                onContentClick = {
                    isFingerprintChecked.value = !isFingerprintChecked.value
                }
            )
//            Exit logout content
            SecurityFields(
                secondaryColor = secondaryColor,
                quaternaryColor = quaternaryColor,
                fiverdColor = fiverdColor,
                sevenrdColor = sevenrdColor,
                contentIcon = R.drawable.ic_logout,
                contentTitle = stringResource(R.string.exit),
                isHaveSwitcher = false,
                isChecked = false,
                onCheckedChange = {},
                onContentClick = { isExitProcess.value = true }
            )
            when {
                isChangePinProcess.value -> {
                    SecurityDialog(
                        secondaryColor = secondaryColor,
                        quaternaryColor = quaternaryColor,
                        fiverdColor = fiverdColor,
                        title = stringResource(R.string.are_you_sure_to_change_pin_code),
                        onDismissClick = {
                            isChangePinProcess.value = false
                        },
                        onConfirmClick = {
                            sharedPreferences.saveIsBiometricAuthOn(isFingerprintChecked.value)
                            sharedPreferences.changePinProcess(true)
                            navController.navigate(ScreensRouter.NewPinScreen.route)
                            isChangePinProcess.value = false
                        },
                        onDismissRequest = { isChangePinProcess.value = false }
                    )
                }
                isChangePasswordProcess.value -> {
                    SecurityDialog(
                        secondaryColor = secondaryColor,
                        quaternaryColor = quaternaryColor,
                        fiverdColor = fiverdColor,
                        title = stringResource(R.string.are_you_want_to_change_password),
                        onDismissClick = { isChangePasswordProcess.value = false },
                        onConfirmClick = {
                            sharedPreferences.saveIsBiometricAuthOn(isFingerprintChecked.value)
                            navController.navigate(ScreensRouter.ChangePasswordScreen.route)
                            isChangePasswordProcess.value = false
                        },
                        onDismissRequest = { isChangePasswordProcess.value = false }
                    )
                }
                isExitProcess.value -> {
                    SecurityDialog(
                        secondaryColor = secondaryColor,
                        quaternaryColor = quaternaryColor,
                        fiverdColor = fiverdColor,
                        title = stringResource(R.string.are_you_sure_to_logout_from_this_account),
                        onDismissClick = { isExitProcess.value = false },
                        onConfirmClick = {
                            if (responseMessage.value == "User deleted"){
                                sharedPreferences.saveIsBiometricAuthOn(false)
                                sharedPreferences.saveBiometricAuthState(false)
                                sharedPreferences.saveIsLoggedIn(false)
                                navController.navigate(ScreensRouter.LoginScreen.route) {
                                    popUpTo(navController.graph.startDestinationId) {
                                        inclusive = true
                                    }
                                    launchSingleTop = true
                                }
                            }else{
                                coroutineScope.launch {
                                    snackbarHostState.showSnackbar(
                                        message = context.getString(R.string.unknown_error)
                                    )
                                }
                            }
                            isExitProcess.value = false
                        },
                        onDismissRequest = { isExitProcess.value = false }
                    )
                }
            }
        }
    }
}