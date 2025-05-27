package com.mr.anonym.toyonamobile.ui.theme

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ShimmerEffectForMainScreen() {

    val shimmerColor = listOf(
        Color.LightGray.copy( alpha = 0.6f ),
        Color.LightGray.copy( alpha = 0.2f ),
        Color.LightGray.copy( alpha = 0.6f ),
    )
    val transition = rememberInfiniteTransition()
    val translateAnimation = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing)
        )
    )
    val brush = Brush.linearGradient(
        colors = shimmerColor,
        start = Offset.Zero,
        end = Offset(x = translateAnimation.value,y = translateAnimation.value)
    )
    Column (
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .width(80.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Box(
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
                .background(brush)
        )
        Spacer(Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .height(12.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(4.dp))
                .background(brush)
        )
    }
}