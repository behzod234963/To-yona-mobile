package com.mr.anonym.toyonamobile.ui.screens.contactsScreen.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mr.anonym.domain.model.Friend
import com.mr.anonym.domain.model.UserModelItem
import com.mr.anonym.toyonamobile.R
import com.mr.anonym.toyonamobile.presentation.extensions.phoneNumberTransformation

@Composable
fun ContactsItem(
    secondaryColor: Color,
    sevenrdColor: Color,
    friend: Friend,
    onContactClick:()-> Unit
) {
    Card (
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        colors = CardDefaults.cardColors(
            containerColor = sevenrdColor,
            contentColor = sevenrdColor
        ),
        onClick = { onContactClick() },
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation( 7.dp )
    ){
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Image(
                modifier = Modifier
                    .size(40.dp),
                painter = when(friend.sex){
                    0 -> painterResource(R.drawable.ic_default_avatar)
                    1 -> painterResource(R.drawable.ic_man)
                    2 -> painterResource(R.drawable.ic_woman)
                    else -> painterResource(R.drawable.ic_default_avatar)
                },
                contentDescription = ""
            )
            Spacer(Modifier.width(10.dp))
            Column {
                Text(
                    text = "${friend.username} ${friend.surname}",
                    color = secondaryColor,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "+998${friend.phonenumber}".phoneNumberTransformation(),
                    color = secondaryColor,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}