package com.mr.anonym.toyonamobile.ui.screens.settingsScreen.screen

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.Image
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
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.mr.anonym.data.instance.local.DataStoreInstance
import com.mr.anonym.data.instance.local.SharedPreferencesInstance
import com.mr.anonym.toyonamobile.R
import com.mr.anonym.toyonamobile.presentation.navigation.ScreensRouter
import com.mr.anonym.toyonamobile.presentation.utils.LocaleConfigurations
import com.mr.anonym.toyonamobile.ui.screens.personalScreen.components.LanguageBottomSheet
import com.mr.anonym.toyonamobile.ui.screens.settingsScreen.components.SettingsField
import com.mr.anonym.toyonamobile.ui.screens.settingsScreen.components.SettingsTopBar
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SettingsScreen(
    navController: NavController
) {

    val context = LocalContext.current
    val activityContext = LocalActivity.current
    val coroutineScope = rememberCoroutineScope()

    val dataStore = DataStoreInstance(context)
    val sharedPreferences = SharedPreferencesInstance(context)
    val localeManager = LocaleConfigurations(context)

    val primaryColor = if (isSystemInDarkTheme()) Color.Black else Color.White
    val secondaryColor = if (isSystemInDarkTheme()) Color.White else Color.Black
    val tertiaryColor = if (isSystemInDarkTheme()) Color.DarkGray else Color.LightGray
    val quaternaryColor = Color.Red
    val fiverdColor = Color.Green
    val sixrdColor = Color.Blue
    val sevenrdColor = if (isSystemInDarkTheme()) Color.Unspecified else primaryColor

    val isNotificationsChecked = remember { mutableStateOf(false) }

    val profileAvatar = dataStore.getAvatar().collectAsState(R.drawable.ic_default_avatar)
    val firstName = dataStore.getFirstname().collectAsState("")
    val lastName = dataStore.getLastname().collectAsState("")
    val phoneNumber = dataStore.getPhoneNumber().collectAsState("")
    sharedPreferences.editProfileProcess(false)

    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    coroutineScope.launch { bottomSheetState.hide() }
    val showLanguageContent = rememberSaveable { mutableStateOf( false ) }
    val isUzbekSelected = rememberSaveable { mutableStateOf( false ) }
    val isRussianSelected = rememberSaveable { mutableStateOf( false ) }
    val localeValue = rememberSaveable { mutableStateOf( sharedPreferences.getLanguage() ) }
    val isPrimaryLocale = rememberSaveable { mutableStateOf( true ) }

    if (isPrimaryLocale.value){
        when{
            localeValue.value?.contains("uz") == true ->{
                isUzbekSelected.value = true
                isRussianSelected.value = false
                Log.d("UtilsLogging", "SettingsScreen: ${localeValue.value}")
            }
            localeValue.value?.contains("ru") == true ->{
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
                onActionsClick = {
                    sharedPreferences.editProfileProcess(true)
                    navController.navigate(ScreensRouter.ProfileScreen.route)
                },
                onNavigationClick = { navController.popBackStack() }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(7.dp)
        ) {
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
                    text = "${firstName.value} ${lastName.value}",
                    fontSize = 16.sp,
                    color = secondaryColor,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = phoneNumber.value,
                    fontSize = 16.sp,
                    color = secondaryColor,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Spacer(Modifier.height(10.dp))
//            Personal data content
            SettingsField(
                secondaryColor = secondaryColor,
                quaternaryColor = quaternaryColor,
                fiverdColor = fiverdColor,
                sevenrdColor = sevenrdColor,
                contentIcon = R.drawable.ic_personal_data,
                contentTitle = stringResource(R.string.personal_data),
                isHaveSwitcher = false,
                isChecked = false,
                onCheckedChange = { },
                onContentClick = { TODO() }
            )
//            Language content
            SettingsField(
                secondaryColor = secondaryColor,
                quaternaryColor = quaternaryColor,
                fiverdColor = fiverdColor,
                sevenrdColor = sevenrdColor,
                contentIcon = R.drawable.ic_language,
                contentTitle = stringResource(R.string.language_change),
                isHaveSwitcher = false,
                isChecked = false,
                onCheckedChange = { },
                onContentClick = { showLanguageContent.value = true }
            )
//            Notification content
            SettingsField(
                secondaryColor = secondaryColor,
                quaternaryColor = quaternaryColor,
                fiverdColor = fiverdColor,
                sevenrdColor = sevenrdColor,
                contentIcon = R.drawable.ic_notifications,
                contentTitle = stringResource(R.string.allow_notifications),
                isHaveSwitcher = true,
                isChecked = isNotificationsChecked.value,
                onCheckedChange = { TODO() },
                onContentClick = { TODO() }
            )
//            Theme content
            SettingsField(
                secondaryColor = secondaryColor,
                quaternaryColor = quaternaryColor,
                fiverdColor = fiverdColor,
                sevenrdColor = sevenrdColor,
                contentIcon = R.drawable.ic_theme,
                contentTitle = stringResource(R.string.theme_settings),
                isHaveSwitcher = false,
                isChecked = false,
                onCheckedChange = { TODO() },
                onContentClick = { TODO() }
            )
//            Security content
            SettingsField(
                secondaryColor = secondaryColor,
                quaternaryColor = quaternaryColor,
                fiverdColor = fiverdColor,
                sevenrdColor = sevenrdColor,
                contentIcon = R.drawable.ic_security,
                contentTitle = stringResource(R.string.security),
                isHaveSwitcher = false,
                isChecked = false,
                onCheckedChange = { TODO() },
                onContentClick = { TODO() }
            )
            if (showLanguageContent.value){
                LanguageBottomSheet(
                    primaryColor = primaryColor,
                    secondaryColor = secondaryColor,
                    tertiaryColor = tertiaryColor,
                    quaternaryColor = quaternaryColor,
                    fiverdColor = fiverdColor,
                    state = bottomSheetState,
                    onDismissRequest = { showLanguageContent.value = false },
                    onUzbekSelected = isUzbekSelected.value,
                    onUzbekClick = {
                        activityContext?.let {
                            localeManager.setApplicationLocales(it,"uz")
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
        }
    }
}

@Preview
@Composable
private fun PreviewSettingsScreen() {
    SettingsScreen(
        NavController(LocalContext.current)
    )
}