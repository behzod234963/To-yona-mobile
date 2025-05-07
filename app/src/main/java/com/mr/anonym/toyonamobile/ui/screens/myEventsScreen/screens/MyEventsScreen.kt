package com.mr.anonym.toyonamobile.ui.screens.myEventsScreen.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mr.anonym.data.instance.local.SharedPreferencesInstance
import com.mr.anonym.toyonamobile.presentation.navigation.ScreensRouter
import com.mr.anonym.toyonamobile.ui.screens.myEventsScreen.components.MyEventTopBar
import com.mr.anonym.toyonamobile.ui.screens.myEventsScreen.components.MyEventsItem
import com.mr.anonym.toyonamobile.ui.screens.myEventsScreen.viewModel.MyEventsViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MyEventsScreen(
    navController: NavController,
    viewModel: MyEventsViewModel = hiltViewModel()
) {

    val context = LocalContext.current

    val sharedPreferences = SharedPreferencesInstance(context)

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
    val quaternaryColor = Color.Red
    val fiverdColor = Color.Green
    val systemSevenrdColor = if (isSystemInDarkTheme()) Color.Unspecified else Color.White
    val sevenrdColor = when {
        isSystemTheme -> systemSevenrdColor
        isDarkTheme -> Color.Unspecified
        else -> Color.White
    }
    val events = viewModel.events

    Scaffold(
        containerColor = primaryColor,
        contentColor = primaryColor,
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
            itemsIndexed(events.value){ index,model->
                MyEventsItem(
                    secondaryColor = secondaryColor,
                    quaternaryColor = quaternaryColor,
                    fiverdColor = fiverdColor,
                    sevenrdColor = sevenrdColor,
                    myEventsModel = model,
                    onEditClick = {
                        navController.navigate(ScreensRouter.AddEventScreen.route + "/${model.id}")
                    },
                    onDeleteClick = {
                        viewModel.deleteEvent(model)
                    },
                    onCheckedChange = {
                        viewModel.updateEventStatus(model.id?:-1,it)
                    },
                ) 
            }
        }
    }
}