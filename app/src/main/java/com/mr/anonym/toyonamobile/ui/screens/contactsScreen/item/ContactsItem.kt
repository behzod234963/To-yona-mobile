package com.mr.anonym.toyonamobile.ui.screens.contactsScreen.item

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mr.anonym.domain.model.FriendsModel

@Composable
fun ContactsItem(
    secondaryColor: Color,
    sevenrdColor: Color,
    friendsModel: FriendsModel
) {
    Card (
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        colors = CardDefaults.cardColors(
            containerColor = sevenrdColor,
            contentColor = sevenrdColor
        ),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation( 7.dp )
    ){
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Icon(
                modifier = Modifier
                    .size(50.dp),
                imageVector = Icons.Default.Person,
                tint = secondaryColor,
                contentDescription = ""
            )
            Spacer(Modifier.width(5.dp))
            Column {
                Text(
                    text = "${friendsModel.name} ${friendsModel.surname}",
                    color = secondaryColor,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(Modifier.height(5.dp))
                Text(
                    text = friendsModel.phone,
                    color = secondaryColor,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}