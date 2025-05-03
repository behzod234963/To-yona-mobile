package com.mr.anonym.toyonamobile.ui.screens.addEventScreen.viewModel

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mr.anonym.domain.model.CardModel
import com.mr.anonym.domain.useCases.local.LocalUseCases
import com.mr.anonym.toyonamobile.presentation.state.LocalDataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.mr.anonym.toyonamobile.R

@HiltViewModel
class AddEventViewModel @Inject constructor(
    context: Context,
    private val localUseCases: LocalUseCases
) : ViewModel(){

    private val _cards = mutableStateOf(LocalDataState().cards)
    val cards: State<List<CardModel>> = _cards
    private val _cardValue = mutableStateOf(context.getString(R.string.empty) )
    val cardValue: State<String> = _cardValue
    private val _card = mutableStateOf(CardModel() )
    val card: State<CardModel> = _card

    init {
        getCards()
    }
    fun getCards() = viewModelScope.launch {
        localUseCases.getCardsUseCase().collect {
            _cards.value = it
            if (it.isNotEmpty()) {
                _cardValue.value = it[0].cardNumber
                _card.value = it[0]
            }
        }
    }
    fun changeCardValue(card: String){
        _cardValue.value = card
    }
    fun changeCardModel(card: CardModel) = viewModelScope.launch {
        _card.value = card
    }
}