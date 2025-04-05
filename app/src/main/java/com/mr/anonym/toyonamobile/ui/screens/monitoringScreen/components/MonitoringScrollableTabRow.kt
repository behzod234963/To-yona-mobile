package com.mr.anonym.toyonamobile.ui.screens.monitoringScreen.components

import android.icu.util.Calendar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabPosition
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MonitoringScrollableTabRow(
    primaryColor: Color,
    secondaryColor: Color,
    selectedTabIndex: Int,
    tabs:Collection<String>,
    onClick:(Int)-> Unit,
    content: @Composable ()-> Unit
) {

    val calendarInstance = Calendar.getInstance()

    ScrollableTabRow(
        selectedTabIndex = selectedTabIndex,
        containerColor = primaryColor,
        contentColor = primaryColor,
        indicator = { tabPosition: List<TabPosition>->
            val modifier = Modifier
                .tabIndicatorOffset( tabPosition[selectedTabIndex])
            Box(
                modifier
                    .fillMaxWidth()
                    .height(5.dp)
                    .background(
                        color = secondaryColor,
                        shape = RoundedCornerShape( topStart = 16.dp , topEnd = 16.dp )
                    )
            )
        },
    ) {
        tabs.forEachIndexed { index, string ->
            Tab(
                selected = selectedTabIndex == index,
                onClick = {
                    onClick( index )
                }
            ) {
                Text(
                    text = string,
                    color = secondaryColor,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(Modifier.height(10.dp))
            }
        }
    }
    content()
}