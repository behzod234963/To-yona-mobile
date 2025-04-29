package com.mr.anonym.toyonamobile.presentation.utils

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class PhoneNumberVisualTransformation: VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {

        val filteredText = text.text.filter { it.isDigit() }
        val trimmed = if (filteredText.length >=9) filteredText.substring(0..8) else filteredText

        val out = StringBuilder()
        for (i in trimmed.indices){
            when(i){
                0-> out.append('(').append(trimmed[i])
                1-> out.append(trimmed[i]).append(") ")
                5-> out.append('-').append(trimmed[i])
                7-> out.append('-').append(trimmed[i])
                else -> out.append(trimmed[i])
            }
        }
        val prefix = "+998 "
        val formattedText = prefix + out.toString()
        val transformedText = AnnotatedString(formattedText)

        val offsetMapping = object : OffsetMapping{
            override fun originalToTransformed(offset: Int): Int {
                var newOffset = offset
                if (offset == 0) newOffset += prefix.length + 1
                if (offset == 1) newOffset += prefix.length + 2
                if (offset in 2..3) newOffset += prefix.length + 2
                if (offset == 4) newOffset += prefix.length + 3
                if (offset == 5) newOffset += prefix.length + 4
                if (offset >= 6) newOffset += prefix.length + 5
                return newOffset.coerceAtMost(transformedText.text.length)
            }

            override fun transformedToOriginal(offset: Int): Int {
                val correctedOffset = offset - prefix.length
                return when{
                    correctedOffset <= 1-> 0
                    correctedOffset <= 2-> 1
                    correctedOffset in 3..4 -> correctedOffset - 2
                    correctedOffset in 5..6 -> correctedOffset - 3
                    correctedOffset in 7..8 -> correctedOffset - 4
                    else -> correctedOffset - 5
                }.coerceAtMost(trimmed.length)
            }
        }
        return TransformedText(transformedText,offsetMapping)
    }
}