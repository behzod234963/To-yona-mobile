package com.mr.anonym.toyonamobile.ui.screens.mainScreen.item

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mr.anonym.domain.model.PartysItem
import com.mr.anonym.domain.model.UserModelItem
import com.mr.anonym.toyonamobile.presentation.extensions.phoneNumberTransformation
import com.mr.anonym.toyonamobile.presentation.extensions.stringEqualizerForMainScreen

@Composable
fun MainScreenItem(
    secondaryColor: Color,
    tertiaryColor: Color,
    sevenrdColor: Color,
    smallFontSize: Int,
    partyModel: PartysItem,
    userModel: UserModelItem,
    showContacts: Boolean,
    onItemClick: () -> Unit,
) {
    Column {
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 10.dp),
            thickness = 1.dp
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(sevenrdColor)
                .clickable { onItemClick() },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.75f)
            ) {
                Row(
                    modifier = Modifier
                        .height(60.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
//                    Avatar
                    Card(
                        modifier = Modifier
                            .size(50.dp)
                            .padding(5.dp),
                        shape = CircleShape
                    ) {
                        Icon(
                            modifier = Modifier
                                .fillMaxSize(),
                            imageVector = Icons.Default.Person,
                            tint = secondaryColor,
                            contentDescription = "profile icon"
                        )
                    }
                    Spacer(Modifier.width(2.dp))

                    Column(
                        modifier = Modifier,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = if (showContacts) {
                                "${userModel.username} ${userModel.surname}".stringEqualizerForMainScreen()
                            } else {
                                partyModel.type ?: ""
                            },
                            color = secondaryColor,
                            fontSize = 17.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            text = if (showContacts) {
                                "+998${userModel.phonenumber}".phoneNumberTransformation()
                            } else {
                                partyModel.name?:""
                            },
                            color = tertiaryColor,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier
                        .padding(end = 5.dp),
                    text = if (showContacts) "" else "${partyModel.startTime} ${partyModel.endTime}",
                    color = secondaryColor,
                    fontSize = smallFontSize.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}