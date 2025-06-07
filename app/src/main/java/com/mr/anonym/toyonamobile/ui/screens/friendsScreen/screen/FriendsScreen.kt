package com.mr.anonym.toyonamobile.ui.screens.friendsScreen.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mr.anonym.data.instance.local.SharedPreferencesInstance
import com.mr.anonym.toyonamobile.presentation.navigation.ScreensRouter
import com.mr.anonym.toyonamobile.ui.screens.friendsScreen.components.FriendsTopBar
import com.mr.anonym.toyonamobile.ui.screens.friendsScreen.item.ContactsItem
import com.mr.anonym.toyonamobile.ui.screens.friendsScreen.viewModel.FriendsViewModel
import com.mr.anonym.toyonamobile.ui.theme.ShimmerEffectForUser

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FriendsScreen(
    navController: NavController,
    viewModel: FriendsViewModel = hiltViewModel()
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
    val systemSevenrdColor = if (isSystemInDarkTheme()) Color.Unspecified else Color.White
    val sevenrdColor = when {
        isSystemTheme -> systemSevenrdColor
        isDarkTheme -> Color.Unspecified
        else -> Color.White
    }

    val isRefresh = viewModel.isRefresh.collectAsState()
    val friends = viewModel.friends
    val swipeRefreshState = rememberPullToRefreshState()

    PullToRefreshBox(
        modifier = Modifier
            .fillMaxSize(),
        state = swipeRefreshState,
        isRefreshing = isRefresh.value,
        onRefresh = { viewModel.isLoading() }
    ) {
        Scaffold(
            containerColor = primaryColor,
            contentColor = primaryColor,
            topBar = {
                FriendsTopBar(
                    primaryColor = primaryColor,
                    secondaryColor = secondaryColor,
                    onNavigationClick = { navController.navigateUp() }
                )
            }
        ) { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                if (isRefresh.value){
                    items(20) {
                        ShimmerEffectForUser()
                    }
                }else{
                    items(friends.value) { model ->
                        ContactsItem(
                            secondaryColor = secondaryColor,
                            sevenrdColor = sevenrdColor,
                            friend = model.friend,
                            onContactClick = { navController.navigate(ScreensRouter.DetailsScreen.route + "/${model.friendId}") }
                        )
                    }
                }
            }
        }
    }
}