package com.mr.anonym.toyonamobile.ui.screens.myEventsScreen.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mr.anonym.domain.model.MyEventsModel
import com.mr.anonym.domain.useCases.local.LocalUseCases
import com.mr.anonym.toyonamobile.presentation.state.ListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyEventsViewModel @Inject constructor(
    private val localUseCases: LocalUseCases
) : ViewModel(){

    private val _events = mutableStateOf(ListState().events )
    val events: State<List<MyEventsModel>> = _events

    init {
        getEvents()
    }
    fun getEvents() = viewModelScope.launch {
        localUseCases.getEventsUseCase.execute().collect {
            _events.value = it
        }
    }
    fun updateEventStatus(id: Int,status: Boolean) = viewModelScope.launch {
        localUseCases.updateEventStatusUseCase.execute(id,status)
    }
    fun deleteEvent(event: MyEventsModel) = viewModelScope.launch {
        localUseCases.deleteEventUseCase.execute(event)
    }
}