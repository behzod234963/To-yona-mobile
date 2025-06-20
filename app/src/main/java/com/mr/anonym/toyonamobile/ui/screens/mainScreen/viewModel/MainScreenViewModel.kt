package com.mr.anonym.toyonamobile.ui.screens.mainScreen.viewModel

import android.annotation.SuppressLint
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.compose.LazyPagingItems
import com.mr.anonym.data.instance.local.SharedPreferencesInstance
import com.mr.anonym.data.instance.remote.PartyApiService
import com.mr.anonym.data.paging.PartyPagingSource
import com.mr.anonym.domain.model.LocalPartyModel
import com.mr.anonym.domain.model.PartysItem
import com.mr.anonym.domain.model.UserModelItem
import com.mr.anonym.domain.useCases.local.LocalUseCases
import com.mr.anonym.domain.useCases.remote.RemoteUseCases
import com.mr.anonym.toyonamobile.R
import com.mr.anonym.toyonamobile.presentation.state.ListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    sharedPrefs: SharedPreferencesInstance,
    pagingSource: PartyApiService,
    private val remoteUseCases: RemoteUseCases,
    private val localUseCases: LocalUseCases
) : ViewModel() {

    private val _id = mutableIntStateOf( sharedPrefs.getID() )
    private val _user = mutableStateOf(UserModelItem())
    val user: State<UserModelItem> = _user
    private val _users = mutableStateOf(ListState().users)
    val users: State<List<UserModelItem>> = _users
    private val _isRefresh = mutableStateOf(false)
    val isRefresh: State<Boolean> = _isRefresh
    private val _profileAvatar = mutableIntStateOf( R.drawable.ic_default_avatar )
    val profileAvatar: State<Int> = _profileAvatar

    @SuppressLint("MutableCollectionMutableState")
    private val _closerParties = mutableStateListOf<PartysItem>()
    val closerParties: SnapshotStateList<PartysItem> = _closerParties
    private val _localParties = mutableStateOf( emptyList<LocalPartyModel>() )
    val localParties: State<List<LocalPartyModel>> = _localParties

    val parties : Flow<PagingData<PartysItem>> = Pager(
        config = PagingConfig(
            pageSize = 10,
            initialLoadSize = 10
        ),
        pagingSourceFactory = { PartyPagingSource(pagingSource) }
    ).flow.cachedIn(viewModelScope)

    init {
        getAllFriends()
        getUserByID()
        getAllLocalParty()
    }

    fun getAllLocalParty() = viewModelScope.launch {
        localUseCases.getAllLocalParty.execute().collect {
            _localParties.value = it
        }
    }
    fun getUserByID() = viewModelScope.launch {
        remoteUseCases.getUserUseCase.execute(_id.intValue).collect {
            _user.value = it
            _closerParties.addAll(
                it.partylist.filter {party-> party.status }
            )
            _closerParties.forEach { closerParty->
                val model = LocalPartyModel(
                    id = closerParty.id,
                    userId = closerParty.userId,
                    userName = closerParty.userName,
                    name = closerParty.name,
                    type = closerParty.type,
                    address = closerParty.address,
                    cardNumber = closerParty.cardNumber,
                    startTime = closerParty.startTime,
                    endTime = closerParty.endTime,
                    status = closerParty.status,
                    createdAt = closerParty.createdAt
                )
                if (!localParties.value.contains(model)){
                    localUseCases.insertLocalParty.execute(model)
                }
            }
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
            _closerParties.forEach { closerParty->
                val model = LocalPartyModel(
                    id = closerParty.id,
                    userId = closerParty.userId,
                    userName = closerParty.userName,
                    name = closerParty.name,
                    type = closerParty.type,
                    address = closerParty.address,
                    cardNumber = closerParty.cardNumber,
                    startTime = closerParty.startTime,
                    endTime = closerParty.endTime,
                    status = closerParty.status,
                    createdAt = closerParty.createdAt
                )
                if (!localParties.value.contains(model)){
                    localUseCases.insertLocalParty.execute(model)
                }
            }
        }
    }
    fun getAllParty(partyList: LazyPagingItems<PartysItem>) = viewModelScope.launch {
        _isRefresh.value = true
        delay(2000L)
        partyList.refresh()
        getAllFriends()
        getUserByID()
        _isRefresh.value = false
    }
    fun searchUser(searchText: String) = viewModelScope.launch {
        remoteUseCases.searchUserUseCase.execute(searchText).collect {
            _users.value = it
        }
    }
}