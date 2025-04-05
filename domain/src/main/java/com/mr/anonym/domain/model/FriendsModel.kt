package com.mr.anonym.domain.model

data class FriendsModel(
    val id: Int,
    val userId: Int,
    val name: String,
    val surname:String,
    val phone: String,
    val cardNumber: String,
    val datetime: String
)
