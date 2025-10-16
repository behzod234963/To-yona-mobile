package com.mr.anonym.toyonamobile.ui.screens.settingsScreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mr.anonym.toyonamobile.R

@Composable
fun SettingsField(
    secondaryColor: Color,
    quaternaryColor:Color,
    fiverdColor:Color,
    sevenrdColor: Color,
    contentIcon: Int,
    contentTitle: String,
    isHaveSwitcher: Boolean,
    isChecked: Boolean,
    onCheckedChange:(Boolean)->Unit,
    onContentClick:()-> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        colors = CardDefaults.cardColors(
            containerColor = sevenrdColor,
            contentColor = sevenrdColor
        ),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(7.dp),
        onClick = { onContentClick() }
    ) {
        Row (
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Row (
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
                    fontWeight = FontWeight.SemiBold
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
                            checkedTrackColor = fiverdColor,
                            checkedBorderColor = fiverdColor,
                            checkedIconColor = fiverdColor,
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
    Spacer(Modifier.height(10.dp))
}

@Preview
@Composable
private fun PreviewSettingsField() {
    SettingsField(
        secondaryColor = Color.Black,
        quaternaryColor = Color.Red,
        fiverdColor = Color.Green,
        sevenrdColor = Color.Unspecified,
        contentIcon = R.drawable.ic_default_avatar,
        contentTitle = "Personal",
        isHaveSwitcher = false,
        isChecked = true,
        onCheckedChange = {  },
        onContentClick = {  }
    )
}