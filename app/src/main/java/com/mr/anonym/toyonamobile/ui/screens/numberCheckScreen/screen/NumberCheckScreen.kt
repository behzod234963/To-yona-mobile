package com.mr.anonym.toyonamobile.ui.screens.numberCheckScreen.screen

import android.widget.Toast
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import androidx.navigation.NavController
import com.mr.anonym.data.instance.local.SharedPreferencesInstance
import com.mr.anonym.toyonamobile.R
import com.mr.anonym.toyonamobile.presentation.navigation.ScreensRouter
import com.mr.anonym.toyonamobile.presentation.utils.Arguments
import com.mr.anonym.toyonamobile.ui.screens.numberCheckScreen.components.OTPField
import kotlinx.coroutines.delay

@Composable
fun NumberCheckScreen(
    arguments: Arguments,
    navController: NavController
) {

    val context = LocalContext.current

    val sharedPreferences = SharedPreferencesInstance(context)

    val primaryColor = if (isSystemInDarkTheme()) Color.Black else Color.White
    val secondaryColor = if (isSystemInDarkTheme()) Color.White else Color.Black
    val tertiaryColor = if (isSystemInDarkTheme()) Color.DarkGray else Color.LightGray
    val quaternaryColor = Color.Red

    val containerPadding = remember { mutableIntStateOf(10) }

    val otpValue = remember { mutableStateOf("") }
    val correctValue = remember { mutableStateOf( "11111" ) }

    val timeLeft = remember { mutableStateOf(40) }
    val isRunning = remember { mutableStateOf(true) }

    LaunchedEffect(isRunning.value,timeLeft.value) {
        while ( isRunning.value && timeLeft.value > 0 ){
            delay(1000L)
            timeLeft.value--
        }
        if (timeLeft.value <= 0) isRunning.value = false
    }

    Scaffold (
        containerColor = primaryColor,
        contentColor = primaryColor
    ){ paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.3f)
                    .padding(containerPadding.intValue.dp),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.log_in),
                    color = secondaryColor,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(Modifier.height(10.dp))
                Text(
                    textAlign = TextAlign.Center,
                    text = stringResource(R.string.enter_code_below, arguments.number),
                    color = secondaryColor,
                    fontSize = 16.sp,
                )
                Spacer(Modifier.height(10.dp))
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.35f)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    OTPField(
                        secondaryColor = secondaryColor,
                        value = otpValue.value,
                        onSend = {
                            if (
                                otpValue.value.isNotEmpty() &&
                                otpValue.value.isNotBlank() &&
                                otpValue.value == correctValue.value
                            ){
                                sharedPreferences.saveIsLoggedIn(true)
                                navController.navigate(ScreensRouter.ProfileScreen.route)
                            }else{
                                Toast.makeText(context,
                                    context.getString(R.string.please_complete_the_process), Toast.LENGTH_SHORT).show()
                            }
                        }
                    ) {
                        if (it.length <= 5 && it.isDigitsOnly()) {
                            otpValue.value = it
                        }
                    }
                }
                Spacer(Modifier.height(10.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    TextButton(
                        onClick = { navController.popBackStack() }
                    ) {
                        Text(
                            text = stringResource(R.string.change_number),
                            color = Color.Blue,
                            fontSize = 16.sp
                        )
                    }
                    IconButton(
                        onClick = { navController.popBackStack() }
                    ) {
                        Icon(
                            modifier = Modifier
                                .size(20.dp),
                            imageVector = Icons.Default.Edit,
                            tint = secondaryColor,
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
                        color = secondaryColor,
                        fontSize = 16.sp
                    )
                    Spacer(Modifier.width(10.dp))
                    if (!isRunning.value&& timeLeft.value == 0 ){
                        IconButton(
                            onClick = {
                                isRunning.value = true
                                timeLeft.value = 40
                                otpValue.value = ""
                            }
                        ) {
                            Icon(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .padding(top = 2.dp),
                                imageVector = Icons.Default.Refresh,
                                tint = secondaryColor,
                                contentDescription = "null"
                            )
                        }
                    }else{
                        Text(
                            text = timeLeft.value.toString(),
                            color = secondaryColor,
                            fontSize = 16.sp
                        )
                    }
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.85f)
                    .padding(horizontal = 15.dp),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = quaternaryColor
                    ),
                    onClick = {
                        if (
                            otpValue.value.isNotEmpty() &&
                            otpValue.value.isNotBlank() &&
                            otpValue.value == correctValue.value
                            ){
                            sharedPreferences.saveIsLoggedIn(true)
                            navController.navigate(ScreensRouter.ProfileScreen.route)
                        }else{
                            Toast.makeText(context,
                                context.getString(R.string.please_complete_the_process), Toast.LENGTH_SHORT).show()
                        }
                    }
                ) {
                    Text(
                        text = stringResource(R.string.Enter),
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
private fun PreviewNumberCheckScreen() {
    NumberCheckScreen(
        navController = NavController(LocalContext.current),
        arguments = Arguments("+998973570498")
    )
}