package com.mr.anonym.toyonamobile.ui.screens.mainScreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
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
import com.mr.anonym.toyonamobile.presentation.extensions.phoneNumberTransformation
import com.mr.anonym.toyonamobile.ui.screens.mainScreen.viewModel.MainScreenViewModel

@Composable
fun MainScreenModalDrawerSheet(
    smallFontSize:Int,
    primaryColor: Color,
    secondaryColor: Color,
    tertiaryColor: Color,
    profileAvatar:Int,
    userId:Int,
    viewModel: MainScreenViewModel,
    onFriendsClick:()-> Unit,
    onMyEventsClick:()-> Unit,
    onMonitoringClick:()-> Unit,
    onWalletClick:()->Unit,
    onSettingsClick:()-> Unit,
    onSupportClick:()-> Unit
) {
    val isMainSelected = remember { mutableStateOf( false ) }
    val isMyEvents = remember { mutableStateOf( false ) }
    val isFriendsSelected = remember { mutableStateOf( false ) }
    val isMonitoringSelected = remember { mutableStateOf( false ) }
    val isWalletSelected = remember { mutableStateOf( false ) }
    val isSettingsSelected = remember { mutableStateOf( false ) }
    val isSupportSelected = remember { mutableStateOf( false) }

    viewModel.getUserByID(userId)
    val user = viewModel.user

    ModalDrawerSheet(
        modifier = Modifier
            .width(intrinsicSize = IntrinsicSize.Max),
        drawerContentColor = primaryColor,
        drawerContainerColor = primaryColor,
    ) {
        Row (
            modifier = Modifier
                .wrapContentWidth()
                .padding(top = 10.dp, bottom = 10.dp , start = 10.dp ,end = 30.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
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
                        .size(45.dp),
                    painter = painterResource(profileAvatar),
                    contentDescription = ""
                )
            }
            Spacer(Modifier.width(10.dp))
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                viewModel.getUserByID(userId)
                Text(
                    text = "${user.value.username} ${user.value.surname}",
                    color = secondaryColor,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "+998${user.value.phonenumber}".phoneNumberTransformation(),
                    color = secondaryColor,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
        HorizontalDivider()
//        Friends
        NavigationDrawerItem(
            icon = {
                Icon(
                    painter = painterResource(R.drawable.ic_contact),
                    tint = secondaryColor,
                    contentDescription = ""
                )
            },
            label = {
                Text(
                    text = stringResource(R.string.friends),
                    color = secondaryColor,
                    fontSize = smallFontSize.sp,
                    fontWeight = FontWeight.SemiBold
                )
            },
            selected = isFriendsSelected.value,
            shape = RectangleShape,
            colors = NavigationDrawerItemDefaults.colors(
                selectedContainerColor = tertiaryColor,
                unselectedContainerColor = primaryColor
            ),
            onClick = {
                isMainSelected.value = false
                isFriendsSelected.value = true
                isMyEvents.value = false
                isMonitoringSelected.value = false
                isWalletSelected.value = false
                isSettingsSelected.value = false
                isSupportSelected.value = false
                onFriendsClick()
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
                isFriendsSelected.value = false
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
                isFriendsSelected.value = false
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
                isFriendsSelected.value = false
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
                isFriendsSelected.value = false
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
                isFriendsSelected.value = false
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