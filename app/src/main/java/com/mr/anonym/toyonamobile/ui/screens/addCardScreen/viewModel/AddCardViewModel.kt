package com.mr.anonym.toyonamobile.ui.screens.addCardScreen.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mr.anonym.domain.model.UserModelItem
import com.mr.anonym.domain.useCases.remote.RemoteUseCases
import com.mr.anonym.toyonamobile.presentation.event.CardEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddCardViewModel @Inject constructor(
    savedState: SavedStateHandle,
    private val remoteUseCases: RemoteUseCases,
) : ViewModel() {

    private val _user = mutableStateOf(UserModelItem())
    val user: State<UserModelItem> = _user
    private val _cardNumber = mutableStateOf("")
    val cardNumber: State<String> = _cardNumber
    private val _expiryDate = mutableStateOf("")
    val expiryDate: State<String> = _expiryDate

    init {
        savedState.get<Int>("cardID")?.let { cardID ->
            if (cardID != -1) {
                getCardByID(cardID)
            }
        }
        getUser()
    }

    fun getCardByID(cardID: Int) = viewModelScope.launch {
        remoteUseCases.getCardByIdUseCase.execute(cardID).collect {
            _cardNumber.value = it.number.filter { cardNumber-> cardNumber.isDigit() }
            _expiryDate.value = "${it.date.take(2)}${it.date.takeLast(2)}"
        }
    }

    fun getUser() = viewModelScope.launch {
        remoteUseCases.getUserUseCase.execute().collect {
            _user.value = it
        }
    }

    fun cardEvents(event: CardEvents) = viewModelScope.launch {
        when (event) {
            is CardEvents.ChangeCardNumber -> {
                _cardNumber.value = event.cardNumber
            }
            is CardEvents.ChangeExpiryDate -> {
                _expiryDate.value = event.date
            }
        }
    }
}