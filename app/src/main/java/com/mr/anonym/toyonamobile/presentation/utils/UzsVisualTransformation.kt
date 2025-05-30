package com.mr.anonym.toyonamobile.presentation.utils

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

class UzsVisualTransformation(private val currency: String): VisualTransformation {

    val decimalFormat = DecimalFormat("#,###", DecimalFormatSymbols(Locale.getDefault()))
    override fun filter(text: AnnotatedString): TransformedText {

        val filteredText = text.text.filter { it.isDigit() }
        val formatted = if (filteredText.isNotEmpty()){
            decimalFormat.format(filteredText.toBigInteger())
        }else{
            ""
        }
        return TransformedText(
            text = AnnotatedString("$formatted $currency"),
            offsetMapping = MoneyOffsetMapping( text.text,formatted )
        )
    }
}