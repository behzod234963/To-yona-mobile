package com.mr.anonym.toyonamobile.ui.screens.logInScreen.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mr.anonym.data.instance.local.DataStoreInstance
import com.mr.anonym.domain.response.LoginRequest
import com.mr.anonym.domain.useCases.remote.RemoteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val dataStore : DataStoreInstance,
    private val remoteUseCases: RemoteUseCases
): ViewModel(){

    private val _message = mutableStateOf("")
    val message: State<String> = _message

    fun loginUser(user: LoginRequest) = viewModelScope.launch {
        remoteUseCases.loginUserUseCase.execute(user).collect {
            dataStore.saveId(it.id)
            _message.value = it.message
        }
    }
}