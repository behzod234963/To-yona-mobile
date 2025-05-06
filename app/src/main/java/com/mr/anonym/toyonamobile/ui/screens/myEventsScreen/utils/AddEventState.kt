package com.mr.anonym.toyonamobile.ui.screens.myEventsScreen.utils

import com.mr.anonym.domain.model.CardModel

sealed class AddEventState(){
    data class ChangeCardModel(val card: CardModel): AddEventState()
    data class ChangeEventIndex(val index: Int): AddEventState()
    data class ChangeStartDate(val startDate: String): AddEventState()
    data class ChangeEndDate(val endDate: String): AddEventState()
    data class ChangeTime(val time: String): AddEventState()
    data class ChangeCardHolder(val holder: String): AddEventState()
    data class ChangeCardNumber(val cardNumber: String): AddEventState()
    data class ChangeEventOldIndex(val oldIndex: Int): AddEventState()
    data class ChangeEventOldValue(val oldValue: String): AddEventState()
}
