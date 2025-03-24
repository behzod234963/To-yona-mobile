package com.mr.anonym.toyonamobile.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mr.anonym.data.instance.local.DataStoreInstance
import com.mr.anonym.data.instance.local.SharedPreferencesInstance
import com.mr.anonym.toyonamobile.ui.screens.logInScreen.screen.LogInScreen
import com.mr.anonym.toyonamobile.ui.screens.onBoardingScreen.screen.OnboardingScreen

@Composable
fun NavGraph() {

    val context = LocalContext.current
    val sharedPreferences = SharedPreferencesInstance(context)
    val navController = rememberNavController()
    val isFirstTime = sharedPreferences.getFirstTimeState()

    NavHost(
        navController = navController,
        startDestination = if (isFirstTime) ScreensRouter.OnboardingScreen.route else ScreensRouter.LogInScreen.route
    ){
        composable(ScreensRouter.OnboardingScreen.route) {
            OnboardingScreen(navController = navController)
        }
        composable (ScreensRouter.LogInScreen.route){
            LogInScreen(navController)
        }
    }
}