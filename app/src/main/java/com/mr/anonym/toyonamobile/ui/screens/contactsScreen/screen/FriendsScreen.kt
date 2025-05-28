package com.mr.anonym.toyonamobile.ui.screens.contactsScreen.screen

import android.annotation.SuppressLint
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.mr.anonym.data.instance.local.SharedPreferencesInstance
import com.mr.anonym.toyonamobile.R
import com.mr.anonym.toyonamobile.presentation.navigation.ScreensRouter
import com.mr.anonym.toyonamobile.presentation.utils.OpenNewContactMethod
import com.mr.anonym.toyonamobile.presentation.utils.PermissionController
import com.mr.anonym.toyonamobile.ui.screens.contactsScreen.components.ContactsTopBar
import com.mr.anonym.toyonamobile.ui.screens.contactsScreen.item.ContactsItem
import com.mr.anonym.toyonamobile.ui.screens.contactsScreen.viewModel.FriendsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FriendsScreen(
    navController: NavController,
    viewModel: FriendsViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    val permissionController = PermissionController(context)
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
    val systemTertiaryColor = if (isSystemInDarkTheme()) Color.DarkGray else Color.LightGray
    val tertiaryColor = when {
        isSystemTheme -> systemTertiaryColor
        isDarkTheme -> Color.DarkGray
        else -> Color.LightGray
    }
    val quaternaryColor = Color.Red
    val sixrdColor = Color.Blue
    val systemSevenrdColor = if (isSystemInDarkTheme()) Color.Unspecified else Color.White
    val sevenrdColor = when {
        isSystemTheme -> systemSevenrdColor
        isDarkTheme -> Color.Unspecified
        else -> Color.White
    }

    val isPermissionGranted = rememberSaveable { mutableStateOf(false) }
    val requestPermission = rememberSaveable { mutableStateOf(false) }
    val searchBarValue = rememberSaveable { mutableStateOf("") }
    val showSearchBar = rememberSaveable { mutableStateOf(false) }
    val isPermissionDenied = remember { mutableStateOf(false) }
    val isRefresh = viewModel.isRefresh.collectAsState()
    val buttonValue = remember { mutableIntStateOf(R.string.give_permission) }
//    val contacts = viewModel.contacts
    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    val friends = viewModel.friends

    val contactsLauncher = OpenNewContactMethod(context)
    val swipeRefreshState = rememberPullToRefreshState()

    val contactsPermissionAnimation = rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.ic_contact_permission)
    )
//    activity?.let {
//        permissionController.ContactsPermissionController(
//            context
//        ) {
//            isPermissionGranted.value = true
//            viewModel.isLoading(context, isPermissionGranted.value)
//            requestPermission.value = false
//            buttonValue.intValue = R.string.refresh
//        }
//    }

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
//            floatingActionButton = {
//                ContactsFAB(
//                    quaternaryColor = quaternaryColor
//                ) {
//                    val intent = Intent(ContactsContract.Intents.Insert.ACTION).apply {
//                        type = ContactsContract.RawContacts.CONTENT_TYPE
//                    }
//                    contactsLauncher.launch(intent)
//                }
//            },
            topBar = {
                ContactsTopBar(
                    primaryColor = primaryColor,
                    secondaryColor = secondaryColor,
                    showSearchBar = showSearchBar.value,
                    value = searchBarValue.value,
                    onValueChange = {
                        searchBarValue.value = it
                    },
                    onNavigationClick = { navController.navigateUp() },
                    onTrailingIconClick = {
                        showSearchBar.value = false
                    },
                    onSend = {
                        TODO()
                    }
                ) { showSearchBar.value = true }
            }
        ) { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
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