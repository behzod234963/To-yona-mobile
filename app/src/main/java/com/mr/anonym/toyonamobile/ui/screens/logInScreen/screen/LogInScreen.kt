package com.mr.anonym.toyonamobile.ui.screens.logInScreen.screen

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
    val systemEightrdColor =
        if (isSystemInDarkTheme()) MaterialTheme.colorScheme.background else Color.LightGray
    val eightrdColor = when {
        isSystemTheme -> systemEightrdColor
        isDarkTheme -> MaterialTheme.colorScheme.background
        else -> Color.LightGray
    }

    val containerPadding = rememberSaveable { mutableIntStateOf(10) }
    val isLoading = remember { mutableStateOf(false) }
    val loadingAnimation = rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.anim_loading)
    )

    val phoneFieldValue = remember { mutableStateOf(TextFieldValue("")) }
    val phoneFieldError = rememberSaveable { mutableStateOf(false) }
    val showPhoneErrorContent = remember { mutableStateOf(false) }

    val (passwordFieldValue, onPasswordFieldValueChange) = remember { mutableStateOf(TextFieldValue()) }
    val passwordValueError = rememberSaveable { mutableStateOf(false) }
    val showPasswordErrorContent = remember { mutableStateOf( false ) }

    val snackbarHostState = remember { SnackbarHostState() }
    val isSnackbarShown = remember { mutableStateOf(false) }
    val isLoginSuccess = viewModel.isLoginSuccess
    Scaffold(
        containerColor = primaryColor,
        contentColor = primaryColor,
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
                        visualTransformation = PhoneNumberVisualTransformation(),
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
                    }
                    Spacer(Modifier.height(10.dp))
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
                    if (showPasswordErrorContent.value){
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 10.dp)
                        ) {
                            if (passwordFieldValue.text.length < 8){
                                Text(
                                    text = stringResource(R.string.minimum_8_symbols),
                                    color = quaternaryColor,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                            if (!passwordFieldValue.text.capitalizeChecker()){
                                Text(
                                    text = stringResource(R.string.at_least_one_capital_letter),
                                    color = quaternaryColor,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                            if (!passwordFieldValue.text.lowercaseChecker()){
                                Text(
                                    text = stringResource(R.string.at_least_one_lowercase_letter),
                                    color = quaternaryColor,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                            if (!passwordFieldValue.text.digitChecker()){
                                Text(
                                    text = stringResource(R.string.at_least_one_digit),
                                    color = quaternaryColor,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                            if (!passwordFieldValue.text.symbolChecker()){
                                Text(
                                    text = stringResource(R.string.at_least_one_special_character_from_the_set),
                                    color = quaternaryColor,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        }
                        if (!passwordValueError.value) showPasswordErrorContent.value = false
                    }
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
                                phoneFieldValue.value.text.isNotEmpty() &&
                                phoneFieldValue.value.text.isNotBlank() &&
                                !phoneFieldError.value
                            ) {
                                if (!passwordValueError.value){
                                    isLoading.value = true
                                    viewModel.loginUser(
                                        LoginRequest(
                                            phonenumber = phoneFieldValue.value.text,
                                            password = passwordFieldValue.text
                                        )
                                    )
                                }else{
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