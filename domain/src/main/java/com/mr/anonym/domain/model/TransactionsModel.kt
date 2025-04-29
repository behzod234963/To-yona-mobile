package com.mr.anonym.domain.model

data class TransactionsModel(
    val id: Int,
    val userId: Int,
    val sender: String,
    val receiver: String,
    val price: Double,
    val dateTime: String
)
