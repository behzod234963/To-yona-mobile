package com.mr.anonym.toyonamobile.ui.screens.securityScreen.screens

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.mr.anonym.data.instance.local.SharedPreferencesInstance
import com.mr.anonym.toyonamobile.R
import com.mr.anonym.toyonamobile.presentation.navigation.ScreensRouter
import com.mr.anonym.toyonamobile.ui.screens.securityScreen.components.SecurityDialog
import com.mr.anonym.toyonamobile.ui.screens.securityScreen.components.SecurityFields
import com.mr.anonym.toyonamobile.ui.screens.securityScreen.components.SecurityTopBar
import com.mr.anonym.toyonamobile.ui.screens.securityScreen.viewModel.SecurityViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
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
    val fiveColor = Color(101, 163, 119, 255)
    val systemNineColor = if (isSystemInDarkTheme()) Color(0xFF222327) else Color(0xFFF1F2F4)
    val nineColor = when{
        isSystemTheme -> systemNineColor
        isDarkTheme -> Color(0xFF222327)
        else -> Color(0xFFF1F2F4)
    }

    val iosFont = FontFamily(Font(R.font.ios_font))

    val isLoading = remember { mutableStateOf( false ) }
    val loadingAnimation = rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.anim_loading)
    )
    val snackbarHostState = remember { SnackbarHostState() }

    val isChangePinProcess = remember { mutableStateOf(false) }
    val isChangePasswordProcess = remember { mutableStateOf(false) }
    val isExitProcess = remember { mutableStateOf(false) }

    val isBiometricAuthOn = sharedPreferences.getIsBiometricAuthOn()
    val isFingerprintChecked = remember { mutableStateOf(isBiometricAuthOn) }

    val responseMessage = viewModel.message

    val pinInteractionSource = remember { MutableInteractionSource() }
    val isPinPressed by pinInteractionSource.collectIsPressedAsState()
    val pinScale by animateFloatAsState( if ( isPinPressed ) 0.95f else 1f )

    val pwdInteractionSource = remember { MutableInteractionSource() }
    val isPwdPressed by pwdInteractionSource.collectIsPressedAsState()
    val pwdScale by animateFloatAsState( if ( isPwdPressed ) 0.95f else 1f )

    val fingerInteractionSource = remember { MutableInteractionSource() }
    val isFingerPressed by fingerInteractionSource.collectIsPressedAsState()
    val fingerScale by animateFloatAsState( if ( isFingerPressed ) 0.95f else 1f )

    val exitInteractionSource = remember { MutableInteractionSource() }
    val isExitPressed by exitInteractionSource.collectIsPressedAsState()
    val exitScale by animateFloatAsState( if ( isExitPressed ) 0.95f else 1f )

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
                fontFamily = iosFont
            ) {
                sharedPreferences.saveIsBiometricAuthOn(isFingerprintChecked.value)
                navController.navigateUp()
            }
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        if (!isLoading.value){
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
                    fiveColor = fiveColor,
                    nineColor = nineColor,
                    fontFamily = iosFont,
                    scale = pinScale,
                    interactionSource = pinInteractionSource,
                    contentIcon = R.drawable.ic_change_pin_code,
                    contentTitle = stringResource(R.string.change_pin_code),
                    isHaveSwitcher = false,
                    isChecked = false,
                    onCheckedChange = { }
                ) { isChangePinProcess.value = true }
                Spacer(Modifier.height(10.dp))
//            Change password content
                SecurityFields(
                    secondaryColor = secondaryColor,
                    quaternaryColor = quaternaryColor,
                    fiveColor = fiveColor,
                    nineColor = nineColor,
                    fontFamily = iosFont,
                    scale = pwdScale,
                    interactionSource = pwdInteractionSource,
                    contentIcon = R.drawable.ic_change_password,
                    contentTitle = stringResource(R.string.change_password),
                    isHaveSwitcher = false,
                    isChecked = false,
                    onCheckedChange = { }
                ) { isChangePasswordProcess.value = true }
                Spacer(Modifier.height(10.dp))
