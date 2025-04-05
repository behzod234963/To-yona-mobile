package com.mr.anonym.toyonamobile.ui.screens.mainScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.mr.anonym.toyonamobile.R

@Composable
fun MainScreenSearchField(
    modifier: Modifier,
    primaryColor:Color,
    secondaryColor:Color,
    tertiaryColor: Color,
    value: String,
    onValueChange:(String)-> Unit,
    onSearch:()-> Unit
) {

    val keyboardController = LocalSoftwareKeyboardController.current
    val keyboardOptions = KeyboardOptions(
        imeAction = ImeAction.Search
    )
    OutlinedTextField(
        modifier = modifier,
        value = value,
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = primaryColor,
            unfocusedContainerColor = primaryColor
        ),
        onValueChange = { onValueChange(it) },
        keyboardActions = KeyboardActions {
            keyboardController?.hide()
            onSearch()
        },
        label = {
            Text(
                text = stringResource(R.string.search),
                color = tertiaryColor
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                tint = secondaryColor,
                contentDescription = "icon search"
            )
        },
        shape = RoundedCornerShape(30.dp),
    )
}