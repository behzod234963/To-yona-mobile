package com.mr.anonym.toyonamobile.ui.screens.numberCheckScreen.screen

import android.annotation.SuppressLint
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.mr.anonym.data.instance.local.DataStoreInstance
import com.mr.anonym.data.instance.local.SharedPreferencesInstance
import com.mr.anonym.domain.model.CardModel
import com.mr.anonym.toyonamobile.R
import com.mr.anonym.toyonamobile.presentation.extensions.phoneNumberTransformation
import com.mr.anonym.toyonamobile.presentation.navigation.ScreensRouter
import com.mr.anonym.toyonamobile.presentation.utils.Arguments
import com.mr.anonym.toyonamobile.ui.components.ButtonPressableEffect
import com.mr.anonym.toyonamobile.ui.screens.numberCheckScreen.components.OTPField
import com.mr.anonym.toyonamobile.ui.screens.numberCheckScreen.viewModel.NumberCheckViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun NumberCheckScreen(
    arguments: Arguments,
    navController: NavController,
    viewModel: NumberCheckViewModel = hiltViewModel()
) {
//    Context
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

//    Object
    val sharedPreferences = SharedPreferencesInstance(context)
    val dataStore = DataStoreInstance(context)

//    Boolean
    val isDarkTheme = sharedPreferences.getDarkThemeState()
    val isSystemTheme = sharedPreferences.getSystemThemeState()

//    Color
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
    val fiveColor = Color(101, 163, 119, 255)
    val systemNineColor = if (isSystemInDarkTheme()) Color(0xFF222327) else Color(0xFFF1F2F4)
    val nineColor = when{
        isSystemTheme -> systemNineColor
        isDarkTheme -> Color(0xFF222327)
        else -> Color(0xFFF1F2F4)
    }

    val iosFont = FontFamily(Font(R.font.ios_font))

//    State
    val snackbarHostState = remember { SnackbarHostState() }

    val otpValue = remember { mutableStateOf("") }
    val correctValue = remember { mutableStateOf("11111") }

    val timeLeft = remember { mutableIntStateOf(60) }
    val isRunning = remember { mutableStateOf(true) }

    val addCardProcess = sharedPreferences.addCardProcessState()
    val isPasswordForgotten = dataStore.isPasswordForgottenState().collectAsState(false)
    val isPinForgotten = dataStore.isPinForgottenState().collectAsState(false)
    val isOldUserState = dataStore.isOldUserState().collectAsState(false)
    val addCardFromDetails = sharedPreferences.addCardFromDetailsState()
    val addCardFromParty = sharedPreferences.addCardFromAddEventState()
    val partyIndex = sharedPreferences.getPartyIndex()
    val detailIndex = sharedPreferences.getDetailIndex()

    val cardNumber = sharedPreferences.getCardNumber()
//    val cardHolder = dataStore.getCardHolder().collectAsState("")
    val expiryDate = dataStore.getExpiryDate().collectAsState("")
    val cardID = dataStore.getCardID().collectAsState(-1)

    val isLoading = remember { mutableStateOf(false) }

    val isCardAdded = viewModel.isCardAdded
    val isCardUpdated = viewModel.isCardUpdated

    val loadingAnimation = rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.anim_loading)
    )

    val buttonContinueInteractionSource = remember { MutableInteractionSource() }
    val isButtonContinuePressed by buttonContinueInteractionSource.collectIsPressedAsState()
    val buttonContinueScale by animateFloatAsState( if ( isButtonContinuePressed ) 0.85f else 0.90f )

    LaunchedEffect(isRunning.value, timeLeft.intValue) {
        while (isRunning.value && timeLeft.intValue > 0) {
            delay(1000L)
            timeLeft.intValue--
        }
        if (timeLeft.intValue <= 0) isRunning.value = false
    }

    Scaffold(
        containerColor = nineColor,
        contentColor = nineColor,
        modifier = Modifier
            .imePadding(),
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        if (!isLoading.value) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(10.dp),
            ) {
//                Instruction
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.25f)
                        .background(color = primaryColor, shape = RoundedCornerShape(15.dp))
                        .padding(10.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(R.string.log_in),
                        color = secondaryColor,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = iosFont
                    )
                    Spacer(Modifier.height(10.dp))
                    Text(
                        textAlign = TextAlign.Center,
                        text = stringResource(
                            R.string.enter_code_below,
                            arguments.number.phoneNumberTransformation()
                        ),
                        color = secondaryColor,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = iosFont
                    )
                    Spacer(Modifier.height(10.dp))
                }
                Spacer(Modifier.height(10.dp))
