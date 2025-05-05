package com.mr.anonym.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("myEvents")
data class MyEventsModel(
    @PrimaryKey (autoGenerate = true)
    val id: Int? = null ,
    val eventStatus: Boolean = false,
    val eventType: String = "",
    val eventDateTime: String = "",
    val cardHolder: String = "",
    val cardNumber: String = ""
)
