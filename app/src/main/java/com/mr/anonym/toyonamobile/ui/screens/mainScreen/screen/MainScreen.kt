package com.mr.anonym.toyonamobile.ui.screens.mainScreen.screen

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.mr.anonym.data.instance.local.DataStoreInstance
import com.mr.anonym.data.instance.local.SharedPreferencesInstance
import com.mr.anonym.domain.model.PartysItem
import com.mr.anonym.toyonamobile.R
import com.mr.anonym.toyonamobile.presentation.managers.PermissionController
import com.mr.anonym.toyonamobile.presentation.navigation.ScreensRouter
import com.mr.anonym.toyonamobile.ui.screens.mainScreen.components.MainScreenFAB
import com.mr.anonym.toyonamobile.ui.screens.mainScreen.components.MainScreenModalDrawerSheet
import com.mr.anonym.toyonamobile.ui.screens.mainScreen.components.MainScreenSearchField
import com.mr.anonym.toyonamobile.ui.screens.mainScreen.components.MainScreenTabRow
import com.mr.anonym.toyonamobile.ui.screens.mainScreen.components.MainScreenTopBar
import com.mr.anonym.toyonamobile.ui.screens.mainScreen.item.MainScreenItem
import com.mr.anonym.toyonamobile.ui.screens.mainScreen.viewModel.MainScreenViewModel
import com.mr.anonym.toyonamobile.ui.theme.ShimmerEffectForUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainScreenViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    val activityContext = LocalActivity.current

    val sharedPreferences = SharedPreferencesInstance(context)
    val permissionController = PermissionController(context)
    val dataStore = DataStoreInstance(context)
    val coroutineScope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

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
    val systemTertiaryColor = if (isSystemInDarkTheme()) Color.DarkGray else Color.LightGray
    val tertiaryColor = when {
        isSystemTheme -> systemTertiaryColor
        isDarkTheme -> Color.DarkGray
        else -> Color.LightGray
    }
    val quaternaryColor = Color.Red
    val fiverdColor = Color.Green
    val sixrdColor = Color.Blue
    val systemSevenrdColor = if (isSystemInDarkTheme()) Color.Unspecified else Color.White
    val sevenrdColor = when {
        isSystemTheme -> systemSevenrdColor
        isDarkTheme -> Color.Unspecified
        else -> Color.White
    }
    val systemEightrdColor = if (isSystemInDarkTheme()) Color.Gray else Color.LightGray
    val eightrdColor = when{
        isSystemTheme -> systemEightrdColor
        isDarkTheme -> Color.Gray
        else -> Color.LightGray
    }

    val smallFontSize = remember { mutableIntStateOf(14) }

    val profileAvatar = viewModel.profileAvatar

    val searchValue = rememberSaveable { mutableStateOf("") }
    val showContacts = rememberSaveable { mutableStateOf(false) }

    val partyModel = remember { mutableStateOf(PartysItem()) }

    val partyList = viewModel.parties.collectAsLazyPagingItems()
    val userList = viewModel.users

    permissionController.requestNotificationPermission(activityContext)
    permissionController.requestExternalStoragePermission(activityContext!!)
    sharedPreferences.isThemeChanged(false)

    val isRefresh = viewModel.isRefresh
    val pullToRefreshState = rememberPullToRefreshState()
    val loadingAnimation = rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.anim_loading)
    )

    val user = viewModel.user

    val selectedTabIndex = remember { mutableIntStateOf(0) }
    val tabs = listOf(
        stringResource(R.string.general_parties),
        stringResource(R.string.other_parties)
    )

    coroutineScope.launch {
        dataStore.isOnline(true)
    }
    BackHandler {
        coroutineScope.launch {
            dataStore.isOnline(false)
            withContext(Dispatchers.Main) {
                activityContext.finish()
            }
        }
    }
    PullToRefreshBox(
        isRefreshing = viewModel.isRefresh.value,
        state = pullToRefreshState,
        onRefresh = {
            viewModel.getAllParty(partyList)
        },
    ) {
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                MainScreenModalDrawerSheet(
                    smallFontSize = smallFontSize.intValue,
                    primaryColor = primaryColor,
                    secondaryColor = secondaryColor,
                    tertiaryColor = tertiaryColor,
                    profileAvatar = profileAvatar.value,
                    viewModel = viewModel,
                    onFriendsClick = {
                        if (drawerState.isOpen) {
                            coroutineScope.launch {
                                drawerState.close()
                                delay(250)
                                withContext(Dispatchers.Main) {
                                    navController.navigate(ScreensRouter.ContactsScreen.route)
                                }
                            }
                        }
                    },
                    onMyEventsClick = {
                        if (drawerState.isOpen) {
                            coroutineScope.launch {
                                drawerState.close()
                                delay(250)
                                withContext(Dispatchers.Main) {
                                    navController.navigate(ScreensRouter.MyEventsScreen.route)
                                }
                            }
                        }
                    },
                    onMonitoringClick = {
                        if (drawerState.isOpen) {
                            coroutineScope.launch {
                                drawerState.close()
                                delay(250)
                                withContext(Dispatchers.Main) {
                                    navController.navigate(ScreensRouter.MonitoringScreen.route)
                                }
                            }
                        }
                    },
                    onWalletClick = {
                        if (drawerState.isOpen) {
                            coroutineScope.launch {
                                drawerState.close()
                                delay(250)
                                withContext(Dispatchers.Main) {
                                    navController.navigate(ScreensRouter.WalletScreen.route)
                                }
                            }
                        }
                    },
                    onSettingsClick = {
                        if (drawerState.isOpen) {
                            coroutineScope.launch {
                                drawerState.close()
                                delay(250)
                                withContext(Dispatchers.Main) {
                                    navController.navigate(ScreensRouter.SettingsScreen.route)
                                }
                            }
                        }
                    }
                ) {
                    if (drawerState.isOpen) {
                        coroutineScope.launch {
                            drawerState.close()
                            delay(250)
                            withContext(Dispatchers.Main) {
                                navController.navigate(ScreensRouter.SupportScreen.route)
                            }
                        }
                    }
                }
            }
        ) {
            Scaffold(
                containerColor = primaryColor,
                contentColor = primaryColor,
                floatingActionButton = {
                    MainScreenFAB(
                        secondaryColor = secondaryColor,
                        quaternaryColor = quaternaryColor,
                        onFabClick = { navController.navigate(ScreensRouter.AddPartyScreen.route + "/-1") }
                    )
                },
                topBar = {
                    MainScreenTopBar(
                        primaryColor = primaryColor,
                        secondaryColor = secondaryColor,
                        title = stringResource(R.string.app_name),
                        navigationIcon = profileAvatar.value,
                        onActionsClick = {
                            navController.navigate(ScreensRouter.NotificationsScreen.route)
                        }
                    ) {
                        coroutineScope.launch {
                            if (drawerState.isOpen) drawerState.close() else drawerState.open()
                        }
                    }
                }
            ) { paddingValues ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    MainScreenSearchField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp, vertical = 5.dp)
                            .clickable { showContacts.value = true },
                        primaryColor = primaryColor,
                        secondaryColor = secondaryColor,
                        tertiaryColor = tertiaryColor,
                        value = searchValue.value,
                        onValueChange = {
                            showContacts.value = true
                            if (it.isEmpty() || it.isBlank()) showContacts.value = false
                            searchValue.value = it
                        },
                        onSearch = {
                            viewModel.searchUser(searchValue.value)
                        }
                    )
                    if (showContacts.value) {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            items(userList.value) { userModel ->
                                MainScreenItem(
                                    primaryColor = primaryColor,
                                    secondaryColor = secondaryColor,
                                    tertiaryColor = tertiaryColor,
                                    sevenrdColor = sevenrdColor,
                                    smallFontSize = smallFontSize.intValue,
                                    partyModel = partyModel.value,
                                    userModel = userModel,
                                    showContacts = showContacts.value,
                                    onItemClick = { navController.navigate(ScreensRouter.DetailsScreen.route + "/${userModel.id}") }
                                )
                            }
                        }
                    } else {
                        MainScreenTabRow(
                            primaryColor = primaryColor,
                            secondaryColor = secondaryColor,
                            tabs = tabs,
                            content = { contentItem ->
                                selectedTabIndex.intValue = contentItem
                                LazyColumn(
                                    modifier = Modifier
                                        .fillMaxSize()
                                ) {
                                    when (selectedTabIndex.intValue) {
                                        0 -> {
//                                                viewModel.closerParties
//                                            LazyColumn{
                                                if (!viewModel.isRefresh.value) {
                                                    items(viewModel.closerParties.distinctBy { it.id }.sortedByDescending { it.type }) { party ->
                                                        MainScreenItem(
                                                            primaryColor = primaryColor,
                                                            secondaryColor = secondaryColor,
                                                            tertiaryColor = tertiaryColor,
                                                            sevenrdColor = sevenrdColor,
                                                            smallFontSize = smallFontSize.intValue,
                                                            partyModel = party,
                                                            userModel = user.value,
                                                            showContacts = showContacts.value,
                                                            onItemClick = {
                                                                navController.navigate(ScreensRouter.DetailsScreen.route + "/${party.userId}")
                                                            }
                                                        )
                                                    }
                                                } else {
                                                    items(20) {
                                                        ShimmerEffectForUser()
                                                    }
                                                }
//                                            }
                                        }

                                        1 -> {
                                            if (isRefresh.value){
                                                items(20){
                                                    ShimmerEffectForUser()
                                                }
                                            }else{
                                                items(
                                                    count = partyList.itemCount,
                                                    key = partyList.itemKey { it.id }
                                                ) { index ->
                                                    val model = partyList[index]
                                                    if (model != null) {
                                                        partyModel.value = model
                                                        MainScreenItem(
                                                            primaryColor = primaryColor,
                                                            secondaryColor = secondaryColor,
                                                            tertiaryColor = tertiaryColor,
                                                            sevenrdColor = sevenrdColor,
                                                            smallFontSize = smallFontSize.intValue,
                                                            partyModel = model,
                                                            userModel = user.value,
                                                            showContacts = showContacts.value,
                                                            onItemClick = {
                                                                navController.navigate(
                                                                    ScreensRouter.DetailsScreen.route + "/${model.userId}"
                                                                )
                                                            }
                                                        )
                                                    }
                                                }
                                                item {
                                                    if (partyList.loadState.append is LoadState.Loading) {
                                                        Row (
                                                            modifier = Modifier
                                                                .fillMaxWidth()
                                                                .padding(vertical = 10.dp),
                                                            horizontalArrangement = Arrangement.Center
                                                        ){
                                                            LottieAnimation(
                                                                modifier = Modifier
                                                                    .size(150.dp),
                                                                composition = loadingAnimation.value,
                                                                restartOnPlay = true,
                                                                iterations = LottieConstants.IterateForever
                                                            )
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}