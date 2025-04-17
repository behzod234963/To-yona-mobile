package com.mr.anonym.toyonamobile.presentation.extensions

fun String.nameChecker(): Boolean{
    val regexPattern = Regex("^[a-zA-Zа-яА-ЯёЁ\\s-]*$")
    return if (
        regexPattern.matches(this) &&
        this.isNotEmpty()
        ){
        true
    }else{
        false
    }
}

fun String.cardHolderChecker(): Boolean{
    val regexPattern = Regex("^[a-zA-Z\\s-]*$")
    return if (
        regexPattern.matches(this) &&
        this.isNotEmpty()
    ){
        true
    }else{
        false
    }
}