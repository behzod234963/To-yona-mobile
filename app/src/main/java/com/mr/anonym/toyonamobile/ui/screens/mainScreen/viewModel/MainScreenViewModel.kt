package com.mr.anonym.toyonamobile.ui.screens.mainScreen.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.mr.anonym.domain.model.UserModelItem
import com.mr.anonym.domain.useCases.remote.RemoteUseCases
import com.mr.anonym.toyonamobile.presentation.state.ListState
import dagger.hilt.android.lifecycle.HiltViewModel
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
    private val _users = mutableStateOf(ListState().users)
    val users: State<List<UserModelItem>> = _users
    private val _isRefresh = MutableStateFlow(false)
    val isRefresh: StateFlow<Boolean> = _isRefresh.asStateFlow()

    fun getUserByID(id: Int) = viewModelScope.launch {
        remoteUseCases.getUserByIdUseCase.execute(id).collect {
            _user.value = it
        }
    }
    fun getAllParty() = remoteUseCases.getAllPartyUseCase.execute().cachedIn(viewModelScope)
    fun searchUser(searchText: String) = viewModelScope.launch {
        remoteUseCases.searchUserUseCase.execute(searchText).collect {
            _users.value = it
        }
    }
    fun changeIsRefreshState(state: Boolean) {
        _isRefresh.value = state
    }
}