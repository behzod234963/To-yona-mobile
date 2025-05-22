package com.mr.anonym.toyonamobile.ui.screens.monitoringScreen.items

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mr.anonym.domain.model.CardModel
import com.mr.anonym.domain.useCases.local.LocalUseCases
import com.mr.anonym.toyonamobile.presentation.state.ListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MonitoringViewModel @Inject constructor(
    private val localUseCases: LocalUseCases
) : ViewModel(){

    private val _cards = mutableStateOf(ListState().cards)
    val cards: State<List<CardModel>> = _cards

    init {
    }
}