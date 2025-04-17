package com.mr.anonym.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("cardModel")
data class CardModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val cardNumber: String = "",
    val cardHolder: String = "",
    val expiryDate: String = "",
    val cvv: String = ""
)