package com.mr.anonym.toyonamobile.ui.screens.mainScreen.viewModel

import android.annotation.SuppressLint
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.mr.anonym.data.instance.local.SharedPreferencesInstance
import com.mr.anonym.domain.model.PartysItem
import com.mr.anonym.domain.model.UserModelItem
import com.mr.anonym.domain.useCases.remote.RemoteUseCases
import com.mr.anonym.toyonamobile.R
import com.mr.anonym.toyonamobile.presentation.state.ListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    sharedPrefs: SharedPreferencesInstance,
    private val remoteUseCases: RemoteUseCases
) : ViewModel() {

    private val _id = mutableIntStateOf( sharedPrefs.getID() )
    private val _user = mutableStateOf(UserModelItem())
    val user: State<UserModelItem> = _user
    private val _users = mutableStateOf(ListState().users)
    val users: State<List<UserModelItem>> = _users
    private val _isRefresh = MutableStateFlow(true)
    val isRefresh: StateFlow<Boolean> = _isRefresh.asStateFlow()
    private val _profileAvatar = mutableIntStateOf( R.drawable.ic_default_avatar )
    val profileAvatar: State<Int> = _profileAvatar

    @SuppressLint("MutableCollectionMutableState")
    private val _closerParties = mutableStateListOf<PartysItem>()
    val closerParties: SnapshotStateList<PartysItem> = _closerParties

    fun getUserByID() = viewModelScope.launch {
        remoteUseCases.getUserUseCase.execute(_id.intValue).collect {
            _user.value = it
            _closerParties.addAll(
                it.partylist.filter {party-> party.status }
            )
            _profileAvatar.intValue = when(it.sex){
                0 -> R.drawable.ic_default_avatar
                1 -> R.drawable.ic_man
                2 -> R.drawable.ic_woman
                else -> R.drawable.ic_default_avatar
            }
        }
    }
    fun getAllFriends() = viewModelScope.launch {
        remoteUseCases.getAllMyFriendUseCase.execute().collect {
            it.forEach { item ->
                getFriendParties(item.friendId)
            }
        }
    }
    fun getFriendParties(friendID: Int) = viewModelScope.launch {
        remoteUseCases.getUserUseCase.execute(friendID).collect {
            _closerParties.addAll(
                it.partylist.filter {party-> party.status }
            )
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