package com.mr.anonym.toyonamobile.ui.screens.detailsScreen.screen

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.mr.anonym.data.instance.local.SharedPreferencesInstance
import com.mr.anonym.domain.model.PartysItem
import com.mr.anonym.toyonamobile.R
import com.mr.anonym.toyonamobile.presentation.extensions.phoneNumberTransformation
import com.mr.anonym.toyonamobile.presentation.navigation.ScreensRouter
import com.mr.anonym.toyonamobile.presentation.utils.Arguments
import com.mr.anonym.toyonamobile.ui.screens.detailsScreen.components.DetailsScreenTabRow
import com.mr.anonym.toyonamobile.ui.screens.detailsScreen.components.TransferCheckDialog
import com.mr.anonym.toyonamobile.ui.screens.detailsScreen.components.TransferDetailsBottomSheet
import com.mr.anonym.toyonamobile.ui.screens.detailsScreen.item.ActualEventsItem
import com.mr.anonym.toyonamobile.ui.screens.detailsScreen.item.DetailsHistoryItem
import com.mr.anonym.toyonamobile.ui.screens.detailsScreen.viewModel.DetailsViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@Composable
fun DetailsScreen(
    navController: NavController,
    arguments: Arguments,
    viewModel: DetailsViewModel = hiltViewModel()
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
    val systemTertiaryColor = if (isSystemInDarkTheme()) Color.DarkGray else Color.LightGray
    val tertiaryColor = when {
        isSystemTheme -> systemTertiaryColor
        isDarkTheme -> Color.DarkGray
        else -> Color.LightGray
    }
    val quaternaryColor = Color.Red
    val fiverdColor = Color.Green
    val systemSevenrdColor = if (isSystemInDarkTheme()) Color.Unspecified else Color.White
    val sevenrdColor = when {
        isSystemTheme -> systemSevenrdColor
        isDarkTheme -> Color.Unspecified
        else -> Color.White
    }
    val profileAvatar = viewModel.profileAvatar
    val selectedTab = rememberSaveable { mutableIntStateOf(1) }

    val priceValue = remember { mutableStateOf("") }
    val receiver = viewModel.user
    val sender = viewModel.sender
    val activeParties = viewModel.activeParties
    val parties = viewModel.parties
    val partyModel = remember { mutableStateOf(PartysItem()) }

    val showTransferDetails = remember { mutableStateOf(false) }
    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val isExpanded = remember { mutableStateOf(false) }
    val isPriceFieldEnabled = remember { mutableStateOf(false) }
    val senderCardNumber = viewModel.senderCard
    val cards = viewModel.cards
    val priceFieldError = rememberSaveable { mutableStateOf(false) }
    val showCheckDetails = rememberSaveable { mutableStateOf(false) }
    val isFriendAdded = viewModel.isFriendAdded

    val priceHistoryValueError = rememberSaveable { mutableStateOf(false) }

    val snackbarState = remember { SnackbarHostState() }
    val isLoading = remember { mutableStateOf( true) }
    val loadingAnimation = rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.anim_loading)
    )

