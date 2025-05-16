package com.mr.anonym.toyonamobile.ui.screens.numberCheckScreen.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mr.anonym.data.instance.local.DataStoreInstance
import com.mr.anonym.domain.model.CardModel
import com.mr.anonym.domain.useCases.local.LocalUseCases
import com.mr.anonym.domain.useCases.remote.RemoteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NumberCheckViewModel @Inject constructor(
    private val dataStore: DataStoreInstance,
    private val localUseCases: LocalUseCases,
    private val remoteUseCases: RemoteUseCases
): ViewModel() {

    fun insertCard(card: CardModel) = viewModelScope.launch {
        localUseCases.insertCardUseCase(card)
    }
    fun getUserByID(id: Int) = viewModelScope.launch {
        remoteUseCases.getUserByIdUseCase.execute(id).collect {
            dataStore.savePhoneNumber(it.phonenumber ?:"")
        }
    }
}