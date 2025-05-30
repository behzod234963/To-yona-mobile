package com.mr.anonym.toyonamobile.presentation.utils

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText

fun cardNumberFormatter(text: AnnotatedString): TransformedText {

    val trimmed = if (text.text.length >= 16) text.text.substring(0..15) else text.text

    val formatted = buildString {
        for (i in trimmed.indices) {
            append(trimmed[i])
            if (
                (i+1) % 4 == 0 &&
                i != 16
            ) {
                append(" ")
            }
        }
    }
    val annotatedString = AnnotatedString(formatted)
//    val cardNumberOffsetTranslator = object : OffsetMapping {
//        override fun originalToTransformed(offset: Int): Int {
//            if (offset <= 0) return 0
//            val extraSpaces = (offset -1)/4
////            if (offset <= 7) return offset + 2
////            if (offset <= 11) return offset + 4
////            if (offset <= 16) return offset + 6
//            return offset + extraSpaces
//        }
//
//        override fun transformedToOriginal(offset: Int): Int {
//            if (offset <= 0) return 0
//            val withoutSpaces = (offset - 1)/4
////            if (offset <= 9) return offset - 2
////            if (offset <= 14) return offset - 4
////            if (offset <= 19) return offset - 6
//            return withoutSpaces.coerceIn(0,trimmed.length)
//        }
//    }
    val offsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            var transformedOffset = offset
            // Добавляем один пробел после каждых 4 символов
            transformedOffset += (0 until offset).count { (it + 1) % 4 == 0 }
            return transformedOffset
        }

        override fun transformedToOriginal(offset: Int): Int {
            var originalOffset = offset
            // Убираем пробелы, которые стоят после каждых 4 символов
            originalOffset -= (0 until offset).count { (it + 1) % 5 == 0 }
            return originalOffset.coerceAtMost(trimmed.length)
        }
    }
    return TransformedText( annotatedString,offsetTranslator )
}