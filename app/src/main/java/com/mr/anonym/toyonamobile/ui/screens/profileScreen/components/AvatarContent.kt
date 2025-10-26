package com.mr.anonym.toyonamobile.ui.screens.profileScreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mr.anonym.toyonamobile.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AvatarContent(
    secondaryColor: Color,
    tertiaryColor: Color,
    fiveColor: Color,
    state: SheetState,
    fontFamily: FontFamily,
    avatarIndex: Int,
    onDismissRequest: () -> Unit,
    onDefaultAvatarClick: (Int) -> Unit,
    onMaleClick: (Int) -> Unit,
    onFemaleClick: (Int) -> Unit
) {

    ModalBottomSheet(
        onDismissRequest = { onDismissRequest() },
        sheetState = state,
        shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp),
        containerColor = tertiaryColor,
        contentColor = tertiaryColor,
        dragHandle = {
            BottomSheetDefaults.DragHandle(
                height = 2.dp,
                shape = RoundedCornerShape(10.dp),
                color = secondaryColor
            )
        },
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.choose_the_avatar),
                color = secondaryColor,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = fontFamily
            )
        }
        Spacer(Modifier.height(10.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            when (avatarIndex) {
                0 -> {
                    Column(
                        modifier = Modifier
                            .width(50.dp)
                            .height(50.dp)
                            .background(color = fiveColor, shape = CircleShape)
                            .clip(CircleShape),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        IconButton(
                            onClick = { onDefaultAvatarClick(R.drawable.ic_default_avatar) }
                        ) {
                            Image(
                                painter = painterResource(R.drawable.ic_default_avatar),
                                contentDescription = ""
                            )

                        }
                    }
                    IconButton(
                        onClick = { onMaleClick(R.drawable.ic_man) }
                    ) {
                        Image(
                            painter = painterResource(R.drawable.ic_man),
                            contentDescription = ""
                        )
                    }
                    IconButton(
                        onClick = { onFemaleClick(R.drawable.ic_woman) }
                    ) {
                        Image(
                            painter = painterResource(R.drawable.ic_woman),
                            contentDescription = ""
                        )
                    }
                }

                1 -> {
                    IconButton(
                        onClick = { onDefaultAvatarClick(R.drawable.ic_default_avatar) }
                    ) {
                        Image(
                            painter = painterResource(R.drawable.ic_default_avatar),
                            contentDescription = ""
                        )
                    }
                    Column(
                        modifier = Modifier
                            .width(50.dp)
                            .height(50.dp)
                            .background(color = fiveColor, shape = CircleShape)
                            .clip(CircleShape),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        IconButton(
                            onClick = { onMaleClick(R.drawable.ic_man) }
                        ) {
                            Image(
                                modifier = Modifier.size(58.dp),
                                painter = painterResource(R.drawable.ic_man),
                                contentDescription = ""
                            )
                        }
                    }
                    IconButton(
                        onClick = { onFemaleClick(R.drawable.ic_woman) }
                    ) {
                        Image(
                            painter = painterResource(R.drawable.ic_woman),
                            contentDescription = ""
                        )
                    }
                }

                2 -> {
                    IconButton(
                        onClick = { onDefaultAvatarClick(R.drawable.ic_default_avatar) }
                    ) {
                        Image(
                            painter = painterResource(R.drawable.ic_default_avatar),
                            contentDescription = ""
                        )
                    }
                    IconButton(
                        onClick = { onMaleClick(R.drawable.ic_man) }
                    ) {
                        Image(
                            painter = painterResource(R.drawable.ic_man),
                            contentDescription = ""
                        )
                    }
                    Column(
                        modifier = Modifier
                            .width(50.dp)
                            .height(50.dp)
                            .background(color = fiveColor, shape = CircleShape)
                            .clip(CircleShape),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        IconButton(
                            onClick = { onFemaleClick(R.drawable.ic_woman) }
                        ) {
                            Image(
                                modifier = Modifier.size(58.dp),
                                painter = painterResource(R.drawable.ic_woman),
                                contentDescription = ""
                            )
                        }
                    }
                }

                else -> {
                    IconButton(
                        onClick = { onDefaultAvatarClick(R.drawable.ic_default_avatar) }
                    ) {
                        Image(
                            painter = painterResource(R.drawable.ic_default_avatar),
                            contentDescription = ""
                        )
                    }
                    IconButton(
                        onClick = { onMaleClick(R.drawable.ic_man) }
                    ) {
                        Image(
                            painter = painterResource(R.drawable.ic_man),
                            contentDescription = ""
                        )
                    }
                    IconButton(
                        onClick = { onFemaleClick(R.drawable.ic_woman) }
                    ) {
                        Image(
                            painter = painterResource(R.drawable.ic_woman),
                            contentDescription = ""
                        )
                    }
                }
            }
        }
        Spacer(Modifier.height(10.dp))
    }
}