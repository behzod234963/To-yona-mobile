package com.mr.anonym.toyonamobile.ui.screens.onBoardingScreen.screen

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mr.anonym.data.instance.local.SharedPreferencesInstance
import com.mr.anonym.toyonamobile.presentation.managers.LocaleConfigurations
import com.mr.anonym.toyonamobile.presentation.navigation.ScreensRouter
import com.mr.anonym.toyonamobile.ui.screens.onBoardingScreen.components.OnBoardingPager
import com.mr.anonym.toyonamobile.ui.screens.onBoardingScreen.components.OnBoardingTopBar
import java.util.Locale

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun OnboardingScreen(
    navController: NavController
) {
//    Context
    val context = LocalContext.current
    val activityContext = LocalActivity.current

//    Object
    val sharedPreferences = SharedPreferencesInstance(context)
    val localeManager = LocaleConfigurations(context)

//    Boolean
    val isDarkTheme = sharedPreferences.getDarkThemeState()
    val isSystemTheme = sharedPreferences.getSystemThemeState()

//    Int
    val page = remember { mutableIntStateOf(0) }

//    Colors
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

//    State
    val primaryLocale = remember { mutableStateOf(Locale.getDefault().language) }
    val defaultLanguage = when (primaryLocale.value) {
        "uz", "ru" -> primaryLocale.value
        else -> "uz"
    }
    val localeState = when (primaryLocale.value) {
        "uz", "ru" -> false
        else -> true
    }
    if (localeState) {
        activityContext?.let {
            localeManager.setApplicationLocales(
                it,
                defaultLanguage
            )
        }
    }
    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { 3 }
    )
    BackHandler(
        enabled = true
    ) {
        sharedPreferences.saveFirstTimeState(false)
        activityContext?.finish()
    }
    Scaffold(
        contentColor = primaryColor,
        containerColor = primaryColor,
        topBar = {
            OnBoardingTopBar(
                primaryColor = primaryColor,
                quaternaryColor = quaternaryColor
            ) {
                sharedPreferences.saveFirstTimeState(false)
                navController.navigate(ScreensRouter.LoginScreen.route) {
                    popUpTo(route = ScreensRouter.OnboardingScreen.route) { inclusive = true }
                }
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OnBoardingPager(
                onPageState = {
                    page.intValue = it
                },
                secondaryColor = secondaryColor,
                state = pagerState,
                tertiaryColor = tertiaryColor,
                quaternaryColor = quaternaryColor,
                onFinish = {
                    sharedPreferences.saveFirstTimeState(false)
                    navController.navigate(ScreensRouter.LoginScreen.route) {
                        popUpTo(ScreensRouter.OnboardingScreen.route) { inclusive = true }
                    }
                }
            )
        }
    }
}

@Preview
@Composable
private fun PreviewOnBoardingScreen() {
    OnboardingScreen(NavController(LocalContext.current))
}