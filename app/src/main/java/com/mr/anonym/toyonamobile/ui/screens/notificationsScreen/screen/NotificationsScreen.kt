package com.mr.anonym.toyonamobile.ui.screens.notificationsScreen.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mr.anonym.data.instance.local.SharedPreferencesInstance
import com.mr.anonym.domain.model.NotificationsModel
import com.mr.anonym.toyonamobile.ui.screens.notificationsScreen.components.NotificationsTopBar
import com.mr.anonym.toyonamobile.ui.screens.notificationsScreen.items.NotificationsItem

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NotificationsScreen(
    navController: NavController
) {

//    Context
    val context = LocalContext.current

//    Object
    val sharedPreferences = SharedPreferencesInstance(context)

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
    val systemNineColor = if (isSystemInDarkTheme()) Color(0xFF222327) else Color(0xFFF1F2F4)
    val nineColor = when{
        isSystemTheme -> systemNineColor
        isDarkTheme -> Color(0xFF222327)
        else -> Color(0xFFF1F2F4)
    }

//    List
    val notifications = listOf(
        NotificationsModel(
            id = 1,
            title = "Худайберганов Бехзод Sunnat to'y",
            description = "Худайберганов Бехзод Sunnat to'y \n Orzu to'yxonasi \n 22-mart 2025",
            date = "31.03.2025"
        ),
        NotificationsModel(
            id = 1,
            title = "Худайберганов Бехзод Sunnat to'y",
            description = "Худайберганов Бехзод Sunnat to'y,Orzu to'yxonasi,22-mart 2025",
            date = "31.03.2025"
        )
    )

//    UI
    Scaffold(
        topBar = {
            NotificationsTopBar(
                primaryColor = primaryColor,
                secondaryColor = secondaryColor,
                onNavigationIconClick = {
                    navController.navigateUp()
                }
            )
        },
        contentColor = primaryColor,
        containerColor = primaryColor
    ) {paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(10.dp)
        ) {
            items(notifications){notification->
                NotificationsItem(
                    secondaryColor = secondaryColor,
                    nineColor = nineColor,
                    notification = notification
                )
            }
        }
    }
}
@Preview
@Composable
private fun PreviewNotificationsScreen() {
    NotificationsScreen(NavController(LocalContext.current))
}