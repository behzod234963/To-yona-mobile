package com.mr.anonym.toyonamobile.presentation.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mr.anonym.domain.model.NotificationsModel
import com.mr.anonym.domain.useCases.local.LocalUseCases
import com.mr.anonym.toyonamobile.presentation.state.LocalDataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificationsViewModel @Inject constructor(
    private val localUseCases: LocalUseCases
): ViewModel() {

    private val _notifications = mutableStateOf(LocalDataState().notifications )
    val notifications: State<List<NotificationsModel>> = _notifications

    init {
        getNotifications()
    }

    fun getNotifications() = viewModelScope.launch {
        localUseCases.getNotificationsUseCase().collect {notifications->
            _notifications.value = notifications
        }
    }
}