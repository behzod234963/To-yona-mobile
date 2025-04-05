package com.mr.anonym.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("notifications")
data class NotificationsModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val title: String,
    val description: String,
    val date: String
)
