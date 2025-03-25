package com.mr.anonym.toyonamobile.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mr.anonym.data.instance.local.SharedPreferencesInstance
import com.mr.anonym.toyonamobile.presentation.utils.Arguments
import com.mr.anonym.toyonamobile.ui.screens.checkNumberScreen.NumberCheckScreen
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
        composable (
            route = ScreensRouter.NumberCheckScreen.route + "/{number}",
            arguments = listOf(
                navArgument("number"){
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ){entry->
            val number = entry.arguments?.getString("number")?:""
            NumberCheckScreen(navController = navController, arguments = Arguments(number))
        }
    }
}