package com.mr.anonym.toyonamobile.ui.screens.settingsScreen.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.mr.anonym.toyonamobile.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsTopBar(
    primaryColor: Color,
    secondaryColor:Color,
    fontFamily: FontFamily,
    onActionsClick:()-> Unit,
    onNavigationClick:()-> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.settings),
                fontSize = 18.sp,
                color = secondaryColor,
                fontWeight = FontWeight.SemiBold,
                fontFamily = fontFamily
            )
        },
        navigationIcon = {
            IconButton(
                onClick = { onNavigationClick() }
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_back),
                    tint = secondaryColor,
                    contentDescription = ""
                )
            }
        },
        actions = {
            IconButton(
                onClick = { onActionsClick() }
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_edit),
                    tint = secondaryColor,
                    contentDescription = ""
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = primaryColor
        )
    )
}