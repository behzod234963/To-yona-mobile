package com.mr.anonym.toyonamobile.ui.screens.detailsScreen.screen

import android.annotation.SuppressLint
import android.content.Intent
import android.provider.ContactsContract
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
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
import com.mr.anonym.data.instance.local.DataStoreInstance
import com.mr.anonym.data.instance.local.SharedPreferencesInstance
import com.mr.anonym.domain.model.PartysItem
import com.mr.anonym.domain.model.TransactionsModel
import com.mr.anonym.toyonamobile.R
import com.mr.anonym.toyonamobile.presentation.extensions.phoneNumberTransformation
import com.mr.anonym.toyonamobile.presentation.navigation.ScreensRouter
import com.mr.anonym.toyonamobile.presentation.utils.Arguments
import com.mr.anonym.toyonamobile.presentation.utils.OpenNewContactMethod
import com.mr.anonym.toyonamobile.ui.screens.detailsScreen.components.DetailsScreenTabRow
import com.mr.anonym.toyonamobile.ui.screens.detailsScreen.components.TransferCheckDialog
import com.mr.anonym.toyonamobile.ui.screens.detailsScreen.components.TransferDetailsBottomSheet
import com.mr.anonym.toyonamobile.ui.screens.detailsScreen.item.ActualEventsItem
import com.mr.anonym.toyonamobile.ui.screens.detailsScreen.item.DetailsHistoryItem
import com.mr.anonym.toyonamobile.ui.screens.detailsScreen.viewModel.DetailsViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailsScreen(
    navController: NavController,
    arguments: Arguments,
    viewModel: DetailsViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    val dataStore = DataStoreInstance(context)
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

    val user = viewModel.user
    val partyList = emptyList<PartysItem>()
    val partyModel = remember { mutableStateOf( PartysItem()) }
    val transactionsModel = TransactionsModel(
        id = 1,
        userId = user.value.id,
        sender = "userModel.cardlist",
        receiver = partyModel.value.cardNumber,
        price = priceValue.value.ifEmpty { "0.0" },
        dateTime = "30.04.2025"
    )

    val showTransferDetails = remember { mutableStateOf(false) }
    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val isExpanded = remember { mutableStateOf(false) }
    val isPriceFieldEnabled = remember { mutableStateOf(false) }
    val senderCardNumber = viewModel.senderCard
    val senderName = viewModel.senderName
    val cards = viewModel.cards
    val priceFieldError = rememberSaveable { mutableStateOf(false) }
    val showCheckDetails = rememberSaveable { mutableStateOf(false) }

    val priceHistoryValueError = rememberSaveable { mutableStateOf(false) }

    val snackbarState = remember { SnackbarHostState() }

    val contactsLauncher = OpenNewContactMethod(context)

    Scaffold(
        containerColor = primaryColor,
        contentColor = primaryColor,
        snackbarHost = { SnackbarHost(snackbarState) }
    ) { paddingValues ->
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
                    onClick = { navController.navigateUp() }
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
                        text = "${user.value.username} ${user.value.surname}",
                        color = secondaryColor,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center
                    )
                    Spacer(Modifier.height(5.dp))
                    Text(
                        text = "+998${user.value.phonenumber}".phoneNumberTransformation(),
                        color = secondaryColor,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                IconButton(
                    onClick = {
                        val intent = Intent(ContactsContract.Intents.Insert.ACTION).apply {
                            type = ContactsContract.RawContacts.CONTENT_TYPE
                            putExtra(
                                ContactsContract.Intents.Insert.NAME,
                                "${user.value.username} ${user.value.surname}"
                            )
                            putExtra(
                                ContactsContract.Intents.Insert.PHONE,
                                user.value.phonenumber
                            )
                        }
                        contactsLauncher.launch(intent)
                    }
                ) {
                    Icon(
                        modifier = Modifier
                            .size(30.dp),
                        painter = painterResource(R.drawable.ic_contact),
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
                                items(partyList) { model ->
                                    partyModel.value = model
                                    ActualEventsItem(
                                        secondaryColor = secondaryColor,
                                        fiverdColor = fiverdColor,
                                        sevenrdColor = sevenrdColor,
                                        partyModel = model,
                                        userModel = user.value,
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
                                items(partyList) { model ->
                                    partyModel.value = model
                                    DetailsHistoryItem(
                                        secondaryColor = secondaryColor,
                                        fiverdColor = fiverdColor,
                                        sevenrdColor = sevenrdColor,
                                        userModel = user.value,
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
                    onItemClick = { cardNumber ->
                        viewModel.changeSenderCard(cardNumber)
                        isExpanded.value = false
                    },
                    onAddCardClick = {
                        isExpanded.value = false
                        coroutineScope.launch {
                            dataStore.addCardFromDetails(true)
                        }
                        navController.navigate(ScreensRouter.AddCardScreen.route + "/-1")
                    },
                    isExpanded = isExpanded.value,
                    senderName = senderName.value,
                    userModel = user.value,
                    partyModel = partyModel.value,
                )
            }
            if (showCheckDetails.value) {
                TransferCheckDialog(
                    secondaryColor = secondaryColor,
                    quaternaryColor = quaternaryColor,
                    commission = "500",
                    userModel = user.value,
                    senderName = senderName.value,
                    transactionsModel = transactionsModel,
                    onDismissClick = { showCheckDetails.value = false },
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
    }
}