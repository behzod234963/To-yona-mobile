package com.mr.anonym.toyonamobile.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.mr.anonym.data.instance.local.DataStoreInstance
import com.mr.anonym.toyonamobile.presentation.navigation.NavGraph
import com.mr.anonym.toyonamobile.presentation.utils.LocaleConfigurations
import com.mr.anonym.toyonamobile.ui.theme.ToyonaMobileTheme
import dagger.Component
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject lateinit var localeConfig: LocaleConfigurations

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        localeConfig.apply {
            this@MainActivity.applySavedLanguage()
        }
        enableEdgeToEdge()
        setContent {
            ToyonaMobileTheme {
                val language =
                NavGraph()
            }
        }
    }
}