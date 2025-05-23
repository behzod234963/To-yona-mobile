package com.mr.anonym.toyonamobile.ui.screens.mainScreen.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
                text = stringResource(R.string.search_from_contacts),
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
        trailingIcon = {
            IconButton(
                onClick = { onSearch() }
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    tint = secondaryColor,
                    contentDescription = ""
                )
            }
            Spacer(Modifier.width(10.dp))
        },
        shape = RoundedCornerShape(30.dp),
        keyboardOptions = keyboardOptions,
    )
}