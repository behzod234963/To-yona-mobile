package com.mr.anonym.toyonamobile.presentation.extensions

import android.icu.text.DecimalFormat

fun String.moneySeparator(cost:Double):String{
    val factory = DecimalFormat("#,###")
    return factory.format(cost)
}
fun Double.moneyType():String = this.toString().moneySeparator(this)