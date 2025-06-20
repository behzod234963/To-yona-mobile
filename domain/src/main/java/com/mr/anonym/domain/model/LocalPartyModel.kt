package com.mr.anonym.domain.model

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey

@Keep
@Entity("LocalPartyModel")
data class LocalPartyModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = -1,
    val userId: Int = -1,
    val userName: String = "",
    val name: String = "",
    val type: String = "",
    val address: String = "",
    val cardNumber: String = "",
    val startTime: String = "",
    val endTime: String = "",
    val status: Boolean = true,
    val createdAt: String = "",
)
