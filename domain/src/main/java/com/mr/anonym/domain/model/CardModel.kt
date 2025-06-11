package com.mr.anonym.domain.model

import androidx.annotation.Keep

@Keep data class CardModel(
    val id: Int = -1,
    val userId: Int = -1,
    val number: String = "",
    val date: String = "",
    val createdAt: String = ""
)