package com.mr.anonym.toyonamobile.ui.screens.contactsScreen.screen

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.provider.ContactsContract
import android.provider.Settings
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.mr.anonym.data.instance.local.SharedPreferencesInstance
import com.mr.anonym.toyonamobile.R
import com.mr.anonym.toyonamobile.presentation.navigation.ScreensRouter
import com.mr.anonym.toyonamobile.presentation.utils.OpenNewContactMethod
import com.mr.anonym.toyonamobile.presentation.utils.PermissionController
import com.mr.anonym.toyonamobile.ui.screens.contactsScreen.components.ContactsBottomBar
import com.mr.anonym.toyonamobile.ui.screens.contactsScreen.components.ContactsFAB
import com.mr.anonym.toyonamobile.ui.screens.contactsScreen.components.ContactsTopBar
import com.mr.anonym.toyonamobile.ui.screens.contactsScreen.item.ContactsItem
import com.mr.anonym.toyonamobile.ui.screens.contactsScreen.viewModel.ContactsViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ContactsScreen(
    navController: NavController,
    viewModel: ContactsViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val activity = LocalActivity.current
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
    val contacts = viewModel.contacts
    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    val contactsLauncher = OpenNewContactMethod(context)
    val swipeRefreshState = rememberPullToRefreshState()

    val contactsPermissionAnimation = rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.ic_contact_permission)
    )
    activity?.let {
        permissionController.ContactsPermissionController(
            context
        ) {
            isPermissionGranted.value = true
            viewModel.isLoading(context, isPermissionGranted.value)
            requestPermission.value = false
            buttonValue.intValue = R.string.refresh
        }
    }

    PullToRefreshBox(
        modifier = Modifier
            .fillMaxSize(),
        state = swipeRefreshState,
        isRefreshing = isRefresh.value,
        onRefresh = { viewModel.isLoading(context, isPermissionGranted.value) }
    ) {
        Scaffold(
            containerColor = primaryColor,
            contentColor = primaryColor,
            floatingActionButton = {
                ContactsFAB(
                    quaternaryColor = quaternaryColor
                ) {
                    val intent = Intent(ContactsContract.Intents.Insert.ACTION).apply {
                        type = ContactsContract.RawContacts.CONTENT_TYPE
                    }
                    contactsLauncher.launch(intent)
                }
            },
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
            if (isPermissionGranted.value) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                ) {
                    items(contacts.value) { model ->
                        ContactsItem(
                            secondaryColor = secondaryColor,
                            sevenrdColor = sevenrdColor,
                            friendsModel = model,
                            onContactClick = { navController.navigate(ScreensRouter.DetailsScreen.route) }
                        )
                    }
                }
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    LottieAnimation(
                        modifier = Modifier
                            .size(230.dp),
                        composition = contactsPermissionAnimation.value,
                        alignment = Alignment.Center,
                        restartOnPlay = true,
                        iterations = LottieConstants.IterateForever
                    )
                    Spacer(Modifier.height(10.dp))
                    Text(
                        text = stringResource(R.string.to_show_contacts_you_need_to_give_permission_for_read_contacts),
                        fontSize = 16.sp,
                        color = secondaryColor,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(Modifier.height(10.dp))
                    Button(
                        colors = ButtonDefaults.buttonColors(
                            containerColor = quaternaryColor,
                            contentColor = quaternaryColor
                        ),
                        shape = RoundedCornerShape(10.dp),
                        onClick = {
                            activity?.let {
                                if (permissionController.checkReadContactsPermission(it)) {
                                    isPermissionDenied.value = true
                                }else{
                                    isPermissionGranted.value = true
                                    viewModel.isLoading(context,isPermissionGranted.value)
                                }
                            }
                        }
                    ) {
                        Text(
                            text = stringResource(buttonValue.intValue),
                            fontSize = 16.sp,
                            color = Color.White,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
            if (isPermissionDenied.value) {
                ContactsBottomBar(
                    secondaryColor = secondaryColor,
                    tertiaryColor = tertiaryColor,
                    quaternaryColor = quaternaryColor,
                    sixrdColor = sixrdColor,
                    sheetState = bottomSheetState,
                    onDismissRequest = {
                        isPermissionDenied.value = false
                        if (bottomSheetState.isVisible) coroutineScope.launch { bottomSheetState.hide() }
                    },
                    onSettingsClick = {
                        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                            data = Uri.fromParts("package", context.packageName, null)
                        }
                        context.startActivity(intent)
                        buttonValue.intValue = R.string.refresh
                    }
                )
            }
            if (requestPermission.value) {
                activity?.let {
                    permissionController.ContactsPermissionController(
                        context
                    ) {
                        isPermissionGranted.value = true
                        viewModel.isLoading(context, isPermissionGranted.value)
                        requestPermission.value = false
                        buttonValue.intValue = R.string.refresh
                    }
                }
            }
        }
    }
}