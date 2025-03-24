package com.mr.anonym.toyonamobile.presentation.navigation

sealed class ScreensRouter (val route:String){
    data object OnboardingScreen:ScreensRouter("OnboardingScreen")
    data object LogInScreen:ScreensRouter("LogInScreen")
}