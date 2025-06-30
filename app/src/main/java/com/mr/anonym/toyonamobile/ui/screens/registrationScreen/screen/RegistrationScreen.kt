package com.mr.anonym.toyonamobile.ui.screens.registrationScreen.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
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
import com.mr.anonym.toyonamobile.presentation.extensions.capitalizeChecker
import com.mr.anonym.toyonamobile.presentation.extensions.digitChecker
import com.mr.anonym.toyonamobile.presentation.extensions.lowercaseChecker
import com.mr.anonym.toyonamobile.presentation.extensions.passwordChecker
import com.mr.anonym.toyonamobile.presentation.extensions.phoneChecker
import com.mr.anonym.toyonamobile.presentation.extensions.symbolChecker
import com.mr.anonym.toyonamobile.presentation.navigation.ScreensRouter
import com.mr.anonym.toyonamobile.presentation.utils.PhoneNumberVisualTransformation
import com.mr.anonym.toyonamobile.ui.components.CustomPasswordTextField
import com.mr.anonym.toyonamobile.ui.components.CustomTextField
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
    val quaternaryColor = Color.Red
    val fiverdColor = Color.Green
    val systemEightrdColor =
        if (isSystemInDarkTheme()) MaterialTheme.colorScheme.background else Color.LightGray
    val eightrdColor = when {
        isSystemTheme -> systemEightrdColor
        isDarkTheme -> MaterialTheme.colorScheme.background
        else -> Color.LightGray
    }

    val containerPadding = remember { mutableIntStateOf(10) }
    val isLoading = remember { mutableStateOf(false) }
    val loadingAnimation = rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.anim_loading)
    )

    val phoneFieldError = remember { mutableStateOf(false) }
    val phoneFieldValue = remember { mutableStateOf(TextFieldValue("")) }
    val showPhoneErrorContent = remember { mutableStateOf(false) }

    val (passwordFieldValue, onPasswordFieldValueChange) = remember { mutableStateOf(TextFieldValue()) }
    val passwordValueError = remember { mutableStateOf(false) }

    val (confirmValue, onConfirmFieldValueChange) = remember { mutableStateOf(TextFieldValue()) }
    val confirmValueError = remember { mutableStateOf(false) }

    val isPasswordForgotten = dataStore.isPasswordForgottenState().collectAsState(false)

    val snackbarHostState = remember { SnackbarHostState() }
    val focusRequester = remember { FocusRequester() }

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
                    CustomTextField(
                        secondaryColor = secondaryColor,
                        eightrdColor = eightrdColor,
                        keyboardType = KeyboardType.Phone,
                        imeAction = ImeAction.Next,
                        value = phoneFieldValue.value,
                        onValueChange = {
                            phoneFieldValue.value = it
                            phoneFieldError.value = !it.text.phoneChecker()
                        },
                        label = stringResource(R.string.enter_your_phone_number),
                        focusRequester = focusRequester,
                        visualTransformation = PhoneNumberVisualTransformation(),
                        isPhoneField = true,
                        secondValue = "",
                        onSecondValueChange = {},
                        icon = null,
                    )
                    if (showPhoneErrorContent.value) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 10.dp),
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Text(
                                text = stringResource(R.string.phone_number_error),
                                color = quaternaryColor,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                        if ( !phoneFieldError.value ) showPhoneErrorContent.value = false
                    }
                    Spacer(Modifier.height(10.dp))
//                    Password content
                    CustomPasswordTextField(
                        secondaryColor = secondaryColor,
                        eightrdColor = eightrdColor,
                        imeAction = ImeAction.Done,
                        value = passwordFieldValue,
                        onValueChange = {
                            onPasswordFieldValueChange.invoke(it)
                            passwordValueError.value = !it.text.passwordChecker()
                        },
                        label = stringResource(R.string.password)
                    )
                    Spacer(Modifier.height(10.dp))
//                    Confirm password content
                    CustomPasswordTextField(
                        secondaryColor = secondaryColor,
                        eightrdColor = eightrdColor,
                        imeAction = ImeAction.Done,
                        value = confirmValue,
                        onValueChange = {
                            onConfirmFieldValueChange.invoke(it)
                            confirmValueError.value = !it.text.passwordChecker()
                        },
                        label = stringResource(R.string.confirm_new_password)
                    )
                    if (passwordFieldValue.text != confirmValue.text){
                        Text(
                            text = stringResource(R.string.passwords_does_not_matches),
                            color = quaternaryColor,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
//                    Password instruction
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = stringResource(R.string.the_field_must_contains_next_steps),
                            color = if (
                                passwordFieldValue.text.length >= 8 &&
                                passwordFieldValue.text.capitalizeChecker() &&
                                passwordFieldValue.text.lowercaseChecker() &&
                                passwordFieldValue.text.digitChecker() &&
                                passwordFieldValue.text.symbolChecker()
                            ) {
                                fiverdColor
                            } else {
                                quaternaryColor
                            },
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 10.dp),
                            horizontalAlignment = Alignment.Start
                        ) {
                            Text(
                                text = stringResource(R.string.minimum_8_symbols),
                                color = if (passwordFieldValue.text.length < 8) quaternaryColor else fiverdColor,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                            Text(
                                text = stringResource(R.string.at_least_one_capital_letter),
                                color = if (passwordFieldValue.text.capitalizeChecker()) fiverdColor else quaternaryColor,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                            Text(
                                text = stringResource(R.string.at_least_one_lowercase_letter),
                                color = if (passwordFieldValue.text.lowercaseChecker()) fiverdColor else quaternaryColor,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                            Text(
                                text = stringResource(R.string.at_least_one_digit),
                                color = if (passwordFieldValue.text.digitChecker()) fiverdColor else quaternaryColor,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                            Text(
                                text = stringResource(R.string.at_least_one_special_character_from_the_set),
                                color = if (passwordFieldValue.text.symbolChecker()) fiverdColor else quaternaryColor,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
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
                                !isPasswordForgotten.value &&
                                !passwordValueError.value -> {
                                    if (
                                        phoneFieldValue.value.text.isNotEmpty() &&
                                        phoneFieldValue.value.text.isNotBlank() &&
                                        !phoneFieldError.value
                                    ) {
                                        if (confirmValue.text == passwordFieldValue.text) {
                                            isLoading.value = true
                                            viewModel.signUpUser(
                                                user = UserModelItem(
                                                    username = "",
                                                    surname = "",
                                                    phonenumber = phoneFieldValue.value.text,
                                                    password = confirmValue.text
                                                )
                                            )
                                        }
                                    }else{
                                        showPhoneErrorContent.value = true
                                    }
                                }

                                isPasswordForgotten.value &&
                                        !passwordValueError.value -> {
                                    if (confirmValue.text == passwordFieldValue.text) {
                                        coroutineScope.launch {
                                            dataStore.isPasswordForgotten(false)
                                            dataStore.isOldUser(true)
                                            sharedPreferences.savePhoneNumber("+998${phoneFieldValue.value.text}")
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
                        sharedPreferences.savePhoneNumber(phoneFieldValue.value.text)
                        sharedPreferences.savePassword(confirmValue.text)
                        val result = "+998" + phoneFieldValue.value.text
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