package com.mr.anonym.toyonamobile.ui.screens.settingsScreen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
fun ThemeBottomSheet(
    secondaryColor:Color,
    tertiaryColor: Color,
    quaternaryColor: Color,
    fiveColor: Color,
    nineColor: Color,
    fontFamily: FontFamily,
    state: SheetState,
    onDismissRequest:()-> Unit,
    isDaySelected: Boolean,
    onDayClick:()-> Unit,
    isNightSelected: Boolean,
    onNightClick:()-> Unit,
    isSystemSelected: Boolean,
    onSystemClick:()-> Unit
) {
    ModalBottomSheet(
        onDismissRequest = { onDismissRequest() },
        sheetState = state,
        shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp),
        containerColor = nineColor,
        contentColor = nineColor,
        tonalElevation = 7.dp,
        dragHandle = {
            BottomSheetDefaults.DragHandle(
                height = 2.dp,
                shape = RoundedCornerShape(10.dp),
                color = secondaryColor
            )
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(7.dp))
            Text(
                text = stringResource(R.string.choose_app_theme),
                color = secondaryColor,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = fontFamily
            )
            Spacer(Modifier.height(7.dp))
//            Day theme field
            Card (
                colors = CardDefaults.cardColors(
                    containerColor = tertiaryColor,
                    contentColor = tertiaryColor
                ),
                shape = RoundedCornerShape(10.dp),
                elevation = CardDefaults.cardElevation(7.dp),
                onClick = { onDayClick() }
            ){
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Row (
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Icon(
                            modifier = Modifier
                                .size(45.dp),
                            painter = painterResource(R.drawable.ic_day),
                            tint = secondaryColor,
                            contentDescription = ""
                        )
                        Spacer(Modifier.width(10.dp))
                        Text(
                            text = stringResource(R.string.day_theme),
                            fontSize = 16.sp,
                            color = secondaryColor,
                            fontWeight = FontWeight.SemiBold,
                            fontFamily = fontFamily
                        )
                    }
                    RadioButton(
                        selected = isDaySelected,
                        onClick = { onDayClick() },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = fiveColor,
                            unselectedColor = quaternaryColor
                        ),
                    )
                }
            }
            Spacer(Modifier.height(10.dp))
//            Night theme field
            Card (
                colors = CardDefaults.cardColors(
                    containerColor = tertiaryColor,
                    contentColor = tertiaryColor
                ),
                shape = RoundedCornerShape(10.dp),
                elevation = CardDefaults.cardElevation(7.dp),
                onClick = { onNightClick() }
            ){
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Row (
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Icon(
                            modifier = Modifier
                                .size(45.dp),
                            painter = painterResource(R.drawable.ic_night),
                            tint = secondaryColor,
                            contentDescription = ""
                        )
                        Spacer(Modifier.width(10.dp))
                        Text(
                            text = stringResource(R.string.night_theme),
                            fontSize = 16.sp,
                            color = secondaryColor,
                            fontWeight = FontWeight.SemiBold,
                            fontFamily = fontFamily
                        )
                    }
                    RadioButton(
                        selected = isNightSelected,
                        onClick = { onNightClick() },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = fiveColor,
                            unselectedColor = quaternaryColor
                        ),
                    )
                }
            }
            Spacer(Modifier.height(10.dp))
//            System theme field
            Card (
                colors = CardDefaults.cardColors(
                    containerColor = tertiaryColor,
                    contentColor = tertiaryColor
                ),
                shape = RoundedCornerShape(10.dp),
                elevation = CardDefaults.cardElevation(7.dp),
                onClick = { onSystemClick() }
            ){
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Row (
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Icon(
                            modifier = Modifier
                                .size(45.dp),
                            painter = painterResource(R.drawable.ic_system_theme),
                            tint = secondaryColor,
                            contentDescription = ""
                        )
                        Spacer(Modifier.width(10.dp))
                        Text(
                            text = stringResource(R.string.system_theme),
                            fontSize = 16.sp,
                            color = secondaryColor,
                            fontWeight = FontWeight.SemiBold,
                            fontFamily = fontFamily
                        )
                    }
                    RadioButton(
                        selected = isSystemSelected,
                        onClick = { onSystemClick() },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = fiveColor,
                            unselectedColor = quaternaryColor
                        ),
                    )
                }
            }
            Spacer(Modifier.height(10.dp))
        }
    }
}