//                OTP field
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .background(color = primaryColor, shape = RoundedCornerShape(15.dp))
                        .padding(10.dp)
                ) {
                    Spacer(Modifier.height(20.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        OTPField(
                            textColor = if ( otpValue.value.length == 5 ) fiveColor else secondaryColor,
                            borderColor = if ( otpValue.value.length == 5 ) fiveColor else secondaryColor,
                            value = otpValue.value,
                            fontFamily = iosFont,
                            onSend = {
                                if (
                                    otpValue.value.isNotEmpty() &&
                                    otpValue.value.isNotBlank() &&
                                    otpValue.value == correctValue.value
                                ) {
                                    when {
                                        addCardProcess -> {
                                            if (cardID.value == -1) {
                                                isLoading.value = true
                                                viewModel.addCard(
                                                    cardModel = CardModel(
                                                        number = cardNumber ?: "",
                                                        date = expiryDate.value
                                                    )
                                                )

                                            } else {
                                                isLoading.value = true
                                                viewModel.updateCard(
                                                    cardID = cardID.value,
                                                    cardModel = CardModel(
                                                        number = cardNumber ?: "",
                                                        date = expiryDate.value
                                                    )
                                                )
                                            }
                                        }

                                        isPasswordForgotten.value -> {
//                                            val result = arguments.number.substring(4..arguments.number.length - 1)
//                                            sharedPreferences.savePhoneNumber(result)
//                                            navController.navigate(ScreensRouter.RegistrationScreen.route) {
//                                                popUpTo(ScreensRouter.NumberCheckScreen.route) {
//                                                    inclusive = true
//                                                }
//                                            }
                                        }

                                        isPinForgotten.value -> {
                                            sharedPreferences.savePhoneNumber(arguments.number)
                                            sharedPreferences.saveNewPinState(true)
                                            navController.navigate(ScreensRouter.NewPinScreen.route) {
                                                popUpTo(ScreensRouter.NumberCheckScreen.route) {
                                                    inclusive = true
                                                }
                                            }
                                        }

                                        else -> {
                                            if (isOldUserState.value) {
                                                sharedPreferences.saveIsLoggedIn(true)
                                                sharedPreferences.saveIsProfileSettingsState(true)
                                                viewModel.getUserByID()
                                                navController.navigate(ScreensRouter.ProfileScreen.route) {
                                                    popUpTo(ScreensRouter.NumberCheckScreen.route) {
                                                        inclusive = true
                                                    }
                                                }
                                            } else {
                                                viewModel.loginUser()
                                                isLoading.value = true
                                            }
                                        }
                                    }
                                } else {
                                    coroutineScope.launch {
                                        snackbarHostState.showSnackbar(
                                            message = context.getString(R.string.please_complete_the_process)
                                        )
                                    }
                                }
                            }
                        ) {
                            if (it.length <= 5 && it.isDigitsOnly()) {
                                otpValue.value = it
                            }
                        }
                    }
                    Spacer(Modifier.height(20.dp))
                    ButtonPressableEffect(
                        buttonColor = fiveColor,
                        interactionSource = buttonContinueInteractionSource,
                        scale = buttonContinueScale,
                        onClick = {
                            if (
                                otpValue.value.isNotEmpty() &&
                                otpValue.value.isNotBlank() &&
                                otpValue.value == correctValue.value
                            ) {
                                when {
                                    addCardProcess -> {
                                        if (cardID.value == -1) {
                                            isLoading.value = true
                                            viewModel.addCard(
                                                cardModel = CardModel(
                                                    number = cardNumber ?: "",
                                                    date = expiryDate.value
                                                )
                                            )

                                        } else {
                                            isLoading.value = true
                                            viewModel.updateCard(
                                                cardID = cardID.value,
                                                cardModel = CardModel(
                                                    number = cardNumber ?: "",
                                                    date = expiryDate.value
                                                )
                                            )
                                        }
                                    }

                                    isPasswordForgotten.value -> {
//                                            val result = arguments.number.substring(4..arguments.number.length - 1)
//                                            sharedPreferences.savePhoneNumber(result)
//                                            navController.navigate(ScreensRouter.RegistrationScreen.route) {
//                                                popUpTo(ScreensRouter.NumberCheckScreen.route) {
//                                                    inclusive = true
//                                                }
//                                            }
                                    }

                                    isPinForgotten.value -> {
                                        sharedPreferences.savePhoneNumber(arguments.number)
                                        sharedPreferences.saveNewPinState(true)
                                        navController.navigate(ScreensRouter.NewPinScreen.route) {
                                            popUpTo(ScreensRouter.NumberCheckScreen.route) {
                                                inclusive = true
                                            }
                                        }
                                    }

                                    else -> {
                                        if (isOldUserState.value) {
                                            sharedPreferences.saveIsLoggedIn(true)
                                            sharedPreferences.saveIsProfileSettingsState(true)
                                            viewModel.getUserByID()
                                            navController.navigate(ScreensRouter.ProfileScreen.route) {
                                                popUpTo(ScreensRouter.NumberCheckScreen.route) {
                                                    inclusive = true
                                                }
                                            }
                                        } else {
                                            viewModel.loginUser()
                                            isLoading.value = true
                                        }
                                    }
                                }
                            } else {
                                coroutineScope.launch {
                                    snackbarHostState.showSnackbar(
                                        message = context.getString(R.string.please_complete_the_process)
                                    )
                                }
                            }
                        }
                    ) {
                        Text(
                            text = stringResource(R.string.Enter),
                            color = Color.White,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            fontFamily = iosFont
                        )
                    }
                    Spacer(Modifier.height(5.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        TextButton(
                            onClick = {
                                navController.navigate(ScreensRouter.LoginScreen.route){
                                    popUpTo(ScreensRouter.NumberCheckScreen.route + "/${arguments.number}")
                                }
                            }
                        ) {
                            Text(
                                text = stringResource(R.string.change_number),
                                color = nineColor,
                                fontSize = 16.sp,
                                fontFamily = iosFont
                            )
                        }
                        IconButton(
                            onClick = {
                                navController.navigate(ScreensRouter.LoginScreen.route){
                                    popUpTo(ScreensRouter.NumberCheckScreen.route + "/${arguments.number}")
                                }
                            }
                        ) {
                            Icon(
                                modifier = Modifier
                                    .size(20.dp),
                                painter = painterResource(R.drawable.ic_edit),
                                tint = nineColor,
                                contentDescription = "change number"
                            )
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(20.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(R.string.send_again),
                            color = nineColor,
                            fontSize = 16.sp,
                            fontFamily = iosFont
                        )
                        Spacer(Modifier.width(10.dp))
                        if (!isRunning.value && timeLeft.intValue == 0) {
                            IconButton(
                                onClick = {
                                    isRunning.value = true
                                    timeLeft.intValue = 60
                                    otpValue.value = ""
                                }
                            ) {
                                Icon(
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .padding(top = 2.dp),
                                    painter = painterResource(R.drawable.ic_refresh),
                                    tint = nineColor,
                                    contentDescription = "null"
                                )
                            }
                        } else {
                            Text(
                                text = timeLeft.intValue.toString(),
                                color = nineColor,
                                fontSize = 16.sp,
                                fontFamily = iosFont
                            )
                        }
                    }
                    Spacer(Modifier.height(10.dp))
                }
            }
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                LottieAnimation(
                    modifier = Modifier
                        .size(150.dp),
                    composition = loadingAnimation.value,
                    restartOnPlay = true,
                    iterations = LottieConstants.IterateForever
                )
                when {
                    addCardProcess -> {
                        when {
                            addCardFromDetails->{
                                if (isCardUpdated.value)  {
                                    coroutineScope.launch {
                                        dataStore.saveCardID(-1)
                                        dataStore.saveExpiryDate("")
                                        sharedPreferences.saveCardNumber("")
                                        sharedPreferences.addCardProcess(false)
                                        delay(1500)
                                        isLoading.value = false
                                        withContext(Dispatchers.Main) {
                                            navController.navigate(ScreensRouter.DetailsScreen.route + "/${detailIndex}") {
                                                popUpTo(ScreensRouter.NumberCheckScreen.route) {
                                                    inclusive = true
                                                }
                                            }
                                        }
                                        sharedPreferences.detailIndex(-1)
                                    }
                                }
                                if (isCardAdded.value) {
                                    coroutineScope.launch {
                                        dataStore.saveCardID(-1)
                                        dataStore.saveExpiryDate("")
                                        sharedPreferences.saveCardNumber("")
                                        sharedPreferences.addCardProcess(false)
                                        delay(1500)
                                        isLoading.value = false
                                        withContext(Dispatchers.Main) {
                                            navController.navigate(ScreensRouter.DetailsScreen.route + "/${detailIndex}") {
                                                popUpTo(ScreensRouter.NumberCheckScreen.route) {
                                                    inclusive = true
                                                }
                                            }
                                        }
                                        sharedPreferences.detailIndex(-1)
                                    }
                                }
                            }
                            addCardFromParty->{
                                if (isCardUpdated.value)  {
                                    coroutineScope.launch {
                                        dataStore.saveCardID(-1)
                                        dataStore.saveExpiryDate("")
                                        sharedPreferences.saveCardNumber("")
                                        sharedPreferences.addCardProcess(false)
                                        delay(1500)
                                        isLoading.value = false
                                        withContext(Dispatchers.Main) {
                                            navController.navigate(ScreensRouter.AddPartyScreen.route + "/${partyIndex}") {
                                                popUpTo(ScreensRouter.NumberCheckScreen.route) {
                                                    inclusive = true
                                                }
                                            }
                                        }
                                        sharedPreferences.partyIndex(-1)
                                    }
                                }
                                if (isCardAdded.value) {
                                    coroutineScope.launch {
                                        dataStore.saveCardID(-1)
                                        dataStore.saveExpiryDate("")
                                        sharedPreferences.saveCardNumber("")
                                        sharedPreferences.addCardProcess(false)
                                        delay(1500)
                                        isLoading.value = false
                                        withContext(Dispatchers.Main) {
                                            navController.navigate(ScreensRouter.AddPartyScreen.route + "/${partyIndex}") {
                                                popUpTo(ScreensRouter.NumberCheckScreen.route) {
                                                    inclusive = true
                                                }
                                            }
                                        }
                                        sharedPreferences.partyIndex(-1)
                                    }
                                }
                            }
                            else -> {
                                if (isCardUpdated.value)  {
                                    coroutineScope.launch {
                                        dataStore.saveCardID(-1)
                                        dataStore.saveExpiryDate("")
                                        sharedPreferences.saveCardNumber("")
                                        sharedPreferences.addCardProcess(false)
                                        delay(1500)
                                        isLoading.value = false
                                        withContext(Dispatchers.Main) {
                                            navController.navigate(ScreensRouter.WalletScreen.route) {
                                                popUpTo(ScreensRouter.NumberCheckScreen.route) {
                                                    inclusive = true
                                                }
                                            }
                                        }
                                    }
                                }
                                if (isCardAdded.value) {
                                    coroutineScope.launch {
                                        dataStore.saveCardID(-1)
                                        dataStore.saveExpiryDate("")
                                        sharedPreferences.saveCardNumber("")
                                        sharedPreferences.addCardProcess(false)
                                        delay(1500)
                                        isLoading.value = false
                                        withContext(Dispatchers.Main) {
                                            navController.navigate(ScreensRouter.WalletScreen.route) {
                                                popUpTo(ScreensRouter.NumberCheckScreen.route) {
                                                    inclusive = true
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    !isOldUserState.value ->{
                        coroutineScope.launch {
                            delay(3000)
                            if (viewModel.isLoginSuccess.value){
                                viewModel.decodeToken()
                                sharedPreferences.saveIsLoggedIn(true)
                                sharedPreferences.saveIsProfileSettingsState(true)
                                delay(2000)
                                navController.navigate(ScreensRouter.ProfileScreen.route) {
                                    popUpTo(ScreensRouter.NumberCheckScreen.route) {
                                        inclusive = true
                                    }
                                }
                                isLoading.value = false
                            }else{
                                snackbarHostState.showSnackbar(
                                    context.getString(R.string.couldn_t_log_in)
                                )
                                isLoading.value = false
                            }
                        }
                    }
                }
            }
        }
    }
}