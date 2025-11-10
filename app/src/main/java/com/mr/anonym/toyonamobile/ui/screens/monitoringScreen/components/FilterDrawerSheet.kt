package com.mr.anonym.toyonamobile.ui.screens.monitoringScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mr.anonym.domain.model.CardModel
import com.mr.anonym.toyonamobile.R
import com.mr.anonym.toyonamobile.presentation.extensions.cardNumberFormatter
import com.mr.anonym.toyonamobile.ui.screens.monitoringScreen.items.FilterByCardItem

@Composable
fun FilterDrawerSheet(
    primaryColor: Color,
    secondaryColor: Color,
    quaternaryColor: Color,
    fiveColor: Color,
    nineColor: Color,
    tenColor: Color,
    fontFamily: FontFamily,
    cardList: List<CardModel>,
    onCardClick: (String) -> Unit,
    onDateIndex: (Int) -> Unit,
    onBackClick: () -> Unit,
    onConfirmClick:()-> Unit
) {

    val showDate = remember { mutableStateOf(false) }
    val showCard = remember { mutableStateOf(false) }

    val forTodayChecked = remember { mutableStateOf(false) }
    val forYesterdayChecked = remember { mutableStateOf(false) }
    val forWeekChecked = remember { mutableStateOf(false) }
    val forPastWeekChecked = remember { mutableStateOf(false) }
    val forMonthChecked = remember { mutableStateOf(false) }
    val forPastMonthChecked = remember { mutableStateOf(false) }

    val isFiltered = remember { mutableStateOf(false) }
    val isDateFiltered = remember { mutableStateOf(false) }
    val isCardFiltered = remember { mutableStateOf(false) }

    val filteredCard = remember { mutableStateOf("") }

    val dateIndex = remember { mutableIntStateOf(0) }
    val selectedCardIndex = remember { mutableIntStateOf( -1 ) }

    /*
    * By date filter index's
    * 1->For today
    * 2->For yesterday
    * 3->For Week
    * 4->For past week
    * 5->For Month
    * 6->For past month
    * */

    ModalDrawerSheet(
        modifier = Modifier.width(300.dp),
        drawerShape = RoundedCornerShape(15.dp),
        drawerContainerColor = primaryColor,
        drawerContentColor = primaryColor,
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp)
        ) {
//            Top bar
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(
                    onClick = { onBackClick() }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_back),
                        tint = secondaryColor,
                        contentDescription = ""
                    )
                }
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = stringResource(R.string.filter),
                    color = secondaryColor,
                    fontFamily = fontFamily,
                    fontSize = 22.sp,
                    textAlign = TextAlign.Center
                )
            }
            Spacer(Modifier.height(10.dp))
            if (isFiltered.value) {
                Column (
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    if (isDateFiltered.value) {
                        FilteredContent(
                            secondaryColor = secondaryColor,
                            text = when (dateIndex.intValue) {
                                0 -> ""
                                1 -> stringResource(R.string.for_today)
                                2 -> stringResource(R.string.for_yesterday)
                                3 -> stringResource(R.string.for_week)
                                4 -> stringResource(R.string.for_past_week)
                                5 -> stringResource(R.string.for_current_month)
                                6 -> stringResource(R.string.for_past_month)
                                else -> ""
                            },
                            fontFamily = fontFamily,
                            onClear = {
                                isDateFiltered.value = false
                                if (!isDateFiltered.value && !isCardFiltered.value) {
                                    isCardFiltered.value = false
                                }
                            }
                        )
                    }
                    if (isCardFiltered.value) {
                        FilteredContent(
                            secondaryColor = secondaryColor,
                            text = filteredCard.value.cardNumberFormatter(),
                            fontFamily = fontFamily,
                            onClear = {
                                isCardFiltered.value = false
                                if (!isCardFiltered.value && !isDateFiltered.value) isFiltered.value =
                                    false
                            }
                        )
                    }
                }
            }
            Spacer(Modifier.height(10.dp))
