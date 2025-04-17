package com.mr.anonym.toyonamobile.ui.screens.numberCheckScreen.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mr.anonym.domain.model.CardModel
import com.mr.anonym.domain.useCases.local.LocalUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NumberCheckViewModel @Inject constructor(
    private val localUseCases: LocalUseCases
): ViewModel() {

    fun insertCard(card: CardModel) = viewModelScope.launch {
        localUseCases.insertCardUseCase(card)
    }
}