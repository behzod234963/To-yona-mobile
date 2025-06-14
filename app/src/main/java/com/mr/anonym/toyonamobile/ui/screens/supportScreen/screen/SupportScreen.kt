package com.mr.anonym.toyonamobile.ui.screens.supportScreen.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mr.anonym.data.instance.local.SharedPreferencesInstance
import com.mr.anonym.toyonamobile.R
import com.mr.anonym.toyonamobile.presentation.utils.SUPPORT_BOT
import com.mr.anonym.toyonamobile.presentation.managers.openSupportBot
import com.mr.anonym.toyonamobile.ui.screens.supportScreen.components.SupportField
import com.mr.anonym.toyonamobile.ui.screens.supportScreen.components.SupportTopBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SupportScreen(
    navController: NavController
) {

    val context = LocalContext.current

    val sharedPreferences = SharedPreferencesInstance(context)

    val isDarkTheme = sharedPreferences.getDarkThemeState()
    val isSystemTheme = sharedPreferences.getSystemThemeState()

    val systemPrimaryColor = if (isSystemInDarkTheme()) Color.Black else Color.White
    val primaryColor = when {
        isSystemTheme-> {
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
    val systemSevenrdColor = if (isSystemInDarkTheme()) Color.Unspecified else Color.White
    val sevenrdColor = when {
        isSystemTheme-> systemSevenrdColor
        isDarkTheme -> Color.Unspecified
        else -> Color.White
    }

    Scaffold (
        containerColor = primaryColor,
        contentColor = primaryColor,
        topBar = {
            SupportTopBar(
                primaryColor = primaryColor,
                secondaryColor = secondaryColor,
                onNavigationClick = { navController.navigateUp() },
                onActionsClick = {
                    openSupportBot(context, SUPPORT_BOT)
                }
            )
        }
    ){paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(10.dp)
        ) {
            SupportField(
                secondaryColor = secondaryColor,
                sevenrdColor = sevenrdColor,
                title = stringResource(R.string.how_to_change_language),
                content = stringResource(R.string.change_language_instruction),
                onClick = {  }
            )
            Spacer(Modifier.height(5.dp))
            SupportField(
                secondaryColor = secondaryColor,
                sevenrdColor = sevenrdColor,
                title = stringResource(R.string.card_support_instruction),
                content = stringResource(R.string.card_support_instruction_content),
                onClick = {  }
            )
        }
    }
}

@Preview
@Composable
private fun PreviewSupportScreen() {
    SupportScreen(NavController(LocalContext.current))
}