package com.mr.anonym.toyonamobile.ui.screens.mainScreen.screen

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
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
//    Context
    val context = LocalContext.current
    val activityContext = LocalActivity.current
    val coroutineScope = rememberCoroutineScope()

//    Object
    val sharedPreferences = SharedPreferencesInstance(context)
    val permissionController = PermissionController(context)
    val dataStore = DataStoreInstance(context)

//    State
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

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
    val systemTertiaryColor = if (isSystemInDarkTheme()) Color.DarkGray else Color.LightGray
    val tertiaryColor = when {
        isSystemTheme -> systemTertiaryColor
        isDarkTheme -> Color.DarkGray
        else -> Color.LightGray
    }
    val quaternaryColor = Color.Red
    val fiveColor = Color(101, 163, 119, 255)
    val sixColor = Color.Blue
    val systemSevenColor = if (isSystemInDarkTheme()) Color.Unspecified else Color.White
    val sevenColor = when {
        isSystemTheme -> systemSevenColor
        isDarkTheme -> Color.Unspecified
        else -> Color.White
    }
    val systemEightColor = if (isSystemInDarkTheme()) MaterialTheme.colorScheme.background else Color.LightGray
    val eightColor = when {
        isSystemTheme -> systemEightColor
        isDarkTheme -> MaterialTheme.colorScheme.background
        else -> Color.LightGray
    }
    val componentColor = Color(67, 123, 205, 255)
    val errorContentColor = Color.Red
    val systemItemsColor = if (isSystemInDarkTheme()) Color(16, 15, 15, 255) else Color.White
    val itemsColor = when {
        isSystemTheme -> systemItemsColor
        isDarkTheme -> Color(16, 15, 15, 255)
        else -> Color.White
    }
    val systemNineColor = if (isSystemInDarkTheme()) Color(0xFF222327) else Color(0xFFF1F2F4)
    val nineColor = when{
        isSystemTheme -> systemNineColor
        isDarkTheme -> Color(0xFF222327)
        else -> Color(0xFFF1F2F4)
    }
    val tenColor = Color(0xFF259BD6)
    
//    Card gradients

//    1.
    val greenGradient = Brush.linearGradient(
        colors = listOf(
            Color(0xFF00C853),
            Color(0xFFB2FF59)
        )
    )
//    2.
    val redGradient = Brush.linearGradient(
        colors = listOf(
            Color(0xFFFF1744),
            Color(0xFFFF8A80)
        )
    )
//    3.
    val purpleGradient = Brush.linearGradient(
        colors = listOf(
            Color(0xFF7C4DFF),
            Color(0xFFB388FF)
        )
    )
//    4.
    val blueGradient = Brush.linearGradient(
        colors = listOf(
            Color(0xFF00B0FF),
            Color(0xFF40C4FF)
        )
    )
//    5.
    val orangeGradient = Brush.linearGradient(
        colors = listOf(
            Color(0xFFFF6D00),
            Color(0xFFFFD600)
        )
    )
//    6.
    val blackGoldGradient = Brush.linearGradient(
        colors = listOf(
            Color(0xFF212121),
            Color(0xFFFFD740)
        )
    )

    val iosFont = FontFamily(Font(R.font.ios_font))

    val smallFontSize = remember { mutableIntStateOf(14) }

    val searchValue = rememberSaveable { mutableStateOf("") }
    val showContacts = rememberSaveable { mutableStateOf(false) }

    val partyModel = remember { mutableStateOf(PartysItem()) }

    val profileAvatar = viewModel.profileAvatar
    val user = viewModel.user
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
                    fontFamily = iosFont,
                    viewModel = viewModel,
                    onProfileClick = {
                        navController.navigate(ScreensRouter.SettingsScreen.route)
                    },
                    onFriendsClick = {
                        if (drawerState.isOpen) {
                            coroutineScope.launch {
                                drawerState.close()
                                delay(250)
                                withContext(Dispatchers.Main) {
                                    navController.navigate(ScreensRouter.FriendsScreen.route)
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
                                    navController.navigate(ScreensRouter.MyPartiesScreen.route)
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
                        fiveColor = fiveColor
                    ) { navController.navigate(ScreensRouter.AddPartyScreen.route + "/-1") }
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
                        .padding(paddingValues)
                        .padding(10.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    MainScreenSearchField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding( vertical = 5.dp)
                            .clickable { showContacts.value = true },
                        secondaryColor = secondaryColor,
                        eightColor = nineColor,
                        tertiaryColor = tertiaryColor,
                        fontFamily = iosFont,
                        value = searchValue.value,
                        onValueChange = {
                            showContacts.value = true
                            if (it.isEmpty() || it.isBlank()) showContacts.value = false
                            searchValue.value = it
                        }
                    ) {
                        viewModel.searchUser(searchValue.value)
                    }
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
                                    sevenColor = sevenColor,
                                    smallFontSize = smallFontSize.intValue,
                                    fontFamily = iosFont,
                                    partyModel = partyModel.value,
                                    userModel = userModel,
                                    showContacts = showContacts.value
                                ) { navController.navigate(ScreensRouter.DetailsScreen.route + "/${userModel.id}") }
                            }
                        }
                    } else {
                        MainScreenTabRow(
                            primaryColor = primaryColor,
                            secondaryColor = secondaryColor,
                            fontFamily = iosFont,
                            tabs = tabs,
                            content = { contentItem ->
                                selectedTabIndex.intValue = contentItem
                                LazyColumn(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .background(color = nineColor, shape = RoundedCornerShape(10.dp))
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
                                                            sevenColor = sevenColor,
                                                            smallFontSize = smallFontSize.intValue,
                                                            fontFamily = iosFont,
                                                            partyModel = party,
                                                            userModel = user.value,
                                                            showContacts = showContacts.value
                                                        ) {
                                                            navController.navigate(ScreensRouter.DetailsScreen.route + "/${party.userId}")
                                                        }
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
                                                            sevenColor = sevenColor,
                                                            smallFontSize = smallFontSize.intValue,
                                                            fontFamily = iosFont,
                                                            partyModel = model,
                                                            userModel = user.value,
                                                            showContacts = showContacts.value
                                                        ) {
                                                            navController.navigate(
                                                                ScreensRouter.DetailsScreen.route + "/${model.userId}"
                                                            )
                                                        }
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