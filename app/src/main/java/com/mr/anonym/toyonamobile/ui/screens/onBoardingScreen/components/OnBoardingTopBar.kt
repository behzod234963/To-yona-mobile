package com.mr.anonym.toyonamobile.ui.screens.onBoardingScreen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.mr.anonym.toyonamobile.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnBoardingTopBar(
    primaryColor:Color,
    fiveColor: Color,
    onSkipClick: () -> Unit,
) {

    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = primaryColor,
        ),
        navigationIcon = {
            Row (
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ){
                IconButton(
                    onClick = {
                        onSkipClick()
                    }
                ) {
                    Icon(
                        modifier = Modifier
                            .size(30.dp),
                        painter = painterResource(R.drawable.ic_close),
                        tint = fiveColor,
                        contentDescription = ""
                    )
                }
            }
        },
        title = { },
        actions = {},
        scrollBehavior = scrollBehavior
    )
}