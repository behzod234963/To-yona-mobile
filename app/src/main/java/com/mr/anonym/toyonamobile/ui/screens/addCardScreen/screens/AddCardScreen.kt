package com.mr.anonym.toyonamobile.ui.screens.addCardScreen.screens

import android.annotation.SuppressLint
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mr.anonym.data.instance.local.DataStoreInstance
import com.mr.anonym.data.instance.local.SharedPreferencesInstance
import com.mr.anonym.toyonamobile.R
import com.mr.anonym.toyonamobile.presentation.event.CardEvents
import com.mr.anonym.toyonamobile.presentation.extensions.cardHolderChecker
import com.mr.anonym.toyonamobile.presentation.extensions.cardNumberSeparator
import com.mr.anonym.toyonamobile.presentation.navigation.ScreensRouter
import com.mr.anonym.toyonamobile.presentation.utils.Arguments
import com.mr.anonym.toyonamobile.presentation.utils.CardScannerIO
import com.mr.anonym.toyonamobile.presentation.utils.startScanning
import com.mr.anonym.toyonamobile.ui.screens.addCardScreen.components.AddCardTopBar
import com.mr.anonym.toyonamobile.ui.screens.addCardScreen.components.CardFields
import com.mr.anonym.toyonamobile.ui.screens.addCardScreen.viewModel.AddCardViewModel
import kotlinx.coroutines.launch
import java.util.Locale.getDefault

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
    val quaternaryColor = Color.Red
    val systemSevenrdColor = if (isSystemInDarkTheme()) Color.Unspecified else Color.White
    val sevenrdColor = when {
        isSystemTheme -> systemSevenrdColor
        isDarkTheme -> Color.Unspecified
        else -> Color.White
    }

    val isScanned = rememberSaveable { mutableStateOf(false) }
    val cardNumberValue = viewModel.cardNumber
    val cardValueError = rememberSaveable { mutableStateOf(false) }
    val expiryDateValue = viewModel.expiryDate
    val cardDateError = rememberSaveable { mutableStateOf(false) }
    val cardHolderValue = viewModel.cardHolder
    val cardHolderValueError = rememberSaveable { mutableStateOf( false ) }

    val phoneNumber = dataStore.getPhoneNumber().collectAsState("")

    val launcher = CardScannerIO(context) { card ->
        viewModel.cardEvents(CardEvents.ChangeCardNumber(card.cardNumber))
    }
    val snackbarHostState = remember { SnackbarHostState() }

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
                onNavigationClick = { navController.navigateUp() }
            )
        }
    ) { paddingValues ->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .background(sevenrdColor)
                .padding(paddingValues)
        ){
            CardFields(
                secondaryColor = secondaryColor,
                cardValue = cardNumberValue.value,
                isScanned = isScanned.value,
                columnModifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.85f)
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
                    viewModel.cardEvents(CardEvents.ChangeExpiryDate(it))
                },
                cardDateError = cardDateError.value,
                cardHolderValue = cardHolderValue.value,
                onCardHolderValueChange = {
                    viewModel.cardEvents(CardEvents.ChangeCardHolder(it.uppercase(getDefault())))
                    cardHolderValueError.value = !it.cardHolderChecker()
                },
                cardHolderValueError = cardHolderValueError.value,
                cardHolderFieldTrailingIcon = {
                    viewModel.cardEvents(CardEvents.ChangeCardHolder(""))
                },
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(horizontal = 16.dp, vertical = 16.dp),
                verticalArrangement = Arrangement.Bottom,
            ) {
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
                            !cardDateError.value &&
                            cardHolderValue.value.isNotEmpty() &&
                            cardHolderValue.value.isNotBlank() &&
                            !cardHolderValueError.value
                        ){
                            val first = expiryDateValue.value.take(2)
                            val last = expiryDateValue.value.takeLast(2)
                            val formatted = cardNumberValue.value.cardNumberSeparator()
                            if (arguments.cardId == -1){
                                sharedPreferences.addCardProcess(true)
                                coroutineScope.launch {
                                    dataStore.saveCardID(-1)
                                    dataStore.saveCardNumber(formatted)
                                    dataStore.saveCardHolder(cardHolderValue.value)
                                    dataStore.saveExpiryDate("$first/$last")
                                }
                                navController.navigate(ScreensRouter.NumberCheckScreen.route + "/${phoneNumber.value}"){
                                    popUpTo(ScreensRouter.AddCardScreen.route){ inclusive = true }
                                }
                            }else{
                                sharedPreferences.addCardProcess(true)
                                coroutineScope.launch {
                                    dataStore.saveCardID(arguments.cardId)
                                    dataStore.saveCardNumber(formatted)
                                    dataStore.saveCardHolder(cardHolderValue.value)
                                    dataStore.saveExpiryDate("$first/$last")
                                }
                                navController.navigate(ScreensRouter.NumberCheckScreen.route + "/${phoneNumber.value}"){
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
                            tint = primaryColor,
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