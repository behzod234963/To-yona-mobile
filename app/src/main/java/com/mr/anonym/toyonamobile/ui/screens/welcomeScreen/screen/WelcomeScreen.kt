package com.mr.anonym.toyonamobile.ui.screens.welcomeScreen.screen

import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalActivity
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.mr.anonym.data.instance.local.SharedPreferencesInstance
import com.mr.anonym.toyonamobile.R
import com.mr.anonym.toyonamobile.presentation.managers.LocaleConfigurations
import com.mr.anonym.toyonamobile.presentation.navigation.ScreensRouter
import com.mr.anonym.toyonamobile.ui.components.HorizontalButton

@Composable
fun WelcomeScreen(
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
    val systemNineColor = if (isSystemInDarkTheme()) Color(0xFF222327) else Color(0xFFF1F2F4)
    val nineColor = when{
        isSystemTheme -> systemNineColor
        isDarkTheme -> Color(0xFF222327)
        else -> Color(0xFFF1F2F4)
    }

//    State
    val localeValue = remember {
        mutableStateOf(
            localeManager.getPrimaryLocale().replaceFirstChar { it.titlecase() })
    }
    val uzbekButtonInteractionSource = remember { MutableInteractionSource() }
    val isUzbekButtonPressed by uzbekButtonInteractionSource.collectIsPressedAsState()
    val uzbekButtonScale by animateFloatAsState( if ( isUzbekButtonPressed ) 0.90f else 0.95f )
    val russianButtonInteractionSource = remember { MutableInteractionSource() }
    val isRussianButtonPressed by russianButtonInteractionSource.collectIsPressedAsState()
    val russianButtonScale by animateFloatAsState( if ( isRussianButtonPressed ) 0.90f else 0.95f )

    val iosFont = FontFamily(Font(R.font.ios_font))

    BackHandler {
        sharedPreferences.isLanguageSelected(false)
        activityContext?.finish()
    }
    Scaffold(
        containerColor = primaryColor,
        contentColor = primaryColor
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(150.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(10.dp)
                    .background(color = nineColor, shape = RoundedCornerShape(15.dp)),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier
                        .size(50.dp),
                    painter = painterResource(R.drawable.ic_language),
                    contentDescription = ""
                )
                Text(
                    text = stringResource(R.string.welcome),
                    fontSize = 22.sp,
                    color = secondaryColor,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = iosFont
                )
                Spacer(Modifier.height(15.dp))
                Text(
                    text = stringResource(R.string.language_instruction),
                    fontSize = 16.sp,
                    color = secondaryColor,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = iosFont
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
//                    .padding(10.dp)
                    .background(color = primaryColor, shape = RoundedCornerShape(15.dp)),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
//                    Uzbek button
                HorizontalButton(
                    buttonColor = nineColor,
                    interactionSource = uzbekButtonInteractionSource,
                    scale = uzbekButtonScale,
                    onClick = {
                        activityContext?.let {
                            localeManager.setApplicationLocales(it, "uz")
                        }
                        localeValue.value = "o'zbek".replaceFirstChar { it.titlecase() }
                        sharedPreferences.isLanguageSelected(true)
                        navController.navigate(ScreensRouter.OnboardingScreen.route)
                    }
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Image(
                                painter = painterResource(R.drawable.ic_uz_flag),
                                contentDescription = ""
                            )
                            Spacer(Modifier.width(10.dp))
                            Text(
                                text = stringResource(R.string.o_zbekcha),
                                color = secondaryColor,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold,
                                fontFamily = iosFont
                            )
                        }
                        Icon(
                            modifier = Modifier
                                .size(30.dp),
                            painter = painterResource(R.drawable.ic_keyboard_right),
                            contentDescription = "",
                            tint = tertiaryColor
                        )
                    }
                }
                Spacer(Modifier.height(10.dp))
//                    Russian button
                HorizontalButton(
                    interactionSource = russianButtonInteractionSource,
                    scale = russianButtonScale,
                    buttonColor = nineColor,
                    onClick = {
                        activityContext?.let {
                            localeManager.setApplicationLocales(it, "ru")
                        }
                        localeValue.value = "русский".replaceFirstChar { it.titlecase() }
                        sharedPreferences.isLanguageSelected(true)
                        navController.navigate(ScreensRouter.OnboardingScreen.route)
                    }
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Image(
                                painter = painterResource(R.drawable.ic_ru_flag),
                                contentDescription = ""
                            )
                            Spacer(Modifier.width(10.dp))
                            Text(
                                text = stringResource(R.string.russian),
                                color = secondaryColor,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold,
                                fontFamily = iosFont
                            )
                        }
                        Icon(
                            modifier = Modifier
                                .size(30.dp),
                            painter = painterResource(R.drawable.ic_keyboard_right),
                            contentDescription = "",
                            tint = tertiaryColor
                        )
                    }
                }
            }
        }
    }
}
@Preview
@Composable
private fun PreviewWelcomeScreen() {
    WelcomeScreen(navController = NavController(LocalContext.current))
}