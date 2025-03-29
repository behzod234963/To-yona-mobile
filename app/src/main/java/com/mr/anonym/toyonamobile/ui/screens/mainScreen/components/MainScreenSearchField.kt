package com.mr.anonym.toyonamobile.ui.screens.mainScreen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MainScreenSearchField(
    modifier: Modifier,
    secondaryColor:Color,
    tertiaryColor: Color,
) {

    OutlinedTextField(
        modifier = modifier,
        value = "",
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = tertiaryColor,
            unfocusedContainerColor = tertiaryColor
        ),
        onValueChange = {  },
        readOnly = true,
        label = {
            Text(
                text = "Search"
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                tint = secondaryColor,
                contentDescription = "icon search"
            )
        },
        trailingIcon = {},
        shape = RoundedCornerShape(30.dp),
    )
}

@Preview
@Composable
private fun PreviewMainScreenSearchField() {
    MainScreenSearchField(
        modifier = Modifier,
        secondaryColor = if (isSystemInDarkTheme()) Color.White else Color.Black,
        tertiaryColor = if (isSystemInDarkTheme()) Color.DarkGray else Color.LightGray
    )
}