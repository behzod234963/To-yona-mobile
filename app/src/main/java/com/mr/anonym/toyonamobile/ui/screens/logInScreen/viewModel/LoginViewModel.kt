package com.mr.anonym.toyonamobile.ui.screens.logInScreen.viewModel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mr.anonym.data.instance.local.SharedPreferencesInstance
import com.mr.anonym.domain.response.LoginRequest
import com.mr.anonym.domain.useCases.remote.RemoteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val sharedPreferences: SharedPreferencesInstance,
    private val remoteUseCases: RemoteUseCases
): ViewModel(){

    private val _isLoginSuccess = mutableStateOf( false )
    val isLoginSuccess: State<Boolean> = _isLoginSuccess

    fun loginUser(user: LoginRequest) = viewModelScope.launch {
        remoteUseCases.loginUserUseCase.execute(user).collect {
            sharedPreferences.saveAccessToken(it.accessToken)
            sharedPreferences.saveRefreshToken(it.refreshToken)
            _isLoginSuccess.value = true
        }
    }
    fun decodeToken() = viewModelScope.launch {
        remoteUseCases.decodeTokenUseCase.execute( sharedPreferences.getAccessToken()?:"" ).collect {
            sharedPreferences.saveId(it.decoded?.id?:-1)
        }
    }
}