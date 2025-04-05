package com.mr.anonym.toyonamobile.ui.screens.mainScreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mr.anonym.toyonamobile.R

@Composable
fun MainScreenModalDrawerSheet(
    smallFontSize:Int,
    mediumFontSize:Int,
    largeFontSize: Int,
    primaryColor: Color,
    secondaryColor: Color,
    tertiaryColor: Color,
    profileTitle: String,
    profileAvatar:Int,
    phoneNumber: String,
    onMainClick:()->Unit,
    onContactsClick:()-> Unit,
    onMyEventsClick:()-> Unit,
    onMonitoringClick:()-> Unit,
    onWalletClick:()->Unit,
    onSettingsClick:()-> Unit,
    onSupportClick:()-> Unit
) {

    val isMainSelected = remember { mutableStateOf( false ) }
    val isMyEvents = remember { mutableStateOf( false ) }
    val isContactsSelected = remember { mutableStateOf( false ) }
    val isMonitoringSelected = remember { mutableStateOf( false ) }
    val isWalletSelected = remember { mutableStateOf( false ) }
    val isSettingsSelected = remember { mutableStateOf( false ) }
    val isSupportSelected = remember { mutableStateOf( false) }

    ModalDrawerSheet(
        drawerContentColor = primaryColor,
        drawerContainerColor = primaryColor
    ) {
        Column(
            modifier = Modifier
                .width(300.dp)
                .padding(vertical = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = primaryColor,
                    contentColor = primaryColor
                ),
                shape = CircleShape
            ) {
                Image(
                    modifier = Modifier
                        .size(60.dp),
                    painter = painterResource(profileAvatar),
                    contentDescription = ""
                )
            }
            Text(
                text = profileTitle,
                color = secondaryColor,
                fontSize = 22.sp
            )
            Text(
                text = phoneNumber,
                color = secondaryColor,
                fontSize = 16.sp
            )
        }
        HorizontalDivider()
//        Main content
        NavigationDrawerItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    tint = secondaryColor,
                    contentDescription = ""
                )
            },
            label = {
                Text(
                    text = stringResource(R.string.main),
                    color = secondaryColor,
                    fontSize = smallFontSize.sp,
                    fontWeight = FontWeight.SemiBold
                )
            },
            selected = isMainSelected.value,
            shape = RectangleShape,
            colors = NavigationDrawerItemDefaults.colors(
                selectedContainerColor = tertiaryColor,
                unselectedContainerColor = primaryColor
            ),
            onClick = {
                isMainSelected.value = true
                isContactsSelected.value = false
                isMyEvents.value = false
                isMonitoringSelected.value = false
                isWalletSelected.value = false
                isSettingsSelected.value = false
                isSupportSelected.value = false
                onMainClick()
            }
        )
        HorizontalDivider()
//        Contacts
        NavigationDrawerItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    tint = secondaryColor,
                    contentDescription = ""
                )
            },
            label = {
                Text(
                    text = stringResource(R.string.contacts),
                    color = secondaryColor,
                    fontSize = smallFontSize.sp,
                    fontWeight = FontWeight.SemiBold
                )
            },
            selected = isContactsSelected.value,
            shape = RectangleShape,
            colors = NavigationDrawerItemDefaults.colors(
                selectedContainerColor = tertiaryColor,
                unselectedContainerColor = primaryColor
            ),
            onClick = {
                isMainSelected.value = false
                isContactsSelected.value = true
                isMyEvents.value = false
                isMonitoringSelected.value = false
                isWalletSelected.value = false
                isSettingsSelected.value = false
                isSupportSelected.value = false
                onContactsClick()
            }
        )
//        My events
        NavigationDrawerItem(
            icon = {
                Icon(
                    painter = painterResource(R.drawable.ic_event_vector),
                    tint = secondaryColor,
                    contentDescription = ""
                )
            },
            label = {
                Text(
                    text = stringResource(R.string.my_events),
                    color = secondaryColor,
                    fontSize = smallFontSize.sp,
                    fontWeight = FontWeight.SemiBold
                )
            },
            selected = isMyEvents.value,
            shape = RectangleShape,
            colors = NavigationDrawerItemDefaults.colors(
                selectedContainerColor = tertiaryColor,
                unselectedContainerColor = primaryColor
            ),
            onClick = {
                isMainSelected.value = false
                isContactsSelected.value = false
                isMyEvents.value = true
                isMonitoringSelected.value = false
                isWalletSelected.value = false
                isSettingsSelected.value = false
                isSupportSelected.value = false
                onMyEventsClick()
            }
        )
