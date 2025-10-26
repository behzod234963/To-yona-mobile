package com.mr.anonym.toyonamobile.ui.theme

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ShimmerEffectForUser() {

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
    Row (
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ){
        Box(
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .background(brush)
        )
        Spacer(Modifier.width(10.dp))
        Box(
            modifier = Modifier
                .height(25.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(4.dp))
                .background(brush)
                .padding(horizontal = 10.dp)
        )
    }
    Spacer(Modifier.height(10.dp))
}
@Composable
fun ShimmerEffectForProfile() {

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
            .padding(horizontal = 10.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Spacer(Modifier.height(50.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(170.dp)
                .clip(RoundedCornerShape(15.dp))
                .background(brush)
        )
        Spacer(Modifier.height(20.dp))
        Box(
            modifier = Modifier
                .height(50.dp)
                .width(400.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(brush)
                .padding(horizontal = 10.dp, vertical = 5.dp)
        )
        Spacer(Modifier.height(10.dp))
        Box(
            modifier = Modifier
                .height(47.dp)
                .width(400.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(brush)
                .padding(horizontal = 10.dp, vertical = 5.dp)
        )
        Spacer(Modifier.height(50.dp))
        Box(
            modifier = Modifier
                .height(50.dp)
                .width(400.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(brush)
                .padding(horizontal = 10.dp, vertical = 5.dp),
        ){}
    }
    Spacer(Modifier.height(10.dp))
}

@Preview
@Composable
private fun PreviewShimmerEffectForProfile() {
    ShimmerEffectForProfile()
}