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
import com.mr.anonym.toyonamobile.ui.screens.enterScreen.screen.EnterScreen
import com.mr.anonym.toyonamobile.ui.screens.numberCheckScreen.screen.NumberCheckScreen
import com.mr.anonym.toyonamobile.ui.screens.newPinScreen.screen.NewPinScreen
import com.mr.anonym.toyonamobile.ui.screens.logInScreen.screen.LogInScreen
import com.mr.anonym.toyonamobile.ui.screens.mainScreen.screen.MainScreen
import com.mr.anonym.toyonamobile.ui.screens.onBoardingScreen.screen.OnboardingScreen
import com.mr.anonym.toyonamobile.ui.screens.profileScreen.screen.ProfileScreen

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

    NavHost(
        navController = navController,
        startDestination = when{
            isFirstTime->{ ScreensRouter.OnboardingScreen.route }
            !isLoggedIn-> { ScreensRouter.LogInScreen.route }
            !newPinState ->{ ScreensRouter.ProfileScreen.route }
            newPinState->{ ScreensRouter.NewPinScreen.route }
            else -> ScreensRouter.EnterScreen.route
        }
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
        composable (ScreensRouter.NewPinScreen.route){
            NewPinScreen(navController)
        }
        composable (ScreensRouter.EnterScreen.route){ EnterScreen(navController) }
        composable (ScreensRouter.MainScreen.route){
            MainScreen(navController)
        }
        composable(ScreensRouter.ProfileScreen.route) {
            ProfileScreen(navController)
        }
    }
}