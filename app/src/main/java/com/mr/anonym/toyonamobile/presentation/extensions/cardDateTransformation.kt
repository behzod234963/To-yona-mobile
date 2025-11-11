package com.mr.anonym.toyonamobile.presentation.extensions

fun String.cardDateTransformation() : String{
    return this
        .take(4)
        .chunked(2)
        .joinToString("/")
}