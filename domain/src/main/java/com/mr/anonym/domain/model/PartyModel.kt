package com.mr.anonym.domain.model

data class PartyModel(
    val id:Int,
    val userID:Int,
    val type: String,
    val cardNumber: String,
    val dateTime: String
)
