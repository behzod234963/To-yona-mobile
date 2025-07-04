package com.mr.anonym.toyonamobile.ui.screens.addPartyScreen.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mr.anonym.toyonamobile.R

@Composable
fun AddPartyAddressField(
    secondaryColor: Color,
    tertiaryColor: Color,
    eightrdColor:Color,
    value:String,
    onValueChange:(String)-> Unit,
) {

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth(),
        value = value,
        onValueChange = { onValueChange(it) },
        shape = RoundedCornerShape(10.dp),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedContainerColor = eightrdColor,
            focusedContainerColor = eightrdColor,
            unfocusedBorderColor = eightrdColor,
            focusedBorderColor = eightrdColor,
            unfocusedLabelColor = tertiaryColor,
            focusedLabelColor = eightrdColor,
        ),
        leadingIcon = {
            Icon(
                painter = painterResource(R.drawable.ic_add_address),
                tint = secondaryColor,
                contentDescription = ""
            )
        },
        placeholder = {
            Text(
                text = stringResource(R.string.address),
                color = tertiaryColor
            )
        }
    )
}