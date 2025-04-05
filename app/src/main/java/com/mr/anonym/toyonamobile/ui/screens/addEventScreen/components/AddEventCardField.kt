package com.mr.anonym.toyonamobile.ui.screens.addEventScreen.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEventCardField(
    secondaryColor: Color,
    tertiaryColor: Color,
    value:String,
    values: List<String>,
    onClick:(String)-> Unit
) {

    val isExpanded = rememberSaveable { mutableStateOf( false ) }
    val anchorType = MenuAnchorType.PrimaryNotEditable

    ExposedDropdownMenuBox(
        modifier = Modifier
            .height(50.dp)
            .width( if ( isExpanded.value ) 200.dp else 70.dp ),
        expanded = isExpanded.value,
        onExpandedChange = { isExpanded.value = !isExpanded.value },
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = {  },
            readOnly = true,
            modifier = Modifier
                .menuAnchor(type = anchorType, enabled = true)
                .focusRequester(FocusRequester())
        )
        ExposedDropdownMenu(
            expanded = isExpanded.value,
            onDismissRequest = { isExpanded.value = false },
            modifier = Modifier.wrapContentSize(),
            containerColor = tertiaryColor,
        ) {
            values.forEach { string ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = string,
                            fontSize = 14.sp,
                            color = secondaryColor,
                            fontWeight = FontWeight.SemiBold
                        )
                    },
                    onClick = {
                        isExpanded.value = false
                        onClick(string)
                    },
                    colors = MenuDefaults.itemColors(
                        textColor = secondaryColor,
                    ),
                )
            }
        }
    }
}