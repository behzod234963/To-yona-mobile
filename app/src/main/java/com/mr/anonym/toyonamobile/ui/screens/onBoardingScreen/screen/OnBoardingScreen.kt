package com.mr.anonym.toyonamobile.ui.screens.onBoardingScreen.screen

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.mr.anonym.data.instance.local.SharedPreferencesInstance
import com.mr.anonym.toyonamobile.R
import com.mr.anonym.toyonamobile.presentation.navigation.ScreensRouter
import com.mr.anonym.toyonamobile.ui.screens.onBoardingScreen.components.OnBoardingPager
import com.mr.anonym.toyonamobile.ui.screens.onBoardingScreen.components.OnBoardingTopBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun OnboardingScreen(
    navController: NavController
) {
    val context = LocalContext.current
    val activityContext = LocalActivity.current
    val sharedPreferences = SharedPreferencesInstance(context)
    val primaryColor = if(isSystemInDarkTheme()) Color.Black else Color.White
    val secondaryColor = if (isSystemInDarkTheme()) Color.White else Color.Black
    val tertiaryColor = if (isSystemInDarkTheme()) Color.DarkGray else Color.LightGray
    val quaternaryColor = Color.Red
    val page = remember { mutableIntStateOf(0) }

    Scaffold(
        contentColor = primaryColor,
        containerColor = primaryColor,
        topBar = {
            OnBoardingTopBar(
                primaryColor = primaryColor,
                secondaryColor = secondaryColor,
                tertiaryColor = tertiaryColor,
                quaternaryColor = quaternaryColor
            ) {
                sharedPreferences.saveFirstTimeState(false)
                navController.navigate(ScreensRouter.LoginScreen.route) {
                    popUpTo(route = ScreensRouter.OnboardingScreen.route) { inclusive = true }
                }
            }
        }
    ) {padding->
        BackHandler (
            enabled = true
        ){
            navController.popBackStack()
            sharedPreferences.saveFirstTimeState(false)
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.80f)
                    .padding(vertical = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OnBoardingPager(
                    onPageState = {
                        page.intValue = it
                    },
                    secondaryColor)
            }
            Spacer(Modifier.height(10.dp))
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Absolute.Center
            ){
                Icon(
                    modifier = Modifier
                        .size(if (page.intValue == 0 ) 18.dp else 15.dp ),
                    painter = painterResource(R.drawable.ic_round),
                    tint = if (page.intValue == 0) quaternaryColor else tertiaryColor,
                    contentDescription = "null"
                )
                Icon(
                    modifier = Modifier
                        .size(if (page.intValue == 1 ) 18.dp else 15.dp ),
                    painter = painterResource(R.drawable.ic_round),
                    tint = if (page.intValue == 1) quaternaryColor else tertiaryColor,
                    contentDescription = "null"
                )
                Icon(
                    modifier = Modifier
                        .size(if (page.intValue == 2 ) 18.dp else 15.dp ),
                    painter = painterResource(R.drawable.ic_round),
                    tint = if (page.intValue == 2) quaternaryColor else tertiaryColor,
                    contentDescription = "null"
                )
            }
            if (page.intValue == 2){
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .padding(horizontal = 25.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = quaternaryColor
                    ),
                    shape = RoundedCornerShape(10.dp),
                    onClick = {
                        sharedPreferences.saveFirstTimeState(false)
                        navController.navigate(ScreensRouter.LoginScreen.route){
                            popUpTo(ScreensRouter.OnboardingScreen.route ){ inclusive = true }
                        }
                    }
                ){
                    Text(
                        text = stringResource(R.string.next),
                        color = secondaryColor,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewOnBoardingScreen() {
    OnboardingScreen(NavController(LocalContext.current))
}