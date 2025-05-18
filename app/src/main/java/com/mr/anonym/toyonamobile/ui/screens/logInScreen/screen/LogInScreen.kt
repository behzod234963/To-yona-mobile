package com.mr.anonym.toyonamobile.ui.screens.logInScreen.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
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
import com.mr.anonym.toyonamobile.ui.screens.logInScreen.components.LoginTextFields
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

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    val dataStore = DataStoreInstance(context)
    val sharedPreferences = SharedPreferencesInstance(context)

    val isPinForgotten = dataStore.isPinForgottenState().collectAsState(false)

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

    val containerPadding = rememberSaveable { mutableIntStateOf(10) }
    val isSendResponse = remember { mutableStateOf(false) }
    val loadingAnimation = rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.ic_loading)
    )
    val focusRequester = remember { FocusRequester() }
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    val phoneFieldValue = rememberSaveable { mutableStateOf("") }
    val phoneFieldError = rememberSaveable { mutableStateOf(false) }

    val passwordValue = rememberSaveable { mutableStateOf("") }
    val passwordValueError = rememberSaveable { mutableStateOf(false) }

    val responseMessage = viewModel.message

    val snackbarHostState = remember { SnackbarHostState() }
    Scaffold(
        containerColor = primaryColor,
        contentColor = primaryColor,
        modifier = Modifier
            .imePadding(),
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        if (!isSendResponse.value) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.20f)
                        .padding(containerPadding.intValue.dp),
                    verticalArrangement = Arrangement.Top,
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
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.60f)
                        .padding(containerPadding.intValue.dp),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    LoginTextFields(
                        secondaryColor = secondaryColor,
                        phoneFieldValue = phoneFieldValue.value,
                        onPhoneValueChange = {
                            phoneFieldValue.value = it.take(9)
                            if (it.length < 10) phoneFieldError.value = !it.phoneChecker()
                        },
                        phoneFieldTrailingFunction = { phoneFieldValue.value = "" },
                        phoneFieldError = phoneFieldError.value,
                        phoneFieldModifier = Modifier
                            .fillMaxWidth()
                            .focusRequester(focusRequester),
                        passwordValue = passwordValue.value,
                        onPasswordValueChange = {
                            passwordValue.value = it
                            passwordValueError.value = !it.passwordChecker()
                        },
                        passwordValueError = passwordValueError.value
                    )
                    TextButton(
                        onClick = {
                            navController.navigate(ScreensRouter.RegistrationScreen.route)
                        }
                    ) {
                        Text(
                            text = stringResource(R.string.i_have_no_account),
                            color = Color.Blue,
                            fontSize = 16.sp
                        )
                    }
                    TextButton(
                        onClick = {
                            if (
                                phoneFieldValue.value.isNotEmpty() &&
                                phoneFieldValue.value.isNotBlank() &&
                                !phoneFieldError.value
                            ) {
                                coroutineScope.launch {
                                    dataStore.isPasswordForgotten(true)
                                }
                                val result = "+998" + phoneFieldValue.value
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
                            color = Color.Blue,
                            fontSize = 16.sp
                        )
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.70f)
                        .padding(horizontal = 15.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom
                ) {
//                API needs for post mobile number
                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = quaternaryColor
                        ),
                        shape = RoundedCornerShape(10.dp),
                        onClick = {
                            if (
                                phoneFieldValue.value.isNotEmpty() &&
                                phoneFieldValue.value.isNotBlank() &&
                                !phoneFieldError.value &&
                                !passwordValueError.value
                            ) {
                                isSendResponse.value = true
                                viewModel.loginUser(
                                    LoginRequest(
                                        phonenumber = phoneFieldValue.value,
                                        password = passwordValue.value
                                    )
                                )
                            } else {
                                coroutineScope.launch {
                                    snackbarHostState.showSnackbar(
                                        message = context.getString(R.string.please_check_validate_places)
                                    )
                                }
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
                val result = "+998" + phoneFieldValue.value
                if (
                    responseMessage.value == "Login success"
                ) {
                    when {
                        isPinForgotten.value -> {
                            sharedPreferences.saveNewPinState(true)
                            navController.navigate(ScreensRouter.NewPinScreen.route) {
                                popUpTo(ScreensRouter.LoginScreen.route) { inclusive = true }
                            }
                        }
                        else -> {
                            coroutineScope.launch {
                                dataStore.isOldUser(true)
                                delay(1500)
                                isSendResponse.value = false
                                withContext(Dispatchers.Main){
                                    navController.navigate(ScreensRouter.NumberCheckScreen.route + "/$result") {
                                        popUpTo(ScreensRouter.LoginScreen.route) {
                                            inclusive = true
                                        }
                                    }
                                }
                            }
                        }
                    }
                } else {
                    coroutineScope.launch {
                        delay(1500)
                        isSendResponse.value = false
                        delay(500)
                        snackbarHostState.showSnackbar(
                            message = context.getString(R.string.user_is_not_found)
                        )
                    }
                }
            }
        }
    }
}