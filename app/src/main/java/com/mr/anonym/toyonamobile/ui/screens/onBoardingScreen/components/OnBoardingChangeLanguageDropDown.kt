package com.mr.anonym.toyonamobile.ui.screens.onBoardingScreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mr.anonym.toyonamobile.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnBoardingChangeLanguageDropDown(
    value: String,
    onUzbekClick: () -> Unit,
    onRussianClick: () -> Unit,
    secondaryColor: Color,
    tertiaryColor: Color
) {

    val isExpanded = remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }
    val anchorType = ExposedDropdownMenuAnchorType.PrimaryNotEditable

    ExposedDropdownMenuBox(
        modifier = Modifier
            .width(if (isExpanded.value) 170.dp else 135.dp)
            .height(55.dp),
        expanded = isExpanded.value,
        onExpandedChange = { isExpanded.value = !isExpanded.value },
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = { },
            readOnly = true,
            shape = RoundedCornerShape(10.dp),
            leadingIcon = {
                Image(
                    modifier = Modifier
                        .size(35.dp),
                    painter = if (value.contains("Русский")) painterResource(R.drawable.ic_ru_flag) else painterResource(
                        R.drawable.ic_uz_flag
                    ),
                    contentDescription = "leading icon for locales"
                )
            },
            modifier = Modifier
                .menuAnchor(anchorType, true)
                .focusRequester(focusRequester)
        )
        ExposedDropdownMenu(
            modifier = Modifier
                .wrapContentSize(),
            expanded = isExpanded.value,
            onDismissRequest = {
                isExpanded.value = false
            },
            containerColor = tertiaryColor
        ) {
            //            Uzbek language content
            DropdownMenuItem(
                text = {
                    Text(
                        modifier = Modifier
                            .clickable {
                                isExpanded.value = false
                                onUzbekClick()
                            },
                        text = stringResource(R.string.o_zbekcha),
                        color = secondaryColor,
                        fontSize = 18.sp
                    )
                },
                onClick = {
                    isExpanded.value = false
                    onUzbekClick()
                },
                leadingIcon = {
                    IconButton(
                        onClick = {
                            isExpanded.value = false
                            onUzbekClick()
                        }
                    ) {
                        Image(
                            painter = painterResource(R.drawable.ic_uz_flag),
                            contentDescription = "uz language"
                        )
                    }
                },
            )
            //            Russian language content
            DropdownMenuItem(
                text = {
                    Text(
                        modifier = Modifier
                            .clickable {
                                isExpanded.value = false
                                onRussianClick()
                                       },
                        text = stringResource(R.string.russian),
                        color = secondaryColor,
                        fontSize = 18.sp
                    )
                },
                onClick = {
                    isExpanded.value = false
                    onRussianClick()
                          },
                leadingIcon = {
                    IconButton(
                        onClick = {
                            isExpanded.value = false
                            onRussianClick()
                        }
                    ) {
                        Image(
                            painter = painterResource(R.drawable.ic_ru_flag),
                            contentDescription = "russian language"
                        )
                    }
                },
            )
        }
    }
}