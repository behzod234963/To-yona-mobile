package com.mr.anonym.toyonamobile.ui.screens.mainScreen.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.mr.anonym.toyonamobile.R

@Composable
fun MainScreenSearchField(
    modifier: Modifier,
    secondaryColor:Color,
    eightrdColor:Color,
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
        onValueChange = { onValueChange(it) },
        keyboardActions = KeyboardActions {
            keyboardController?.hide()
            onSearch()
        },
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedContainerColor = eightrdColor,
            focusedContainerColor = eightrdColor,
            unfocusedBorderColor = eightrdColor,
            focusedBorderColor = eightrdColor,
            unfocusedLabelColor = tertiaryColor,
            focusedLabelColor = eightrdColor,
        ),
        placeholder = {
            Text(
                text = stringResource(R.string.search_from_contacts),
            )
        },
        leadingIcon = {
            Icon(
                painter = painterResource(R.drawable.ic_search),
                tint = secondaryColor,
                contentDescription = "icon search"
            )
        },
        trailingIcon = {
            IconButton(
                onClick = { onSearch() }
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_arrow_forward),
                    tint = secondaryColor,
                    contentDescription = ""
                )
            }
            Spacer(Modifier.width(10.dp))
        },
        shape = RoundedCornerShape(20.dp),
        keyboardOptions = keyboardOptions,
    )
}