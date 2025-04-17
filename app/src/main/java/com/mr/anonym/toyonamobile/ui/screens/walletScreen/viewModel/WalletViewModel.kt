package com.mr.anonym.toyonamobile.ui.screens.walletScreen.viewModel

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

@HiltViewModel
class WalletViewModel @Inject constructor(
    private val localUseCases: LocalUseCases
): ViewModel() {

    private val _cards = mutableStateOf(LocalDataState().cards )
    val cards: State<List<CardModel>> = _cards

    init {
        getCards()
    }

    fun getCards() = viewModelScope.launch {
        localUseCases.getCardsUseCase().collect {
            _cards.value = it
        }
    }
    fun deleteCard(card: CardModel) = viewModelScope.launch {
        localUseCases.deleteCardUseCase(card)
    }
}