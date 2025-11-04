package com.mr.anonym.toyonamobile.ui.screens.addPartyScreen.components

import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mr.anonym.toyonamobile.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEventTopBar(
    primaryColor:Color,
    secondaryColor: Color,
    fontFamily: FontFamily,
    navigationIconClick:()-> Unit,
    onActionsClick:()-> Unit
) {

    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = primaryColor,
        ),
        title = {
            Text(
                text = stringResource(R.string.create_event),
                color = secondaryColor,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = fontFamily
            )
        },
        navigationIcon = {
            IconButton(
                onClick = { navigationIconClick() }
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
                    modifier = Modifier
                        .size(30.dp),
                    painter = painterResource(R.drawable.ic_event_vector),
                    tint = secondaryColor,
                    contentDescription = ""
                )
            }
        },
    )
}