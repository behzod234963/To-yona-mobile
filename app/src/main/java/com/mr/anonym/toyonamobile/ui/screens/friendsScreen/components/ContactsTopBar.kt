package com.mr.anonym.toyonamobile.ui.screens.friendsScreen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.sp
import com.mr.anonym.toyonamobile.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactsTopBar(
    primaryColor:Color,
    secondaryColor: Color,
    showSearchBar: Boolean,
    value:String,
    onValueChange:(String)-> Unit,
    onNavigationClick:()-> Unit,
    onTrailingIconClick:()-> Unit,
    onSend:()-> Unit,
    onActionsClick:()-> Unit
) {

    val keyboardOptions = KeyboardOptions(
        imeAction = ImeAction.Search
    )
    val keyboardController = LocalSoftwareKeyboardController.current

    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = primaryColor,
        ),
        navigationIcon = {
            IconButton(
                onClick = { onNavigationClick() }
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    tint = secondaryColor,
                    contentDescription = ""
                )
            }
        },
        title = {
            if (showSearchBar){
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                ){
                    TextField(
                        value = value,
                        onValueChange = { onValueChange(it) },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = primaryColor,
                            unfocusedContainerColor = primaryColor
                        ),
                        textStyle = TextStyle(
                            color = secondaryColor,
                            fontSize = 16.sp
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(),
                        placeholder = {
                            Text(
                                text = stringResource(R.string.search)
                            )
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Search,
                                tint = secondaryColor,
                                contentDescription = ""
                            )
                        },
                        trailingIcon = {
                            IconButton(
                                onClick = {
                                    onTrailingIconClick()
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Clear,
                                    tint = secondaryColor,
                                    contentDescription = ""
                                )
                            }
                        },
                        keyboardOptions = keyboardOptions,
                        keyboardActions = KeyboardActions {
                            keyboardController?.hide()
                            onSend()
                        },
                        singleLine = true,
                    )
                }
            }else{
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Text(
                        text = stringResource(R.string.friends),
                        color = secondaryColor,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    IconButton(
                        onClick = {
                            onActionsClick()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            tint = secondaryColor,
                            contentDescription = ""
                        )
                    }
                }
            }
        },
    )
}