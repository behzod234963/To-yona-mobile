package com.mr.anonym.toyonamobile.ui.screens.myEventsScreen.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mr.anonym.data.instance.local.SharedPreferencesInstance
import com.mr.anonym.domain.model.PartysItem
import com.mr.anonym.domain.useCases.remote.RemoteUseCases
import com.mr.anonym.toyonamobile.presentation.state.ListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyEventsViewModel @Inject constructor(
    sharedPreferences: SharedPreferencesInstance,
    private val remoteUseCases: RemoteUseCases,
) : ViewModel(){

    private val _id = mutableIntStateOf( sharedPreferences.getID() )
    private val _parties = mutableStateOf(ListState().parties)
    val parties: State<List<PartysItem>> = _parties
    private val _message = mutableStateOf("")
    val message: State<String> = _message
    private val _isPartyUpdated = mutableStateOf( false )
    val isPartyUpdated: State<Boolean> = _isPartyUpdated

    init {
        getUserParties()
    }

    fun getUserParties() = viewModelScope.launch {
        remoteUseCases.getUserPartiesUseCase.execute(_id.intValue).collect {
            _parties.value = it
        }
    }
    fun updateParty(partyID: Int,partyModel: PartysItem) = viewModelScope.launch {
        remoteUseCases.updatePartyUseCase.execute(partyID,partyModel).collect {
            _isPartyUpdated.value = it
        }
    }
    fun deleteParty(id: Int) = viewModelScope.launch {
        remoteUseCases.deletePartyUseCase.execute(id).collect {
            _message.value = it
        }
    }
}