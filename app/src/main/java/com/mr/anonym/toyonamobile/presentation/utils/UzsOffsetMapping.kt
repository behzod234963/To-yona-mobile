package com.mr.anonym.toyonamobile.presentation.utils

import androidx.compose.ui.text.input.OffsetMapping

class UzsOffsetMapping(
    private val original: String,
    private val transformed: String,
): OffsetMapping{
    override fun originalToTransformed(offset: Int): Int {
        var newOffset = offset

        if (offset > 3) newOffset += 1
        if (offset > 6) newOffset += 1
        if (offset > 9) newOffset += 1
        return (newOffset + 4).coerceAtMost(transformed.length)
    }
    override fun transformedToOriginal(offset: Int): Int {
        var newOffset = ( offset - 4 ).coerceAtLeast(0)
        if (newOffset > 6) newOffset -= 1
        if (newOffset > 3) newOffset -= 1
        if (newOffset > 0) newOffset -= 1
        return newOffset.coerceAtMost(original.length)
    }
}
