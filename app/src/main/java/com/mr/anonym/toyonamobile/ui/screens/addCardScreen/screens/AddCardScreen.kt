package com.mr.anonym.toyonamobile.ui.screens.addCardScreen.screens

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mr.anonym.data.instance.local.DataStoreInstance
import com.mr.anonym.data.instance.local.SharedPreferencesInstance
import com.mr.anonym.toyonamobile.R
import com.mr.anonym.toyonamobile.presentation.event.CardEvents
import com.mr.anonym.toyonamobile.presentation.extensions.cardDateTransformation
import com.mr.anonym.toyonamobile.presentation.extensions.cardNumberSeparator
import com.mr.anonym.toyonamobile.presentation.navigation.ScreensRouter
import com.mr.anonym.toyonamobile.presentation.utils.Arguments
import com.mr.anonym.toyonamobile.presentation.managers.cardScannerIO
import com.mr.anonym.toyonamobile.presentation.managers.startScanning
import com.mr.anonym.toyonamobile.ui.screens.addCardScreen.components.AddCardTopBar
import com.mr.anonym.toyonamobile.ui.screens.addCardScreen.components.CardFields
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
    val quaternaryColor = Color.Red
    val fiveColor = Color(101, 163, 119, 255)
    val systemSevenrdColor = if (isSystemInDarkTheme()) Color.Unspecified else Color.White
    val sevenrdColor = when {
        isSystemTheme -> systemSevenrdColor
        isDarkTheme -> Color.Unspecified
        else -> Color.White
    }
    val systemEightrdColor = if (isSystemInDarkTheme()) MaterialTheme.colorScheme.background else Color.LightGray
    val eightrdColor = when {
        isSystemTheme -> systemEightrdColor
        isDarkTheme -> MaterialTheme.colorScheme.background
        else -> Color.LightGray
    }

    //    Card gradients
//    1.
    val greenGradient = Brush.linearGradient(
        colors = listOf(
            Color(0xFF00C853), // ярко-зелёный
            Color(0xFFB2FF59)  // светло-зелёный
        )
    )
//    2.
    val redGradient = Brush.linearGradient(
        colors = listOf(
            Color(0xFFFF1744), // насыщенный красный
            Color(0xFFFF8A80)  // мягкий коралловый
        )
    )
//    3.
    val purpleGradient = Brush.linearGradient(
        colors = listOf(
            Color(0xFF7C4DFF), // насыщенный фиолетовый
            Color(0xFFB388FF)  // светлый лавандовый
        )
    )
//    4.
    val blueGradient = Brush.linearGradient(
        colors = listOf(
            Color(0xFF00B0FF), // небесно-голубой
            Color(0xFF40C4FF)  // яркий бирюзовый
        )
    )
//    5.
    val orangeGradient = Brush.linearGradient(
        colors = listOf(
            Color(0xFFFF6D00), // оранжевый
            Color(0xFFFFD600)  // солнечный жёлтый
        )
    )
//    6.
    val blackGoldGradient = Brush.linearGradient(
        colors = listOf(
            Color(0xFF212121), // тёмный графит
            Color(0xFFFFD740)  // золотистый акцент
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
                onNavigationClick = {
                    navController.navigateUp()
                }
            )
        }
    ) { paddingValues ->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .background(sevenrdColor)
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
                    eightrdColor = eightrdColor,
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
//                Spacer(Modifier.height(30.dp))
//                Text(
//                    text = cardNumberValue.value.cardNumberSeparator(),
//                    fontSize = 22.sp,
//                    color = secondaryColor,
//                    fontFamily = iosFont
//                )
//                Spacer(Modifier.height(10.dp))
//                Text(
//                    modifier = Modifier
//                        .fillMaxWidth(),
//                    text = expiryDateValue.value.cardDateTransformation(),
//                    fontSize = 22.sp,
//                    color = secondaryColor,
//                    fontFamily = iosFont,
//                    textAlign = TextAlign.Center
//                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(horizontal = 16.dp, vertical = 16.dp),
                verticalArrangement = Arrangement.Bottom,
            ) {
//                Add card
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = quaternaryColor,
                        contentColor = quaternaryColor
                    ),
                    shape = RoundedCornerShape(10.dp),
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
                    }
                ) {
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
                        )
                    }
                }
            }
        }
    }
}