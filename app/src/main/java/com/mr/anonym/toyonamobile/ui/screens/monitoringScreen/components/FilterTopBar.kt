package com.mr.anonym.toyonamobile.ui.screens.monitoringScreen.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.mr.anonym.toyonamobile.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterTopBar(
    primaryColor: Color,
    secondaryColor: Color,
    onNavigationClick:()-> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.filter),
                color = secondaryColor,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = primaryColor
        ),
        navigationIcon = {
            IconButton(
                onClick = { onNavigationClick() }
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    tint = secondaryColor,
                    contentDescription = ""
                )
            }
        },
    )
}