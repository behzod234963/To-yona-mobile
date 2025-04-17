package com.mr.anonym.toyonamobile.presentation.utils

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText

fun cardDateTrimmer(text: AnnotatedString): TransformedText{

    val trimmed = if (text.text.length >= 4) text.text.substring(0..3) else text.text

    val formatted = buildString {
        for (i in trimmed.indices){
            append(trimmed[i])
            if (
                i == 1
            ){
                append("/")
            }
        }
    }
    val annotatedString = AnnotatedString(formatted)
    val offsetMapper = object : OffsetMapping{
        override fun originalToTransformed(offset: Int): Int {
            return when{
                offset <= 1 -> offset
                offset in 2..4 -> offset + 1
                else -> 5
            }
        }

        override fun transformedToOriginal(offset: Int): Int {
            return when{
                offset <= 1 -> offset
                offset == 2 -> 1
                offset in 3..5 -> offset - 1
                else -> 4
            }.coerceIn(0,trimmed.length)
        }
    }
    return TransformedText(annotatedString,offsetMapper)
}