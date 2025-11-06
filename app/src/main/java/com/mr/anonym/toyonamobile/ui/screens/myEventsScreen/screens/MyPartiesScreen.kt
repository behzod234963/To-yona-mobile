package com.mr.anonym.toyonamobile.ui.screens.myEventsScreen.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.mr.anonym.data.instance.local.SharedPreferencesInstance
import com.mr.anonym.domain.model.LocalPartyModel
import com.mr.anonym.domain.model.PartysItem
import com.mr.anonym.toyonamobile.R
import com.mr.anonym.toyonamobile.presentation.navigation.ScreensRouter
import com.mr.anonym.toyonamobile.ui.screens.myEventsScreen.components.MyEventTopBar
import com.mr.anonym.toyonamobile.ui.screens.myEventsScreen.components.MyPartiesItem
import com.mr.anonym.toyonamobile.ui.screens.myEventsScreen.viewModel.MyPartiesViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@Composable
fun MyPartiesScreen(
    navController: NavController,
    viewModel: MyPartiesViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    val sharedPreferences = SharedPreferencesInstance(context)

    val isDarkTheme = sharedPreferences.getDarkThemeState()
    val isSystemTheme = sharedPreferences.getSystemThemeState()

    val systemPrimaryColor = if (isSystemInDarkTheme()) Color.Black else Color.White
    val primaryColor = when {
        isSystemTheme -> {
            systemPrimaryColor
        }

        isDarkTheme -> Color.Black
        else -> Color.White
    }
    val systemSecondaryColor = if (isSystemInDarkTheme()) Color.White else Color.Black
    val secondaryColor = when {
        isSystemTheme -> systemSecondaryColor
        isDarkTheme -> Color.White
        else -> Color.Black
    }
    val quaternaryColor = Color.Red
    val fiveColor = Color(101, 163, 119, 255)
    val systemSevenColor = if (isSystemInDarkTheme()) Color.Unspecified else Color.White
    val sevenColor = when {
        isSystemTheme -> systemSevenColor
        isDarkTheme -> Color.Unspecified
        else -> Color.White
    }
    val systemNineColor = if (isSystemInDarkTheme()) Color(0xFF222327) else Color(0xFFF1F2F4)
    val nineColor = when{
        isSystemTheme -> systemNineColor
        isDarkTheme -> Color(0xFF222327)
        else -> Color(0xFFF1F2F4)
    }

    val iosFont = FontFamily(Font(R.font.ios_font))

    val isLoading = remember { mutableStateOf( false ) }
    val loadingAnimation = rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.anim_loading)
    )

    val parties = viewModel.parties

    Scaffold(
        containerColor = nineColor,
        contentColor = nineColor,
        topBar = {
            MyEventTopBar(
                primaryColor = primaryColor,
                secondaryColor = secondaryColor,
                fontFamily = iosFont,
            ) { navController.navigateUp() }
        }
    ) { paddingValues ->
        if (!isLoading.value){
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(10.dp)
            ) {
                items(parties.value) { model ->
                    val localParty = LocalPartyModel(
                        id = model.id,
                        userId = model.userId,
                        userName = model.userName,
                        name = model.name,
                        type = model.type,
                        address = model.address,
                        cardNumber = model.cardNumber,
                        startTime = model.startTime,
                        endTime = model.endTime,
                        status = model.status,
                        createdAt = model.createdAt
                    )
                    MyPartiesItem(
                        secondaryColor = secondaryColor,
                        quaternaryColor = quaternaryColor,
                        fiveColor = fiveColor,
                        sevenColor = sevenColor,
                        fontFamily = iosFont,
                        partyModel = model,
                        onEditClick = {
                            navController.navigate(ScreensRouter.AddPartyScreen.route + "/${model.id}")
                        },
                        onDeleteClick = {
                            isLoading.value = true
                            viewModel.deleteParty(model.id)
                            viewModel.deleteLocalParty(localParty)
                        }
                    ) {
                        isLoading.value = true
                        viewModel.updateParty(
                            partyID = model.id,
                            partyModel = PartysItem(
                                name = model.name,
                                type = model.type,
                                address = model.address,
                                cardNumber = model.cardNumber,
                                startTime = model.startTime,
                                endTime = model.endTime,
                                status = it
                            )
                        )
                        if (model.status) {
                            viewModel.insertLocalParty(localParty)
                        } else {
                            viewModel.deleteLocalParty(localParty)
                        }
                    }
                }
            }
        }else{
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                LottieAnimation(
                    modifier = Modifier
                        .size(150.dp),
                    composition = loadingAnimation.value,
                    restartOnPlay = true,
                    iterations = LottieConstants.IterateForever
                )
                coroutineScope.launch {
                    viewModel.getUserParties()
                    delay(1200)
                    isLoading.value = false
                }
            }
        }
    }
}