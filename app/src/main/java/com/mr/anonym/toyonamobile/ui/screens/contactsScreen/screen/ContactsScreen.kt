package com.mr.anonym.toyonamobile.ui.screens.contactsScreen.screen

import android.annotation.SuppressLint
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.mr.anonym.data.instance.local.DataStoreInstance
import com.mr.anonym.domain.model.FriendsModel
import com.mr.anonym.toyonamobile.presentation.utils.PermissionController
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

    val primaryColor = if (isSystemInDarkTheme()) Color.Black else Color.White
    val secondaryColor = if (isSystemInDarkTheme()) Color.White else Color.Black
    val tertiaryColor = if (isSystemInDarkTheme()) Color.DarkGray else Color.LightGray
    val quaternaryColor = Color.Red
    val fiverdColor = Color.Green
    val sixrdColor = Color.Blue
    val sevenrdColor = if (isSystemInDarkTheme()) Color.Unspecified else primaryColor

    val searchBarValue = rememberSaveable { mutableStateOf( "" ) }
    val showSearchBar = rememberSaveable { mutableStateOf( false ) }

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
    ) {paddingValues ->
        LazyColumn (
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ){
            items( contactList ){ model->
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