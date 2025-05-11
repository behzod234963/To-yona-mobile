package com.mr.anonym.toyonamobile.ui.screens.detailsScreen.viewModel

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mr.anonym.domain.model.CardModel
import com.mr.anonym.domain.useCases.local.LocalUseCases
import com.mr.anonym.toyonamobile.R
import com.mr.anonym.toyonamobile.presentation.state.ListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    context: Context,
    private val localUseCases: LocalUseCases,
): ViewModel() {

    private val _cards = mutableStateOf(ListState().cards )
    val cards: State<List<CardModel>> = _cards
    private val _senderCard = mutableStateOf(context.getString(R.string.you_have_not_active_card))
    val senderCard: State<String> = _senderCard

    init {
        getCards()
    }

    fun getCards() = viewModelScope.launch {
        localUseCases.getCardsUseCase().collect {
            _cards.value = it
            if (it.isNotEmpty()) _senderCard.value = it[0].cardNumber
        }
    }
    fun changeSenderCard(card: String){
        _senderCard.value = card
    }
}