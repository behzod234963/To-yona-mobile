package com.mr.anonym.toyonamobile.presentation.extensions

fun String.cardNumberSeparator(): String {
    return this.filter { it.isDigit() }
        .chunked(4)
        .joinToString(" ")
}