package com.mr.anonym.toyonamobile.ui.screens.registrationScreen.screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mr.anonym.data.instance.local.SharedPreferencesInstance
import com.mr.anonym.domain.model.UserModelItem
import com.mr.anonym.domain.response.LoginRequest
import com.mr.anonym.domain.useCases.remote.RemoteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val sharedPreferences: SharedPreferencesInstance,
    private val remoteUseCases: RemoteUseCases
): ViewModel() {

    private val _userModel = mutableStateOf(UserModelItem())
    val user: State<UserModelItem> = _userModel

    fun signUpUser(user: UserModelItem) = viewModelScope.launch {
        remoteUseCases.registerUserUseCase.execute(user).collect {
            _userModel.value = it
            sharedPreferences.saveId(it.id)
        }
    }
}