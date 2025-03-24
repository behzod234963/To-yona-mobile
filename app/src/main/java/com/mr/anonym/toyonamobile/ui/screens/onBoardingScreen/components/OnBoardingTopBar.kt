package com.mr.anonym.toyonamobile.ui.screens.onBoardingScreen.components

import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mr.anonym.data.instance.local.SharedPreferencesInstance
import com.mr.anonym.toyonamobile.R
import com.mr.anonym.toyonamobile.presentation.utils.LocaleConfigurations

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnBoardingTopBar(
    onSkipClick: () -> Unit,
    secondaryColor: Color,
    tertiaryColor: Color,
    quaternaryColor: Color
) {

    val context = LocalContext.current
    val activityContext = LocalActivity.current
    val localeManager = LocaleConfigurations(context)
    val sharedPreferences = SharedPreferencesInstance(context)
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val localeValue = remember { mutableStateOf( localeManager.getPrimaryLocale() ) }
    val expanded = remember { mutableStateOf( false ) }

    TopAppBar(
        navigationIcon = {
            TextButton(
                onClick = { onSkipClick() }
            ) {
                Text(
                    text = stringResource(R.string.skip),
                    color = quaternaryColor,
                    fontSize = 18.sp
                )
            }
        },
        title = { },
        actions = {
            OnBoardingChangeLanguageDropDown(
                expanded = expanded.value,
                value = localeValue.value,
                onUzbekClick = {
                    sharedPreferences.saveLanguage("uz")
                    localeManager.apply {
                        activityContext?.setLocale("uz")
                    }
                    expanded.value = false
                },
                onRussianClick = {
                    sharedPreferences.saveLanguage("ru")
                    localeManager.apply {
                        activityContext?.setLocale("ru")
                    }
                    expanded.value = false
                },
                secondaryColor = secondaryColor,
                tertiaryColor = tertiaryColor
            )
            Spacer(Modifier.width(10.dp))
        },
        scrollBehavior = scrollBehavior
    )
}