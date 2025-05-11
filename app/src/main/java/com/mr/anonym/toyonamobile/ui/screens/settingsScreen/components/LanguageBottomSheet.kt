package com.mr.anonym.toyonamobile.ui.screens.settingsScreen.components

import androidx.compose.foundation.Image
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mr.anonym.toyonamobile.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LanguageBottomSheet(
    primaryColor: Color,
    secondaryColor:Color,
    tertiaryColor: Color,
    quaternaryColor: Color,
    fiverdColor: Color,
    state: SheetState,
    onDismissRequest:()-> Unit,
    onUzbekSelected: Boolean,
    onUzbekClick:()-> Unit,
    onRussianSelected: Boolean,
    onRussianClick:()-> Unit
) {
    ModalBottomSheet(
        onDismissRequest = { onDismissRequest() },
        sheetState = state,
        shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp),
        containerColor = tertiaryColor,
        contentColor = tertiaryColor,
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
                text = stringResource(R.string.choose_the_language),
                color = secondaryColor,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(Modifier.height(7.dp))
            Card (
                colors = CardDefaults.cardColors(
                    containerColor = primaryColor,
                    contentColor = primaryColor
                ),
                shape = RoundedCornerShape(10.dp),
                elevation = CardDefaults.cardElevation(7.dp),
                onClick = { onUzbekClick() }
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
                        Image(
                            modifier = Modifier
                                .size(45.dp),
                            painter = painterResource(R.drawable.ic_uz_flag),
                            contentDescription = ""
                        )
                        Spacer(Modifier.width(10.dp))
                        Text(
                            text = stringResource(R.string.o_zbekcha),
                            fontSize = 16.sp,
                            color = secondaryColor,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                    RadioButton(
                        selected = onUzbekSelected,
                        onClick = { onUzbekClick() },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = fiverdColor,
                            unselectedColor = quaternaryColor
                        ),
                    )
                }
            }
            Spacer(Modifier.height(10.dp))
            Card (
                colors = CardDefaults.cardColors(
                    containerColor = primaryColor,
                    contentColor = primaryColor
                ),
                shape = RoundedCornerShape(10.dp),
                elevation = CardDefaults.cardElevation(7.dp),
                onClick = { onRussianClick() }
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
                        Image(
                            modifier = Modifier
                                .size(45.dp),
                            painter = painterResource(R.drawable.ic_ru_flag),
                            contentDescription = ""
                        )
                        Spacer(Modifier.width(10.dp))
                        Text(
                            text = stringResource(R.string.russian),
                            fontSize = 16.sp,
                            color = secondaryColor,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                    RadioButton(
                        selected = onRussianSelected,
                        onClick = { onRussianClick() },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = fiverdColor,
                            unselectedColor = quaternaryColor
                        ),
                    )
                }
            }
            Spacer(Modifier.height(10.dp))
        }
    }
}