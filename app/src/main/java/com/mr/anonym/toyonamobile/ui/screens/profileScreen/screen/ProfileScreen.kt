package com.mr.anonym.toyonamobile.ui.screens.profileScreen.screen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
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
import androidx.navigation.NavController
import com.mr.anonym.data.instance.local.DataStoreInstance
import com.mr.anonym.data.instance.local.SharedPreferencesInstance
import com.mr.anonym.toyonamobile.R
import com.mr.anonym.toyonamobile.presentation.extensions.nameChecker
import com.mr.anonym.toyonamobile.presentation.navigation.ScreensRouter
import com.mr.anonym.toyonamobile.ui.screens.profileScreen.components.AvatarContent
import com.mr.anonym.toyonamobile.ui.screens.profileScreen.components.NameField
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navController: NavController
) {

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val dataStore = DataStoreInstance(context)
    val sharedPreferences = SharedPreferencesInstance(context)

    val primaryColor = if (isSystemInDarkTheme()) Color.Black else Color.White
    val secondaryColor = if (isSystemInDarkTheme()) Color.White else Color.Black
    val tertiaryColor = if (isSystemInDarkTheme()) Color.DarkGray else Color.LightGray
    val quaternaryColor = Color.Red

    val showAvatarContent = rememberSaveable { mutableStateOf( false ) }
    val avatar = rememberSaveable { mutableIntStateOf( R.drawable.ic_default_avatar ) }

    val nameValue = rememberSaveable { mutableStateOf("") }
    val nameValueError = rememberSaveable { mutableStateOf(false) }

    val surnameValue = rememberSaveable { mutableStateOf("") }
    val surnameValueError = rememberSaveable { mutableStateOf(false) }

    val bottomSheetState = rememberModalBottomSheetState()
    coroutineScope.launch { bottomSheetState.hide() }

    val phoneNumber = dataStore.getPhoneNumber().collectAsState("")

    Scaffold(
        containerColor = primaryColor,
        contentColor = primaryColor,
        modifier = Modifier
            .imePadding()
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
                Spacer(Modifier.height(10.dp))
                Text(
                    text = "${nameValue.value} ${surnameValue.value}",
                    color = secondaryColor,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(Modifier.height(10.dp))
                Text(
                    text = phoneNumber.value,
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
                Spacer(Modifier.height(20.dp))
                NameField(
                    secondaryColor = secondaryColor,
                    nameValue = nameValue.value,
                    onNameValueChange = {
                        nameValue.value = it
                        nameValueError.value = !it.nameChecker()
                    },
                    nameValueTrailingIcon = {
                        nameValue.value = ""
                    },
                    nameValueError = nameValueError.value,
                    surnameValue = surnameValue.value,
                    onSurnameValueChange = {
                        surnameValue.value = it
                        surnameValueError.value = !it.nameChecker()
                    },
                    surnameValueTrailingIcon = {
                        surnameValue.value = ""
                    },
                    surnameValueError = surnameValueError.value
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
                    onClick = {
                        if (
                            !nameValueError.value &&
                            !surnameValueError.value
                        ) {
                            coroutineScope.launch {
                                dataStore.saveAvatar(avatar.value)
                                dataStore.saveFirstname(firstname = nameValue.value)
                                dataStore.saveLastname(lastname = surnameValue.value)
                            }
                            sharedPreferences.saveIsProfileSettingsState(false)
                            sharedPreferences.saveNewPinState(true)
                            navController.navigate(ScreensRouter.NewPinScreen.route){
                                popUpTo(ScreensRouter.ProfileScreen.route){ inclusive = true }
                            }
                        } else {
                            Toast.makeText(
                                context,
                                context.getString(R.string.please_check_validate_places),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                ) {
                    Text(
                        text = stringResource(R.string.continue_),
                        color = secondaryColor,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
            if (showAvatarContent.value){
                AvatarContent(
                    primaryColor = primaryColor,
                    tertiaryColor = tertiaryColor,
                    state = bottomSheetState,
                    onDismissRequest = {
                        showAvatarContent.value = false
                    },
                    onDefaultAvatarClick = {
                        avatar.value = it
                        if (bottomSheetState.isVisible) {
                            showAvatarContent.value = false
                        }
                    },
                    onMaleClick = {
                        avatar.value = it
                        if (bottomSheetState.isVisible) {
                            showAvatarContent.value = false
                        }
                    },
                    onFemaleClick = {
                        avatar.value = it
                        if (bottomSheetState.isVisible) {
                            showAvatarContent.value = false
                        }
                    }
                )
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