package com.mr.anonym.toyonamobile.ui.screens.contactsScreen.screen

import android.annotation.SuppressLint
import android.content.Intent
import android.provider.ContactsContract
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.mr.anonym.data.instance.local.DataStoreInstance
import com.mr.anonym.domain.model.FriendsModel
import com.mr.anonym.toyonamobile.presentation.utils.OpenNewContactMethod
import com.mr.anonym.toyonamobile.presentation.utils.PermissionController
import com.mr.anonym.toyonamobile.ui.screens.contactsScreen.components.ContactsFAB
import com.mr.anonym.toyonamobile.ui.screens.contactsScreen.components.ContactsTopBar
import com.mr.anonym.toyonamobile.ui.screens.contactsScreen.item.ContactsItem

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ContactsScreen(
    navController: NavController
) {
    val context = LocalContext.current
    val activityContext = LocalActivity.current

    val permissionController = PermissionController(context)
    val dataStore = DataStoreInstance(context)
    val coroutineScope = rememberCoroutineScope()

    val isDarkTheme = dataStore.getDarkThemeState().collectAsState(false)
    val iSystemTheme = dataStore.getSystemThemeState().collectAsState(true)

    val systemPrimaryColor = if (isSystemInDarkTheme()) Color.Black else Color.White
    val primaryColor = when {
        iSystemTheme.value -> {
            systemPrimaryColor
        }

        isDarkTheme.value -> Color.Black
        else -> Color.White
    }
    val systemSecondaryColor = if (isSystemInDarkTheme()) Color.White else Color.Black
    val secondaryColor = when {
        iSystemTheme.value -> systemSecondaryColor
        isDarkTheme.value -> Color.White
        else -> Color.Black
    }
    val systemTertiaryColor = if (isSystemInDarkTheme()) Color.DarkGray else Color.LightGray
    val tertiaryColor = when {
        iSystemTheme.value -> systemTertiaryColor
        isDarkTheme.value -> Color.DarkGray
        else -> Color.LightGray
    }
    val quaternaryColor = Color.Red
    val fiverdColor = Color.Green
    val sixrdColor = Color.Blue
    val systemSevenrdColor = if (isSystemInDarkTheme()) Color.Unspecified else Color.White
    val sevenrdColor = when {
        iSystemTheme.value -> systemSevenrdColor
        isDarkTheme.value -> Color.Unspecified
        else -> Color.White
    }

    val searchBarValue = rememberSaveable { mutableStateOf("") }
    val showSearchBar = rememberSaveable { mutableStateOf(false) }

    val contactsLauncher = OpenNewContactMethod(context)

    val contactList = listOf<FriendsModel>(
        FriendsModel(
            id = 1,
            userId = 2,
            name = "Behzod",
            surname = "Xudaybergenov",
            phone = "+998973570498",
            cardNumber = "",
            datetime = "Recently"
        ),
        FriendsModel(
            id = 1,
            userId = 2,
            name = "Behzod",
            surname = "Xudaybergenov",
            phone = "+998973570498",
            cardNumber = "",
            datetime = "Recently"
        ),
        FriendsModel(
            id = 1,
            userId = 2,
            name = "Behzod",
            surname = "Xudaybergenov",
            phone = "+998973570498",
            cardNumber = "",
            datetime = "Recently"
        ),
        FriendsModel(
            id = 1,
            userId = 2,
            name = "Behzod",
            surname = "Xudaybergenov",
            phone = "+998973570498",
            cardNumber = "",
            datetime = "Recently"
        )
    )

    Scaffold(
        containerColor = primaryColor,
        contentColor = primaryColor,
        floatingActionButton = {
            ContactsFAB(
                primaryColor = primaryColor,
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
                onNavigationClick = { navController.popBackStack() },
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
            items(contactList) { model ->
                ContactsItem(
                    secondaryColor = secondaryColor,
                    tertiaryColor = tertiaryColor,
                    sevenrdColor = sevenrdColor,
                    friendsModel = model
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewContactsScreen() {
    ContactsScreen(
        NavController(LocalContext.current)
    )
}