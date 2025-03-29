package com.mr.anonym.toyonamobile.presentation.navigation

sealed class ScreensRouter (val route:String){
    data object OnboardingScreen:ScreensRouter("OnboardingScreen")
    data object LogInScreen:ScreensRouter("LogInScreen")
    data object NumberCheckScreen:ScreensRouter("NumberCheckScreen")
    data object NewPinScreen:ScreensRouter("NewPinScreen")
    data object MainScreen:ScreensRouter("MainScreen")
    data object EnterScreen: ScreensRouter("EnterScreen")
    data object ProfileScreen: ScreensRouter("ProfileScreen")
}