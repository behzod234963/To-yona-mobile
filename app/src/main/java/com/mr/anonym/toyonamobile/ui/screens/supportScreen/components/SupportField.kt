package com.mr.anonym.toyonamobile.ui.screens.supportScreen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mr.anonym.toyonamobile.R

@Composable
fun SupportField(
    secondaryColor: Color,
    sevenrdColor: Color,
    title: String,
    content: String,
    onClick: () -> Unit
) {

    val isCardClicked = rememberSaveable { mutableStateOf(false) }
    Card(
        colors = CardDefaults.cardColors(
            containerColor = sevenrdColor,
            contentColor = sevenrdColor
        ),
        elevation = CardDefaults.cardElevation(7.dp),
        onClick = {
            isCardClicked.value = !isCardClicked.value
            onClick()
        },
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(if (isCardClicked.value) IntrinsicSize.Max else IntrinsicSize.Min)
                .padding(10.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Row (
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    modifier = Modifier
                        .fillMaxWidth(0.92f),
                    text = title,
                    fontSize = 17.sp,
                    color = secondaryColor,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Start
                )
                Icon(
                    imageVector = if (isCardClicked.value) Icons.Default.KeyboardArrowDown else Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    tint = secondaryColor,
                    contentDescription = ""
                )
            }
            if (isCardClicked.value){
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ){
                    Text(
                        text = content,
                        fontSize = 14.sp,
                        color = secondaryColor
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewSupportField() {
    SupportField(
        secondaryColor = Color.Black,
        sevenrdColor = Color.Unspecified,
        title = "Каким образом можно сменить язык интерфейса?",
        content = "Откройте «Настройки» -> «Сменить язык» и выберите удобный вариант из списка."
    ) { }
}