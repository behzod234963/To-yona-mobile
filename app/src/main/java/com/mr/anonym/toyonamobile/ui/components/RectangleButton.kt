package com.mr.anonym.toyonamobile.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun RectangleButton(
    buttonColor: Color,
    interactionSource: MutableInteractionSource,
    scale: Float,
    onClick:()-> Unit,
    content: @Composable ()->Unit
) {

    Box(
        modifier = Modifier
            .width(70.dp)
            .height(70.dp)
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            }
            .clickable(
                interactionSource = interactionSource,
                onClick = onClick
            )
            .background(buttonColor, RoundedCornerShape(10.dp))
            .padding(horizontal = 10.dp),
        contentAlignment = Alignment.Center
    ){
        content()
    }
}

@Preview
@Composable
private fun PreviewRectangleButton() {
    val intSource = remember { MutableInteractionSource() }
    val pressed by intSource.collectIsPressedAsState()
    val scale by animateFloatAsState(if (pressed) 0.95f else 1f)
    RectangleButton(
        buttonColor = Color.LightGray,
        interactionSource = intSource,
        scale = scale,
        onClick = {

        }
    ) {
        Text("1")
    }
}