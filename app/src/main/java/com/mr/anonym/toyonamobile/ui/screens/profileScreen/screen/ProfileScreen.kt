package com.mr.anonym.toyonamobile.ui.screens.profileScreen.screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.mr.anonym.data.instance.local.DataStoreInstance
import com.mr.anonym.data.instance.local.SharedPreferencesInstance
import com.mr.anonym.domain.model.UserModelItem
import com.mr.anonym.toyonamobile.R
import com.mr.anonym.toyonamobile.presentation.event.ProfileEvent
import com.mr.anonym.toyonamobile.presentation.extensions.nameChecker
import com.mr.anonym.toyonamobile.presentation.extensions.phoneNumberTransformation
import com.mr.anonym.toyonamobile.presentation.navigation.ScreensRouter
import com.mr.anonym.toyonamobile.ui.screens.profileScreen.components.AvatarContent
import com.mr.anonym.toyonamobile.ui.screens.profileScreen.components.NameField
import com.mr.anonym.toyonamobile.ui.screens.profileScreen.viewModel.ProfileViewModel
import com.mr.anonym.toyonamobile.ui.theme.ShimmerEffectForProfile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@SuppressLint("CoroutineCreationDuringComposition")
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
    val fiveColor = Color(101, 163, 119, 255)
    val systemNineColor = if (isSystemInDarkTheme()) Color(0xFF222327) else Color(0xFFF1F2F4)
    val nineColor = when{
        isSystemTheme -> systemNineColor
        isDarkTheme -> Color(0xFF222327)
        else -> Color(0xFFF1F2F4)
    }

    val iosFont = FontFamily(Font(R.font.ios_font))

    val isOldUserState = dataStore.isOldUserState().collectAsState(false)
    val editProfileProcess = sharedPreferences.editProfileProcessState()

    val user = viewModel.user
    val phoneNumber = viewModel.phoneNumber
    val firstname = viewModel.firstname
    val nameValueError = rememberSaveable { mutableStateOf(false) }
    val lastname = viewModel.lastname
    val lastnameValueError = rememberSaveable { mutableStateOf(false) }

    val snackbarHostState = remember { SnackbarHostState() }
    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val focusRequester = remember { FocusRequester() }

    val loadingAnimation = rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.anim_loading)
    )
    val showAvatarContent = rememberSaveable { mutableStateOf(false) }
    val avatar = viewModel.profileAvatar
    val avatarIndex = viewModel.avatarIndex
    val isSendResponse = remember { mutableStateOf(false) }
    val isLoading = remember { mutableStateOf(true) }
    LaunchedEffect(Unit) {
        delay(1500L)
        isLoading.value = false
        focusRequester.requestFocus()
    }

    Scaffold(
        containerColor = nineColor,
        contentColor = nineColor,
        modifier = Modifier
            .imePadding(),
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        if (!isSendResponse.value) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                if (isLoading.value) {
                    ShimmerEffectForProfile()
                } else {
                    Spacer(Modifier.height(10.dp))
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(horizontal = 10.dp, vertical = 5.dp)
                            .background(color = primaryColor, shape = RoundedCornerShape(15.dp))
                            .padding(10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Row (
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp),
                            verticalAlignment = Alignment.Top,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ){
                            IconButton(
                                onClick = { navController.navigateUp() }
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.ic_back),
                                    tint = secondaryColor,
                                    contentDescription = ""
                                )
                            }
                            Column (
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ){
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
                                Spacer(Modifier.height(10.dp))
                                Text(
                                    text = "${firstname.value} ${lastname.value}",
                                    color = secondaryColor,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    fontFamily = iosFont
                                )
                                Text(
                                    text = "+998${user.value.phonenumber}".phoneNumberTransformation(),
                                    color = secondaryColor,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 16.sp,
                                    fontFamily = iosFont
                                )
                            }
                            IconButton(
                                onClick = { showAvatarContent.value = true }
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.ic_edit),
                                    tint = secondaryColor,
                                    contentDescription = ""
                                )
                            }
                        }
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(vertical = 5.dp, horizontal = 10.dp)
                            .background(color = primaryColor, shape = RoundedCornerShape(15.dp))
                            .padding(10.dp),
                    ) {
                        NameField(
                            secondaryColor = secondaryColor,
                            tertiaryColor = nineColor,
                            eightColor = nineColor,
                            fontFamily = iosFont
//                    Firstname field properties
                            ,
                            focusedRequester = focusRequester,
                            nameValue = firstname.value,
                            //                    Lastname field properties
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
                            surnameValue = lastname.value,
                            onSurnameValueChange = {
                                viewModel.onEvent(ProfileEvent.ChangeLastname(it))
                                lastnameValueError.value = !it.nameChecker()
                            },
                            surnameValueError = lastnameValueError.value,
                        ) {
                            viewModel.onEvent(ProfileEvent.ChangeLastname(""))
                        }
                        Spacer(Modifier.height(10.dp))
                        Button(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = fiveColor,
                                contentColor = fiveColor,
                            ),
                            shape = RoundedCornerShape(10.dp),
                            onClick = {
                                if (
                                    !nameValueError.value &&
                                    !lastnameValueError.value
                                ) {
                                    isSendResponse.value = true
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
                                fontWeight = FontWeight.SemiBold,
                                fontFamily = iosFont
                            )
                        }
                        Spacer(Modifier.height(20.dp))
                    }
                }
                if (showAvatarContent.value) {
                    AvatarContent(
                        secondaryColor = secondaryColor,
                        tertiaryColor = nineColor,
                        fiveColor = fiveColor,
                        state = bottomSheetState,
                        fontFamily = iosFont,
                        avatarIndex = avatarIndex.value,
                        onDismissRequest = {
                            showAvatarContent.value = false
                        },
                        onDefaultAvatarClick = {
                            viewModel.onEvent(ProfileEvent.ChangeAvatar(it))
                            viewModel.onEvent(ProfileEvent.ChangeIndex(0))
                            if (bottomSheetState.isVisible) {
                                showAvatarContent.value = false
                            }
                        },
                        onMaleClick = {
                            viewModel.onEvent(ProfileEvent.ChangeAvatar(it))
                            viewModel.onEvent(ProfileEvent.ChangeIndex(1))
                            if (bottomSheetState.isVisible) {
                                showAvatarContent.value = false
                            }
                        }
                    ) {
                        viewModel.onEvent(ProfileEvent.ChangeIndex(2))
                        viewModel.onEvent(ProfileEvent.ChangeAvatar(it))
                        if (bottomSheetState.isVisible) {
                            showAvatarContent.value = false
                        }
                    }
                }
            }
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                LottieAnimation(
                    modifier = Modifier
                        .size(150.dp),
                    composition = loadingAnimation.value,
                    restartOnPlay = true,
                    iterations = LottieConstants.IterateForever
                )
                when {
                    isOldUserState.value -> {
                        coroutineScope.launch {
                            delay(1500)
                            coroutineScope.launch {
                                dataStore.isOldUser(false)
                            }
                            sharedPreferences.saveIsProfileSettingsState(false)
                            sharedPreferences.saveNewPinState(true)
                            viewModel.updateUser(
                                user = UserModelItem(
                                    username = firstname.value,
                                    surname = lastname.value,
                                    sex = avatarIndex.value,
                                    phonenumber = phoneNumber.value,
                                    password = user.value.password
                                )
                            )
                            withContext(Dispatchers.Main) {
                                navController.navigate(ScreensRouter.NewPinScreen.route) {
                                    popUpTo(ScreensRouter.ProfileScreen.route) {
                                        inclusive = true
                                    }
                                }
                            }
                        }
                    }

                    editProfileProcess -> {
                        coroutineScope.launch {
                            delay(1500)
                            sharedPreferences.editProfileProcess(false)
                            viewModel.updateUser(
                                user = UserModelItem(
                                    username = firstname.value,
                                    surname = lastname.value,
                                    sex = avatarIndex.value,
                                    phonenumber = user.value.phonenumber,
                                    password = user.value.password
                                )
                            )
                            withContext(Dispatchers.Main) {
                                navController.navigate(ScreensRouter.SettingsScreen.route) {
                                    popUpTo(ScreensRouter.ProfileScreen.route) {
                                        inclusive = true
                                    }
                                }
                            }
                        }
                    }

                    else -> {
                        coroutineScope.launch {
                            viewModel.updateUser(
                                user = UserModelItem(
                                    username = firstname.value,
                                    surname = lastname.value,
                                    sex = avatarIndex.value,
                                    phonenumber = user.value.phonenumber,
                                    password = user.value.password
                                )
                            )
                            delay(1500)
                            sharedPreferences.saveIsProfileSettingsState(false)
                            sharedPreferences.saveNewPinState(true)
                            withContext(Dispatchers.Main) {
                                navController.navigate(ScreensRouter.NewPinScreen.route) {
                                    popUpTo(ScreensRouter.ProfileScreen.route) {
                                        inclusive = true
                                    }
                                }
                            }
                        }
                        Log.d(
                            "UtilsLogging",
                            "ProfileScreen: ${phoneNumber.value}"
                        )
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