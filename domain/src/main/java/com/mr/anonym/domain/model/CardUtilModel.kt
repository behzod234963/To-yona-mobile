package com.mr.anonym.domain.model

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("CardUtilModel")
@Keep data class CardUtilModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = -1,
    val colorIndex: Int = 1
)
