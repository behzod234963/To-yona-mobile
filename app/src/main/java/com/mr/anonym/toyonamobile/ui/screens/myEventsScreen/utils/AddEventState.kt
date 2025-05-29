package com.mr.anonym.toyonamobile.ui.screens.myEventsScreen.utils

import com.mr.anonym.domain.model.CardModel

sealed class AddEventState(){
    data class ChangeCardModel(val card: CardModel): AddEventState()
    data class ChangeEventIndex(val index: Int): AddEventState()
    data class ChangeStartDate(val startDate: String): AddEventState()
    data class ChangeEndDate(val endDate: String): AddEventState()
    data class ChangeCardNumber(val cardNumber: String): AddEventState()
    data class ChangeTitle(val title: String): AddEventState()
    data class ChangeOtherField(val fieldValue: String): AddEventState()
    data class ChangeAddressField(val address: String): AddEventState()
}
