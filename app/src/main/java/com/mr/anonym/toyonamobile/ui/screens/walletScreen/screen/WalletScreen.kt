package com.mr.anonym.toyonamobile.ui.screens.walletScreen.screen

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
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
import com.mr.anonym.domain.model.CardModel
import com.mr.anonym.domain.model.CardUtilModel
import com.mr.anonym.toyonamobile.R
import com.mr.anonym.toyonamobile.presentation.navigation.ScreensRouter
import com.mr.anonym.toyonamobile.ui.screens.walletScreen.components.WalletScreenDialog
import com.mr.anonym.toyonamobile.ui.screens.walletScreen.components.WalletTopBar
import com.mr.anonym.toyonamobile.ui.screens.walletScreen.item.WalletScreenItem
import com.mr.anonym.toyonamobile.ui.screens.walletScreen.viewModel.WalletViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@Composable
fun WalletScreen(
    navController: NavController,
    viewModel: WalletViewModel = hiltViewModel()
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
    val fiveColor = Color.Green

    val iosFont = FontFamily(Font(R.font.ios_font))

    val showDeleteDialog = rememberSaveable { mutableStateOf(false) }

    val colorIndex = viewModel.colorIndex
//    val cards = viewModel.cards
    val cards = remember {
        mutableStateOf(
            listOf(
                CardModel(
                    number = "9860030160619356",
                    date = "0925",
                    createdAt = "01.11.2011"
                ),
                CardModel(
                    number = "9860030160619356",
                    date = "0925",
                    createdAt = "01.11.2011"
                )
            )
        )
    }
    val cardID = remember { mutableIntStateOf(-1) }
    val cardUtilModel = remember { mutableStateOf(CardUtilModel() ) }
    sharedPreferences.addCardProcess(false)

    val isLoading = remember { mutableStateOf(false) }
    val loadingAnimations = rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.anim_loading)
    )

//    Card gradients
//    1.
    val greenGradient = Brush.linearGradient(
        colors = listOf(
            Color(0xFF00C853), // ярко-зелёный
            Color(0xFFB2FF59)  // светло-зелёный
        )
    )
//    2.
    val redGradient = Brush.linearGradient(
        colors = listOf(
            Color(0xFFFF1744), // насыщенный красный
            Color(0xFFFF8A80)  // мягкий коралловый
        )
    )
//    3.
    val purpleGradient = Brush.linearGradient(
        colors = listOf(
            Color(0xFF7C4DFF), // насыщенный фиолетовый
            Color(0xFFB388FF)  // светлый лавандовый
        )
    )
//    4.
    val blueGradient = Brush.linearGradient(
        colors = listOf(
            Color(0xFF00B0FF), // небесно-голубой
            Color(0xFF40C4FF)  // яркий бирюзовый
        )
    )
//    5.
    val orangeGradient = Brush.linearGradient(
        colors = listOf(
            Color(0xFFFF6D00), // оранжевый
            Color(0xFFFFD600)  // солнечный жёлтый
        )
    )
//    6.
    val blackGoldGradient = Brush.linearGradient(
        colors = listOf(
            Color(0xFF212121), // тёмный графит
            Color(0xFFFFD740)  // золотистый акцент
        )
    )


    BackHandler {
        navController.navigate(ScreensRouter.MainScreen.route) {
            popUpTo(ScreensRouter.WalletScreen.route) {
                inclusive = true
            }
        }
    }
    Scaffold(
        containerColor = primaryColor,
        contentColor = primaryColor,
        topBar = {
            WalletTopBar(
                primaryColor = primaryColor,
                secondaryColor = secondaryColor,
                fontFamily = iosFont,
                navigationClick = {
                    navController.navigate(ScreensRouter.MainScreen.route) {
                        popUpTo(ScreensRouter.WalletScreen.route) {
                            inclusive = true
                        }
                    }
                }
            ) { navController.navigate(ScreensRouter.AddCardScreen.route + "/-1") }
        }
    ) { paddingValues ->
        if (!isLoading.value) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(10.dp)
            ) {
                items(cards.value) { model ->
                    val formattedID = model.number.takeLast(4).toInt()
                    viewModel.getColorIndex(formattedID)
                    WalletScreenItem(
                        secondaryColor = secondaryColor,
                        fontFamily = iosFont,
                        brush = when (colorIndex.value) {
                            1 -> greenGradient
                            2 -> redGradient
                            3 -> purpleGradient
                            4 -> blueGradient
                            5 -> orangeGradient
                            6 -> blackGoldGradient
                            else -> greenGradient
                        },
                        model = model,
                        onChangeClick = { navController.navigate(ScreensRouter.AddCardScreen.route + "/${model.id}") },
                        onDeleteClick = {
                            cardID.intValue = model.id
                            showDeleteDialog.value = true
                            cardUtilModel.value = CardUtilModel(
                                id = formattedID,
                                colorIndex = colorIndex.value
                            )
                        },
                    )
                }
            }
            if (showDeleteDialog.value) {
                WalletScreenDialog(
                    secondaryColor = secondaryColor,
                    quaternaryColor = quaternaryColor,
                    fiveColor = fiveColor,
                    fontFamily = iosFont,
                    onConfirmClick = {
                        showDeleteDialog.value = false
                        viewModel.deleteCard(id = cardID.intValue)
                        viewModel.deleteCardUtil(
                            CardUtilModel(
                                id = cardUtilModel.value.id,
                                colorIndex = colorIndex.value
                            )
                        )
                        isLoading.value = true
                    },
                    onDismissClick = { showDeleteDialog.value = false }
                ) { showDeleteDialog.value = false }
            }
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                LottieAnimation(
                    modifier = Modifier
                        .size(150.dp),
                    composition = loadingAnimations.value,
                    restartOnPlay = true,
                    iterations = LottieConstants.IterateForever
                )
                coroutineScope.launch {
                    delay(1000)
                    viewModel.getUserCards()
                    isLoading.value = false
                }
            }
        }
    }
}