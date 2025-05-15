package com.mr.anonym.toyonamobile.ui.screens.mainScreen.viewModel

import android.annotation.SuppressLint
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mr.anonym.domain.model.UserModelItem
import com.mr.anonym.domain.useCases.remote.RemoteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val remoteUseCases: RemoteUseCases
) : ViewModel() {

    private val _user = mutableStateOf(UserModelItem())
    val user: State<UserModelItem> = _user
    private val _isRefresh = MutableStateFlow(false)
    val isRefresh: StateFlow<Boolean> = _isRefresh.asStateFlow()
    private val _isPartyOwnerState = mutableStateOf(false)
    val isPartyOwnerState: State<Boolean> = _isPartyOwnerState

    private val _otherUser = mutableStateOf(UserModelItem())
    val otherUser: State<UserModelItem> = _otherUser

    @SuppressLint("MutableCollectionMutableState")

    fun getUserByID(id: Int,partyOwner:(String)-> Unit) = viewModelScope.launch {
        remoteUseCases.getUserByIdUseCase.execute(id).collect {
            if (!isPartyOwnerState.value){
                _user.value = it
            }else{
                partyOwner("${it.username} ${it.surname}")
            }
        }
    }
    fun getAllParty() = remoteUseCases.getAllPartyUseCase.execute()
    fun isLoading() = viewModelScope.launch {
        _isRefresh.value = true
        delay(2000)
        _isRefresh.value = false
        getAllParty()
    }

    fun changeOtherUserState(state: Boolean) = viewModelScope.launch {
        _isPartyOwnerState.value = state
    }

    fun changeIsRefreshState(state: Boolean) {
        _isRefresh.value = state
    }
}