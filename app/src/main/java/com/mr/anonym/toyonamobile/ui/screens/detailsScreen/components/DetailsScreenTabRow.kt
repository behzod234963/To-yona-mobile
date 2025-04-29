package com.mr.anonym.toyonamobile.ui.screens.detailsScreen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mr.anonym.toyonamobile.R

@Composable
fun DetailsScreenTabRow(
    modifier: Modifier,
    secondaryColor: Color,
    content: @Composable (Int)-> Unit
) {
    
    val tabs = listOf<TabsItem>(
        TabsItem(
            title = stringResource(R.string.actual_events),
            icon = R.drawable.ic_active
        ),
        TabsItem(
            title = stringResource(R.string.event_history),
            icon = R.drawable.ic_history
        )
    )
    val selectedTab = rememberSaveable { mutableStateOf( 0 ) }
    
    TabRow(
        modifier = modifier,
        selectedTabIndex = selectedTab.value,
    ) {
        tabs.forEachIndexed { index, item ->
            Tab(
                selected = selectedTab.value == index,
                onClick = { selectedTab.value = index }
            ) {
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(55.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Icon(
                        modifier = Modifier
                            .size(30.dp),
                        painter = painterResource(item.icon),
                        tint = secondaryColor,
                        contentDescription = "",
                    )
                    Spacer(Modifier.width(5.dp))
                    Text(
                        text = item.title,
                        color = secondaryColor,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
    content(selectedTab.value)
}
data class TabsItem(
    val title: String,
    val icon: Int
)