package com.mr.anonym.toyonamobile.ui.screens.addEventScreen.items

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mr.anonym.toyonamobile.presentation.extensions.cardNumberFormatter

@Composable
fun AddEventCardItem(
    secondaryColor: Color,
    quaternaryColor:Color,
    fiverdColor: Color,
    value: String,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Top
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Agrobank HUMO",
                fontSize = 16.sp,
                color = secondaryColor,
                fontWeight = FontWeight.SemiBold
            )
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
            Row(
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = value.cardNumberFormatter(),
                    fontSize = 16.sp,
                    color = secondaryColor,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}