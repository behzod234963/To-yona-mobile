package com.mr.anonym.toyonamobile.ui.screens.contactsScreen.screen

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.provider.ContactsContract
import androidx.annotation.RequiresApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.mr.anonym.data.instance.local.SharedPreferencesInstance
import com.mr.anonym.domain.model.FriendsModel
import com.mr.anonym.toyonamobile.presentation.utils.OpenNewContactMethod
import com.mr.anonym.toyonamobile.presentation.utils.getContacts
import com.mr.anonym.toyonamobile.ui.screens.contactsScreen.components.ContactsFAB
import com.mr.anonym.toyonamobile.ui.screens.contactsScreen.components.ContactsTopBar
import com.mr.anonym.toyonamobile.ui.screens.contactsScreen.item.ContactsItem

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ContactsScreen(
    navController: NavController
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
    val systemTertiaryColor = if (isSystemInDarkTheme()) Color.DarkGray else Color.LightGray
    val tertiaryColor = when {
        isSystemTheme -> systemTertiaryColor
        isDarkTheme -> Color.DarkGray
        else -> Color.LightGray
    }
    val quaternaryColor = Color.Red
    val systemSevenrdColor = if (isSystemInDarkTheme()) Color.Unspecified else Color.White
    val sevenrdColor = when {
        isSystemTheme -> systemSevenrdColor
        isDarkTheme -> Color.Unspecified
        else -> Color.White
    }

    val searchBarValue = rememberSaveable { mutableStateOf("") }
    val showSearchBar = rememberSaveable { mutableStateOf(false) }

    val contactsLauncher = OpenNewContactMethod(context)

    val contactList = getContacts(context)

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
                    sevenrdColor = sevenrdColor,
                    friendsModel = model
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
private fun PreviewContactsScreen() {
    ContactsScreen(
        NavController(LocalContext.current)
    )
}