package com.mr.anonym.toyonamobile.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mr.anonym.toyonamobile.R

@Composable
fun CustomPasswordTextField(
    secondaryColor: Color,
    eightrdColor: Color,
    imeAction: ImeAction,
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    label: String,
    @DrawableRes icon: Int? = null,
) {

    val focusManager = LocalFocusManager.current
    val isPasswordVisible = remember { mutableStateOf(false) }
    val bringIntoViewRequester = remember { BringIntoViewRequester() }
    val interactionSource = remember { MutableInteractionSource() }

    val isFocused = remember { mutableStateOf(false) }
    BasicTextField(
        modifier = Modifier
            .onFocusChanged {
                isFocused.value = it.isFocused
            }
            .bringIntoViewRequester(bringIntoViewRequester)
            .fillMaxWidth(),
        value = value,
        onValueChange = onValueChange,
        interactionSource = interactionSource,
        singleLine = true,
        visualTransformation =
            if (isPasswordVisible.value) VisualTransformation.None
            else PasswordVisualTransformation('*'),
        keyboardActions = KeyboardActions(
            onDone = { focusManager.clearFocus() },
            onNext = { focusManager.moveFocus(FocusDirection.Next) },
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = imeAction
        ),
        textStyle = TextStyle(
            color = secondaryColor,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        ),
        cursorBrush = SolidColor(secondaryColor),
        decorationBox = { innerTextField ->
            Row (
                modifier = Modifier
                    .clip(MaterialTheme.shapes.medium)
                    .background(eightrdColor)
                    .height(62.dp)
            ){
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
                ){
                    val hasText = value.text.isNotEmpty()
                    val animPlaceholder: Dp by animateDpAsState(
                        if (isFocused.value || hasText) 2.dp else 12.dp
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
                    Box(
                        Modifier
                            .padding(top = if (isFocused.value) 30.dp else 35.dp)
                            .fillMaxWidth()
                            .height(18.dp)
                    ) {
                        innerTextField()
                    }
                }
                Spacer(Modifier.width(16.dp))
                IconButton(
                    modifier = Modifier.align(Alignment.CenterVertically),
                    onClick = { isPasswordVisible.value = !isPasswordVisible.value }
                ) {
                    AnimatedVisibility(
                        visible = isPasswordVisible.value,
                        enter = fadeIn(),
                        exit = fadeOut()
                    ) {
                        Icon(
                            modifier = Modifier
                                .size(25.dp),
                            painter = painterResource(id = R.drawable.ic_hide),
                            tint = secondaryColor,
                            contentDescription = "Show Password"
                        )
                    }
                    AnimatedVisibility(
                        visible = !isPasswordVisible.value,
                        enter = fadeIn(),
                        exit = fadeOut()
                    ) {
                        Icon(
                            modifier = Modifier
                                .size(25.dp),
                            painter = painterResource(id = R.drawable.ic_show),
                            tint = secondaryColor,
                            contentDescription = "Hide Password"
                        )
                    }
                }
            }
        }
    )
}