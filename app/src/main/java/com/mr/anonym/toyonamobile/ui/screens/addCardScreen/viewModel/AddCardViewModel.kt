package com.mr.anonym.toyonamobile.ui.screens.addCardScreen.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mr.anonym.domain.model.CardModel
import com.mr.anonym.domain.useCases.local.LocalUseCases
import com.mr.anonym.toyonamobile.presentation.event.CardEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddCardViewModel @Inject constructor(
    savedState: SavedStateHandle,
    private val localUseCases: LocalUseCases
): ViewModel(){

    private val _cardNumber = mutableStateOf( "" )
    val cardNumber: State<String> = _cardNumber
    private val _cardHolder = mutableStateOf( "" )
    val cardHolder: State<String> = _cardHolder
    private val _expiryDate = mutableStateOf( "" )
    val expiryDate: State<String> = _expiryDate

    init {
        savedState.get<Int>( "cardID" )?.let {cardID->
            if (cardID != -1){
                getCardByID(cardID)
            }
        }
    }

    fun insertCard(card: CardModel) = viewModelScope.launch {
        localUseCases.insertCardUseCase(card)
    }
    fun getCardByID(id: Int) = viewModelScope.launch {
        localUseCases.getCardByIdUseCase(id).collect {card->
            _cardNumber.value = card.cardNumber.filter { it.isDigit() }
            _cardHolder.value = card.cardHolder
            _expiryDate.value = card.expiryDate.filter { it.isDigit() }
        }
    }
    fun cardEvents(event: CardEvents) = viewModelScope.launch {
        when(event){
            is CardEvents.ChangeCardHolder -> {
                _cardHolder.value = event.cardHolder
            }
            is CardEvents.ChangeCardNumber -> {
                _cardNumber.value = event.cardNumber
            }
            is CardEvents.ChangeExpiryDate -> {
                _expiryDate.value = event.date
            }
        }
    }
}