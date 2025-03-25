package com.mr.anonym.toyonamobile.ui.screens.checkNumberScreen

import android.widget.Toast
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.runtime.mutableIntStateOf
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
import androidx.navigation.NavController
import com.mr.anonym.toyonamobile.R
import com.mr.anonym.toyonamobile.presentation.extensions.nameChecker
import com.mr.anonym.toyonamobile.presentation.extensions.phoneChecker
import com.mr.anonym.toyonamobile.presentation.navigation.ScreensRouter
import com.mr.anonym.toyonamobile.presentation.utils.Arguments
import com.mr.anonym.toyonamobile.ui.screens.logInScreen.components.LogInTextFields

@Composable
fun NumberCheckScreen(
    arguments: Arguments,
    navController: NavController
) {

    val primaryColor = if (isSystemInDarkTheme()) Color.Black else Color.White
    val secondaryColor = if (isSystemInDarkTheme()) Color.White else Color.Black
    val tertiaryColor = if (isSystemInDarkTheme()) Color.DarkGray else Color.LightGray
    val quaternaryColor = Color.Red
    val containerPadding = remember { mutableIntStateOf( 10 ) }

    Scaffold { paddingValues ->
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
            }
        }
    }
}

@Preview
@Composable
private fun PreviewNumberCheckScreen() {
    NumberCheckScreen( navController = NavController(LocalContext.current), arguments = Arguments("+998973570498"))
}