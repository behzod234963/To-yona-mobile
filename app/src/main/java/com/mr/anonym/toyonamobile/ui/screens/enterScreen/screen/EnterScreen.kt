package com.mr.anonym.toyonamobile.ui.screens.enterScreen.screen

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.mr.anonym.data.instance.local.DataStoreInstance
import com.mr.anonym.data.instance.local.SharedPreferencesInstance
import com.mr.anonym.toyonamobile.R
import com.mr.anonym.toyonamobile.presentation.navigation.ScreensRouter
import com.mr.anonym.toyonamobile.ui.screens.newPinScreen.components.EnterScreenDialog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
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

    val primaryColor = if (isSystemInDarkTheme()) Color.Black else Color.White
    val secondaryColor = if (isSystemInDarkTheme()) Color.White else Color.Black
    val tertiaryColor = if (isSystemInDarkTheme()) Color.DarkGray else Color.LightGray
    val quaternaryColor = Color.Red
    val fiverdColor = Color.Green
    val sixrdColor = Color.Blue

    val isBiometricAuthOn = dataStore.getIsBiometricAuthOn().collectAsState(false)
    val showBiometricSettings = remember { mutableStateOf( false ) }

    val isEnterCompleted = dataStore.getIsEnterCompleted().collectAsState(false)
    val pinValueError = remember { mutableStateOf(false) }
    val pinCode = dataStore.getPinCode().collectAsState("")
    val pinValue = remember { mutableStateOf("") }
    val iconSize = remember { mutableIntStateOf(30) }

    val phoneNumber = dataStore.getPhoneNumber().collectAsState("")

    LaunchedEffect(pinValue.value) {
        if(
            pinValue.value.length > 3 &&
            pinValue.value == pinCode.value
        ){
            navController.navigate(ScreensRouter.MainScreen.route){
                popUpTo(route = ScreensRouter.EnterScreen.route){
                    inclusive = true
                }
            }
        }
    }
    Scaffold(
        containerColor = primaryColor,
        contentColor = primaryColor
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.2f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                Text(
                    text = stringResource(R.string.enter_your_pin_code),
                    color = secondaryColor,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(Modifier.height(30.dp))
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
                            .size(iconSize.value.dp)
                            .padding(vertical = 7.dp),
                        painter = painterResource(R.drawable.ic_round),
                        tint = when {
                            pinValue.value.length == 4 && pinValue.value != pinCode.value -> { quaternaryColor }
                            pinValue.value.isNotEmpty() -> { fiverdColor }
                            else -> { tertiaryColor }
                        },
                        contentDescription = "null"
                    )
                    Icon(
                        modifier = Modifier
                            .size(iconSize.intValue.dp)
                            .padding(vertical = 7.dp),
                        painter = painterResource(R.drawable.ic_round),
                        tint = when {
                            pinValue.value.length == 4  && pinValue.value != pinCode.value -> { quaternaryColor }
                            pinValue.value.length > 1 -> { fiverdColor }
                            else -> { tertiaryColor }
                        },
                        contentDescription = "null"
                    )
                    Icon(
                        modifier = Modifier
                            .size(iconSize.intValue.dp)
                            .padding(vertical = 7.dp),
                        painter = painterResource(R.drawable.ic_round),
                        tint = when {
                            pinValue.value.length == 4  && pinValue.value != pinCode.value -> { quaternaryColor }

                            pinValue.value.length > 2 -> {
                                fiverdColor
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
                            pinValue.value.length == 4  && pinValue.value != pinCode.value -> { quaternaryColor }

                            pinValue.value.length > 3 -> {
                                fiverdColor
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
                    Card(
                        modifier = Modifier
                            .size(70.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = tertiaryColor, contentColor = tertiaryColor
                        ),
                        shape = RoundedCornerShape(10.dp),
                        onClick = {
                            if (pinValue.value.length < 4) {
                                if (pinValue.value.isEmpty() && pinValue.value.isBlank()) {
                                    pinValue.value = "1"
                                } else {
                                    pinValue.value += "1"
                                }
                            }
                        }) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "1",
                                color = secondaryColor,
                                fontSize = 25.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
//                    2
                    Card(
                        modifier = Modifier
                            .size(70.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = tertiaryColor,
                            contentColor = tertiaryColor
                        ),
                        shape = RoundedCornerShape(10.dp),
                        onClick = {
                            if (pinValue.value.length < 4) {
                                if (pinValue.value.isEmpty() && pinValue.value.isBlank()) {
                                    pinValue.value = "2"
                                } else {
                                    pinValue.value += "2"
                                }
                            }
                        }) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "2",
                                color = secondaryColor,
                                fontSize = 25.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
//                    3
                    Card(
                        modifier = Modifier.size(70.dp), colors = CardDefaults.cardColors(
                            containerColor = tertiaryColor, contentColor = tertiaryColor
                        ), shape = RoundedCornerShape(10.dp), onClick = {
                            if (pinValue.value.length < 4) {
                                if (pinValue.value.isEmpty() && pinValue.value.isBlank()) {
                                    pinValue.value = "3"
                                } else {
                                    pinValue.value += "3"
                                }
                            }
                        }) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "3",
                                color = secondaryColor,
                                fontSize = 25.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
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
                    Card(
                        modifier = Modifier.size(70.dp), colors = CardDefaults.cardColors(
                            containerColor = tertiaryColor, contentColor = tertiaryColor
                        ), shape = RoundedCornerShape(10.dp), onClick = {
                            if (pinValue.value.length < 4) {
                                if (pinValue.value.isEmpty() && pinValue.value.isBlank()) {
                                    pinValue.value = "4"
                                } else {
                                    pinValue.value += "4"
                                }
                            }
                        }) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "4",
                                color = secondaryColor,
                                fontSize = 25.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
//                    5
                    Card(
                        modifier = Modifier.size(70.dp), colors = CardDefaults.cardColors(
                            containerColor = tertiaryColor, contentColor = tertiaryColor
                        ), shape = RoundedCornerShape(10.dp), onClick = {
                            if (pinValue.value.length < 4) {
                                if (pinValue.value.isEmpty() && pinValue.value.isBlank()) {
                                    pinValue.value = "5"
                                } else {
                                    pinValue.value += "5"
                                }
                            }
                        }) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "5",
                                color = secondaryColor,
                                fontSize = 25.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
//                    6
                    Card(
                        modifier = Modifier.size(70.dp), colors = CardDefaults.cardColors(
                            containerColor = tertiaryColor, contentColor = tertiaryColor
                        ), shape = RoundedCornerShape(10.dp), onClick = {
                            if (pinValue.value.length < 4) {
                                if (pinValue.value.isEmpty() && pinValue.value.isBlank()) {
                                    pinValue.value = "6"
                                } else {
                                    pinValue.value += "6"
                                }
                            }
                        }) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "6",
                                color = secondaryColor,
                                fontSize = 25.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
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
                    Card(
                        modifier = Modifier.size(70.dp), colors = CardDefaults.cardColors(
                            containerColor = tertiaryColor, contentColor = tertiaryColor
                        ), shape = RoundedCornerShape(10.dp), onClick = {
                            if (pinValue.value.length < 4) {
                                if (pinValue.value.isEmpty() && pinValue.value.isBlank()) {
                                    pinValue.value = "7"
                                } else {
                                    pinValue.value += "7"
                                }
                            }
                        }) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "7",
                                color = secondaryColor,
                                fontSize = 25.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
//                    8
                    Card(
                        modifier = Modifier.size(70.dp), colors = CardDefaults.cardColors(
                            containerColor = tertiaryColor, contentColor = tertiaryColor
                        ), shape = RoundedCornerShape(10.dp), onClick = {
                            if (pinValue.value.length < 4) {
                                if (pinValue.value.isEmpty() && pinValue.value.isBlank()) {
                                    pinValue.value = "8"
                                } else {
                                    pinValue.value += "8"
                                }
                            }
                        }) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "8",
                                color = secondaryColor,
                                fontSize = 25.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
//                    9
                    Card(
                        modifier = Modifier.size(70.dp), colors = CardDefaults.cardColors(
                            containerColor = tertiaryColor, contentColor = tertiaryColor
                        ), shape = RoundedCornerShape(10.dp), onClick = {
                            if (pinValue.value.length < 4) {
                                if (pinValue.value.isEmpty() && pinValue.value.isBlank()) {
                                    pinValue.value = "9"
                                } else {
                                    pinValue.value += "9"
                                }
                            }
                        }) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "9",
                                color = secondaryColor,
                                fontSize = 25.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
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
                    Card(
                        modifier = Modifier
                            .size(70.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = primaryColor,
                            contentColor = primaryColor
                        ),
                        shape = RoundedCornerShape(10.dp),
                        onClick = {
                            if (isBiometricAuthOn.value){
                                CoroutineScope(Dispatchers.Default).launch {
                                    dataStore.saveBiometricAuthState(true)
                                }
                            }else{
                                showBiometricSettings.value = true
                            }
                        }
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                modifier = Modifier.padding(15.dp),
                                tint = secondaryColor,
                                painter = painterResource(R.drawable.ic_fingerprint),
                                contentDescription = "button fingerprint"
                            )
                        }
                    }
                    if (showBiometricSettings.value){
                        EnterScreenDialog(
                            secondaryColor = secondaryColor,
                            tertiaryColor = tertiaryColor,
                            quaternaryColor = quaternaryColor,
                            sixrdColor = sixrdColor,
                            title = stringResource(R.string.allow_fingerprint),
                            confirmButton = {
                                coroutineScope.launch {
                                    dataStore.saveIsBiometricAuthOn(true)
                                }
                                showBiometricSettings.value = false
                            },
                            dismissButton = {
                                coroutineScope.launch {
                                    dataStore.saveIsBiometricAuthOn(false)
                                }
                                showBiometricSettings.value = false
                            },
                            onDismissRequest = {
                                coroutineScope.launch {
                                    dataStore.saveIsBiometricAuthOn(false)
                                }
                                showBiometricSettings.value = false
                            }
                        )
                    }
//                    0
                    Card(
                        modifier = Modifier
                            .size(70.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = tertiaryColor,
                            contentColor = tertiaryColor
                        ),
                        shape = RoundedCornerShape(10.dp),
                        onClick = {
                            if (pinValue.value.length < 4) {
                                if (pinValue.value.isEmpty() && pinValue.value.isBlank()) {
                                    pinValue.value = "0"
                                } else {
                                    pinValue.value += "0"
                                }
                            }
                        }) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "0",
                                color = secondaryColor,
                                fontSize = 25.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
//                    button clear
                    Card(
                        modifier = Modifier
                            .size(70.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = primaryColor, contentColor = primaryColor
                        ),
                        shape = RoundedCornerShape(10.dp),
                        onClick = {
                            pinValue.value = pinValue.value.dropLast(1)
                        }) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                modifier = Modifier.size(40.dp),
                                painter = painterResource(R.drawable.ic_backspace),
                                tint = secondaryColor,
                                contentDescription = "button backspace"
                            )
                        }
                    }
                }
                Spacer(Modifier.height(30.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        modifier = Modifier
                            .clickable {
                                coroutineScope.launch {
                                    dataStore.isPinForgotten(true)
                                }
                                navController.navigate(ScreensRouter.NumberCheckScreen.route + "/${phoneNumber.value}")
                            },
                        text = stringResource(R.string.forgot_pin_code),
                        color = sixrdColor,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
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