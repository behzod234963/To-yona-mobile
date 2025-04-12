package com.mr.anonym.toyonamobile.ui.screens.logInScreens.screen

import android.widget.Toast
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.navigation.NavController
import com.mr.anonym.data.instance.local.DataStoreInstance
import com.mr.anonym.toyonamobile.R
import com.mr.anonym.toyonamobile.presentation.extensions.passwordChecker
import com.mr.anonym.toyonamobile.presentation.extensions.phoneChecker
import com.mr.anonym.toyonamobile.presentation.navigation.ScreensRouter
import com.mr.anonym.toyonamobile.ui.screens.logInScreens.components.LoginTextFields
import kotlinx.coroutines.launch

@Composable
fun LogInScreen(
    navController: NavController
) {

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope ()

    val dataStore = DataStoreInstance(context)

    val primaryColor = if (isSystemInDarkTheme()) Color.Black else Color.White
    val secondaryColor = if (isSystemInDarkTheme()) Color.White else Color.Black
    val tertiaryColor = if (isSystemInDarkTheme()) Color.DarkGray else Color.LightGray
    val quaternaryColor = Color.Red
    val fiverdColor = Color.Green
    val sixrdColor = Color.Blue
    val sevenrdColor = if (isSystemInDarkTheme()) Color.Unspecified else primaryColor

    val containerPadding = rememberSaveable { mutableIntStateOf(10) }
    val focusRequester = remember { FocusRequester() }
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    val phoneFieldValue = rememberSaveable { mutableStateOf("") }
    val phoneFieldError = rememberSaveable { mutableStateOf(false) }

    val passwordValue = rememberSaveable { mutableStateOf("") }
    val passwordValueError = rememberSaveable { mutableStateOf(false) }

    Scaffold(
        containerColor = primaryColor,
        contentColor = primaryColor,
        modifier = Modifier
            .imePadding()
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.20f)
                    .padding(containerPadding.value.dp),
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
                LoginTextFields(
                    secondaryColor = secondaryColor,
                    phoneFieldValue = phoneFieldValue.value,
                    onPhoneValueChange = {
                        phoneFieldValue.value = it
                        phoneFieldError.value = !it.phoneChecker()
                    },
                    phoneFieldTrailingFunction = { phoneFieldValue.value = "" },
                    phoneFieldError = phoneFieldError.value,
                    phoneFieldModifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(focusRequester),
                    passwordValue = passwordValue.value,
                    onPasswordValueChange = {
                        passwordValue.value = it
                        passwordValueError.value = !it.passwordChecker()
                    },
                    passwordValueError = passwordValueError.value
                )
                TextButton (
                    onClick = {
                        navController.navigate(ScreensRouter.RegistrationScreen.route)
                    }
                ){
                    Text(
                        text = stringResource(R.string.i_have_no_account),
                        color = Color.Blue,
                        fontSize = 16.sp
                    )
                }
                TextButton (
                    onClick = {
                        if (
                            phoneFieldValue.value.isNotEmpty() &&
                            phoneFieldValue.value.isNotBlank() &&
                            !phoneFieldError.value
                            ) {
                            coroutineScope.launch {
                                dataStore.isPasswordForgotten(true)
                            }
                            val result = "+998" + phoneFieldValue.value
                            navController.navigate(ScreensRouter.NumberCheckScreen.route + "/$result"){
                                popUpTo(ScreensRouter.LoginScreen.route){ inclusive = true }
                            }
                        }else{
                            phoneFieldError.value = true
                        }
                    }
                ){
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
                    onClick = {
                        if (
                            phoneFieldValue.value.isNotEmpty() &&
                            phoneFieldValue.value.isNotBlank() &&
                            !phoneFieldError.value &&
                            !passwordValueError.value
                        ) {
                            coroutineScope.launch {
                                dataStore.isOldUser(true)
                            }
                            val result = "+998" + phoneFieldValue.value
                            navController.navigate(ScreensRouter.NumberCheckScreen.route + "/$result"){
                                popUpTo(ScreensRouter.LoginScreen.route){ inclusive = true }
                            }
                        } else {
                            Toast.makeText(
                                context,
                                context.getString(R.string.please_check_validate_places),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                ) {
                    Text(
                        text = stringResource(R.string.continue_),
                        color = secondaryColor,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewLogInScreen() {
    LogInScreen(
        navController = NavController(LocalContext.current)
    )
}