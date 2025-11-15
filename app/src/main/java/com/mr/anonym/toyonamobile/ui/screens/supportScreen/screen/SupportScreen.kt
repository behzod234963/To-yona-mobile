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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
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
    val systemNineColor = if (isSystemInDarkTheme()) Color(0xFF222327) else Color(0xFFF1F2F4)
    val nineColor = when{
        isSystemTheme -> systemNineColor
        isDarkTheme -> Color(0xFF222327)
        else -> Color(0xFFF1F2F4)
    }

    val iosFont = FontFamily(Font(R.font.ios_font))

    Scaffold (
        containerColor = primaryColor,
        contentColor = primaryColor,
        topBar = {
            SupportTopBar(
                primaryColor = primaryColor,
                secondaryColor = secondaryColor,
                fontFamily = iosFont,
                onNavigationClick = { navController.navigateUp() }
            ) {
                openSupportBot(context, SUPPORT_BOT)
            }
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
                nineColor = nineColor,
                fontFamily = iosFont,
                title = stringResource(R.string.how_to_change_language),
                content = stringResource(R.string.change_language_instruction)
            ) { }
            Spacer(Modifier.height(5.dp))
            SupportField(
                secondaryColor = secondaryColor,
                nineColor = nineColor,
                fontFamily = iosFont,
                title = stringResource(R.string.card_support_instruction),
                content = stringResource(R.string.card_support_instruction_content)
            ) { }
        }
    }
}

@Preview
@Composable
private fun PreviewSupportScreen() {
    SupportScreen(NavController(LocalContext.current))
}