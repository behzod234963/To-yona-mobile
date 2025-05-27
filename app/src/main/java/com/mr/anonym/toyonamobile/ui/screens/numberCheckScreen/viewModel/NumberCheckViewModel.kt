package com.mr.anonym.toyonamobile.ui.screens.numberCheckScreen.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mr.anonym.data.instance.local.SharedPreferencesInstance
import com.mr.anonym.domain.model.CardModel
import com.mr.anonym.domain.response.LoginRequest
import com.mr.anonym.domain.useCases.remote.RemoteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NumberCheckViewModel @Inject constructor(
    private val sharedPrefs: SharedPreferencesInstance,
    private val remoteUseCases: RemoteUseCases
) : ViewModel() {

    private val _id = mutableIntStateOf(sharedPrefs.getID())
    private val _isCardAdded = mutableStateOf(false)
    val isCardAdded: State<Boolean> = _isCardAdded
    private val _isCardUpdated = mutableStateOf(false)
    val isCardUpdated: State<Boolean> = _isCardUpdated
    private val _isLoginSuccess = mutableStateOf(false)
    val isLoginSuccess: State<Boolean> = _isLoginSuccess

    init {
        getUserByID()
    }

    fun addCard(cardModel: CardModel) = viewModelScope.launch {
        remoteUseCases.addCardUseCase.execute(cardModel).collect {
            _isCardAdded.value = true
        }
    }

    fun loginUser() = viewModelScope.launch {
        remoteUseCases.loginUserUseCase.execute(
            LoginRequest(
                phonenumber = sharedPrefs.getPhoneNumber() ?: "",
                password = sharedPrefs.getPassword() ?: ""
            )
        ).collect {
            sharedPrefs.saveAccessToken(it.accessToken)
            sharedPrefs.saveRefreshToken(it.refreshToken)
            _isLoginSuccess.value = true
        }
    }

    fun decodeToken() = viewModelScope.launch {
        remoteUseCases.decodeTokenUseCase.execute(sharedPrefs.getAccessToken() ?: "").collect {
            sharedPrefs.saveId(it.decoded?.id ?: -1)
        }
    }

    fun updateCard(cardID: Int, cardModel: CardModel) = viewModelScope.launch {
        remoteUseCases.updateCardUseCase.execute(cardID, cardModel).collect {
            _isCardUpdated.value = true
        }
    }

    fun getUserByID() = viewModelScope.launch {
        remoteUseCases.getUserUseCase.execute(_id.intValue).collect {
            sharedPrefs.savePhoneNumber(it.phonenumber)
        }
    }
}