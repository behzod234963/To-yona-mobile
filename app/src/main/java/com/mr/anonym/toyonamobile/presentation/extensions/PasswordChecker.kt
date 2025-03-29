package com.mr.anonym.toyonamobile.presentation.extensions

fun String.passwordChecker(): Boolean{

    val regex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@.$!%*?&#])[A-Za-z\\d@$!%*?.&#]{8,}$".toRegex()
    return if (
        regex.matches(this)
    ){
        true
    }else{
        false
    }
}