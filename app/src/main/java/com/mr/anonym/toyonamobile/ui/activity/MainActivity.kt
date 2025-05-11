package com.mr.anonym.toyonamobile.ui.activity

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.rememberNavController
import com.mr.anonym.data.instance.local.DataStoreInstance
import com.mr.anonym.data.instance.local.SharedPreferencesInstance
import com.mr.anonym.toyonamobile.R
import com.mr.anonym.toyonamobile.presentation.constants.CONTACTS_REQUEST_CODE
import com.mr.anonym.toyonamobile.presentation.navigation.NavGraph
import com.mr.anonym.toyonamobile.presentation.navigation.ScreensRouter
import com.mr.anonym.toyonamobile.presentation.utils.BiometricAuthManager
import com.mr.anonym.toyonamobile.presentation.utils.BiometricResult
import com.mr.anonym.toyonamobile.presentation.utils.LocaleConfigurations
import com.mr.anonym.toyonamobile.presentation.utils.PermissionController
import com.mr.anonym.toyonamobile.ui.theme.ToyonaMobileTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    val promptManager by lazy { BiometricAuthManager(activity = this) }

    @Inject
    lateinit var localeConfig: LocaleConfigurations
    @Inject lateinit var sharedPreferences: SharedPreferencesInstance
    @Inject lateinit var dataStore: DataStoreInstance
    @Inject lateinit var permissionController: PermissionController

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
            localeConfig.apply {
                this@MainActivity.applySavedLanguage()
            }
        }
        enableEdgeToEdge()
        setContent {
            val isSystemTheme = sharedPreferences.getSystemThemeState()
            val isDarkTheme = sharedPreferences.getDarkThemeState()
            ToyonaMobileTheme(
                darkTheme =
                when{
                    isSystemTheme-> isSystemInDarkTheme()
                    isDarkTheme-> true
                    else-> false
                },
                dynamicColor = false
            ) {
                val navController = rememberNavController()
                NavGraph(navController)
                val coroutineScope = rememberCoroutineScope()
                val biometricAuthState = sharedPreferences.getBiometricAuthState()
                val isBiometricAuthOn = sharedPreferences.getIsBiometricAuthOn()
                val showBiometricAuthState = dataStore.showBiometricAuthManuallyState().collectAsState(false)
                val openSecurityContentState = dataStore.openSecurityContentState().collectAsState(false)
                val isBiometricAuthSuccess = remember { mutableStateOf(false) }
                val biometricResult by promptManager.promptResult.collectAsState(null)
                val enrollLauncher = rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.StartActivityForResult(),
                    onResult = {}
                )
                LaunchedEffect(
                    isBiometricAuthOn,
                    biometricAuthState,
                    isBiometricAuthSuccess.value,
                    showBiometricAuthState.value
                ) {
                    if (isBiometricAuthOn && biometricAuthState && !isBiometricAuthSuccess.value || showBiometricAuthState.value) {
                        coroutineScope.launch {
                            dataStore.showBiometricAuthManually(false)
                        }
                        promptManager.showBiometricPrompt(
                            title = getString(R.string.please_verify),
                            description = "",
                            negativeButtonText = getString(R.string.cancel)
                        )
                    }
                }
                LaunchedEffect(biometricResult) {
                    when (biometricResult) {
                        is BiometricResult.AuthenticationSuccess -> {
                            if (!openSecurityContentState.value){
                                coroutineScope.launch {
                                    dataStore.showBiometricAuthManually(false)
                                }
                                isBiometricAuthSuccess.value = true
                                sharedPreferences.saveBiometricAuthState(false)
                                navController.navigate(ScreensRouter.MainScreen.route)
                            }else{
                                coroutineScope.launch {
                                    dataStore.showBiometricAuthManually(false)
                                    dataStore.openSecurityContent(false)
                                }
                                isBiometricAuthSuccess.value = true
                                sharedPreferences.saveBiometricAuthState(false)
                                navController.navigate(ScreensRouter.SecurityScreen.route)
                            }
                        }
                        is BiometricResult.AuthenticationError -> {}
                        BiometricResult.AuthenticationFailed -> {}
                        BiometricResult.AuthenticationNotSet -> {
                            if (Build.VERSION.SDK_INT >= 30) {
                                val enrollIntent = Intent(Settings.ACTION_BIOMETRIC_ENROLL).apply {
                                    putExtra(
                                        Settings.EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED,
                                        BIOMETRIC_STRONG or DEVICE_CREDENTIAL
                                    )
                                }
                                enrollLauncher.launch(enrollIntent)
                            }
                        }
                        BiometricResult.FeatureUnavailable -> {}
                        BiometricResult.HardwareUnavailable -> {}
                        null -> {}
                    }
                }
//                LaunchedEffect(isStateOnPause.value) {
//                    if (isStateOnPause.value) navController.navigate(ScreensRouter.EnterScreen.route)
//                }
            }
        }
    }
    override fun onStop() {
        super.onStop()
        CoroutineScope(Dispatchers.Default).launch {
            dataStore.isPasswordForgotten(false)
            dataStore.openSecurityContent(false)
            dataStore.addCardFromDetails(false)
            dataStore.addCardFromAddEvent(false)
            dataStore.isMainScreenLaunched(false)
        }
        sharedPreferences.saveBiometricAuthState(true)
        sharedPreferences.addCardProcess(false)
    }

    override fun onDestroy() {
        super.onDestroy()
        sharedPreferences.addCardProcess(false)
        sharedPreferences.saveBiometricAuthState(true)
        CoroutineScope(Dispatchers.Default).launch {
            dataStore.isPasswordForgotten(false)
            dataStore.isMainScreenLaunched(false)
            dataStore.openSecurityContent(false)
            dataStore.addCardFromDetails(false)
            dataStore.addCardFromAddEvent(false)
        }
    }
}