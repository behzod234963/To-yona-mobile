package com.mr.anonym.toyonamobile.ui.screens.walletScreen.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mr.anonym.data.instance.local.SharedPreferencesInstance
import com.mr.anonym.domain.model.CardModel
import com.mr.anonym.domain.model.CardUtilModel
import com.mr.anonym.domain.useCases.local.LocalUseCases
import com.mr.anonym.domain.useCases.remote.RemoteUseCases
import com.mr.anonym.toyonamobile.presentation.state.ListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WalletViewModel @Inject constructor(
    sharedPrefs: SharedPreferencesInstance,
    private val localUseCases: LocalUseCases,
    private val remoteUseCases: RemoteUseCases,
): ViewModel() {

    private val _id = mutableIntStateOf( sharedPrefs.getID() )
    private val _cards = mutableStateOf(ListState().cards )
    val cards: State<List<CardModel>> = _cards
    private val _message = mutableStateOf( "" )
    private val _colorIndex = mutableStateOf( 1 )
    val colorIndex: State<Int> = _colorIndex

    init {
        getUserCards()
    }

    fun getColorIndex(id: Int) = viewModelScope.launch {
        localUseCases.getCardIndexByIdUseCase.execute(id).collect {
            _colorIndex.value = it.colorIndex
        }
    }
    fun deleteCardUtil(model: CardUtilModel) = viewModelScope.launch {
        localUseCases.deleteCardUtilUseCase.execute(model)
    }
    fun getUserCards() = viewModelScope.launch {
        remoteUseCases.getUserCardsUseCase.execute(_id.intValue).collect {
            _cards.value = it
        }
    }
    fun deleteCard(id: Int) = viewModelScope.launch {
        remoteUseCases.deleteCardUseCase.execute(id).collect {
            _message.value = it
        }
    }
}