//        Monitoring
        NavigationDrawerItem(
            icon = {
                Icon(
                    painter = painterResource(R.drawable.ic_chart),
                    tint = secondaryColor,
                    contentDescription = ""
                )
            },
            label = {
                Text(
                    text = stringResource(R.string.monitoring),
                    color = secondaryColor,
                    fontSize = smallFontSize.sp,
                    fontWeight = FontWeight.SemiBold
                )
            },
            selected = isMonitoringSelected.value,
            shape = RectangleShape,
            colors = NavigationDrawerItemDefaults.colors(
                selectedContainerColor = tertiaryColor,
                unselectedContainerColor = primaryColor
            ),
            onClick = {
                isMainSelected.value = false
                isContactsSelected.value = false
                isMyEvents.value = false
                isMonitoringSelected.value = true
                isWalletSelected.value = false
                isSettingsSelected.value = false
                isSupportSelected.value = false
                onMonitoringClick()
            }
        )
//        Payment methods
        NavigationDrawerItem(
            icon = {
                Icon(
                    painter = painterResource(R.drawable.ic_wallet),
                    tint = secondaryColor,
                    contentDescription = ""
                )
            },
            label = {
                Text(
                    text = stringResource(R.string.wallet),
                    color = secondaryColor,
                    fontSize = smallFontSize.sp,
                    fontWeight = FontWeight.SemiBold
                )
            },
            selected = isWalletSelected.value,
            shape = RectangleShape,
            colors = NavigationDrawerItemDefaults.colors(
                selectedContainerColor = tertiaryColor,
                unselectedContainerColor = primaryColor
            ),
            onClick = {
                isMainSelected.value = false
                isContactsSelected.value = false
                isMyEvents.value = false
                isMonitoringSelected.value = false
                isWalletSelected.value = true
                isSettingsSelected.value = false
                isSupportSelected.value = false
                onWalletClick()
            }
        )
        HorizontalDivider()
//        Settings
        NavigationDrawerItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Settings,
                    tint = secondaryColor,
                    contentDescription = ""
                )
            },
            label = {
                Text(
                    text = stringResource(R.string.settings),
                    color = secondaryColor,
                    fontSize = smallFontSize.sp,
                    fontWeight = FontWeight.SemiBold
                )
            },
            selected = isSettingsSelected.value,
            shape = RectangleShape,
            colors = NavigationDrawerItemDefaults.colors(
                selectedContainerColor = tertiaryColor,
                unselectedContainerColor = primaryColor
            ),
            onClick = {
                isMainSelected.value = false
                isContactsSelected.value = false
                isMyEvents.value = false
                isMonitoringSelected.value = false
                isWalletSelected.value = false
                isSettingsSelected.value = true
                isSupportSelected.value = false
                onSettingsClick()
            }
        )
//        Support
        NavigationDrawerItem(
            icon = {
                Icon(
                    painter = painterResource(R.drawable.ic_support),
                    tint = secondaryColor,
                    contentDescription = ""
                )
            },
            label = {
                Text(
                    text = stringResource(R.string.support_help),
                    color = secondaryColor,
                    fontSize = smallFontSize.sp,
                    fontWeight = FontWeight.SemiBold
                )
            },
            selected = isSupportSelected.value,
            shape = RectangleShape,
            colors = NavigationDrawerItemDefaults.colors(
                selectedContainerColor = tertiaryColor,
                unselectedContainerColor = primaryColor
            ),
            onClick = {
                isMainSelected.value = false
                isContactsSelected.value = false
                isMyEvents.value = false
                isMonitoringSelected.value = false
                isWalletSelected.value = false
                isSettingsSelected.value = false
                isSupportSelected.value = true
                onSupportClick()
            }
        )
    }
}