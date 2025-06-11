package com.mr.anonym.domain.model

import androidx.annotation.Keep

@Keep data class MonitoringModel(
    val id:Int? = null,
    val monthIndex: Int,
    val eventName: String,
    val eventOwnerName: String,
    val eventOwnerLastName: String,
    val senderCardNumber: String,
    val senderCardHolder:String,
    val receiverCardNumber: String,
    val receiverCardHolder: String,
    val amount: String,
    val dateTime: String,
    val transferStatus: String
)
