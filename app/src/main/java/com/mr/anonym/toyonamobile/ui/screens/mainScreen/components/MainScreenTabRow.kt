package com.mr.anonym.toyonamobile.ui.screens.mainScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MainScreenTabRow(
    primaryColor:Color,
    secondaryColor: Color,
    tabs: List<String>,
    content: @Composable (Int)-> Unit
) {

    val selectedTab = remember { mutableIntStateOf( 0 ) }

    SecondaryTabRow(
        selectedTabIndex = selectedTab.intValue,
        Modifier
            .padding(vertical = 10.dp),
        TabRowDefaults.primaryContainerColor, TabRowDefaults.primaryContentColor, {
            val modifier = Modifier
                .tabIndicatorOffset( selectedTabIndex = selectedTab.intValue)
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .height(5.dp)
                    .background(
                        color = secondaryColor,
                        shape = RoundedCornerShape( topStart = 16.dp , topEnd = 16.dp )
                    )
            )
        },
        @Composable { HorizontalDivider() }, {
            tabs.forEachIndexed { index, string ->
                Tab(
                    selected = selectedTab.intValue == index,
                    onClick = { selectedTab.intValue = index }
                ) {
                    Row (
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(45.dp)
                            .background(primaryColor),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Text(
                            text = string,
                            color = secondaryColor,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
        })
    content(selectedTab.intValue)
}