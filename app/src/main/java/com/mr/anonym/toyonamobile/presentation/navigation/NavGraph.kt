package com.mr.anonym.toyonamobile.presentation.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.mr.anonym.data.instance.local.SharedPreferencesInstance
import com.mr.anonym.toyonamobile.presentation.utils.Arguments
import com.mr.anonym.toyonamobile.ui.screens.addCardScreen.screens.AddCardScreen
import com.mr.anonym.toyonamobile.ui.screens.addEventScreen.screens.AddEventScreen
import com.mr.anonym.toyonamobile.ui.screens.changePasswordScreen.screens.ChangePasswordScreen
import com.mr.anonym.toyonamobile.ui.screens.contactsScreen.screen.ContactsScreen
import com.mr.anonym.toyonamobile.ui.screens.detailsScreen.screen.DetailsScreen
import com.mr.anonym.toyonamobile.ui.screens.enterScreen.screen.EnterScreen
import com.mr.anonym.toyonamobile.ui.screens.logInScreen.screen.LogInScreen
import com.mr.anonym.toyonamobile.ui.screens.mainScreen.screen.MainScreen
import com.mr.anonym.toyonamobile.ui.screens.monitoringScreen.screen.MonitoringFilterScreen
import com.mr.anonym.toyonamobile.ui.screens.monitoringScreen.screen.MonitoringScreen
import com.mr.anonym.toyonamobile.ui.screens.myEventsScreen.screens.MyEventsScreen
import com.mr.anonym.toyonamobile.ui.screens.newPinScreen.screen.NewPinScreen
import com.mr.anonym.toyonamobile.ui.screens.notificationsScreen.screen.NotificationsScreen
import com.mr.anonym.toyonamobile.ui.screens.numberCheckScreen.screen.NumberCheckScreen
import com.mr.anonym.toyonamobile.ui.screens.onBoardingScreen.screen.OnboardingScreen
import com.mr.anonym.toyonamobile.ui.screens.profileScreen.screen.ProfileScreen
import com.mr.anonym.toyonamobile.ui.screens.registrationScreen.screen.RegistrationScreen
import com.mr.anonym.toyonamobile.ui.screens.securityScreen.screens.SecurityScreen
import com.mr.anonym.toyonamobile.ui.screens.settingsScreen.screen.SettingsScreen
import com.mr.anonym.toyonamobile.ui.screens.supportScreen.screen.SupportScreen
import com.mr.anonym.toyonamobile.ui.screens.walletScreen.screen.WalletScreen

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun NavGraph(
    navController: NavHostController
) {

    val context = LocalContext.current
    val sharedPreferences = SharedPreferencesInstance(context)
    val isFirstTime = sharedPreferences.getFirstTimeState()
    val newPinState = sharedPreferences.getNewPinState()
    val isLoggedIn = sharedPreferences.getIsLoggedIn()
    val isProfileSettingsState = sharedPreferences.getIsProfileSettingsState()
    val isThemeChanged = sharedPreferences.isThemeChangedState()

    NavHost(
        navController = navController,
        startDestination = when {
            isFirstTime -> ScreensRouter.OnboardingScreen.route
            !isLoggedIn -> ScreensRouter.LoginScreen.route
            isProfileSettingsState -> ScreensRouter.ProfileScreen.route
            newPinState -> ScreensRouter.NewPinScreen.route
            isThemeChanged -> ScreensRouter.MainScreen.route
            else -> ScreensRouter.EnterScreen.route
        }
    ) {
        composable(ScreensRouter.OnboardingScreen.route) {
            OnboardingScreen(navController = navController)
        }
        composable(ScreensRouter.RegistrationScreen.route) {
            RegistrationScreen(navController)
        }
        composable(
            route = ScreensRouter.NumberCheckScreen.route + "/{number}",
            arguments = listOf(
                navArgument("number") {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ) { entry ->
            val number = entry.arguments?.getString("number") ?: ""
            NumberCheckScreen(navController = navController, arguments = Arguments(number))
        }
        composable(ScreensRouter.NewPinScreen.route) {
            NewPinScreen(navController)
        }
        composable(ScreensRouter.EnterScreen.route) { EnterScreen(navController) }
        composable(ScreensRouter.MainScreen.route) {
            MainScreen(navController)
        }
        composable(ScreensRouter.ProfileScreen.route) {
            ProfileScreen(navController)
        }
        composable(ScreensRouter.NotificationsScreen.route) {
            NotificationsScreen(navController)
        }
        composable(ScreensRouter.ContactsScreen.route) {
            ContactsScreen(navController)
        }
        composable(ScreensRouter.MonitoringScreen.route) {
            MonitoringScreen(navController)
        }
        composable(ScreensRouter.WalletScreen.route) {
            WalletScreen(navController)
        }
        composable(ScreensRouter.SettingsScreen.route) {
            SettingsScreen(navController)
        }
        composable(ScreensRouter.SupportScreen.route) {
            SupportScreen(navController)
        }
        composable(ScreensRouter.DetailsScreen.route) {
            DetailsScreen(navController)
        }
        composable(ScreensRouter.MyEventsScreen.route) {
            MyEventsScreen(navController)
        }
        composable(
            route = ScreensRouter.AddEventScreen.route + "/{eventID}",
            arguments = listOf(
                navArgument("eventID") {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) { entry ->
            val eventID = entry.arguments?.getInt("eventID") ?: -1
            AddEventScreen(
                arguments = Arguments(eventID = eventID),
                navController = navController
            )
        }
        composable(ScreensRouter.LoginScreen.route) {
            LogInScreen(navController)
        }
        composable(
            arguments = listOf(
                navArgument("cardID") {
                    type = NavType.IntType
                    defaultValue = -1
                }
            ),
            route = ScreensRouter.AddCardScreen.route + "/{cardID}"
        ) { entry ->
            val cardID = entry.arguments?.getInt("cardID") ?: -1
            AddCardScreen(
                arguments = Arguments(cardId = cardID),
                navController = navController
            )
        }
        composable(ScreensRouter.SecurityScreen.route) {
            SecurityScreen(
                navController = navController
            )
        }
        composable(ScreensRouter.ChangePasswordScreen.route) {
            ChangePasswordScreen(
                navController = navController
            )
        }
        composable(ScreensRouter.MonitoringFilterScreen.route) {
            MonitoringFilterScreen(navController)
        }
    }
}