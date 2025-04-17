package com.mr.anonym.toyonamobile.presentation.utils

sealed class CardEvents {
    data class ChangeCardNumber(val cardNumber: String): CardEvents()
    data class ChangeCardHolder(val cardHolder: String): CardEvents()
    data class ChangeExpiryDate(val date: String): CardEvents()
}