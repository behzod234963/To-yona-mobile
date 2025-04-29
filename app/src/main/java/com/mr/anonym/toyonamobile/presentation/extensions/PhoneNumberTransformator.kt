package com.mr.anonym.toyonamobile.presentation.extensions

fun String.phoneNumberTransformation(): String{
    val digits = this.filter { it.isDigit() }

    if ( digits.length != 12 || !digits.startsWith("998") ){
        return this
    }
    val operatorCode = digits.substring(3,5)
    val firstPart = digits.substring(5,8)
    val secondPart = digits.substring(8,10)
    val thirdPart = digits.substring(10,12)
    return "+998 ($operatorCode) $firstPart-$secondPart-$thirdPart"
}