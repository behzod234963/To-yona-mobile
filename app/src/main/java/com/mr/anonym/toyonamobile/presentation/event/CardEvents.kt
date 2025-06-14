package com.mr.anonym.toyonamobile.presentation.event

sealed class CardEvents {
    data class ChangeCardNumber(val cardNumber: String): CardEvents()
    data class ChangeExpiryDate(val date: String): CardEvents()
}