//    val contactsLauncher = OpenNewContactMethod(context)

    BackHandler {
        navController.navigate(ScreensRouter.MainScreen.route)
    }
    Scaffold(
        containerColor = primaryColor,
        contentColor = primaryColor,
        snackbarHost = { SnackbarHost(snackbarState) }
    ) { paddingValues ->
        if(!isLoading.value){
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    IconButton(
                        onClick = { navController.navigate(ScreensRouter.MainScreen.route) }
                    ) {
                        Icon(
                            modifier = Modifier
                                .size(30.dp),
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            tint = secondaryColor,
                            contentDescription = ""
                        )
                    }
                    Column(
                        modifier = Modifier
                            .padding(top = 5.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            modifier = Modifier
                                .size(70.dp),
                            painter = painterResource(profileAvatar.value),
                            contentDescription = ""
                        )
                        Spacer(Modifier.height(10.dp))
                        Text(
                            text = "${receiver.value.username} ${receiver.value.surname}",
                            color = secondaryColor,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center
                        )
                        Spacer(Modifier.height(5.dp))
                        Text(
                            text = "+998${receiver.value.phonenumber}".phoneNumberTransformation(),
                            color = secondaryColor,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                    IconButton(
                        onClick = {
                            isLoading.value = true
                            if (isFriendAdded.value){
                                viewModel.deleteFriend(arguments.userId)
                            }else{
                                viewModel.addFriend(arguments.userId)
                            }
//                            val intent = Intent(ContactsContract.Intents.Insert.ACTION).apply {
//                                type = ContactsContract.RawContacts.CONTENT_TYPE
//                                putExtra(
//                                    ContactsContract.Intents.Insert.NAME,
//                                    "${receiver.value.username} ${receiver.value.surname}"
//                                )
//                                putExtra(
//                                    ContactsContract.Intents.Insert.PHONE,
//                                    receiver.value.phonenumber
//                                )
//                            }
//                            contactsLauncher.launch(intent)
                        }
                    ) {
                        Icon(
                            modifier = Modifier
                                .size(30.dp),
                            painter = if (isFriendAdded.value) painterResource(R.drawable.ic_friend_added) else painterResource(R.drawable.ic_add_friend),
                            tint = secondaryColor,
                            contentDescription = ""
                        )
                    }
                }
                Spacer(Modifier.height(5.dp))
                DetailsScreenTabRow(
                    modifier = Modifier
                        .padding(vertical = 10.dp),
                    secondaryColor = secondaryColor,
                    content = {
                        selectedTab.intValue = it
                        when (selectedTab.intValue) {
                            0 -> {
                                LazyColumn(
                                    modifier = Modifier
                                        .fillMaxSize()
                                ) {
                                    items(activeParties.value) { model ->
                                        partyModel.value = model
                                        ActualEventsItem(
                                            secondaryColor = secondaryColor,
                                            fiverdColor = fiverdColor,
                                            sevenrdColor = sevenrdColor,
                                            partyModel = model,
                                            userModel = receiver.value,
                                            priceFieldError = priceFieldError.value,
                                        ) { price ->
                                            partyModel.value = model
                                            priceValue.value = price.filter { digit -> digit.isDigit() }
                                            try {
                                                if (
                                                    priceValue.value.isDigitsOnly() &&
                                                    priceValue.value.isNotEmpty() &&
                                                    priceValue.value.isNotBlank() &&
                                                    priceValue.value.toInt() >= 1000
                                                ) {
                                                    showTransferDetails.value = true
                                                } else {
                                                    priceHistoryValueError.value = true
                                                }
                                            } catch (_: Exception) {
                                                coroutineScope.launch {
                                                    snackbarState.showSnackbar(
                                                        message = context.getString(R.string.the_field_must_not_be_empty_or_can_contain_only_digits)
                                                    )
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            1 -> {
                                LazyColumn(
                                    modifier = Modifier
                                        .fillMaxSize()
                                ) {
                                    items(parties.value) { model ->
                                        partyModel.value = model
                                        DetailsHistoryItem(
                                            secondaryColor = secondaryColor,
                                            fiverdColor = fiverdColor,
                                            sevenrdColor = sevenrdColor,
                                            userModel = receiver.value,
                                            partyModel = model,
                                            priceFieldError = priceHistoryValueError.value,
                                        ) { price ->
                                            partyModel.value = model
                                            priceValue.value = price.filter { digit -> digit.isDigit() }
                                            try {
                                                if (
                                                    priceValue.value.isDigitsOnly() &&
                                                    priceValue.value.isNotEmpty() &&
                                                    priceValue.value.isNotBlank() &&
                                                    priceValue.value.toInt() >= 1000
                                                ) {
                                                    showTransferDetails.value = true
                                                } else {
                                                    priceHistoryValueError.value = true
                                                }
                                            } catch (_: Exception) {
                                                coroutineScope.launch {
                                                    snackbarState.showSnackbar(
                                                        message = context.getString(R.string.the_field_must_not_be_empty_or_can_contain_only_digits)
                                                    )
                                                }
                                            }
                                        }
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
                        sender = sender.value,
                        receiver = receiver.value,
                        partyModel = partyModel.value,
                        isFieldEnabled = isPriceFieldEnabled.value,
//                Price field properties
                        priceValue = priceValue.value,
                        onValueChange = {
                            priceValue.value = it
                        },
                        onTrailingIconClick = {
                            isPriceFieldEnabled.value = true
                        },
                        senderCardNumber = senderCardNumber.value,
//                DropDown menu properties
                        onConfirmButtonClick = {
                            if (
                                priceValue.value.isDigitsOnly() &&
                                priceValue.value.isNotEmpty() &&
                                priceValue.value.isNotBlank() &&
                                priceValue.value.toInt() >= 1000
                            ) {
                                showCheckDetails.value = true
                            } else {
                                priceFieldError.value = true
                            }
                        },
                        onDismissRequest = {
                            showTransferDetails.value = false
                            if (bottomSheetState.isVisible) {
                                coroutineScope.launch { bottomSheetState.hide() }
                            }
                            priceFieldError.value = false
                            isPriceFieldEnabled.value = false
                        },
                        onSelectCardClick = {
                            isExpanded.value = true
                        },
                        userCards = cards.value,
                        onDropDownDismissRequest = {
                            isExpanded.value = false
                        },
                        onItemClick = { cardNumber ->
                            viewModel.changeSenderCard(cardNumber)
                            isExpanded.value = false
                        },
                        onAddCardClick = {
                            isExpanded.value = false
                            sharedPreferences.addCardFromDetails(true)
                            sharedPreferences.detailIndex(arguments.userId)
                            navController.navigate(ScreensRouter.AddCardScreen.route + "/-1")
                        },
                        isExpanded = isExpanded.value,
                    )
                }
                if (showCheckDetails.value) {
                    TransferCheckDialog(
                        secondaryColor = secondaryColor,
                        quaternaryColor = quaternaryColor,
                        commission = "500",
                        sender = sender.value,
                        receiver = receiver.value,
                        onDismissClick = { showCheckDetails.value = false },
                        senderCard = senderCardNumber.value,
                        transferAmount = priceValue.value,
                        partyModel = partyModel.value,
                    ) {
                        showCheckDetails.value = false
                        navController.navigate(ScreensRouter.MainScreen.route) {
                            popUpTo(ScreensRouter.MainScreen.route) {
                                inclusive = true
                            }
                            launchSingleTop = true
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
                    delay(1500)
                    viewModel.getAllFriends(arguments.userId)
                    isLoading.value = false
                }
            }
        }
    }
}