package com.mr.anonym.toyonamobile.presentation.extensions

fun String.cardNumberFormatter(): String{
    val card = this.takeLast(5)
    val format = card.replaceFirst(oldChar = card.first(), newChar = '*',false)
    return format
}