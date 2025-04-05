package com.mr.anonym.toyonamobile.presentation.navigation

sealed class ScreensRouter (val route:String){
    data object OnboardingScreen:ScreensRouter("OnboardingScreen")
    data object LogInScreen:ScreensRouter("LogInScreen")
    data object NumberCheckScreen:ScreensRouter("NumberCheckScreen")
    data object NewPinScreen:ScreensRouter("NewPinScreen")
    data object MainScreen:ScreensRouter("MainScreen")
    data object EnterScreen: ScreensRouter("EnterScreen")
    data object ProfileScreen: ScreensRouter("ProfileScreen")
    data object NotificationsScreen: ScreensRouter("NotificationsScreen")
    data object ContactsScreen: ScreensRouter("ContactsScreen")
    data object MonitoringScreen: ScreensRouter("MonitoringScreen")
    data object WalletScreen: ScreensRouter("WalletScreen")
    data object SettingsScreen: ScreensRouter("SettingsScreen")
    data object SupportScreen: ScreensRouter("SupportScreen")
    data object DetailsScreen: ScreensRouter("DetailsScreen")
    data object MyEventsScreen: ScreensRouter("MyEventsScreen")
    data object AddEventScreen: ScreensRouter("AddEventScreen")
}