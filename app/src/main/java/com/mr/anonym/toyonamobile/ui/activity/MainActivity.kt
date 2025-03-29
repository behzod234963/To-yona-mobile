package com.mr.anonym.toyonamobile.ui.activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.rememberNavController
import com.mr.anonym.data.instance.local.DataStoreInstance
import com.mr.anonym.toyonamobile.presentation.navigation.NavGraph
import com.mr.anonym.toyonamobile.presentation.navigation.ScreensRouter
import com.mr.anonym.toyonamobile.presentation.utils.BiometricAuthManager
import com.mr.anonym.toyonamobile.presentation.utils.BiometricResult
import com.mr.anonym.toyonamobile.presentation.utils.LocaleConfigurations
import com.mr.anonym.toyonamobile.ui.activity.viewModel.MainActivityViewModel
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

    @Inject
    lateinit var dataStore: DataStoreInstance
    private val viewModel: MainActivityViewModel by viewModels()

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
            ToyonaMobileTheme {
                val navController = rememberNavController()
                NavGraph(navController)
                val isStateOnPause = remember { mutableStateOf(false) }
                val biometricAuthState = dataStore.getBiometricAuthState().collectAsState(false)
                val isBiometricAuthOn = dataStore.getIsBiometricAuthOn().collectAsState(false)
                val isBiometricAuthSuccess = remember { mutableStateOf(false) }
                val biometricResult by promptManager.promptResult.collectAsState(null)
                val enrollLauncher = rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.StartActivityForResult(),
                    onResult = {
                        Toast.makeText(
                            this,
                            "Auth ${promptManager.promptResult}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                )
                LaunchedEffect(
                    isBiometricAuthOn.value,
                    biometricAuthState.value,
                    isBiometricAuthSuccess.value
                ) {
                    if (isBiometricAuthOn.value && biometricAuthState.value && !isBiometricAuthSuccess.value) {
                        promptManager.showBiometricPrompt(
                            title = "Please go fuck",
                            description = "ABCDEFG",
                            negativeButtonText = "Cancel"
                        )
                    }
                }
                LaunchedEffect(biometricResult) {
                    when (biometricResult) {
                        is BiometricResult.AuthenticationSuccess -> {
                            isBiometricAuthSuccess.value = true
                            dataStore.saveBiometricAuthState(false)
                            navController.navigate(ScreensRouter.MainScreen.route)
                        }
                        is BiometricResult.AuthenticationError -> {

                        }
                        BiometricResult.AuthenticationFailed -> {

                        }
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
            dataStore.saveBiometricAuthState(true)
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        CoroutineScope(Dispatchers.Default).launch {
            dataStore.saveBiometricAuthState(true)
        }
    }
}