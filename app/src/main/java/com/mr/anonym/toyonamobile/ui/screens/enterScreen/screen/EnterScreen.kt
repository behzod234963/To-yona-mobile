package com.mr.anonym.toyonamobile.ui.screens.enterScreen.screen

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.mr.anonym.data.instance.local.DataStoreInstance
import com.mr.anonym.data.instance.local.SharedPreferencesInstance
import com.mr.anonym.toyonamobile.R
import com.mr.anonym.toyonamobile.presentation.managers.CheckConnection
import com.mr.anonym.toyonamobile.presentation.navigation.ScreensRouter
import com.mr.anonym.toyonamobile.ui.components.RectangleButton
import com.mr.anonym.toyonamobile.ui.screens.newPinScreen.components.EnterScreenDialog
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun EnterScreen(
    navController: NavController
) {

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    val sharedPreferences = SharedPreferencesInstance(context)
    val dataStore = DataStoreInstance(context)
    val connector = CheckConnection(context)

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
    val fiveColor = Color(101, 163, 119, 255)
    val sixColor = Color.Blue
    val systemSevenColor = if (isSystemInDarkTheme()) Color.Unspecified else Color.White
    val sevenColor = when {
        isSystemTheme -> systemSevenColor
        isDarkTheme -> Color.Unspecified
        else -> Color.White
    }

    val iosFont = FontFamily(Font(R.font.ios_font))

    val isBiometricAuthOn = sharedPreferences.getIsBiometricAuthOn()
    val showBiometricSettings = remember { mutableStateOf(false) }

    val pinCode = sharedPreferences.getPinCode()
    val pinValue = remember { mutableStateOf("") }
    val iconSize = remember { mutableIntStateOf(30) }

    val openSecurityContentState = dataStore.openSecurityContentState().collectAsState(false)
    val isConnected = connector.networkStatus.collectAsState(true)
    val connectionAnimation = rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.anim_no_internet_globus_version)
    )

    LaunchedEffect(pinValue.value) {
        if (
            pinValue.value.length > 3 &&
            pinValue.value == pinCode
        ) {
            if (openSecurityContentState.value) {
                navController.navigate(ScreensRouter.SecurityScreen.route) {
                    popUpTo(route = ScreensRouter.EnterScreen.route) {
                        inclusive = true
                    }
                }
                dataStore.openSecurityContent(false)
            } else {
                navController.navigate(ScreensRouter.MainScreen.route) {
                    popUpTo(route = ScreensRouter.EnterScreen.route) {
                        inclusive = true
                    }
                }
            }
        }
    }

    Scaffold(
        containerColor = primaryColor,
        contentColor = primaryColor
    ) { paddingValues ->
        if (isConnected.value) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.3f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom
                ) {
                    Text(
                        text = stringResource(R.string.enter_your_pin_code),
                        color = secondaryColor,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = iosFont
                    )
                    Spacer(Modifier.height(20.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .padding(horizontal = 20.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            modifier = Modifier
                                .size(iconSize.intValue.dp)
                                .padding(vertical = 7.dp),
                            painter = painterResource(R.drawable.ic_round),
                            tint = when {
                                pinValue.value.length == 4 && pinValue.value != pinCode -> {
                                    quaternaryColor
                                }

                                pinValue.value.isNotEmpty() -> {
                                    fiveColor
                                }

                                else -> {
                                    tertiaryColor
                                }
                            },
                            contentDescription = "null"
                        )
                        Icon(
                            modifier = Modifier
                                .size(iconSize.intValue.dp)
                                .padding(vertical = 7.dp),
                            painter = painterResource(R.drawable.ic_round),
                            tint = when {
                                pinValue.value.length == 4 && pinValue.value != pinCode -> {
                                    quaternaryColor
                                }

                                pinValue.value.length > 1 -> {
                                    fiveColor
                                }

                                else -> {
                                    tertiaryColor
                                }
                            },
                            contentDescription = "null"
                        )
                        Icon(
                            modifier = Modifier
                                .size(iconSize.intValue.dp)
                                .padding(vertical = 7.dp),
                            painter = painterResource(R.drawable.ic_round),
                            tint = when {
                                pinValue.value.length == 4 && pinValue.value != pinCode -> {
                                    quaternaryColor
                                }

                                pinValue.value.length > 2 -> {
                                    fiveColor
                                }

                                else -> {
                                    tertiaryColor
                                }
                            },
                            contentDescription = "null"
                        )
                        Icon(
                            modifier = Modifier
                                .size(iconSize.intValue.dp)
                                .padding(vertical = 7.dp),
                            painter = painterResource(R.drawable.ic_round),
                            tint = when {
                                pinValue.value.length == 4 && pinValue.value != pinCode -> {
                                    quaternaryColor
                                }

                                pinValue.value.length > 3 -> {
                                    fiveColor
                                }

                                else -> {
                                    tertiaryColor
                                }
                            },
                            contentDescription = "null"
                        )
                        Spacer(Modifier.height(10.dp))
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.99f),
                    verticalArrangement = Arrangement.Center
                ) {
//                123
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(70.dp)
                            .padding(horizontal = 20.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
//                    1
                        val oneInteractionSource = remember { MutableInteractionSource() }
                        val isOnePressed by oneInteractionSource.collectIsPressedAsState()
                        val oneScale by animateFloatAsState( if ( isOnePressed ) 0.90f else 1f )
                        RectangleButton(
                            buttonColor = tertiaryColor,
                            interactionSource = oneInteractionSource,
                            scale = oneScale,
                            onClick = {
                                if (pinValue.value.length < 4) {
                                    if (pinValue.value.isEmpty() && pinValue.value.isBlank()) {
                                        pinValue.value = "1"
                                    } else {
                                        pinValue.value += "1"
                                    }
                                }
                            }
                        ) {
                            Text(
                                text = "1",
                                color = secondaryColor,
                                fontSize = 25.sp,
                                fontWeight = FontWeight.SemiBold,
                                fontFamily = iosFont
                            )
                        }
//                    2
                        val twoInteractionSource = remember { MutableInteractionSource() }
                        val isTwoPressed by twoInteractionSource.collectIsPressedAsState()
                        val twoScale by animateFloatAsState( if ( isTwoPressed ) 0.90f else 1f )
                        RectangleButton(
                            buttonColor = tertiaryColor,
                            interactionSource = twoInteractionSource,
                            scale = twoScale,
                            onClick = {
                                if (pinValue.value.length < 4) {
                                    if (pinValue.value.isEmpty() && pinValue.value.isBlank()) {
                                        pinValue.value = "2"
                                    } else {
                                        pinValue.value += "2"
                                    }
                                }
                            }
                        ) {
                            Text(
                                text = "2",
                                color = secondaryColor,
                                fontSize = 25.sp,
                                fontWeight = FontWeight.SemiBold,
                                fontFamily = iosFont
                            )
                        }
//                    3
                        val threeInteractionSource = remember { MutableInteractionSource() }
                        val isThreePressed by threeInteractionSource.collectIsPressedAsState()
                        val threeScale by animateFloatAsState( if ( isThreePressed ) 0.90f else 1f )
                        RectangleButton(
                            buttonColor = tertiaryColor,
                            interactionSource = threeInteractionSource,
                            scale = threeScale,
                            onClick = {
                                if (pinValue.value.length < 4) {
                                    if (pinValue.value.isEmpty() && pinValue.value.isBlank()) {
                                        pinValue.value = "3"
                                    } else {
                                        pinValue.value += "3"
                                    }
                                }
                            }
                        ) {
                            Text(
                                text = "3",
                                color = secondaryColor,
                                fontSize = 25.sp,
                                fontWeight = FontWeight.SemiBold,
                                fontFamily = iosFont
                            )
                        }
                    }
                    Spacer(Modifier.height(30.dp))
//                456
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(70.dp)
                            .padding(horizontal = 20.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
//                    4
                        val fourInteractionSource = remember { MutableInteractionSource() }
                        val isFourPressed by fourInteractionSource.collectIsPressedAsState()
                        val fourScale by animateFloatAsState( if ( isFourPressed ) 0.90f else 1f )
                        RectangleButton(
                            buttonColor = tertiaryColor,
                            interactionSource = fourInteractionSource,
                            scale = fourScale,
                            onClick = {
                                if (pinValue.value.length < 4) {
                                    if (pinValue.value.isEmpty() && pinValue.value.isBlank()) {
                                        pinValue.value = "4"
                                    } else {
                                        pinValue.value += "4"
                                    }
                                }
                            }
                        ) {
                            Text(
                                text = "4",
                                color = secondaryColor,
                                fontSize = 25.sp,
                                fontWeight = FontWeight.SemiBold,
                                fontFamily = iosFont
                            )
                        }
//                    5
                        val fiveInteractionSource = remember { MutableInteractionSource() }
                        val isFivePressed by fiveInteractionSource.collectIsPressedAsState()
                        val fiveScale by animateFloatAsState( if ( isFivePressed ) 0.90f else 1f )
                        RectangleButton(
                            buttonColor = tertiaryColor,
                            interactionSource = fiveInteractionSource,
                            scale = fiveScale,
                            onClick = {
                                if (pinValue.value.length < 4) {
                                    if (pinValue.value.isEmpty() && pinValue.value.isBlank()) {
                                        pinValue.value = "5"
                                    } else {
                                        pinValue.value += "5"
                                    }
                                }
                            }
                        ) {
                            Text(
                                text = "5",
                                color = secondaryColor,
                                fontSize = 25.sp,
                                fontWeight = FontWeight.SemiBold,
                                fontFamily = iosFont
                            )
                        }
//                    6
                        val sixInteractionSource = remember { MutableInteractionSource() }
                        val isSixPressed by sixInteractionSource.collectIsPressedAsState()
                        val sixScale by animateFloatAsState( if ( isSixPressed ) 0.90f else 1f )
                        RectangleButton(
                            buttonColor = tertiaryColor,
                            interactionSource = sixInteractionSource,
                            scale = sixScale,
                            onClick = {
                                if (pinValue.value.length < 4) {
                                    if (pinValue.value.isEmpty() && pinValue.value.isBlank()) {
                                        pinValue.value = "6"
                                    } else {
                                        pinValue.value += "6"
                                    }
                                }
                            }
                        ) {
                            Text(
                                text = "6",
                                color = secondaryColor,
                                fontSize = 25.sp,
                                fontWeight = FontWeight.SemiBold,
                                fontFamily = iosFont
                            )
                        }
                    }
                    Spacer(Modifier.height(30.dp))
//                789
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(70.dp)
                            .padding(horizontal = 20.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
//                    7
                        val sevenInteractionSource = remember { MutableInteractionSource() }
                        val isSevenPressed by sevenInteractionSource.collectIsPressedAsState()
                        val sevenScale by animateFloatAsState( if ( isSevenPressed ) 0.90f else 1f )
                        RectangleButton(
                            buttonColor = tertiaryColor,
                            interactionSource = sevenInteractionSource,
                            scale = sevenScale,
                            onClick = {
                                if (pinValue.value.length < 4) {
                                    if (pinValue.value.isEmpty() && pinValue.value.isBlank()) {
                                        pinValue.value = "7"
                                    } else {
                                        pinValue.value += "7"
                                    }
                                }
                            }
                        ) {
                            Text(
                                text = "7",
                                color = secondaryColor,
                                fontSize = 25.sp,
                                fontWeight = FontWeight.SemiBold,
                                fontFamily = iosFont
                            )
                        }
//                    8
                        val eightInteractionSource = remember { MutableInteractionSource() }
                        val isEightPressed by eightInteractionSource.collectIsPressedAsState()
                        val eightScale by animateFloatAsState( if ( isEightPressed ) 0.90f else 1f )
                        RectangleButton(
                            buttonColor = tertiaryColor,
                            interactionSource = eightInteractionSource,
                            scale = eightScale,
                            onClick = {
                                if (pinValue.value.length < 4) {
                                    if (pinValue.value.isEmpty() && pinValue.value.isBlank()) {
                                        pinValue.value = "8"
                                    } else {
                                        pinValue.value += "8"
                                    }
                                }
                            }
                        ) {
                            Text(
                                text = "8",
                                color = secondaryColor,
                                fontSize = 25.sp,
                                fontWeight = FontWeight.SemiBold,
                                fontFamily = iosFont
                            )
                        }
//                    9
                        val nineInteractionSource = remember { MutableInteractionSource() }
                        val isNinePressed by nineInteractionSource.collectIsPressedAsState()
                        val nineScale by animateFloatAsState( if ( isNinePressed ) 0.90f else 1f )
                        RectangleButton(
                            buttonColor = tertiaryColor,
                            interactionSource = nineInteractionSource,
                            scale = nineScale,
                            onClick = {
                                if (pinValue.value.length < 4) {
                                    if (pinValue.value.isEmpty() && pinValue.value.isBlank()) {
                                        pinValue.value = "9"
                                    } else {
                                        pinValue.value += "9"
                                    }
                                }
                            }
                        ) {
                            Text(
                                text = "9",
                                color = secondaryColor,
                                fontSize = 25.sp,
                                fontWeight = FontWeight.SemiBold,
                                fontFamily = iosFont
                            )
                        }
                    }
                    Spacer(Modifier.height(30.dp))
//               fingerprint 0 clear
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(70.dp)
                            .padding(horizontal = 20.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
//                    fingerprint
                        val fingerInteractionSource = remember { MutableInteractionSource() }
                        val isFingerPressed by fingerInteractionSource.collectIsPressedAsState()
                        val fingerScale by animateFloatAsState( if ( isFingerPressed ) 0.90f else 1f )
                        RectangleButton(
                            buttonColor = primaryColor,
                            interactionSource = fingerInteractionSource,
                            scale = fingerScale,
                            onClick = {
                                if (isBiometricAuthOn) {
                                    coroutineScope.launch {
                                        dataStore.showBiometricAuthManually(true)
                                    }
                                    sharedPreferences.saveBiometricAuthState(true)
                                } else {
                                    showBiometricSettings.value = true
                                }
                            }
                        ) {
                            Icon(
                                modifier = Modifier
                                    .size(70.dp),
                                tint = secondaryColor,
                                painter = painterResource(R.drawable.ic_fingerprint),
                                contentDescription = "button fingerprint"
                            )
                        }
                        if (showBiometricSettings.value) {
                            EnterScreenDialog(
                                secondaryColor = secondaryColor,
                                tertiaryColor = tertiaryColor,
                                quaternaryColor = quaternaryColor,
                                sixColor = sixColor,
                                title = stringResource(R.string.allow_fingerprint),
                                fontFamily = iosFont,
                                confirmButton = {
                                    sharedPreferences.saveIsBiometricAuthOn(true)
                                    showBiometricSettings.value = false
                                },
                                dismissButton = {
                                    coroutineScope.launch {
                                        dataStore.showBiometricAuthManually(
                                            false
                                        )
                                    }
                                    sharedPreferences.saveIsBiometricAuthOn(false)
                                    showBiometricSettings.value = false
                                }
                            ) {
                                coroutineScope.launch {
                                    dataStore.showBiometricAuthManually(
                                        false
                                    )
                                }
                                sharedPreferences.saveIsBiometricAuthOn(false)
                                showBiometricSettings.value = false
                            }
                        }
//                    0
                        val zeroInteractionSource = remember { MutableInteractionSource() }
                        val isZeroPressed by zeroInteractionSource.collectIsPressedAsState()
                        val zeroScale by animateFloatAsState( if ( isZeroPressed ) 0.90f else 1f )
                        RectangleButton(
                            buttonColor = tertiaryColor,
                            interactionSource = zeroInteractionSource,
                            scale = zeroScale,
                            onClick = {
                                if (pinValue.value.length < 4) {
                                    if (pinValue.value.isEmpty() && pinValue.value.isBlank()) {
                                        pinValue.value = "0"
                                    } else {
                                        pinValue.value += "0"
                                    }
                                }
                            }
                        ) {
                            Text(
                                text = "0",
                                color = secondaryColor,
                                fontSize = 25.sp,
                                fontWeight = FontWeight.SemiBold,
                                fontFamily = iosFont
                            )
                        }
//                    button clear
                        val clearInteractionSource = remember { MutableInteractionSource() }
                        val isClearPressed by clearInteractionSource.collectIsPressedAsState()
                        val clearScale by animateFloatAsState( if ( isClearPressed ) 0.90f else 1f )
                        RectangleButton(
                            buttonColor = primaryColor,
                            interactionSource = clearInteractionSource,
                            scale = clearScale,
                            onClick = {
                                pinValue.value = pinValue.value.dropLast(1)
                            }
                        ) {
                            Icon(
                                modifier = Modifier.size(40.dp),
                                painter = painterResource(R.drawable.ic_backspace),
                                tint = secondaryColor,
                                contentDescription = "button backspace"
                            )
                        }
                    }
                    Spacer(Modifier.height(30.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        TextButton(
                            onClick = {
                                coroutineScope.launch {
                                    dataStore.isPinForgotten(true)
                                }
                                navController.navigate(ScreensRouter.LoginScreen.route)
                            }
                        ) {
                            Text(
                                text = stringResource(R.string.forgot_pin_code),
                                color = tertiaryColor,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold,
                                fontFamily = iosFont
                            )
                        }
                    }
                }
            }
        } else {
            Column (
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                LottieAnimation(
                    modifier = Modifier
                        .size(150.dp),
                    composition = connectionAnimation.value
                )
                Spacer(Modifier.height(10.dp))
                Card (
                    modifier = Modifier
                        .width(300.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = sevenColor,
                        contentColor = sevenColor
                    ),
                    shape = RoundedCornerShape(10.dp),
                    elevation = CardDefaults.cardElevation(7.dp)
                ){
                    Column (
                        modifier = Modifier
                            .padding(10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        Text(
                            text = stringResource(R.string.no_connection),
                            color = quaternaryColor,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center,
                            fontFamily = iosFont
                        )
                        Spacer(Modifier.height(10.dp))
                        Text(
                            text = stringResource(R.string.no_connection_instruction),
                            color = secondaryColor,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center,
                            fontFamily = iosFont
                        )
                    }
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.P)
@Preview
@Composable
private fun PreviewEnterScreen() {
    EnterScreen(NavController(LocalContext.current))
}