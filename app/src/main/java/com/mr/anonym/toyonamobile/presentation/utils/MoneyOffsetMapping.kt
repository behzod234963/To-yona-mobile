package com.mr.anonym.toyonamobile.presentation.utils

import androidx.compose.ui.text.input.OffsetMapping

class MoneyOffsetMapping(
    private val original: String,
    private val transformed: String
) : OffsetMapping {

    override fun originalToTransformed(offset: Int): Int {
        if (offset == 0) return 0
        var transformedOffset = offset
        var groupCount = 0
        var i = 0

        // Пройдем по оригиналу и считаем, сколько пробелов будет вставлено до текущей позиции
        while (i < offset && i < original.length) {
            val posFromEnd = original.length - i
            if (posFromEnd % 3 == 0 && i != 0) {
                groupCount++
            }
            i++
        }

        transformedOffset += groupCount
        return transformedOffset.coerceAtMost(transformed.length)
    }

    override fun transformedToOriginal(offset: Int): Int {
        if (offset == 0) return 0
        var originalOffset = offset
        var spaceCount = 0
        var i = 0

        // Считаем пробелы в отформатированной строке до позиции курсора
        while (i < offset && i < transformed.length) {
            if (transformed[i] == ' ') {
                spaceCount++
            }
            i++
        }

        originalOffset -= spaceCount
        return originalOffset.coerceAtMost(original.length)
    }
}