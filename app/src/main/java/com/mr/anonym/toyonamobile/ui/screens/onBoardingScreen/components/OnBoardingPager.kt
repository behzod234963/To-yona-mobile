package com.mr.anonym.toyonamobile.ui.screens.onBoardingScreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mr.anonym.toyonamobile.R
import com.mr.anonym.toyonamobile.presentation.managers.LocaleConfigurations

@Composable
fun OnBoardingPager(
    onPageState:(Int)->Unit,
    secondaryColor: Color
) {

    val context = LocalContext.current
    val pagerState = rememberPagerState { 3 }
    val localeManager = LocaleConfigurations(context)
    val currentLocale = localeManager.getPrimaryLocale()
    val images = listOf<Int>(
        R.drawable.ic_rings,
        R.drawable.ic_app_secure,
        R.drawable.ic_donate
    )
    val descriptions = listOf<String>(
        stringResource(R.string.happiness_days_of_your_friends),
        stringResource(R.string.safe_and_fast_payments),
        stringResource(R.string.just_and_trust)
    )
    HorizontalPager(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize(),
        state = pagerState
    ) { page ->
        onPageState(pagerState.currentPage)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    modifier = Modifier
                        .size(100.dp),
                    painter = painterResource(images[page]),
                    contentDescription = null
                )
                Spacer(Modifier.height(10.dp))
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),
                    horizontalArrangement = Arrangement.Center
                ){
                    Text(
                        text = if (currentLocale.contains("русский") && page == 0 ) stringResource(R.string.be_line) else descriptions[page] ,
                        color = secondaryColor,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                if (page == 0) {
                    Row (
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp),
                        horizontalArrangement = Arrangement.Center
                    ){
                        Text(
                            text = if (currentLocale.contains("русский")) stringResource(R.string.happiness_days_of_your_friends) else stringResource(R.string.be_line) ,
                            color = secondaryColor,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
        }
    }
}