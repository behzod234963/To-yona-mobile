package com.mr.anonym.toyonamobile.ui.screens.settingsScreen.screen

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.activity.compose.LocalActivity
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.mr.anonym.data.instance.local.DataStoreInstance
import com.mr.anonym.data.instance.local.SharedPreferencesInstance
import com.mr.anonym.toyonamobile.R
import com.mr.anonym.toyonamobile.presentation.extensions.phoneNumberTransformation
import com.mr.anonym.toyonamobile.presentation.navigation.ScreensRouter
import com.mr.anonym.toyonamobile.presentation.notifications.notificationManager
import com.mr.anonym.toyonamobile.presentation.managers.LocaleConfigurations
import com.mr.anonym.toyonamobile.presentation.managers.restartApp
import com.mr.anonym.toyonamobile.ui.screens.settingsScreen.components.LanguageBottomSheet
import com.mr.anonym.toyonamobile.ui.screens.settingsScreen.components.SettingsField
import com.mr.anonym.toyonamobile.ui.screens.settingsScreen.components.SettingsTopBar
import com.mr.anonym.toyonamobile.ui.screens.settingsScreen.components.ThemeBottomSheet
import com.mr.anonym.toyonamobile.ui.screens.settingsScreen.viewModel.SettingsViewModel
import com.mr.anonym.toyonamobile.ui.theme.ShimmerEffectForSettings
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SettingsScreen(
    navController: NavController,
    viewModel: SettingsViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    val activityContext = LocalActivity.current
    val coroutineScope = rememberCoroutineScope()

    val dataStore = DataStoreInstance(context)
    val sharedPreferences = SharedPreferencesInstance(context)
    val localeManager = LocaleConfigurations(context)

    val isDarkTheme = sharedPreferences.getDarkThemeState()
    val isSystemTheme = sharedPreferences.getSystemThemeState()

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
    val fiveColor = Color(101, 163, 119, 255)
    val systemNineColor = if (isSystemInDarkTheme()) Color(0xFF222327) else Color(0xFFF1F2F4)
    val nineColor = when{
        isSystemTheme -> systemNineColor
        isDarkTheme -> Color(0xFF222327)
        else -> Color(0xFFF1F2F4)
    }

    val iosFont = FontFamily(Font(R.font.ios_font))

    val isNotificationsChecked = remember {
        mutableStateOf(
            ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        )
    }
    val isNotificationContentClicked = rememberSaveable { mutableStateOf(false) }

    val profileAvatar = viewModel.profileAvatar
    sharedPreferences.editProfileProcess(false)

    val languageBottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val showLanguageContent = rememberSaveable { mutableStateOf(false) }
    val isUzbekSelected = rememberSaveable { mutableStateOf(false) }
    val isRussianSelected = rememberSaveable { mutableStateOf(false) }
    val localeValue = rememberSaveable { mutableStateOf(sharedPreferences.getLanguage()) }
    val isPrimaryLocale = rememberSaveable { mutableStateOf(true) }

    val themeBottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val isDaySelected = rememberSaveable { mutableStateOf(false) }
    val isNightSelected = rememberSaveable { mutableStateOf(false) }
    val isSystemSelected = rememberSaveable { mutableStateOf(false) }
    val isThemeSelected = rememberSaveable { mutableStateOf(false) }
    val showThemeContent = rememberSaveable { mutableStateOf(false) }

    val personalInteractionSource = remember { MutableInteractionSource() }
    val isPersonalPressed by personalInteractionSource.collectIsPressedAsState()
    val personalScale by animateFloatAsState( if ( isPersonalPressed ) 0.95f else 1f )

    val languageInteractionSource = remember { MutableInteractionSource() }
    val isLanguagePressed by languageInteractionSource.collectIsPressedAsState()
    val languageScale by animateFloatAsState( if ( isLanguagePressed ) 0.95f else 1f )

    val notifyInteractionSource = remember { MutableInteractionSource() }
    val isNotifyPressed by notifyInteractionSource.collectIsPressedAsState()
    val notifyScale by animateFloatAsState( if ( isNotifyPressed ) 0.95f else 1f )

    val themeInteractionSource = remember { MutableInteractionSource() }
    val isThemePressed by themeInteractionSource.collectIsPressedAsState()
    val themeScale by animateFloatAsState( if ( isThemePressed ) 0.95f else 1f )

    val securityInteractionSource = remember { MutableInteractionSource() }
    val isSecurityPressed by securityInteractionSource.collectIsPressedAsState()
    val securityScale by animateFloatAsState( if ( isSecurityPressed ) 0.95f else 1f )

    val isLoading = remember { mutableStateOf( true ) }
    LaunchedEffect(Unit) {
        delay(1500L)
        isLoading.value = false
    }

    val isBiometricAuthOn = sharedPreferences.getIsBiometricAuthOn()

    viewModel.getUserByID()
    val user = viewModel.user

    if (!isThemeSelected.value) {
        when {
            isSystemTheme -> {
                isSystemSelected.value = true
                isDaySelected.value = false
                isNightSelected.value = false
            }

            isDarkTheme -> {
                isNightSelected.value = true
                isDaySelected.value = false
                isSystemSelected.value = false
            }

            !isDarkTheme -> {
                isDaySelected.value = true
                isNightSelected.value = false
                isSystemSelected.value = false
            }
        }
    }
    if (isPrimaryLocale.value) {
        when {
            localeValue.value?.contains("uz") == true -> {
                isUzbekSelected.value = true
                isRussianSelected.value = false
                Log.d("UtilsLogging", "SettingsScreen: ${localeValue.value}")
            }

            localeValue.value?.contains("ru") == true -> {
                isRussianSelected.value = true
                isUzbekSelected.value = false
                Log.d("UtilsLogging", "SettingsScreen: ${localeValue.value}")
            }
        }
    }
    Scaffold(
        containerColor = primaryColor,
        contentColor = primaryColor,
        topBar = {
            SettingsTopBar(
                primaryColor = primaryColor,
                secondaryColor = secondaryColor,
                fontFamily = iosFont,
                onActionsClick = {
                    sharedPreferences.editProfileProcess(true)
                    navController.navigate(ScreensRouter.ProfileScreen.route)
                }
            ) {
                navController.navigate(ScreensRouter.MainScreen.route) {
                    popUpTo(ScreensRouter.SettingsScreen.route) { inclusive = true }
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(7.dp)
        ) {
            if (isLoading.value){
                ShimmerEffectForSettings()
            }else{
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        modifier = Modifier
                            .size(60.dp),
                        painter = painterResource(profileAvatar.value),
                        contentDescription = ""
                    )
                    Spacer(Modifier.height(7.dp))
                    Text(
                        text = "${user.value.username} ${user.value.surname}",
                        fontSize = 16.sp,
                        color = secondaryColor,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = iosFont
                    )
                    Text(
                        text = "+998${user.value.phonenumber}".phoneNumberTransformation(),
                        fontSize = 16.sp,
                        color = secondaryColor,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = iosFont
                    )
                }
                Spacer(Modifier.height(10.dp))
//            Personal data content
                SettingsField(
                    secondaryColor = secondaryColor,
                    quaternaryColor = quaternaryColor,
                    fiveColor = fiveColor,
                    nineColor = nineColor,
                    scale = personalScale,
                    contentIcon = R.drawable.ic_personal_data,
                    contentTitle = stringResource(R.string.personal_data),
                    isHaveSwitcher = false,
                    isChecked = false,
                    onCheckedChange = { },
                    interactionSource = personalInteractionSource,
                    onClick = {  },
                    fontFamily = iosFont
                )
                Spacer(Modifier.height(10.dp))
//            Language content
                SettingsField(
                    secondaryColor = secondaryColor,
                    quaternaryColor = quaternaryColor,
                    fiveColor = fiveColor,
                    nineColor = nineColor,
                    scale = languageScale,
                    contentIcon = R.drawable.ic_language,
                    contentTitle = stringResource(R.string.language_change),
                    isHaveSwitcher = false,
                    isChecked = false,
                    onCheckedChange = { },
                    interactionSource = languageInteractionSource,
                    onClick = { showLanguageContent.value = true },
                    fontFamily = iosFont
                )
                Spacer(Modifier.height(10.dp))
//            Notification content
                SettingsField(
                    secondaryColor = secondaryColor,
                    quaternaryColor = quaternaryColor,
                    fiveColor = fiveColor,
                    nineColor = nineColor,
                    scale = notifyScale,
                    contentIcon = R.drawable.ic_notifications,
                    contentTitle = stringResource(R.string.allow_notifications),
                    isHaveSwitcher = true,
                    isChecked = isNotificationsChecked.value,
                    onCheckedChange = { isNotificationContentClicked.value = true },
                    interactionSource = notifyInteractionSource,
                    onClick = { isNotificationContentClicked.value = true },
                    fontFamily = iosFont
                )
                Spacer(Modifier.height(10.dp))
//            Theme content
                SettingsField(
                    secondaryColor = secondaryColor,
                    quaternaryColor = quaternaryColor,
                    fiveColor = fiveColor,
                    nineColor = nineColor,
                    scale = themeScale,
                    contentIcon = R.drawable.ic_theme,
                    contentTitle = stringResource(R.string.theme_settings),
                    isHaveSwitcher = false,
                    isChecked = false,
                    onCheckedChange = { },
                    interactionSource = themeInteractionSource,
                    onClick = { showThemeContent.value = true },
                    fontFamily = iosFont
                )
                Spacer(Modifier.height(10.dp))
//            Security content
                SettingsField(
                    secondaryColor = secondaryColor,
                    quaternaryColor = quaternaryColor,
                    fiveColor = fiveColor,
                    nineColor = nineColor,
                    scale = securityScale,
                    contentIcon = R.drawable.ic_security,
                    contentTitle = stringResource(R.string.security),
                    isHaveSwitcher = false,
                    isChecked = false,
                    onCheckedChange = { },
                    interactionSource = securityInteractionSource,
                    onClick = {
                        coroutineScope.launch {
                            if (isBiometricAuthOn) {
                                dataStore.showBiometricAuthManually(true)
                            } else {
                                dataStore.showBiometricAuthManually(false)
                            }
                            dataStore.openSecurityContent(true)
                        }
                        navController.navigate(ScreensRouter.EnterScreen.route)
                    },
                    fontFamily = iosFont
                )
                Spacer(Modifier.height(10.dp))
                if (showLanguageContent.value) {
                    LanguageBottomSheet(
                        tertiaryColor = tertiaryColor,
                        secondaryColor = secondaryColor,
                        quaternaryColor = quaternaryColor,
                        fiveColor = fiveColor,
                        nineColor = nineColor,
                        fontFamily = iosFont,
                        state = languageBottomSheetState,
                        onDismissRequest = { showLanguageContent.value = false },
                        onUzbekSelected = isUzbekSelected.value,
                        onUzbekClick = {
                            activityContext?.let {
                                localeManager.setApplicationLocales(it, "uz")
                            }
                            isUzbekSelected.value = true
                            isRussianSelected.value = false
                            isPrimaryLocale.value = false
                            showLanguageContent.value = false
                        },
                        onRussianSelected = isRussianSelected.value
                    ) {
                        activityContext?.let {
                            localeManager.setApplicationLocales(it, "ru")
                        }
                        isRussianSelected.value = true
                        isUzbekSelected.value = false
                        isPrimaryLocale.value = false
                        showLanguageContent.value = false
                    }
                }
                if (isNotificationContentClicked.value) {
                    notificationManager(context)
                    isNotificationContentClicked.value = false
                }
                if (showThemeContent.value) {
                    ThemeBottomSheet(
                        secondaryColor = secondaryColor,
                        tertiaryColor = tertiaryColor,
                        quaternaryColor = quaternaryColor,
                        fiveColor = fiveColor,
                        nineColor = nineColor,
                        fontFamily = iosFont,
                        state = themeBottomSheetState,
                        onDismissRequest = {
                            showThemeContent.value = false
                        },
                        isDaySelected = isDaySelected.value,
                        onDayClick = {
                            coroutineScope.launch {
                                sharedPreferences.isDarkTheme(false)
                                sharedPreferences.isSystemTheme(false)
                                sharedPreferences.isThemeChanged(true)
                                withContext(Dispatchers.Main) {
                                    isThemeSelected.value = true
                                    isDaySelected.value = true
                                    isNightSelected.value = false
                                    isSystemSelected.value = false
                                    showThemeContent.value = false
                                    restartApp(context)
                                }
                            }
                        },
                        isNightSelected = isNightSelected.value,
                        onNightClick = {
                            coroutineScope.launch {
                                sharedPreferences.isDarkTheme(true)
                                sharedPreferences.isSystemTheme(false)
                                sharedPreferences.isThemeChanged(true)
                                withContext(Dispatchers.Main) {
                                    isThemeSelected.value = true
                                    isDaySelected.value = false
                                    isNightSelected.value = true
                                    isSystemSelected.value = false
                                    showThemeContent.value = false
                                    restartApp(context)
                                }
                            }
                        },
                        isSystemSelected = isSystemSelected.value
                    ) {
                        coroutineScope.launch {
                            sharedPreferences.isDarkTheme(false)
                            sharedPreferences.isSystemTheme(true)
                            sharedPreferences.isThemeChanged(true)
                            withContext(Dispatchers.Main) {
                                isThemeSelected.value = true
                                isDaySelected.value = false
                                isNightSelected.value = false
                                isSystemSelected.value = true
                                showThemeContent.value = false
                                restartApp(context)
                            }
                        }
                    }
                }
            }
        }
    }
}