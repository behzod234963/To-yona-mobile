package com.mr.anonym.toyonamobile.ui.screens.mainScreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenTopBar(
    primaryColor:Color,
    secondaryColor: Color,
    title: String,
    onActionsClick:()-> Unit,
    navigationIcon: Int,
    onNavigationIconClick:()-> Unit,
) {

    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = primaryColor,

        ),
        title = {
            Text(
                modifier = Modifier
                    .padding(start = 10.dp),
                text = title
            )
        },
        navigationIcon = {
            IconButton(
                onClick = { onNavigationIconClick() }
            ) {
                Image(
                    modifier = Modifier
                        .size(30.dp),
                    painter = painterResource(navigationIcon),
                    contentDescription = "navigationDrawer button"
                )
            }
        },
        actions = {
            IconButton(
                onClick = {
                    onActionsClick()
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Notifications,
                    tint = secondaryColor,
                    contentDescription = "button notifications"
                )
            }
        }
    )
}