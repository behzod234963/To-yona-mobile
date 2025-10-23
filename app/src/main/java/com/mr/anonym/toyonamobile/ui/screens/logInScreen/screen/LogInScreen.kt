package com.mr.anonym.toyonamobile.ui.screens.logInScreen.screen

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.mr.anonym.data.instance.local.DataStoreInstance
import com.mr.anonym.data.instance.local.SharedPreferencesInstance
import com.mr.anonym.domain.response.LoginRequest
import com.mr.anonym.toyonamobile.R
import com.mr.anonym.toyonamobile.presentation.extensions.passwordChecker
import com.mr.anonym.toyonamobile.presentation.extensions.phoneChecker
import com.mr.anonym.toyonamobile.presentation.navigation.ScreensRouter
import com.mr.anonym.toyonamobile.presentation.utils.PhoneNumberVisualTransformation
import com.mr.anonym.toyonamobile.ui.components.CustomPasswordTextField
import com.mr.anonym.toyonamobile.ui.components.CustomTextField
import com.mr.anonym.toyonamobile.ui.screens.logInScreen.viewModel.LoginViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun LogInScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {

//    Context
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val activityContext = LocalActivity.current

    val dataStore = DataStoreInstance(context)
    val sharedPreferences = SharedPreferencesInstance(context)

    val isPinForgotten = dataStore.isPinForgottenState().collectAsState(false)
    val isDarkTheme = sharedPreferences.getDarkThemeState()
    val isSystemTheme = sharedPreferences.getSystemThemeState()

//    Colors
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
    val systemTertiaryColor = if (isSystemInDarkTheme()) Color.DarkGray else Color.LightGray
    val tertiaryColor = when {
        isSystemTheme -> systemTertiaryColor
        isDarkTheme -> Color.DarkGray
        else -> Color.LightGray
    }
    val systemNineColor = if (isSystemInDarkTheme()) Color(0xFF222327) else Color(0xFFF1F2F4)
    val nineColor = when {
        isSystemTheme -> systemNineColor
        isDarkTheme -> Color(0xFF222327)
        else -> Color(0xFFF1F2F4)
    }

    val isLoading = remember { mutableStateOf(false) }
    val loadingAnimation = rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.anim_loading)
    )

    val phoneFieldValue = remember { mutableStateOf(TextFieldValue("")) }
    val phoneFieldError = rememberSaveable { mutableStateOf(false) }
    val showPhoneErrorContent = remember { mutableStateOf(false) }

    val (passwordFieldValue, onPasswordFieldValueChange) = remember { mutableStateOf(TextFieldValue()) }
    val passwordValueError = rememberSaveable { mutableStateOf(false) }
    val showPasswordErrorContent = remember { mutableStateOf(false) }

    val snackbarHostState = remember { SnackbarHostState() }
    val isSnackbarShown = remember { mutableStateOf(false) }
    val isLoginSuccess = viewModel.isLoginSuccess

    BackHandler {
        activityContext?.finish()
    }
    Scaffold(
        containerColor = nineColor,
        contentColor = nineColor,
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        if (!isLoading.value) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(10.dp),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.20f)
                        .background(color = primaryColor, shape = RoundedCornerShape(15.dp))
                        .padding(15.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(Modifier.height(10.dp))
                    Text(
                        text = stringResource(R.string.log_in),
                        color = secondaryColor,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(Modifier.height(10.dp))
                    Text(
                        modifier = Modifier
                            .padding(horizontal = 10.dp),
                        textAlign = TextAlign.Center,
                        text = stringResource(R.string.login_instruction),
                        color = secondaryColor,
                        fontSize = 16.sp,
                    )
                }
                Spacer(Modifier.height(10.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .background(color = primaryColor, shape = RoundedCornerShape(15.dp))
                        .padding(10.dp),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CustomTextField(
                        secondaryColor = secondaryColor,
                        eightrdColor = nineColor,
                        keyboardType = KeyboardType.Phone,
                        imeAction = ImeAction.Next,
                        value = phoneFieldValue.value,
                        onValueChange = {
                            phoneFieldValue.value = it
                            phoneFieldError.value = !it.text.phoneChecker()
                        },
                        label = stringResource(R.string.enter_your_phone_number),
                        visualTransformation = PhoneNumberVisualTransformation(),
                    )
                    Spacer(Modifier.height(10.dp))
                    CustomPasswordTextField(
                        secondaryColor = secondaryColor,
                        eightrdColor = nineColor,
                        imeAction = ImeAction.Done,
                        value = passwordFieldValue,
                        onValueChange = {
                            onPasswordFieldValueChange.invoke(it)
                            passwordValueError.value = !it.text.passwordChecker()
                        },
                        label = stringResource(R.string.password)
                    )
                    Spacer(Modifier.height(10.dp))
                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .padding(horizontal = 10.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = quaternaryColor
                        ),
                        shape = RoundedCornerShape(15.dp),
                        onClick = {
                            if (
                                phoneFieldValue.value.text.isNotEmpty() &&
                                phoneFieldValue.value.text.isNotBlank() &&
                                !phoneFieldError.value
                            ) {
                                if (!passwordValueError.value) {
                                    isLoading.value = true
                                    viewModel.loginUser(
                                        LoginRequest(
                                            phonenumber = phoneFieldValue.value.text,
                                            password = passwordFieldValue.text
                                        )
                                    )
                                } else {
                                    showPasswordErrorContent.value = true
                                }
                            } else {
                                showPhoneErrorContent.value = true
                                phoneFieldError.value = true
                            }
                        }
                    ) {
                        Text(
                            text = stringResource(R.string.continue_),
                            color = Color.White,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                    Spacer(Modifier.height(10.dp))
                    //                    Create account
                    TextButton(
                        onClick = {
                            navController.navigate(ScreensRouter.RegistrationScreen.route)
                        }
                    ) {
                        Text(
                            text = stringResource(R.string.i_have_no_account),
                            color = tertiaryColor,
                            fontSize = 16.sp
                        )
                    }
//                    Forgot password
                    TextButton(
                        onClick = {
                            if (
                                phoneFieldValue.value.text.isNotEmpty() &&
                                phoneFieldValue.value.text.isNotBlank() &&
                                !phoneFieldError.value
                            ) {
                                coroutineScope.launch {
                                    dataStore.isPasswordForgotten(true)
                                }
                                val result = "+998" + phoneFieldValue.value.text
                                navController.navigate(ScreensRouter.NumberCheckScreen.route + "/$result") {
                                    popUpTo(ScreensRouter.LoginScreen.route) { inclusive = true }
                                }
                            } else {
                                phoneFieldError.value = true
                            }
                        }
                    ) {
                        Text(
                            text = stringResource(R.string.forgot_password),
                            color = tertiaryColor,
                            fontSize = 16.sp
                        )
                    }
                }
            }
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                LottieAnimation(
                    modifier = Modifier
                        .size(150.dp),
                    composition = loadingAnimation.value,
                    restartOnPlay = true,
                    iterations = LottieConstants.IterateForever
                )
                val result = "+998" + phoneFieldValue.value.text
                LaunchedEffect(isLoading.value) {
                    delay(1500)
                    if (
                        isLoginSuccess.value
                    ) {
                        when {
                            isPinForgotten.value -> {
                                sharedPreferences.saveNewPinState(true)
                                withContext(Dispatchers.Main) {
                                    navController.navigate(ScreensRouter.NewPinScreen.route) {
                                        popUpTo(ScreensRouter.LoginScreen.route) {
                                            inclusive = true
                                        }
                                    }
                                }
                            }

                            else -> {
                                viewModel.decodeToken()
                                dataStore.isOldUser(true)
                                isLoading.value = false
                                withContext(Dispatchers.Main) {
                                    navController.navigate(ScreensRouter.NumberCheckScreen.route + "/$result") {
                                        popUpTo(ScreensRouter.LoginScreen.route) {
                                            inclusive = true
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        if (!isSnackbarShown.value) {
                            delay(2000)
                            snackbarHostState.showSnackbar(
                                message = context.getString(R.string.user_is_not_found)
                            )
                            isSnackbarShown.value = true
                        }
                        isLoading.value = false
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewLoginScreen() {
    LogInScreen(
        navController = NavController(LocalContext.current),
        viewModel = hiltViewModel()
    )
}