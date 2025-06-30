package com.mr.anonym.toyonamobile.presentation.extensions

fun String.capitalizeChecker(): Boolean {
    return this.any { it.isUpperCase() }
}

fun String.lowercaseChecker(): Boolean {
    return this.any { it.isLowerCase() }
}

fun String.digitChecker(): Boolean {
    return this.any { it.isDigit() }
}

fun String.symbolChecker(): Boolean {
    val allowedSymbols = setOf('!', '#', '$', '%', '&', '*', '.', '?', '@')
    return this.any { it in allowedSymbols }
}