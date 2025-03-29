package com.mr.anonym.toyonamobile.ui.screens.logInScreen.screen

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.mr.anonym.toyonamobile.R
import com.mr.anonym.toyonamobile.presentation.extensions.passwordChecker
import com.mr.anonym.toyonamobile.presentation.extensions.phoneChecker
import com.mr.anonym.toyonamobile.presentation.navigation.ScreensRouter
import com.mr.anonym.toyonamobile.ui.screens.logInScreen.components.LogInTextFields

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LogInScreen(
    navController: NavController
) {

    val context = LocalContext.current
    val activityContext = LocalActivity.current
    val primaryColor = if (isSystemInDarkTheme()) Color.Black else Color.White
    val secondaryColor = if (isSystemInDarkTheme()) Color.White else Color.Black
    val tertiaryColor = if (isSystemInDarkTheme()) Color.DarkGray else Color.LightGray
    val quaternaryColor = Color.Red
    val containerPadding = remember { mutableIntStateOf(10) }

    val phoneFieldError = remember { mutableStateOf(false) }
    val phoneFieldValue = rememberSaveable { mutableStateOf("") }

    val nameFieldValue = rememberSaveable { mutableStateOf("") }
    val nameFieldError = remember { mutableStateOf(false) }

    val passwordValue = rememberSaveable { mutableStateOf( "" ) }
    val passwordValueError = remember { mutableStateOf( false ) }

    val confirmValue = rememberSaveable { mutableStateOf( "" ) }
    val confirmValueError = remember { mutableStateOf( false ) }

    val focusRequester = remember { FocusRequester() }
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
    Scaffold(
        containerColor = primaryColor,
        contentColor = primaryColor
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
                    textAlign = TextAlign.Center,
                    text = stringResource(R.string.to_log_in_you_need_to_enter_phone_and_firstname_lastname),
                    color = secondaryColor,
                    fontSize = 16.sp,
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.55f)
                    .padding(containerPadding.intValue.dp),
                verticalArrangement = Arrangement.Top
            ) {
                LogInTextFields(
                    secondaryColor = secondaryColor,
                    tertiaryColor = tertiaryColor,
                    phoneFieldModifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(focusRequester),
                    phoneFieldError = phoneFieldError.value,
                    phoneFieldValue = phoneFieldValue.value,
                    phoneFieldTrailingFunction = { phoneFieldValue.value = "" },
                    onPhoneValueChange = {
                        phoneFieldValue.value = it
//                        if (it.isEmpty()) phoneFieldValue.value = "+998"
                        phoneFieldError.value = !it.phoneChecker()
                    },
                    passwordValue = passwordValue.value,
                    onPasswordValueChange = {
                        passwordValue.value = it
                        passwordValueError.value = !it.passwordChecker()
                    },
                    passwordValueTrailingIcon = {
                        passwordValue.value = ""
                    },
                    passwordValueError = passwordValueError.value,
                    confirmPasswordValue = confirmValue.value,
                    onConfirmPasswordValueChange = {
                        confirmValue.value = it
                    },
                    confirmPasswordValueTrailingIcon = {
                        confirmValue.value = ""
                    },
                    confirmPasswordValueError = confirmValueError.value
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.75f)
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
                            if (confirmValue.value == passwordValue.value){
                                val result = "+998" + phoneFieldValue.value
                                navController.navigate(ScreensRouter.NumberCheckScreen.route + "/$result")
                            }else{
                                confirmValueError.value = true
                            }
                        } else {
                            Toast.makeText(
                                activityContext,
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
    LogInScreen(NavController(LocalContext.current))
}