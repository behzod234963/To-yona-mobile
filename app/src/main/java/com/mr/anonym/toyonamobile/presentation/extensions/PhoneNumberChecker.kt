package com.mr.anonym.toyonamobile.presentation.extensions

fun String.phoneChecker(): Boolean{

    val regexPattern =  Regex("^\\+?[0-9]*$")  /* Regex pattern for numbers and + */
    return if (
        regexPattern.matches(this) &&
        numberChecker(this)
        ){
        true
    }else{
        false
    }
}
fun numberChecker(string: String): Boolean{
    return if ( string.length == 9 ){
        true
    }else{
        false
    }
}