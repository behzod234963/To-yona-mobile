package com.mr.anonym.toyonamobile.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomTextField(
    secondaryColor: Color,
    eightrdColor: Color,
    isPhoneField: Boolean,
    keyboardType: KeyboardType,
    imeAction: ImeAction,
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    secondValue: String,
    onSecondValueChange:(TextFieldValue)-> Unit,
    label: String,
    @DrawableRes icon: Int? = null,
    focusRequester: FocusRequester,
    visualTransformation: VisualTransformation
) {

    val focusManager = LocalFocusManager.current
    val bringIntoViewRequester = remember { BringIntoViewRequester() }
    val interactionSource = remember { MutableInteractionSource() }

    val isFocused = remember { mutableStateOf(false) }

    BasicTextField(
        modifier = Modifier
            .fillMaxWidth()
            .bringIntoViewRequester(bringIntoViewRequester)
            .onFocusChanged {
                isFocused.value = it.isFocused
            }
            .focusRequester(focusRequester),
        textStyle = TextStyle(
            color = secondaryColor,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeAction
        ),
        interactionSource = interactionSource,
        value = if (isPhoneField) value else TextFieldValue(secondValue),
        onValueChange = {
            if (isPhoneField){
                val digits = it.text.filter { char -> char.isDigit() }.take(9)
                val updated = it.copy(text = digits)
                onValueChange( updated )
            }else{
                onSecondValueChange.invoke((it))
            }
        },
        singleLine = true,
        visualTransformation = visualTransformation,
        keyboardActions = KeyboardActions(
            onDone = { focusManager.clearFocus() },
            onNext = { focusManager.moveFocus(FocusDirection.Next) },
            onSearch = { focusManager.clearFocus() }
        ),
        cursorBrush = SolidColor(secondaryColor),
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .clip(MaterialTheme.shapes.medium)
                    .background(eightrdColor)
                    .height(62.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    icon?.let {
                        Image(
                            modifier = Modifier
                                .size(45.dp)
                                .padding(15.dp),
                            painter = painterResource(icon),
                            contentDescription = "",
                            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.background)
                        )
                    }
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .padding(
                                start = if (icon == null) 15.dp else 0.dp,
                                bottom = 0.dp,
                                end = 15.dp
                            )
                    ) {
                        val hasText = value.text.isNotEmpty()
                        val animPlaceholder: Dp by animateDpAsState(
                            if (isFocused.value || hasText) 6.dp else 12.dp
                        )
                        val animPlaceholderFontSize: Int by animateIntAsState(
                            if (isFocused.value || hasText) 12 else 14
                        )
                        Text(
                            modifier = Modifier
                                .graphicsLayer {
                                    translationY = animPlaceholder.toPx()
                                },
                            text = label,
                            color = secondaryColor,
                            fontSize = animPlaceholderFontSize.sp,
                            fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
                            overflow = TextOverflow.Ellipsis
                        )
                        Spacer(Modifier.height(10.dp))
                        Box(
                            modifier = Modifier
                                .padding(top = if (isFocused.value) 30.dp else 35.dp)
                                .fillMaxWidth()
                        ) {
                            innerTextField()
                        }
                    }
                }
            }
        }
    )
}