//            By date
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = nineColor, shape = RoundedCornerShape(15.dp))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Text(
                        text = stringResource(R.string.by_date),
                        color = secondaryColor,
                        fontSize = 16.sp,
                        fontFamily = fontFamily
                    )
                    IconButton(
                        modifier = Modifier
                            .size(25.dp),
                        onClick = { showDate.value = !showDate.value }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_add),
                            tint = secondaryColor,
                            contentDescription = ""
                        )
                    }
                }
                if (showDate.value) {
                    ByDateContent(
                        text = stringResource(R.string.for_today),
                        secondaryColor = secondaryColor,
                        quaternaryColor = quaternaryColor,
                        fiveColor = fiveColor,
                        nineColor = nineColor,
                        fontFamily = fontFamily,
                        isChecked = forTodayChecked.value
                    ) {
                        forTodayChecked.value = it
                        if (it) {
                            forYesterdayChecked.value = false
                            forWeekChecked.value = false
                            forPastWeekChecked.value = false
                            forMonthChecked.value = false
                            forPastMonthChecked.value = false
                            onDateIndex(1)
                            dateIndex.intValue = 1
                            isFiltered.value = true
                            isDateFiltered.value = true
                        } else {
                            isDateFiltered.value = false
                        }
                    }
                    ByDateContent(
                        text = stringResource(R.string.for_yesterday),
                        secondaryColor = secondaryColor,
                        quaternaryColor = quaternaryColor,
                        fiveColor = fiveColor,
                        nineColor = nineColor,
                        fontFamily = fontFamily,
                        isChecked = forYesterdayChecked.value
                    ) {
                        forYesterdayChecked.value = it
                        if (it) {
                            forTodayChecked.value = false
                            forWeekChecked.value = false
                            forPastWeekChecked.value = false
                            forMonthChecked.value = false
                            forPastMonthChecked.value = false
                            onDateIndex(2)
                            dateIndex.intValue = 2
                            isFiltered.value = true
                            isDateFiltered.value = true
                        } else {
                            isDateFiltered.value = false
                        }
                    }
                    ByDateContent(
                        text = stringResource(R.string.for_week),
                        secondaryColor = secondaryColor,
                        quaternaryColor = quaternaryColor,
                        fiveColor = fiveColor,
                        nineColor = nineColor,
                        fontFamily = fontFamily,
                        isChecked = forWeekChecked.value
                    ) {
                        forWeekChecked.value = it
                        if (it) {
                            forTodayChecked.value = false
                            forYesterdayChecked.value = false
                            forPastWeekChecked.value = false
                            forMonthChecked.value = false
                            forPastMonthChecked.value = false
                            onDateIndex(3)
                            dateIndex.intValue = 3
                            isFiltered.value = true
                            isDateFiltered.value = true
                        } else {
                            isDateFiltered.value = false
                        }
                    }
                    ByDateContent(
                        text = stringResource(R.string.for_past_week),
                        secondaryColor = secondaryColor,
                        quaternaryColor = quaternaryColor,
                        fiveColor = fiveColor,
                        nineColor = nineColor,
                        fontFamily = fontFamily,
                        isChecked = forPastWeekChecked.value
                    ) {
                        forPastWeekChecked.value = it
                        if (it) {
                            forTodayChecked.value = false
                            forYesterdayChecked.value = false
                            forWeekChecked.value = false
                            forMonthChecked.value = false
                            forPastMonthChecked.value = false
                            onDateIndex(4)
                            dateIndex.intValue = 4
                            isFiltered.value = true
                            isDateFiltered.value = true
                        } else {
                            isDateFiltered.value = false
                        }
                    }
                    ByDateContent(
                        text = stringResource(R.string.for_current_month),
                        secondaryColor = secondaryColor,
                        quaternaryColor = quaternaryColor,
                        fiveColor = fiveColor,
                        nineColor = nineColor,
                        fontFamily = fontFamily,
                        isChecked = forMonthChecked.value
                    ) {
                        forMonthChecked.value = it
                        if (it) {
                            forTodayChecked.value = false
                            forYesterdayChecked.value = false
                            forWeekChecked.value = false
                            forPastWeekChecked.value = false
                            forPastMonthChecked.value = false
                            onDateIndex(5)
                            dateIndex.intValue = 5
                            isFiltered.value = true
                            isDateFiltered.value = true
                        } else {
                            isDateFiltered.value = false
                        }
                    }
                    ByDateContent(
                        text = stringResource(R.string.for_past_month),
                        secondaryColor = secondaryColor,
                        quaternaryColor = quaternaryColor,
                        fiveColor = fiveColor,
                        nineColor = nineColor,
                        fontFamily = fontFamily,
                        isChecked = forPastMonthChecked.value
                    ) {
                        forPastMonthChecked.value = it
                        if (it) {
                            forTodayChecked.value = false
                            forYesterdayChecked.value = false
                            forWeekChecked.value = false
                            forPastWeekChecked.value = false
                            forMonthChecked.value = false
                            onDateIndex(6)
                            dateIndex.intValue = 6
                            isFiltered.value = true
                            isDateFiltered.value = true
                        } else {
                            isDateFiltered.value = false
                        }
                    }
                }
            }
            Spacer(Modifier.height(10.dp))
//            By card
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = nineColor, shape = RoundedCornerShape(15.dp))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = nineColor, shape = RoundedCornerShape(15.dp))
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Text(
                        text = stringResource(R.string.by_card),
                        color = secondaryColor,
                        fontSize = 16.sp,
                        fontFamily = fontFamily
                    )
                    IconButton(
                        modifier = Modifier
                            .size(25.dp),
                        onClick = { showCard.value = !showCard.value }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_add),
                            tint = secondaryColor,
                            contentDescription = ""
                        )
                    }
                }
                if (showCard.value) {
                    LazyColumn {
                        itemsIndexed(cardList) {index, model ->
                            filteredCard.value = model.number
                            FilterByCardItem(
                                secondaryColor = secondaryColor,
                                quaternaryColor = quaternaryColor,
                                fiveColor = Color.Green,
                                fontFamily = fontFamily,
                                model = model,
                                isSelected = selectedCardIndex.intValue == index,
                            ) {
                                onCardClick(model.number)
                                isFiltered.value = true
                                isCardFiltered.value = true
                                selectedCardIndex.value = index
                            }
                        }
                    }
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.BottomCenter
            ){
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Button(
                        shape = RoundedCornerShape(15.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = tenColor,
                            contentColor = tenColor
                        ),
                        onClick = {
                            isDateFiltered.value = false
                            isCardFiltered.value = false
                            isFiltered.value = false
                            selectedCardIndex.intValue = -1
                            showDate.value = false
                                showCard.value = false
                        }
                    ) {
                        Text(
                            text = stringResource(R.string.reset),
                            color = secondaryColor,
                            fontSize = 16.sp,
                            fontFamily = fontFamily,
                        )
                    }
                    Button(
                        shape = RoundedCornerShape(15.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = fiveColor,
                            contentColor = fiveColor
                        ),
                        onClick = {
                            onConfirmClick()
                        }
                    ) {
                        Text(
                            text = stringResource(R.string.apply),
                            color = secondaryColor,
                            fontSize = 16.sp,
                            fontFamily = fontFamily,
                        )
                    }
                }
            }
        }
    }
}