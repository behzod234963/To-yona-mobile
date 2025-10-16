package com.mr.anonym.toyonamobile.ui.screens.changePasswordScreen.components

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.mr.anonym.toyonamobile.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangePasswordTopBar(
    primaryColor: Color,
    secondaryColor: Color,
    onNavigationClick: () -> Unit
){
    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.change_password),
                fontSize = 18.sp,
                color = secondaryColor,
                fontWeight = FontWeight.SemiBold
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
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = primaryColor
        ),
    )
}