package com.mr.anonym.toyonamobile.ui.screens.monitoringScreen.components

import androidx.compose.material3.DrawerState
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import com.mr.anonym.domain.model.CardModel

@Composable
fun FilterNavigationDrawer(
    primaryColor: Color,
    secondaryColor: Color,
    quaternaryColor: Color,
    fiveColor:Color,
    nineColor: Color,
    tenColor: Color,
    fontFamily: FontFamily,
    cardList: List<CardModel>,
    onCardClick:(String)-> Unit,
    onDateIndex:(Int)-> Unit,
    onBackClick:()-> Unit,
    onConfirmClick:()-> Unit,
    drawerState: DrawerState,
    content: @Composable ()->Unit
) {

    ModalNavigationDrawer(
        drawerContent = {
            FilterDrawerSheet(
                primaryColor = primaryColor,
                secondaryColor = secondaryColor,
                quaternaryColor = quaternaryColor,
                fiveColor = fiveColor,
                nineColor = nineColor,
                tenColor = tenColor,
                fontFamily = fontFamily,
                cardList = cardList,
                onCardClick = { onCardClick(it) },
                onDateIndex = { onDateIndex(it) },
                onConfirmClick = { onConfirmClick() },
                onBackClick = { onBackClick() }
            )
        },
        drawerState = drawerState,
    ) {
        content()
    }
}