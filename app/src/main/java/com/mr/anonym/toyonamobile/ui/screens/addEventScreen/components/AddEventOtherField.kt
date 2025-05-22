package com.mr.anonym.toyonamobile.ui.screens.addEventScreen.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mr.anonym.toyonamobile.R

@Composable
fun AddEventOtherField(
    secondaryColor: Color,
    tertiaryColor: Color,
    isEventError: Boolean,
    isTitle: Boolean,
    value:String,
    onValueChange:(String)->Unit,
    isValueConfirmed: Boolean,
    onConfirmClick:()-> Unit,
    onEditClick:()-> Unit,
) {

    OutlinedTextField(
        value = value,
        onValueChange = { onValueChange(it) },
        modifier = Modifier
            .fillMaxWidth(),
        enabled = if (isTitle) true else !isValueConfirmed,
        textStyle = TextStyle(
            color = secondaryColor,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        ),
        label = {
            Text(
                text = if ( isTitle ) stringResource(R.string.title) else stringResource(R.string.enter_the_event),
                color = tertiaryColor,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold
            )
        },
        trailingIcon = {
            if ( !isTitle ){
                if (isValueConfirmed){
                    IconButton(
                        onClick = { onEditClick() }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            tint = secondaryColor,
                            contentDescription = ""
                        )
                    }
                }else{
                    IconButton(
                        onClick = { onConfirmClick() }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            tint = secondaryColor,
                            contentDescription = ""
                        )
                    }
                }
            }
        },
        supportingText = {
            if (isEventError){
                Text(
                    text = stringResource(R.string.the_place_must_not_be_empty),
                )
            }
        },
        isError = isEventError,
        shape = RoundedCornerShape(10.dp),
    )
}