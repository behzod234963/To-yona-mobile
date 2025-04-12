package com.mr.anonym.toyonamobile.ui.screens.addCardScreen.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mr.anonym.toyonamobile.R
import com.mr.anonym.toyonamobile.presentation.utils.PermissionController
import com.mr.anonym.toyonamobile.ui.screens.addCardScreen.components.AddCardTopBar
import com.mr.anonym.toyonamobile.ui.screens.addCardScreen.components.CardFields
import com.mr.anonym.toyonamobile.ui.screens.addCardScreen.components.CardNumberScanner

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddCardScreen(
    navController: NavController
) {

    val context = LocalContext.current
    val activity = LocalActivity.current
    val permissionManager = PermissionController(context)

    val primaryColor = if (isSystemInDarkTheme()) Color.Black else Color.White
    val secondaryColor = if (isSystemInDarkTheme()) Color.White else Color.Black
    val tertiaryColor = if (isSystemInDarkTheme()) Color.DarkGray else Color.LightGray
    val quaternaryColor = Color.Red
    val fiverdColor = Color.Green
    val sixrdColor = Color.Blue
    val sevenrdColor = if (isSystemInDarkTheme()) Color.Unspecified else primaryColor

    val cardValue = rememberSaveable { mutableStateOf("") }
    val cardValueError = rememberSaveable { mutableStateOf(false) }
    val carDateValue = rememberSaveable { mutableStateOf("") }
    val cardDateError = rememberSaveable { mutableStateOf(false) }

    val isScannerOn = rememberSaveable { mutableStateOf(false) }
    val isFlashOn = rememberSaveable { mutableStateOf(false) }

    Scaffold(
        containerColor = primaryColor,
        contentColor = primaryColor,
        topBar = {
            AddCardTopBar(
                primaryColor = primaryColor,
                secondaryColor = secondaryColor,
                onNavigationClick = { navController.popBackStack() }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(sevenrdColor)
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            CardFields(
                secondaryColor = secondaryColor,
                cardValue = cardValue.value,
                onCardValueChange = {
                    cardValue.value = it
                },
                cardFieldError = cardValueError.value,
                cardFieldTrailingClick = {
                    isScannerOn.value = true
                },
                cardDateValue = carDateValue.value,
                onCardDateValueChange = {
                    carDateValue.value = it
                },
                cardDateError = cardDateError.value
            )
        }
    }
    if (isScannerOn.value &&
        permissionManager.requestCameraPermission(activity!!)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            CardNumberScanner(
                context = context,
                isFlashOn = isFlashOn.value,
                onCardNumberFound = {
                    cardValue.value = it
                    isScannerOn.value = false
                    Log.d("UtilsLogging", "AddCardScreen: it's worked")
                }
            )
            Box(
                modifier = Modifier
                    .padding(16.dp),
                contentAlignment = Alignment.TopEnd
            ) {
                IconButton(
                    onClick = {
                        isFlashOn.value = !isFlashOn.value
                    }
                ) {
                    Icon(
                        painter = if (!isFlashOn.value) painterResource(R.drawable.ic_flash_on) else painterResource(
                            R.drawable.ic_flash_off
                        ),
                        tint = secondaryColor,
                        contentDescription = ""
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewAddCardScreen() {
    AddCardScreen(
        navController = NavController(LocalContext.current)
    )
}