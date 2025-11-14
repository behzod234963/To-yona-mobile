package com.mr.anonym.toyonamobile.ui.screens.securityScreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mr.anonym.toyonamobile.R

@Composable
fun SecurityFields(
    secondaryColor: Color,
    quaternaryColor:Color,
    fiveColor:Color,
    nineColor: Color,
    fontFamily: FontFamily,
    scale: Float,
    interactionSource: MutableInteractionSource,
    contentIcon: Int,
    contentTitle: String,
    isHaveSwitcher: Boolean,
    isChecked: Boolean,
    onCheckedChange:(Boolean)->Unit,
    onClick:()-> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            }
            .clickable(
                interactionSource = interactionSource,
                onClick = onClick
            )
            .background(nineColor, RoundedCornerShape(10.dp))
            .padding(horizontal = 10.dp),
        contentAlignment = Alignment.Center
    ) {
        Row (
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Row (
                modifier = Modifier
                    .fillMaxWidth(0.8f),
                verticalAlignment = Alignment.CenterVertically
            ){
                Image(
                    modifier = Modifier.size(35.dp),
                    painter = painterResource(contentIcon),
                    contentDescription = ""
                )
                Spacer(Modifier.width(15.dp))
                Text(
                    text = contentTitle,
                    color = secondaryColor,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Start,
                    fontFamily = fontFamily
                )
            }
            if (isHaveSwitcher){
                Row (
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Switch(
                        checked = isChecked,
                        onCheckedChange = { onCheckedChange(it) },
                        enabled = true,
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = secondaryColor,
                            checkedTrackColor = fiveColor,
                            checkedBorderColor = fiveColor,
                            checkedIconColor = fiveColor,
                            uncheckedThumbColor = secondaryColor,
                            uncheckedTrackColor = quaternaryColor,
                            uncheckedBorderColor = quaternaryColor,
                            uncheckedIconColor = quaternaryColor
                        ),
                    )
                }
            }else{
                Icon(
                    painter = painterResource(R.drawable.ic_arrow_forward),
                    tint = secondaryColor,
                    contentDescription = ""
                )
            }
        }
    }
}