package com.mr.anonym.toyonamobile.ui.screens.onBoardingScreen.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mr.anonym.toyonamobile.R
import com.mr.anonym.toyonamobile.presentation.managers.LocaleConfigurations
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun OnBoardingPager(
    state: PagerState,
    onPageState:(Int)->Unit,
    secondaryColor: Color,
    tertiaryColor:Color,
    quaternaryColor:Color,
    onFinish:()-> Unit,
    fontFamily: FontFamily
) {

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val localeManager = LocaleConfigurations(context)
    val currentLocale = localeManager.getPrimaryLocale()
    val progress = remember { Animatable(0f) }
    val fadeAlpha = remember { Animatable(0f) }
    val duration = 4000
    val images = listOf(
        R.drawable.ic_rings,
        R.drawable.ic_app_secure,
        R.drawable.ic_donate
    )
    val descriptions = listOf(
        stringResource(R.string.happiness_days_of_your_friends),
        stringResource(R.string.safe_and_fast_payments),
        stringResource(R.string.just_and_trust)
    )

    LaunchedEffect(state.currentPage) {

        fadeAlpha.snapTo(0f)
        fadeAlpha.animateTo(1f,tween(500))

        progress.snapTo(0f)
        progress.animateTo(
            targetValue = 1f,
            animationSpec = tween (duration, easing = LinearEasing)
        )

        fadeAlpha.animateTo(0f,tween(400))

        if ( state.currentPage < images.lastIndex &&
            state.currentPage < descriptions.lastIndex ){
            coroutineScope.launch {
                state.animateScrollToPage(state.currentPage + 1)
            }
        }else{
            delay(500)
            onFinish()
        }
    }
    HorizontalPager(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize()
            .alpha(fadeAlpha.value),
        state = state
    ) { page ->
        onPageState(page)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.99f),
                horizontalAlignment = Alignment.CenterHorizontally,
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
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = fontFamily
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
                            fontWeight = FontWeight.SemiBold,
                            fontFamily = fontFamily
                        )
                    }
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                Row(
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    val fillFraction = when{
                        page < state.currentPage -> 1f
                        page == state.currentPage -> progress.value
                        else -> 0f
                    }
                    Spacer(Modifier.height(100.dp))
                    Box(
                        modifier = Modifier
                            .width(150.dp)
                            .height(8.dp)
                            .background(color = tertiaryColor, shape = RoundedCornerShape(15.dp)),
                        contentAlignment = Alignment.BottomCenter
                    ){
                        Box(
                            modifier = Modifier
                                .fillMaxHeight()
                                .fillMaxWidth(fillFraction)
                                .background(color = quaternaryColor, shape = RoundedCornerShape(15.dp)),
                        )
                    }
                }
            }
        }
    }
}