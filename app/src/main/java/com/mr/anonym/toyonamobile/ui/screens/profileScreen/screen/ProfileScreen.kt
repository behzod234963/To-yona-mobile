package com.mr.anonym.toyonamobile.ui.screens.profileScreen.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mr.anonym.data.instance.local.DataStoreInstance
import com.mr.anonym.data.instance.local.SharedPreferencesInstance
import com.mr.anonym.toyonamobile.R
import com.mr.anonym.toyonamobile.presentation.event.ProfileEvent
import com.mr.anonym.toyonamobile.presentation.extensions.nameChecker
import com.mr.anonym.toyonamobile.presentation.extensions.phoneNumberTransformation
import com.mr.anonym.toyonamobile.presentation.navigation.ScreensRouter
import com.mr.anonym.toyonamobile.ui.screens.profileScreen.components.AvatarContent
import com.mr.anonym.toyonamobile.ui.screens.profileScreen.components.NameField
import com.mr.anonym.toyonamobile.ui.screens.profileScreen.components.ProfileTopBar
import com.mr.anonym.toyonamobile.ui.screens.profileScreen.viewModel.ProfileViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: ProfileViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    val dataStore = DataStoreInstance(context)
    val sharedPreferences = SharedPreferencesInstance(context)

    val isDarkTheme = sharedPreferences.getDarkThemeState()
    val isSystemTheme = sharedPreferences.getSystemThemeState()

    val systemPrimaryColor = if (isSystemInDarkTheme()) Color.Black else Color.White
    val primaryColor = when {
        isSystemTheme-> {
            systemPrimaryColor
        }
        isDarkTheme -> Color.Black
        else -> Color.White
    }
    val systemSecondaryColor = if (isSystemInDarkTheme()) Color.White else Color.Black
    val secondaryColor = when {
        isSystemTheme-> systemSecondaryColor
        isDarkTheme -> Color.White
        else -> Color.Black
    }
    val systemTertiaryColor = if (isSystemInDarkTheme()) Color.DarkGray else Color.LightGray
    val tertiaryColor = when {
        isSystemTheme-> systemTertiaryColor
        isDarkTheme -> Color.DarkGray
        else -> Color.LightGray
    }
    val quaternaryColor = Color.Red

    val phoneNumber = dataStore.getPhoneNumber().collectAsState("")
    val isOldUserState = dataStore.isOldUserState().collectAsState(false)
    val editProfileProcess = sharedPreferences.editProfileProcessState()

    val showAvatarContent = rememberSaveable { mutableStateOf(false) }
    val avatar = viewModel.profileAvatar

    val firstname = viewModel.firstname
    val nameValueError = rememberSaveable { mutableStateOf(false) }

    val lastname = viewModel.lastname
    val lastnameValueError = rememberSaveable { mutableStateOf(false) }

    val snackbarHostState = remember { SnackbarHostState() }
    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    Scaffold(
        containerColor = primaryColor,
        contentColor = primaryColor,
        modifier = Modifier
            .imePadding(),
        topBar = {
            ProfileTopBar(
                primaryColor = primaryColor,
                secondaryColor = secondaryColor,
                onNavigationClick = { navController.navigateUp() }
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.3f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Card(
                    modifier = Modifier
                        .size(90.dp),
                    shape = CircleShape,
                    onClick = {
                        showAvatarContent.value = true
                    }
                ) {
                    Image(
                        painter = painterResource(avatar.value),
                        contentDescription = "man"
                    )
                }
                Text(
                    text = when {
                        isOldUserState.value -> {
                            "${firstname.value} ${lastname.value}"
                        }

                        editProfileProcess -> {
                            "${firstname.value} ${lastname.value}"
                        }

                        else -> {
                            "${firstname.value} ${lastname.value}"
                        }
                    },
                    color = secondaryColor,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = phoneNumber.value.phoneNumberTransformation(),
                    color = secondaryColor,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.6f)
                    .padding(horizontal = 10.dp),
            ) {
                NameField(
                    secondaryColor = secondaryColor,
//                    Firstname field properties
                    nameValue = firstname.value,
                    onNameValueChange = {
                        viewModel.onEvent(ProfileEvent.ChangeFirstname(it))
                        nameValueError.value = !it.nameChecker()
                    },
                    onNameValueEnabledTrailingIconClick = {
                        viewModel.onEvent(
                            ProfileEvent.ChangeFirstname(
                                ""
                            )
                        )
                    },
                    nameValueError = nameValueError.value,
                    //                    Lastname field properties
                    surnameValue = lastname.value,
                    onSurnameValueChange = {
                        viewModel.onEvent(ProfileEvent.ChangeLastname(it))
                        lastnameValueError.value = !it.nameChecker()
                    },
                    surnameValueError = lastnameValueError.value,
                    onSurnameEnabledTrailingIconClick = {
                        viewModel.onEvent(ProfileEvent.ChangeLastname(""))
                    }
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.99f)
                    .padding(horizontal = 10.dp),
                verticalArrangement = Arrangement.Center,
            ) {
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = quaternaryColor,
                        contentColor = quaternaryColor,
                    ),
                    shape = RoundedCornerShape(10.dp),
                    onClick = {
                        if (
                            !nameValueError.value &&
                            !lastnameValueError.value
                        ) {
                            when {
                                isOldUserState.value -> {
                                    coroutineScope.launch {
                                        dataStore.isOldUser(false)
                                    }
                                    sharedPreferences.saveAvatar(avatar.value)
                                    sharedPreferences.saveFirstname(firstname.value)
                                    sharedPreferences.saveLastname(lastname.value)
                                    sharedPreferences.saveIsProfileSettingsState(false)
                                    navController.navigate(ScreensRouter.EnterScreen.route) {
                                        popUpTo(ScreensRouter.ProfileScreen.route) {
                                            inclusive = true
                                        }
                                    }
                                }

                                editProfileProcess -> {
                                    sharedPreferences.saveAvatar(avatar.value)
                                    sharedPreferences.saveFirstname(firstname.value)
                                    sharedPreferences.saveLastname(lastname.value)
                                    sharedPreferences.editProfileProcess(false)
                                    navController.navigate(ScreensRouter.SettingsScreen.route)
                                }

                                else -> {
                                    sharedPreferences.saveAvatar(avatar.value)
                                    sharedPreferences.saveFirstname(firstname.value)
                                    sharedPreferences.saveLastname(lastname.value)
                                    sharedPreferences.saveIsProfileSettingsState(false)
                                    sharedPreferences.saveNewPinState(true)
                                    navController.navigate(ScreensRouter.NewPinScreen.route) {
                                        popUpTo(ScreensRouter.ProfileScreen.route) {
                                            inclusive = true
                                        }
                                    }
                                }
                            }
                        } else {
                            coroutineScope.launch {
                                snackbarHostState.showSnackbar(
                                    message = context.getString(R.string.please_check_validate_places)
                                )
                            }
                        }
                    }
                ) {
                    Text(
                        text = stringResource(R.string.continue_),
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
            if (showAvatarContent.value) {
                AvatarContent(
                    secondaryColor = secondaryColor,
                    tertiaryColor = tertiaryColor,
                    state = bottomSheetState,
                    onDismissRequest = {
                        showAvatarContent.value = false
                    },
                    onDefaultAvatarClick = {
                        viewModel.onEvent(ProfileEvent.ChangeAvatar(it))
                        if (bottomSheetState.isVisible) {
                            showAvatarContent.value = false
                        }
                    },
                    onMaleClick = {
                        viewModel.onEvent(ProfileEvent.ChangeAvatar(it))
                        if (bottomSheetState.isVisible) {
                            showAvatarContent.value = false
                        }
                    }
                ) {
                    viewModel.onEvent(ProfileEvent.ChangeAvatar(it))
                    if (bottomSheetState.isVisible) {
                        showAvatarContent.value = false
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewProfileScreen() {
    ProfileScreen(
        navController = NavController(LocalContext.current)
    )
}