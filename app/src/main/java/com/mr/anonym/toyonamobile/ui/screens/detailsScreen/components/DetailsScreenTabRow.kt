package com.mr.anonym.toyonamobile.ui.screens.detailsScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Tab
import androidx.compose.material3.TabPosition
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mr.anonym.toyonamobile.R

@Composable
fun DetailsScreenTabRow(
    modifier: Modifier,
    secondaryColor: Color,
    fontFamily: FontFamily,
    content: @Composable (Int)-> Unit
) {
    
    val tabs = listOf(
        TabsItem(
            title = stringResource(R.string.actual_events),
            icon = R.drawable.ic_active
        ),
        TabsItem(
            title = stringResource(R.string.event_history),
            icon = R.drawable.ic_history
        )
    )
    val selectedTab = rememberSaveable { mutableIntStateOf( 0 ) }
    
    TabRow(
        modifier = modifier,
        selectedTabIndex = selectedTab.intValue,
        indicator = { tabPosition:List<TabPosition> ->
            val modifier = Modifier
                .tabIndicatorOffset( tabPosition[selectedTab.intValue])
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .height(5.dp)
                    .background(
                        color = secondaryColor,
                        shape = RoundedCornerShape( topStart = 16.dp , topEnd = 16.dp )
                    )
            )
        }
    ) {
        tabs.forEachIndexed { index, item ->
            Tab(
                selected = selectedTab.intValue == index,
                onClick = { selectedTab.intValue = index }
            ) {
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        text = item.title,
                        color = secondaryColor,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = fontFamily
                    )
                }
            }
        }
    }
    content(selectedTab.intValue)
}
data class TabsItem(
    val title: String,
    val icon: Int
)