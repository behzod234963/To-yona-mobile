package com.mr.anonym.toyonamobile.ui.screens.detailsScreen.screen

import android.annotation.SuppressLint
import androidx.activity.compose.LocalActivity
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.mr.anonym.data.instance.local.DataStoreInstance
import com.mr.anonym.domain.model.PartyModel
import com.mr.anonym.domain.model.UserModel
import com.mr.anonym.toyonamobile.R
import com.mr.anonym.toyonamobile.presentation.extensions.moneyType
import com.mr.anonym.toyonamobile.presentation.utils.PermissionController
import com.mr.anonym.toyonamobile.ui.screens.detailsScreen.components.DetailsPriceFields
import com.mr.anonym.toyonamobile.ui.screens.detailsScreen.components.DetailsScreenTabRow
import com.mr.anonym.toyonamobile.ui.screens.detailsScreen.item.DetailsHistoryItem

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailsScreen(
    navController: NavController
) {

    val context = LocalContext.current
    val activityContext = LocalActivity.current
    val coroutineScope = rememberCoroutineScope()

    val dataStore = DataStoreInstance(context)
    val permissionController = PermissionController(context)

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
    val sixrdColor = Color.Blue
    val systemSevenrdColor = if (isSystemInDarkTheme()) Color.Unspecified else Color.White
    val sevenrdColor = when {
        iSystemTheme.value -> systemSevenrdColor
        isDarkTheme.value -> Color.Unspecified
        else -> Color.White
    }

    val verticalScrollState = rememberScrollState()

    val profileAvatar = dataStore.getAvatar().collectAsState(R.drawable.ic_default_avatar)

    val selectedTab = rememberSaveable { mutableStateOf(1) }

    val priceValue = remember { mutableStateOf("" ) }

    val userModel = UserModel(
        id = 1,
        name = "Ойбек",
        surName = "Худайкулов",
        phone = "+998973570498",
        cardNumber = "9860030160619356",
        password = "",
        dateTime = ""
    )
    val partyModel = PartyModel(
        id = 1,
        userID = 1,
        type = "Келин туй",
        cardNumber = "9860030160619356",
        dateTime = "21-22-mart 2025,17:00"
    )
    val partyList = listOf<PartyModel>(
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
                            painter = painterResource(profileAvatar.value),
                            contentDescription = ""
                        )
                        Spacer(Modifier.height(10.dp))
                        Text(
                            text = "Бехзод Худайбергенов",
                            color = secondaryColor,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center
                        )
                        Spacer(Modifier.height(5.dp))
                        Text(
                            text = "+998973570498",
                            color = secondaryColor,
                            fontSize = 22.sp,
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
                                        .padding(10.dp)
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Text(
                                            text = stringResource(R.string.event),
                                            color = secondaryColor,
                                            fontSize = 18.sp,
                                            fontWeight = FontWeight.SemiBold
                                        )
                                        Text(
                                            text = partyModel.type,
                                            color = secondaryColor,
                                            fontSize = 18.sp,
                                            fontWeight = FontWeight.SemiBold
                                        )
                                    }
                                    Spacer(Modifier.height(5.dp))
                                    HorizontalDivider()
                                    Spacer(Modifier.height(10.dp))
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
                                            fontSize = 18.sp,
                                            fontWeight = FontWeight.SemiBold
                                        )
                                        Text(
                                            text = partyModel.dateTime,
                                            color = secondaryColor,
                                            fontSize = 18.sp,
                                            fontWeight = FontWeight.SemiBold,
                                            textAlign = TextAlign.End
                                        )
                                    }
                                    Spacer(Modifier.height(5.dp))
                                    HorizontalDivider()
                                    Spacer(Modifier.height(10.dp))
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth(),
                                        horizontalArrangement = Arrangement.Center
                                    ) {
                                        Text(
                                            text = stringResource(R.string.requisites),
                                            color = secondaryColor,
                                            fontSize = 18.sp,
                                            fontWeight = FontWeight.SemiBold
                                        )
                                    }
                                    Spacer(Modifier.height(10.dp))
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
                                            text = "9860030160619356",
                                            color = secondaryColor,
                                            fontSize = 16.sp,
                                            fontWeight = FontWeight.SemiBold,
                                            textAlign = TextAlign.End
                                        )
                                    }
                                    Spacer(Modifier.height(5.dp))
                                    HorizontalDivider()
                                    Spacer(Modifier.height(10.dp))
                                }
                                DetailsPriceFields(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(55.dp),
                                    secondaryColor = secondaryColor,
                                    tertiaryColor = tertiaryColor,
                                    fiverdColor = fiverdColor,
                                    value = priceValue.value,
                                    onValueChange = { newValue ->
                                        priceValue.value = newValue
                                    },
                                    onTransferClick = { TODO() }
                                )
                            }
                        }

                        1 -> {
                            LazyColumn(
                                modifier = Modifier
                                    .fillMaxSize()
                            ) {
                                items(partyList){ model->
                                    DetailsHistoryItem(
                                        secondaryColor = secondaryColor,
                                        tertiaryColor = tertiaryColor,
                                        fiverdColor = fiverdColor,
                                        sevenrdColor = sevenrdColor,
                                        partyModel = model,
                                        onTransferClick = {  }
                                    )
                                }
                            }
                        }
                    }
                }
            )
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