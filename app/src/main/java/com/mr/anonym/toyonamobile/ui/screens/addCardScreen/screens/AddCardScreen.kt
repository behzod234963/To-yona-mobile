package com.mr.anonym.toyonamobile.ui.screens.addCardScreen.screens

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.mr.anonym.data.instance.local.DataStoreInstance
import com.mr.anonym.data.instance.local.SharedPreferencesInstance
import com.mr.anonym.toyonamobile.R
import com.mr.anonym.toyonamobile.presentation.event.CardEvents
import com.mr.anonym.toyonamobile.presentation.extensions.cardNumberSeparator
import com.mr.anonym.toyonamobile.presentation.managers.cardScannerIO
import com.mr.anonym.toyonamobile.presentation.managers.startScanning
import com.mr.anonym.toyonamobile.presentation.navigation.ScreensRouter
import com.mr.anonym.toyonamobile.presentation.utils.Arguments
import com.mr.anonym.toyonamobile.ui.components.HorizontalButton
import com.mr.anonym.toyonamobile.ui.screens.addCardScreen.components.AddCardTopBar
import com.mr.anonym.toyonamobile.ui.screens.addCardScreen.components.CardFields
import com.mr.anonym.toyonamobile.ui.screens.addCardScreen.components.ColorContent
import com.mr.anonym.toyonamobile.ui.screens.addCardScreen.viewModel.AddCardViewModel
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddCardScreen(
    arguments: Arguments,
    navController: NavController,
    viewModel: AddCardViewModel = hiltViewModel()
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
    val fiveColor = Color(101, 163, 119, 255)
    val systemSevenColor = if (isSystemInDarkTheme()) Color.Unspecified else Color.White
    val sevenColor = when {
        isSystemTheme -> systemSevenColor
        isDarkTheme -> Color.Unspecified
        else -> Color.White
    }
    val systemEightColor = if (isSystemInDarkTheme()) MaterialTheme.colorScheme.background else Color.LightGray
    val eightColor = when {
        isSystemTheme -> systemEightColor
        isDarkTheme -> MaterialTheme.colorScheme.background
        else -> Color.LightGray
    }

    //    Card gradients
//    1.
    val greenGradient = Brush.linearGradient(
        colors = listOf(
            Color(0xFF00C853),
            Color(0xFFB2FF59)
        )
    )
//    2.
    val redGradient = Brush.linearGradient(
        colors = listOf(
            Color(0xFFFF1744),
            Color(0xFFFF8A80)
        )
    )
//    3.
    val purpleGradient = Brush.linearGradient(
        colors = listOf(
            Color(0xFF7C4DFF),
            Color(0xFFB388FF)
        )
    )
//    4.
    val blueGradient = Brush.linearGradient(
        colors = listOf(
            Color(0xFF00B0FF),
            Color(0xFF40C4FF)
        )
    )
//    5.
    val orangeGradient = Brush.linearGradient(
        colors = listOf(
            Color(0xFFFF6D00),
            Color(0xFFFFD600)
        )
    )
//    6.
    val blackGoldGradient = Brush.linearGradient(
        colors = listOf(
            Color(0xFF212121),
            Color(0xFFFFD740)
        )
    )

    val iosFont = FontFamily(Font(R.font.ios_font))

    val isScanned = rememberSaveable { mutableStateOf(false) }
    val cardNumberValue = viewModel.cardNumber
    val cardValueError = rememberSaveable { mutableStateOf(false) }
    val expiryDateValue = viewModel.expiryDate
    val cardDateError = rememberSaveable { mutableStateOf(false) }

    val colorIndex = remember { mutableIntStateOf( 5 ) }

    val user = viewModel.user

    val launcher = cardScannerIO { card ->
        viewModel.cardEvents(CardEvents.ChangeCardNumber(card.number))
        viewModel.cardEvents(CardEvents.ChangeExpiryDate(card.date))
    }
    val snackbarHostState = remember { SnackbarHostState() }

    val greenInteractionSource = remember { MutableInteractionSource() }
    val isGreenPressed by greenInteractionSource.collectIsPressedAsState()
    val greenScale by animateFloatAsState( if ( isGreenPressed ) 0.90f else 1f )
    val isGreenSelected = remember { mutableStateOf( true ) }

    val redInteractionSource = remember { MutableInteractionSource() }
    val isRedPressed by redInteractionSource.collectIsPressedAsState()
    val redScale by animateFloatAsState( if ( isRedPressed ) 0.90f else 1f )
    val isRedSelected = remember { mutableStateOf( false ) }

    val purpleInteractionSource = remember { MutableInteractionSource() }
    val isPurplePressed by purpleInteractionSource.collectIsPressedAsState()
    val purpleScale by animateFloatAsState( if ( isPurplePressed ) 0.90f else 1f )
    val isPurpleSelected = remember { mutableStateOf( false ) }

    val blueInteractionSource = remember { MutableInteractionSource() }
    val isBluePressed by blueInteractionSource.collectIsPressedAsState()
    val blueScale by animateFloatAsState( if ( isBluePressed ) 0.90f else 1f )
    val isBlueSelected = remember { mutableStateOf( false ) }

    val orangeInteractionSource = remember { MutableInteractionSource() }
    val isOrangePressed by orangeInteractionSource.collectIsPressedAsState()
    val orangeScale by animateFloatAsState( if ( isOrangePressed ) 0.90f else 1f )
    val isOrangeSelected = remember { mutableStateOf( false ) }

    val blackInteractionSource = remember { MutableInteractionSource() }
    val isBlackPressed by blackInteractionSource.collectIsPressedAsState()
    val blackScale by animateFloatAsState( if ( isBlackPressed ) 0.90f else 1f )
    val isBlackSelected = remember { mutableStateOf( false) }

    val buttonContinueInteractionSource = remember { MutableInteractionSource() }
    val isButtonContinuePressed by buttonContinueInteractionSource.collectIsPressedAsState()
    val buttonContinueScale by animateFloatAsState( if ( isButtonContinuePressed ) 0.90f else 1f )

    BackHandler (
        enabled = true
    ){
        navController.navigateUp()
    }
    Scaffold(
        modifier = Modifier
            .imePadding(),
        containerColor = primaryColor,
        contentColor = primaryColor,
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            AddCardTopBar(
                primaryColor = primaryColor,
                secondaryColor = secondaryColor,
                fontFamily = iosFont,
            ) {
                navController.navigateUp()
            }
        }
    ) { paddingValues ->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .background(sevenColor)
                .padding(paddingValues)
                .padding(10.dp)
        ){
//            Add card field
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(230.dp)
                    .background(
                        brush = when (colorIndex.intValue) {
                            1 -> greenGradient
                            2 -> redGradient
                            3 -> purpleGradient
                            4 -> blueGradient
                            5 -> orangeGradient
                            6 -> blackGoldGradient
                            else -> greenGradient
                        },
                        shape = RoundedCornerShape(10.dp)
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CardFields(
                    secondaryColor = secondaryColor,
                    tertiaryColor = tertiaryColor,
                    eightColor = eightColor,
                    fontFamily = iosFont,
                    cardValue = cardNumberValue.value,
                    isScanned = isScanned.value,
                    columnModifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    onCardValueChange = {
                        isScanned.value = false
                        viewModel.cardEvents(CardEvents.ChangeCardNumber(it.take(16)))
                    },
                    cardFieldError = cardValueError.value,
                    cardFieldTrailingClick = {
                        isScanned.value = true
                        startScanning(context, launcher)
                    },
                    cardDateValue = expiryDateValue.value,
                    onCardDateValueChange = {
                        viewModel.cardEvents(CardEvents.ChangeExpiryDate(it.take(4)))
                    },
                    cardDateError = cardDateError.value,
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
//                    1
                    ColorContent(
                        color = fiveColor,
                        brush = greenGradient,
                        scale = greenScale,
                        interactionSource = greenInteractionSource,
                        isSelected = isGreenSelected.value,
                        onClick = {
                            colorIndex.intValue = 1
                            isGreenSelected.value = true
                            isRedSelected.value = false
                            isPurpleSelected.value = false
                            isBlueSelected.value = false
                            isOrangeSelected.value = false
                            isBlackSelected.value = false
                        },
                    )
//                    2
                    ColorContent(
                        color = fiveColor,
                        brush = redGradient,
                        scale = redScale,
                        interactionSource = redInteractionSource,
                        isSelected = isRedSelected.value,
                        onClick = {
                            colorIndex.intValue = 2
                            isGreenSelected.value = false
                            isRedSelected.value = true
                            isPurpleSelected.value = false
                            isBlueSelected.value = false
                            isOrangeSelected.value = false
                            isBlackSelected.value = false
                        },
                    )
//                    3
                    ColorContent(
                        color = fiveColor,
                        brush = purpleGradient,
                        scale = purpleScale,
                        interactionSource = purpleInteractionSource,
                        isSelected = isPurpleSelected.value,
                        onClick = {
                            colorIndex.intValue = 3
                            isGreenSelected.value = false
                            isRedSelected.value = false
                            isPurpleSelected.value = true
                            isBlueSelected.value = false
                            isOrangeSelected.value = false
                            isBlackSelected.value = false
                        },
                    )
//                    4
                    ColorContent(
                        color = fiveColor,
                        brush = blueGradient,
                        scale = blueScale,
                        interactionSource = blueInteractionSource,
                        isSelected = isBlueSelected.value,
                        onClick = {
                            colorIndex.intValue = 4
                            isGreenSelected.value = false
                            isRedSelected.value = false
                            isPurpleSelected.value = false
                            isBlueSelected.value = true
                            isOrangeSelected.value = false
                            isBlackSelected.value = false
                        },
                    )
//                    5
                    ColorContent(
                        color = fiveColor,
                        brush = orangeGradient,
                        scale = orangeScale,
                        interactionSource = orangeInteractionSource,
                        isSelected = isOrangeSelected.value,
                        onClick = {
                            colorIndex.intValue = 5
                            isGreenSelected.value = false
                            isRedSelected.value = false
                            isPurpleSelected.value = false
                            isBlueSelected.value = false
                            isOrangeSelected.value = true
                            isBlackSelected.value = false
                        },
                    )
//                    6
                    ColorContent(
                        color = fiveColor,
                        brush = blackGoldGradient,
                        scale = blackScale,
                        interactionSource = blackInteractionSource,
                        isSelected = isBlackSelected.value,
                        onClick = {
                            colorIndex.intValue = 6
                            isGreenSelected.value = false
                            isRedSelected.value = false
                            isPurpleSelected.value = false
                            isBlueSelected.value = false
                            isOrangeSelected.value = false
                            isBlackSelected.value = true
                        },
                    )
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(horizontal = 16.dp, vertical = 16.dp),
                verticalArrangement = Arrangement.Bottom,
            ) {
//                Add card
                HorizontalButton(
                    buttonColor = fiveColor,
                    interactionSource = buttonContinueInteractionSource,
                    scale = buttonContinueScale,
                    onClick = {
                        isScanned.value = true
                        if (
                            cardNumberValue.value.isNotEmpty() &&
                            cardNumberValue.value.isNotBlank() &&
                            !cardValueError.value &&
                            expiryDateValue.value.isNotEmpty() &&
                            expiryDateValue.value.isNotBlank() &&
                            !cardDateError.value
                        ){
                            val first = expiryDateValue.value.take(2)
                            val last = expiryDateValue.value.takeLast(2)
                            val formatted = cardNumberValue.value.cardNumberSeparator()
                            if (arguments.cardId == -1){
                                sharedPreferences.addCardProcess(true)
                                sharedPreferences.saveCardNumber(formatted)
                                sharedPreferences.saveCardColorIndex(colorIndex.intValue)
                                coroutineScope.launch {
                                    dataStore.saveCardID(-1)
                                    dataStore.saveExpiryDate("$first/$last")
                                }
                                navController.navigate(ScreensRouter.NumberCheckScreen.route + "/+998${user.value.phonenumber}"){
                                    popUpTo(ScreensRouter.AddCardScreen.route){ inclusive = true }
                                }
                            }else{
                                sharedPreferences.addCardProcess(true)
                                sharedPreferences.saveCardNumber(formatted)
                                coroutineScope.launch {
                                    dataStore.saveCardID(arguments.cardId)
                                    dataStore.saveExpiryDate("$first/$last")
                                }
                                navController.navigate(ScreensRouter.NumberCheckScreen.route + "/${user.value.phonenumber}"){
                                    popUpTo(ScreensRouter.AddCardScreen.route){ inclusive = true }
                                }
                            }
                        }else{
                            coroutineScope.launch {
                                snackbarHostState.showSnackbar(
                                    message = context.getString(R.string.please_check_validate_places)
                                )
                            }
                        }
                    },
                    content = {
                        Row (
                            modifier = Modifier
                                .fillMaxSize(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ){
                            Icon(
                                painter = painterResource(R.drawable.ic_add_circle),
                                tint = Color.White,
                                contentDescription = ""
                            )
                            Spacer(Modifier.width(10.dp))
                            Text(
                                text = stringResource(R.string.add_cart),
                                color = Color.White,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold,
                                fontFamily = iosFont
                            )
                        }
                    }
                )
            }
        }
    }
}