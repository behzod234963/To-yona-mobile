package com.mr.anonym.toyonamobile.ui.screens.addCardScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp

@Composable
fun ColorContent(
    color: Color,
    brush: Brush,
    scale: Float,
    interactionSource: MutableInteractionSource,
    isSelected: Boolean,
    onClick:()-> Unit,
) {
    Box(
        modifier = Modifier
            .width(40.dp)
            .height(40.dp)
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            }
            .clickable(
                interactionSource = interactionSource,
                onClick = onClick
            )
            .background(color = color, shape = CircleShape),
        contentAlignment = Alignment.Center
    ){
        Column(
            modifier = Modifier
                .size( if (isSelected) 30.dp else 40.dp )
                .background(brush = brush, shape = CircleShape)
        ) { }
    }
}