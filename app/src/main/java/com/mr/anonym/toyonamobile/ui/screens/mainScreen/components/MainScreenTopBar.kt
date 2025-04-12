package com.mr.anonym.toyonamobile.ui.screens.mainScreen.components

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenTopBar(
    primaryColor:Color,
    secondaryColor: Color,
    title: String,
    onActionsClick:()-> Unit,
    onNavigationIconClick:()-> Unit,
) {

    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = primaryColor,

        ),
        title = {
            Text(
                text = title
            )
        },
        navigationIcon = {
            IconButton(
                onClick = { onNavigationIconClick() }
            ) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    tint = secondaryColor,
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