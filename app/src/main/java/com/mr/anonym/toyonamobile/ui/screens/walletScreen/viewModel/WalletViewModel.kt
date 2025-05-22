package com.mr.anonym.toyonamobile.ui.screens.walletScreen.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mr.anonym.data.instance.local.SharedPreferencesInstance
import com.mr.anonym.domain.model.CardModel
import com.mr.anonym.domain.useCases.remote.RemoteUseCases
import com.mr.anonym.toyonamobile.presentation.state.ListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WalletViewModel @Inject constructor(
    sharedPreferences: SharedPreferencesInstance,
    private val remoteUseCases: RemoteUseCases,
): ViewModel() {

    private val _id = mutableIntStateOf( sharedPreferences.getID() )
    private val _cards = mutableStateOf(ListState().cards )
    val cards: State<List<CardModel>> = _cards

    init {
        getUserCards()
    }

    fun getUserCards() = viewModelScope.launch {
        remoteUseCases.getUserCardsUseCase.execute(_id.intValue).collect {
            _cards.value = it
        }
    }
}