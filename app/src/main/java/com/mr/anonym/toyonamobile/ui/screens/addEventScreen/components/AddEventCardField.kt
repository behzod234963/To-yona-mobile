package com.mr.anonym.toyonamobile.ui.screens.addEventScreen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mr.anonym.domain.model.CardModel
import com.mr.anonym.toyonamobile.R
import com.mr.anonym.toyonamobile.presentation.extensions.cardNumberFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEventCardField(
    secondaryColor: Color,
    tertiaryColor: Color,
    value:String,
    values: List<CardModel>,
    onClick:(CardModel)-> Unit,
    onAddCardClick:()-> Unit
) {

    val isExpanded = rememberSaveable { mutableStateOf( false ) }
    val anchorType = MenuAnchorType.PrimaryNotEditable

    ExposedDropdownMenuBox(
        modifier = Modifier
            .height(50.dp)
            .width( if ( isExpanded.value ) 170.dp else 100.dp ),
        expanded = isExpanded.value,
        onExpandedChange = { isExpanded.value = !isExpanded.value },
    ) {
        OutlinedTextField(
            value = if (values.isNotEmpty()) value.cardNumberFormatter() else value,
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
            if (values.isEmpty()){
                DropdownMenuItem(
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Add,
                            tint = secondaryColor,
                            contentDescription = ""
                        )
                    },
                    text = {
                        Text(
                            text = stringResource(R.string.add_cart),
                            fontSize = 14.sp,
                            color = secondaryColor,
                            fontWeight = FontWeight.SemiBold
                        )
                    },
                    onClick = {
                        isExpanded.value = false
                        onAddCardClick()
                    },
                )
            }else{
                values.forEach { card ->
                    DropdownMenuItem(
                        text = {
                            Row (
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            ){
                                Text(
                                    text = card.cardNumber.cardNumberFormatter(),
                                    fontSize = 14.sp,
                                    color = secondaryColor,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        },
                        onClick = {
                            isExpanded.value = false
                            onClick(card)
                        },
                    )
                }
            }
            if (values.isNotEmpty()){
                DropdownMenuItem(
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Add,
                            tint = secondaryColor,
                            contentDescription = ""
                        )
                    },
                    text = {
                        Text(
                            text = stringResource(R.string.add_cart),
                            fontSize = 14.sp,
                            color = secondaryColor,
                            fontWeight = FontWeight.SemiBold
                        )
                    },
                    onClick = {
                        isExpanded.value = false
                        onAddCardClick()
                    },
                )
            }
        }
    }
}