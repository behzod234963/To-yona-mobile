package com.mr.anonym.domain.model

data class UserModel(
    val id:Int,
    val name: String,
    val surName:String,
    val phone: String,
    val cardNumber: String,
    val password: String,
    val dateTime: String
)
