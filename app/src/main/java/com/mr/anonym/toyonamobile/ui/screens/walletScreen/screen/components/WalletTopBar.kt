package com.mr.anonym.toyonamobile.ui.screens.walletScreen.screen.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.mr.anonym.toyonamobile.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WalletTopBar(
    secondaryColor: Color,
    navigationClick:()->Unit,
    onActionsClick:()-> Unit
) {

    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.wallet),
                color = secondaryColor,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
        },
        navigationIcon = {
            IconButton(
                onClick = { navigationClick() }
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
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
                    imageVector = Icons.Default.Add,
                    tint = secondaryColor,
                    contentDescription = ""
                )
            }
        },
    )
}