//            Enter with fingerprint content
                SecurityFields(
                    secondaryColor = secondaryColor,
                    quaternaryColor = quaternaryColor,
                    fiveColor = fiveColor,
                    nineColor = nineColor,
                    fontFamily = iosFont,
                    scale = fingerScale,
                    interactionSource = fingerInteractionSource,
                    contentIcon = R.drawable.ic_allow_fingerprint,
                    contentTitle = stringResource(R.string.enter_with_fingerprint),
                    isHaveSwitcher = true,
                    isChecked = isFingerprintChecked.value,
                    onCheckedChange = {
                        isFingerprintChecked.value = it
                    }
                ) {
                    isFingerprintChecked.value = !isFingerprintChecked.value
                }
                Spacer(Modifier.height(10.dp))
//            Exit logout content
                SecurityFields(
                    secondaryColor = secondaryColor,
                    quaternaryColor = quaternaryColor,
                    fiveColor = fiveColor,
                    nineColor = nineColor,
                    fontFamily = iosFont,
                    scale = exitScale,
                    interactionSource = exitInteractionSource,
                    contentIcon = R.drawable.ic_logout,
                    contentTitle = stringResource(R.string.exit),
                    isHaveSwitcher = false,
                    isChecked = false,
                    onCheckedChange = {}
                ) { isExitProcess.value = true }
                Spacer(Modifier.height(10.dp))
                when {
                    isChangePinProcess.value -> {
                        SecurityDialog(
                            secondaryColor = secondaryColor,
                            quaternaryColor = quaternaryColor,
                            fiveColor = fiveColor,
                            nineColor = nineColor,
                            fontFamily = iosFont,
                            title = stringResource(R.string.are_you_sure_to_change_pin_code),
                            onDismissClick = {
                                isChangePinProcess.value = false
                            },
                            onConfirmClick = {
                                sharedPreferences.saveIsBiometricAuthOn(isFingerprintChecked.value)
                                sharedPreferences.changePinProcess(true)
                                navController.navigate(ScreensRouter.NewPinScreen.route)
                                isChangePinProcess.value = false
                            }
                        ) { isChangePinProcess.value = false }
                    }
                    isChangePasswordProcess.value -> {
                        SecurityDialog(
                            secondaryColor = secondaryColor,
                            quaternaryColor = quaternaryColor,
                            fiveColor = fiveColor,
                            nineColor = nineColor,
                            fontFamily = iosFont,
                            title = stringResource(R.string.are_you_want_to_change_password),
                            onDismissClick = { isChangePasswordProcess.value = false },
                            onConfirmClick = {
                                sharedPreferences.saveIsBiometricAuthOn(isFingerprintChecked.value)
                                navController.navigate(ScreensRouter.ChangePasswordScreen.route)
                                isChangePasswordProcess.value = false
                            }
                        ) { isChangePasswordProcess.value = false }
                    }
                    isExitProcess.value -> {
                        SecurityDialog(
                            secondaryColor = secondaryColor,
                            quaternaryColor = quaternaryColor,
                            fiveColor = fiveColor,
                            nineColor = nineColor,
                            fontFamily = iosFont,
                            title = stringResource(R.string.are_you_sure_to_logout_from_this_account),
                            onDismissClick = { isExitProcess.value = false },
                            onConfirmClick = {
                                isLoading.value = true
                                viewModel.deleteUser()
                                isExitProcess.value = false
                            }
                        ) { isExitProcess.value = false }
                    }
                }
            }
        }else{
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                LottieAnimation(
                    modifier = Modifier
                        .size(150.dp),
                    composition = loadingAnimation.value,
                    restartOnPlay = true,
                    iterations = LottieConstants.IterateForever
                )
                coroutineScope.launch {
                    delay(2000)
                    if (responseMessage.value == "User deleted"){
                        sharedPreferences.saveIsBiometricAuthOn(false)
                        sharedPreferences.saveBiometricAuthState(false)
                        sharedPreferences.saveIsLoggedIn(false)
                        withContext(Dispatchers.Main){
                            navController.navigate(ScreensRouter.LoginScreen.route) {
                                popUpTo(navController.graph.startDestinationId) {
                                    inclusive = true
                                }
                                launchSingleTop = true
                            }
                        }
                        isLoading.value = false
                    }else{
                        if (isLoading.value){
                            coroutineScope.launch {
                                snackbarHostState.showSnackbar(
                                    message = context.getString(R.string.unknown_error)
                                )
                            }
                            isLoading.value = false
                        }else{
                            isLoading.value = false
                        }
                    }
                }
            }
        }
    }
}