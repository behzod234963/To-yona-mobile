package com.mr.anonym.toyonamobile.ui.screens.securityScreen.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mr.anonym.domain.useCases.remote.RemoteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SecurityViewModel @Inject constructor(
    private val remoteUseCases: RemoteUseCases
): ViewModel() {

    private val _message = mutableStateOf( "" )
    val message: State<String> = _message

    fun deleteUser() = viewModelScope.launch {
        remoteUseCases.deleteUserUseCase.execute().collect {
            _message.value = it.message
        }
    }
}