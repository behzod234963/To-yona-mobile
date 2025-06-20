package com.mr.anonym.toyonamobile.ui.screens.detailsScreen.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mr.anonym.data.instance.local.SharedPreferencesInstance
import com.mr.anonym.domain.model.CardModel
import com.mr.anonym.domain.model.PartysItem
import com.mr.anonym.domain.model.UserModelItem
import com.mr.anonym.domain.useCases.remote.RemoteUseCases
import com.mr.anonym.toyonamobile.R
import com.mr.anonym.toyonamobile.presentation.state.ListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    savedState: SavedStateHandle,
    sharedPrefs: SharedPreferencesInstance,
    private val remoteUseCases: RemoteUseCases,
): ViewModel() {

    private val _id = mutableIntStateOf( sharedPrefs.getID() )
    private val _isFriendAdded = mutableStateOf( false )
    val isFriendAdded: State<Boolean> = _isFriendAdded
    private val _sender = mutableStateOf(UserModelItem() )
    val sender: State<UserModelItem> = _sender
    private val _user = mutableStateOf(UserModelItem())
    val user: State<UserModelItem> = _user
    private val _cards = mutableStateOf(ListState().cards )
    val cards: State<List<CardModel>> = _cards
    private val _senderCard = mutableStateOf("")
    val senderCard: State<String> = _senderCard
    private val _profileAvatar = mutableIntStateOf( R.drawable.ic_default_avatar )
    val profileAvatar: State<Int> = _profileAvatar

    private val _parties = mutableStateOf(ListState().parties )
    val parties: State<List<PartysItem>> = _parties
    private val _activeParties = mutableStateOf(ListState().parties )
    val activeParties: State<List<PartysItem>> = _activeParties

    init {
        savedState.get<Int>("userID")?.let { userID->
            if ( userID != -1 ){
                getUser(userID)
                getUserActiveParties(userID)
                getAllFriends(userID)
            }
        }
        getSender()
    }
    private fun getSender() = viewModelScope.launch {
        remoteUseCases.getUserUseCase.execute(_id.intValue).collect {
            _sender.value = it
            _cards.value = it.cardlist
            _senderCard.value = it.cardlist[0].number
        }
    }
    fun getAllFriends(userID: Int) = viewModelScope.launch {
        remoteUseCases.getAllMyFriendUseCase.execute().collect {
            it.forEach {item ->
                if (item.friendId == userID){
                    _isFriendAdded.value = true
                }
            }
        }
    }
    fun getUser(userID: Int) = viewModelScope.launch {
        remoteUseCases.getUserUseCase.execute(userID).collect {
            _parties.value = it.partylist
            _user.value = it
            _profileAvatar.intValue = when(it.sex){
                0 -> R.drawable.ic_default_avatar
                1 -> R.drawable.ic_man
                2 -> R.drawable.ic_woman
                else -> R.drawable.ic_default_avatar
            }
        }
    }
    fun getUserActiveParties(userID: Int) = viewModelScope.launch {
        remoteUseCases.getUserActiveParties.execute(userID).collect {
            _activeParties.value = it
        }
    }
    fun addFriend(friendID:Int) = viewModelScope.launch {
        remoteUseCases.addFriendUseCase.execute(friendsID = friendID).collect {}
    }
    fun deleteFriend(friendID: Int) = viewModelScope.launch {
        remoteUseCases.deleteFriendUseCase.execute(friendID).collect {
            if (it.message == "Friends deleted"){
                _isFriendAdded.value = false
            }
        }
    }
    fun changeSenderCard(card: String){
        _senderCard.value = card
    }
}