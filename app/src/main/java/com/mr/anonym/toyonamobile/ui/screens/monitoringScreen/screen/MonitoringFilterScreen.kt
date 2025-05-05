package com.mr.anonym.toyonamobile.ui.screens.monitoringScreen.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mr.anonym.data.instance.local.DataStoreInstance
import com.mr.anonym.toyonamobile.R
import com.mr.anonym.toyonamobile.presentation.navigation.ScreensRouter
import com.mr.anonym.toyonamobile.ui.screens.monitoringScreen.components.FilterTopBar
import com.mr.anonym.toyonamobile.ui.screens.monitoringScreen.items.FilterItem
import com.mr.anonym.toyonamobile.ui.screens.monitoringScreen.items.MonitoringViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MonitoringFilterScreen(
    navController: NavController,
    viewModel: MonitoringViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    val dataStore = DataStoreInstance(context)

    val isDarkTheme = dataStore.getDarkThemeState().collectAsState(false)
    val isSystemTheme = dataStore.getSystemThemeState().collectAsState(true)

    val systemPrimaryColor = if (isSystemInDarkTheme()) Color.Black else Color.White
    val primaryColor = when {
        isSystemTheme.value -> {
            systemPrimaryColor
        }

        isDarkTheme.value -> Color.Black
        else -> Color.White
    }
    val systemSecondaryColor = if (isSystemInDarkTheme()) Color.White else Color.Black
    val secondaryColor = when {
        isSystemTheme.value -> systemSecondaryColor
        isDarkTheme.value -> Color.White
        else -> Color.Black
    }
    val quaternaryColor = Color.Red
    val fiverdColor = Color.Green
    val systemSevenrdColor = if (isSystemInDarkTheme()) Color.Unspecified else Color.White
    val sevenrdColor = when {
        isSystemTheme.value -> systemSevenrdColor
        isDarkTheme.value -> Color.Unspecified
        else -> Color.White
    }

    val isExpanded = rememberSaveable { mutableStateOf(false) }
    val isSelected = rememberSaveable { mutableStateOf(0) }
    val selectedCard = rememberSaveable { mutableStateOf("") }
    val cards = viewModel.cards

    Scaffold(
        containerColor = primaryColor,
        contentColor = primaryColor,
        topBar = { FilterTopBar(primaryColor, secondaryColor) {
            navController.navigate(ScreensRouter.MonitoringScreen.route){
                popUpTo(ScreensRouter.MonitoringFilterScreen.route){ inclusive = true }
                launchSingleTop = true
            }
        } }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(paddingValues)
                .padding(10.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.9f)
            ) {
                FilterItem(
                    secondaryColor = secondaryColor,
                    quaternaryColor = quaternaryColor,
                    fiverdColor = fiverdColor,
                    sevenrdColor = sevenrdColor,
                    isExpanded = isExpanded.value,
                    isSelected = isSelected.value,
                    selectedCard = selectedCard.value,
                    onFilterClick = { isExpanded.value = !isExpanded.value },
                    onCardClick = { card,index->
                        selectedCard.value = card
                        isSelected.value = index
                    },
                    cards = cards.value
                )
            }
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = quaternaryColor,
                    contentColor = quaternaryColor
                ),
                shape = RoundedCornerShape(10.dp),
                onClick = {
                    navController.navigate(ScreensRouter.MonitoringScreen.route){
                        popUpTo(ScreensRouter.MonitoringFilterScreen.route){ inclusive = true }
                        launchSingleTop = true
                    }
                }
            ) {
                Text(
                    text = stringResource(R.string.Confirm),
                    fontSize = 16.sp,
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}