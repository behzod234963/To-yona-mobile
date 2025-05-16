package com.mr.anonym.toyonamobile.ui.screens.myEventsScreen.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mr.anonym.domain.useCases.local.LocalUseCases
import com.mr.anonym.domain.useCases.remote.RemoteUseCases
import com.mr.anonym.toyonamobile.presentation.state.ListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyEventsViewModel @Inject constructor(
    private val remoteUseCases: RemoteUseCases,
    private val localUseCases: LocalUseCases
) : ViewModel(){

    private val _message = mutableStateOf("")
    val message: State<String> = _message

    init {

    }
    fun deleteEventRemote(id: Int) = viewModelScope.launch {
        remoteUseCases.deleteEventUseCase.execute(id).collect {
            _message.value = it
        }
    }
}