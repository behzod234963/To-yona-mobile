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
import androidx.navigation.NavController
import com.mr.anonym.domain.model.NotificationsModel
import com.mr.anonym.toyonamobile.ui.screens.notificationsScreen.components.NotificationsTopBar
import com.mr.anonym.toyonamobile.ui.screens.notificationsScreen.items.NotificationsItem

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NotificationsScreen(
    navController: NavController
) {

    val primaryColor = if (isSystemInDarkTheme()) Color.Black else Color.White
    val secondaryColor = if (isSystemInDarkTheme()) Color.White else Color.Black
    val tertiaryColor = if (isSystemInDarkTheme()) Color.DarkGray else Color.LightGray

    val notifications = listOf<NotificationsModel>(
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

    Scaffold(
        topBar = {
            NotificationsTopBar(
                primaryColor = primaryColor,
                secondaryColor = secondaryColor,
                onNavigationIconClick = {
                    navController.popBackStack()
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
        ) {
            items(notifications){notification->
                NotificationsItem(
                    secondaryColor = secondaryColor,
                    tertiaryColor = tertiaryColor,
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