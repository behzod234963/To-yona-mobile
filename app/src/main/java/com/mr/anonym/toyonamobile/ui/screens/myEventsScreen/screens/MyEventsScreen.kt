package com.mr.anonym.toyonamobile.ui.screens.myEventsScreen.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mr.anonym.domain.model.MyEventsModel
import com.mr.anonym.toyonamobile.presentation.navigation.ScreensRouter
import com.mr.anonym.toyonamobile.ui.screens.myEventsScreen.components.MyEventTopBar
import com.mr.anonym.toyonamobile.ui.screens.myEventsScreen.components.MyEventsItem

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MyEventsScreen(
    navController: NavController
) {

    val primaryColor = if (isSystemInDarkTheme()) Color.Black else Color.White
    val secondaryColor = if (isSystemInDarkTheme()) Color.White else Color.Black
    val tertiaryColor = if (isSystemInDarkTheme()) Color.DarkGray else Color.LightGray
    val quaternaryColor = Color.Red
    val fiverdColor = Color.Green
    val sixrdColor = Color.Blue
    val sevenrdColor = if (isSystemInDarkTheme()) Color.Unspecified else primaryColor

    val isEventStatus = rememberSaveable { mutableStateOf(true) }

    val events = listOf<MyEventsModel>(
        MyEventsModel(
            eventStatus = true,
            eventType = "Wedding",
            eventDateTime = "05.04.2025 , 06.04.2025  12:00",
            cardHolder = "BEKHZOD KHUDAYBERGENOV",
            cardNumber = "9860030160619356"
        ),
        MyEventsModel(
            eventStatus = false,
            eventType = "Wedding",
            eventDateTime = "05.04.2025 , 06.04.2025  12:00",
            cardHolder = "BEKHZOD KHUDAYBERGENOV",
            cardNumber = "9860030160619356"
        ),
        MyEventsModel(
            eventStatus = false,
            eventType = "Wedding",
            eventDateTime = "05.04.2025 , 06.04.2025  12:00",
            cardHolder = "BEKHZOD KHUDAYBERGENOV",
            cardNumber = "9860030160619356"
        ),
        MyEventsModel(
            eventStatus = false,
            eventType = "Wedding",
            eventDateTime = "05.04.2025 , 06.04.2025  12:00",
            cardHolder = "BEKHZOD KHUDAYBERGENOV",
            cardNumber = "9860030160619356"
        )
    )

    Scaffold(
        containerColor = sevenrdColor,
        contentColor = sevenrdColor,
        topBar = {
            MyEventTopBar(
                primaryColor = primaryColor,
                secondaryColor = secondaryColor
            ) { navController.popBackStack() }
        }
    ) { paddingValues ->
        LazyColumn (
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(10.dp)
        ){
            items(events){ model->
                MyEventsItem(
                    secondaryColor = secondaryColor,
                    quaternaryColor = quaternaryColor,
                    fiverdColor = fiverdColor,
                    sevenrdColor = sevenrdColor,
                    myEventsModel = model,
                    onEditClick = {
                        navController.navigate(ScreensRouter.AddEventScreen.route)
                    },
                    onCheckedChange = {

                    }
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewMyEventsScreen() {
    MyEventsScreen(
        NavController(LocalContext.current)
    )
}