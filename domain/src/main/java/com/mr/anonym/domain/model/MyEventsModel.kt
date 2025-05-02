package com.mr.anonym.domain.model

import androidx.room.Entity

data class MyEventsModel(
    val eventStatus: Boolean,
    val eventType: String,
    val eventDateTime: String,
    val cardHolder: String,
    val cardNumber: String
)
