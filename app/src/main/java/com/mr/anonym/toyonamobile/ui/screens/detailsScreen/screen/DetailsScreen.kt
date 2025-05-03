package com.mr.anonym.toyonamobile.ui.screens.detailsScreen.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mr.anonym.data.instance.local.DataStoreInstance
import com.mr.anonym.data.instance.local.SharedPreferencesInstance
import com.mr.anonym.domain.model.FriendsModel
import com.mr.anonym.domain.model.PartyModel
import com.mr.anonym.domain.model.TransactionsModel
import com.mr.anonym.domain.model.UserModel
import com.mr.anonym.toyonamobile.R
import com.mr.anonym.toyonamobile.presentation.extensions.cardNumberFormatter
import com.mr.anonym.toyonamobile.presentation.navigation.ScreensRouter
import com.mr.anonym.toyonamobile.ui.screens.detailsScreen.components.DetailsPriceFields
import com.mr.anonym.toyonamobile.ui.screens.detailsScreen.components.DetailsScreenTabRow
import com.mr.anonym.toyonamobile.ui.screens.detailsScreen.components.TransferCheckDialog
import com.mr.anonym.toyonamobile.ui.screens.detailsScreen.components.TransferDetailsBottomSheet
import com.mr.anonym.toyonamobile.ui.screens.detailsScreen.item.DetailsHistoryItem
import com.mr.anonym.toyonamobile.ui.screens.detailsScreen.viewModel.DetailsViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailsScreen(
    navController: NavController,
    viewModel: DetailsViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    val dataStore = DataStoreInstance(context)
    val sharedPreferences = SharedPreferencesInstance(context)

    val isDarkTheme = dataStore.getDarkThemeState().collectAsState(false)
    val iSystemTheme = dataStore.getSystemThemeState().collectAsState(true)

    val systemPrimaryColor = if (isSystemInDarkTheme()) Color.Black else Color.White
    val primaryColor = when {
        iSystemTheme.value -> {
            systemPrimaryColor
        }

        isDarkTheme.value -> Color.Black
        else -> Color.White
    }
    val systemSecondaryColor = if (isSystemInDarkTheme()) Color.White else Color.Black
    val secondaryColor = when {
        iSystemTheme.value -> systemSecondaryColor
        isDarkTheme.value -> Color.White
        else -> Color.Black
    }
    val systemTertiaryColor = if (isSystemInDarkTheme()) Color.DarkGray else Color.LightGray
    val tertiaryColor = when {
        iSystemTheme.value -> systemTertiaryColor
        isDarkTheme.value -> Color.DarkGray
        else -> Color.LightGray
    }
    val quaternaryColor = Color.Red
    val fiverdColor = Color.Green
    val systemSevenrdColor = if (isSystemInDarkTheme()) Color.Unspecified else Color.White
    val sevenrdColor = when {
        iSystemTheme.value -> systemSevenrdColor
        isDarkTheme.value -> Color.Unspecified
        else -> Color.White
    }

    val profileAvatar = sharedPreferences.getAvatar()

    val selectedTab = rememberSaveable { mutableStateOf(1) }

    val priceValue = remember { mutableStateOf("") }

    val friendsModel = FriendsModel(
        id = 1,
        name = "Ойбек",
        surname = "Худайкулов",
        phone = "+998973570498",
        cardNumber = "9860030160619356",
        userId = 1,
        datetime = "01.01.1900",
    )
    val partyModel = PartyModel(
        id = 1,
        userID = 1,
        type = "Келин туй",
        cardNumber = "9860030160619356",
        dateTime = "21-22-mart 2025,17:00"
    )

    val partyList = listOf(
        PartyModel(
            id = 1,
            userID = 1,
            type = "Келин туй",
            cardNumber = "9860030160619356",
            dateTime = "21-22-mart 2025,17:00"
        ),
        PartyModel(
            id = 1,
            userID = 1,
            type = "Келин туй",
            cardNumber = "9860030160619356",
            dateTime = "21-22-mart 2025,17:00"
        ),
        PartyModel(
            id = 1,
            userID = 1,
            type = "Келин туй",
            cardNumber = "9860030160619356",
            dateTime = "21-22-mart 2025,17:00"
        ),
    )

    val showTransferDetails = remember { mutableStateOf(false) }
    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val isExpanded = remember { mutableStateOf(false) }
    val isPriceFieldEnabled = remember { mutableStateOf(false) }
    val receiverCardNumber = "000111122223333"
    val senderCardNumber = viewModel.senderCard
    val cards = viewModel.cards
    val priceFieldError = rememberSaveable { mutableStateOf(false) }
    val showCheckDetails = rememberSaveable { mutableStateOf(false) }

    val priceHistoryValue = rememberSaveable { mutableStateOf("") }
    val priceHistoryValueError = rememberSaveable { mutableStateOf(false) }
    val showHistoryTransferDetails = rememberSaveable { mutableStateOf(false) }
    val showHistoryTransferCheck = rememberSaveable { mutableStateOf(false) }

    val userModel = UserModel(
        id = 1,
        name = "BEKHZOD",
        surName = "KHUDAYBERGENOV",
        phone = "+998973570498",
        cardNumber = senderCardNumber.value,
        password = "0000",
        dateTime = "04.06.1998"
    )
    val transactionsModel = TransactionsModel(
        id = 1,
        userId = userModel.id,
        sender = userModel.cardNumber,
        receiver = friendsModel.cardNumber,
        price = if (priceValue.value.isEmpty()) 0.0 else priceValue.value.toDouble(),
        dateTime = "30.04.2025"
    )

    Scaffold(
        containerColor = primaryColor,
        contentColor = primaryColor
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(0.1f)
                        .padding(top = 10.dp)
                ) {
                    IconButton(
                        onClick = { navController.popBackStack() }
                    ) {
                        Icon(
                            modifier = Modifier
                                .size(30.dp),
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            tint = secondaryColor,
                            contentDescription = ""
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 30.dp, top = 10.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            modifier = Modifier
                                .size(70.dp),
                            painter = painterResource(profileAvatar),
                            contentDescription = ""
                        )
                        Spacer(Modifier.height(10.dp))
                        Text(
                            text = "Бехзод Худайбергенов",
                            color = secondaryColor,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center
                        )
                        Spacer(Modifier.height(5.dp))
                        Text(
                            text = "+998973570498",
                            color = secondaryColor,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
            Spacer(Modifier.height(5.dp))
            DetailsScreenTabRow(
                modifier = Modifier
                    .padding(vertical = 10.dp),
                secondaryColor = secondaryColor,
                content = {
                    selectedTab.value = it
                    when (selectedTab.value) {
                        0 -> {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(10.dp),
                                contentAlignment = Alignment.BottomCenter
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(horizontal = 10.dp)
                                ) {
//                                    Event content
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Text(
                                            text = stringResource(R.string.event),
                                            color = secondaryColor,
                                            fontSize = 16.sp,
                                            fontWeight = FontWeight.SemiBold
                                        )
                                        Text(
                                            text = partyModel.type,
                                            color = secondaryColor,
                                            fontSize = 16.sp,
                                            fontWeight = FontWeight.SemiBold
                                        )
                                    }
                                    Spacer(Modifier.height(5.dp))
                                    HorizontalDivider()
                                    Spacer(Modifier.height(10.dp))
//                                    Event date and time content
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth(),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Text(
                                            modifier = Modifier
                                                .fillMaxWidth(0.5f),
                                            text = stringResource(R.string.event_date_and_time),
                                            color = secondaryColor,
                                            fontSize = 16.sp,
                                            fontWeight = FontWeight.SemiBold
                                        )
                                        Text(
                                            text = partyModel.dateTime,
                                            color = secondaryColor,
                                            fontSize = 16.sp,
                                            fontWeight = FontWeight.SemiBold,
                                            textAlign = TextAlign.End
                                        )
                                    }
                                    Spacer(Modifier.height(5.dp))
                                    HorizontalDivider()
                                    Spacer(Modifier.height(10.dp))
//                                    Requisites content
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth(),
                                        horizontalArrangement = Arrangement.Center
                                    ) {
                                        Text(
                                            text = stringResource(R.string.requisites),
                                            color = secondaryColor,
                                            fontSize = 16.sp,
                                            fontWeight = FontWeight.SemiBold
                                        )
                                    }
//                                    Cardholder content
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth(),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Text(
                                            modifier = Modifier
                                                .fillMaxWidth(0.5f),
                                            text = stringResource(R.string.card_holder_name),
                                            color = secondaryColor,
                                            fontSize = 16.sp,
                                            fontWeight = FontWeight.SemiBold
                                        )
                                        Text(
                                            text = "BEKHZOD KHUDAYBERGENOV",
                                            color = secondaryColor,
                                            fontSize = 16.sp,
                                            fontWeight = FontWeight.SemiBold,
                                            textAlign = TextAlign.End
                                        )
                                    }
                                    Spacer(Modifier.height(5.dp))
                                    HorizontalDivider()
                                    Spacer(Modifier.height(10.dp))
//                                    Card number content
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth(),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Text(
                                            modifier = Modifier
                                                .fillMaxWidth(0.5f),
                                            text = stringResource(R.string.card_number),
                                            color = secondaryColor,
                                            fontSize = 16.sp,
                                            fontWeight = FontWeight.SemiBold
                                        )
                                        Text(
                                            text = receiverCardNumber.cardNumberFormatter(),
                                            color = secondaryColor,
                                            fontSize = 16.sp,
                                            fontWeight = FontWeight.SemiBold,
                                            textAlign = TextAlign.End
                                        )
                                    }
                                    Spacer(Modifier.height(10.dp))
                                    HorizontalDivider()
                                    Spacer(Modifier.height(10.dp))
                                }
                                DetailsPriceFields(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(100.dp),
                                    secondaryColor = secondaryColor,
                                    fiverdColor = fiverdColor,
                                    value = priceValue.value,
                                    priceFieldError = priceFieldError.value,
                                    onValueChange = { newValue ->
                                        priceValue.value = newValue
                                    }
                                ) {
                                    if (
                                        priceValue.value.isDigitsOnly() &&
                                        priceValue.value.isNotEmpty() &&
                                        priceValue.value.isNotBlank() &&
                                        priceValue.value.toInt() >= 1000
                                    ) {
                                        showTransferDetails.value = true
                                    } else {
                                        priceFieldError.value = true
                                    }
                                }
                            }
                        }

                        1 -> {
                            LazyColumn(
                                modifier = Modifier
                                    .fillMaxSize()
                            ) {
                                items(partyList) { model ->
                                    DetailsHistoryItem(
                                        secondaryColor = secondaryColor,
                                        tertiaryColor = tertiaryColor,
                                        fiverdColor = fiverdColor,
                                        sevenrdColor = sevenrdColor,
                                        partyModel = model,
                                        priceFieldError = priceHistoryValueError.value,
                                        onTransferClick = { price ->
                                            priceHistoryValue.value = price
                                            showHistoryTransferDetails.value = true
                                        }
                                    )
                                }
                            }
                        }
                    }
                }
            )
            if (showTransferDetails.value) {
                TransferDetailsBottomSheet(
                    primaryColor = primaryColor,
                    secondaryColor = secondaryColor,
                    tertiaryColor = tertiaryColor,
                    quaternaryColor = quaternaryColor,
                    state = bottomSheetState,
                    onConfirmButtonClick = {
                        showCheckDetails.value = true
                    },
                    onDismissRequest = {
                        showTransferDetails.value = false
                        if (bottomSheetState.isVisible) {
                            coroutineScope.launch { bottomSheetState.hide() }
                        }
                        isPriceFieldEnabled.value = false
                    },
                    receiverCardNumber = receiverCardNumber,
                    senderCardNumber = senderCardNumber.value,
                    onSelectCardClick = {
                        isExpanded.value = true
                    },
//                Price field properties
                    isFieldEnabled = isPriceFieldEnabled.value,
                    priceValue = priceValue.value,
                    onValueChange = {
                        priceValue.value = it
                    },
                    onTrailingIconClick = {
                        isPriceFieldEnabled.value = true
                    },
//                DropDown menu properties
                    userCards = cards.value,
                    onDropDownDismissRequest = {
                        isExpanded.value = false
                    },
                    onItemClick = {
                        viewModel.changeSenderCard(it)
                        isExpanded.value = false
                    },
                    onAddCardClick = {
                        isExpanded.value = false
                        coroutineScope.launch {
                            dataStore.addCardFromDetails(true)
                        }
                        navController.navigate(ScreensRouter.AddCardScreen.route + "/-1")
                    },
                    isExpanded = isExpanded.value
                )
            }
            if (showCheckDetails.value) {
                TransferCheckDialog(
                    secondaryColor = secondaryColor,
                    quaternaryColor = quaternaryColor,
                    userModel = userModel,
                    friendsModel = friendsModel,
                    transactionsModel = transactionsModel,
                    onDismissClick = { showCheckDetails.value = false },
                    onConfirmClick = {
                        showCheckDetails.value = false
                        navController.navigate(ScreensRouter.MainScreen.route){
                            popUpTo(ScreensRouter.MainScreen.route){
                                inclusive = true
                            }
                            launchSingleTop = true
                        }
                    }
                )
            }
            if (showHistoryTransferDetails.value){
                TransferDetailsBottomSheet(
                    primaryColor = primaryColor,
                    secondaryColor = secondaryColor,
                    tertiaryColor = tertiaryColor,
                    quaternaryColor = quaternaryColor,
                    state = bottomSheetState,
                    isFieldEnabled = isPriceFieldEnabled.value,
                    priceValue = priceHistoryValue.value,
                    onValueChange = {
                        priceHistoryValue.value = it
                    },
                    onTrailingIconClick = {
                        isPriceFieldEnabled.value = true
                    },
                    receiverCardNumber = receiverCardNumber,
                    senderCardNumber = senderCardNumber.value,
                    onConfirmButtonClick = {
                        showHistoryTransferCheck.value = true
                    },
                    onDismissRequest = {
                        showHistoryTransferDetails.value = false
                        if (bottomSheetState.isVisible) {
                            coroutineScope.launch { bottomSheetState.hide() }
                        }
                        isPriceFieldEnabled.value = false
                    },
                    onSelectCardClick = {
                        isExpanded.value = true
                    },
                    userCards = cards.value,
                    onDropDownDismissRequest = {
                        isExpanded.value = false
                    },
                    onItemClick = {
                        viewModel.changeSenderCard(it)
                        isExpanded.value = false
                    },
                    onAddCardClick = {
                        isExpanded.value = false
                        coroutineScope.launch {
                            dataStore.addCardFromDetails(true)
                        }
                        navController.navigate(ScreensRouter.AddCardScreen.route + "/-1")
                    },
                    isExpanded = isExpanded.value
                )
            }
            if (showHistoryTransferCheck.value){
                TransferCheckDialog(
                    secondaryColor = secondaryColor,
                    quaternaryColor = quaternaryColor,
                    userModel = userModel,
                    friendsModel = friendsModel,
                    transactionsModel = transactionsModel,
                    onDismissClick = {
                        showHistoryTransferCheck.value = false
                    },
                    onConfirmClick = {
                        showHistoryTransferCheck.value = false
                    }
                )
            }
        }
    }
}

    @Preview
    @Composable
    private fun PreviewDetailsScreen() {
        DetailsScreen(
            NavController(LocalContext.current)
        )
    }