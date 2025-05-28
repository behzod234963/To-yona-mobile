package com.mr.anonym.toyonamobile.ui.screens.registrationScreen.screen

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
import androidx.compose.ui.tooling.preview.Preview
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
import com.mr.anonym.domain.model.UserModelItem
import com.mr.anonym.toyonamobile.R
import com.mr.anonym.toyonamobile.presentation.extensions.passwordChecker
import com.mr.anonym.toyonamobile.presentation.extensions.phoneChecker
import com.mr.anonym.toyonamobile.presentation.navigation.ScreensRouter
import com.mr.anonym.toyonamobile.ui.screens.registrationScreen.components.RegistrationTextFields
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@Composable
fun RegistrationScreen(
    navController: NavController,
    viewModel: RegistrationViewModel = hiltViewModel()
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
    val systemTertiaryColor = if (isSystemInDarkTheme()) Color.DarkGray else Color.LightGray
    val tertiaryColor = when {
        isSystemTheme -> systemTertiaryColor
        isDarkTheme -> Color.DarkGray
        else -> Color.LightGray
    }
    val quaternaryColor = Color.Red

    val containerPadding = remember { mutableIntStateOf(10) }
    val isLoading = remember { mutableStateOf(false) }
    val loadingAnimation = rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.anim_loading)
    )

    val phoneFieldError = remember { mutableStateOf(false) }
    val phoneFieldValue = rememberSaveable { mutableStateOf("") }

    val passwordValue = rememberSaveable { mutableStateOf("") }
    val passwordValueError = remember { mutableStateOf(false) }

    val confirmValue = rememberSaveable { mutableStateOf("") }
    val confirmValueError = remember { mutableStateOf(false) }

    val isPasswordForgotten = dataStore.isPasswordForgottenState().collectAsState(false)

    val snackbarHostState = remember { SnackbarHostState() }
    val focusRequester = remember { FocusRequester() }

    val phoneNumber = ""

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
    Scaffold(
        containerColor = primaryColor,
        contentColor = primaryColor,
        modifier = Modifier
            .imePadding(),
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        if (!isLoading.value) {
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
                        text = if (isPasswordForgotten.value) {
                            stringResource(R.string.password_recovery)
                        } else {
                            stringResource(R.string.sign_up)
                        },
                        color = secondaryColor,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(Modifier.height(10.dp))
                    Text(
                        textAlign = TextAlign.Center,
                        text = if (isPasswordForgotten.value) {
                            stringResource(R.string.password_recovery_instruction)
                        } else {
                            stringResource(R.string.registration_instruction)
                        },
                        color = secondaryColor,
                        fontSize = 16.sp,
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.80f)
                        .padding(containerPadding.intValue.dp),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    RegistrationTextFields(
                        secondaryColor = secondaryColor,
                        tertiaryColor = tertiaryColor,
                        phoneFieldModifier = Modifier
                            .fillMaxWidth()
                            .focusRequester(focusRequester),
                        phoneFieldError = phoneFieldError.value,
                        isPhoneFieldEnabled = !isPasswordForgotten.value,
                        phoneFieldValue = if (isPasswordForgotten.value) phoneNumber else phoneFieldValue.value,
                        phoneFieldTrailingFunction = { phoneFieldValue.value = "" },
                        onPhoneValueChange = {
                            phoneFieldValue.value = it.take(9)
//                        if (it.isEmpty()) phoneFieldValue.value = "+998"
                            if (it.length < 10) phoneFieldError.value = !it.phoneChecker()
                        },
                        passwordValue = passwordValue.value,
                        onPasswordValueChange = {
                            passwordValue.value = it
                            passwordValueError.value = !it.passwordChecker()
                        },
                        passwordValueError = passwordValueError.value,
                        confirmPasswordValue = confirmValue.value,
                        onConfirmPasswordValueChange = {
                            confirmValue.value = it
                        },
                        confirmPasswordValueError = confirmValueError.value
                    )
                    if (!isPasswordForgotten.value) {
                        TextButton(
                            onClick = { navController.navigateUp() }
                        ) {
                            Text(
                                text = stringResource(R.string.i_have_an_account),
                                color = Color.Blue,
                                fontSize = 16.sp
                            )
                        }
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.60f)
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
                            when {
                                phoneFieldValue.value.isNotEmpty() &&
                                        phoneFieldValue.value.isNotBlank() &&
                                        !phoneFieldError.value &&
                                        !passwordValueError.value &&
                                        !isPasswordForgotten.value -> {
                                    if (confirmValue.value == passwordValue.value) {
                                        isLoading.value = true
                                        viewModel.signUpUser(
                                            user = UserModelItem(
                                                username = "",
                                                surname = "",
                                                phonenumber = phoneFieldValue.value,
                                                password = confirmValue.value
                                            )
                                        )
                                    } else {
                                        confirmValueError.value = true
                                    }
                                }

                                isPasswordForgotten.value &&
                                        !passwordValueError.value -> {
                                    if (confirmValue.value == passwordValue.value) {
                                        coroutineScope.launch {
                                            dataStore.isPasswordForgotten(false)
                                            dataStore.isOldUser(true)
                                            sharedPreferences.savePhoneNumber("+998${phoneFieldValue}")
                                        }
                                        sharedPreferences.saveIsLoggedIn(true)
                                        sharedPreferences.saveIsProfileSettingsState(true)
                                        navController.navigate(ScreensRouter.ProfileScreen.route) {
                                            popUpTo(ScreensRouter.RegistrationScreen.route) {
                                                inclusive = true
                                            }
                                        }
                                    } else {
                                        confirmValueError.value = true
                                    }
                                }

                                else -> {
                                    coroutineScope.launch {
                                        snackbarHostState.showSnackbar(
                                            message = context.getString(R.string.please_check_validate_places)
                                        )
                                    }
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
                coroutineScope.launch {
                    delay(1500)
                    if (viewModel.user.value.id != -1) {
                        sharedPreferences.savePhoneNumber(phoneFieldValue.value)
                        sharedPreferences.savePassword(confirmValue.value)
                        val result = "+998" + phoneFieldValue.value
                        withContext(Dispatchers.Main) {
                            navController.navigate(ScreensRouter.NumberCheckScreen.route + "/$result") {
                                popUpTo(ScreensRouter.RegistrationScreen.route) {
                                    inclusive = true
                                }
                            }
                        }
                        isLoading.value = false
                    } else {
                        delay(500)
                        if (isLoading.value) {
                            snackbarHostState.showSnackbar(
                                message = context.getString(R.string.user_is_already_exists)
                            )
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
private fun PreviewRegistrationScreen() {
    RegistrationScreen(NavController(LocalContext.current))
}