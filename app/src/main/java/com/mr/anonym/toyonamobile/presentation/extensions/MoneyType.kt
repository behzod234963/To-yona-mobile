package com.mr.anonym.toyonamobile.presentation.extensions

import android.icu.text.DecimalFormat

fun String.moneySeparator():String{
    val factory = DecimalFormat("#,###")
    return factory.format(this.toDouble())
}
//fun Double.moneyType():String = this.toString().moneySeparator(this)