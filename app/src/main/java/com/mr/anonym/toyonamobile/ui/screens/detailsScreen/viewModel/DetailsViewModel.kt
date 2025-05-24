package com.mr.anonym.toyonamobile.ui.screens.detailsScreen.viewModel

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mr.anonym.domain.model.CardModel
import com.mr.anonym.domain.model.UserModelItem
import com.mr.anonym.domain.useCases.local.LocalUseCases
import com.mr.anonym.domain.useCases.remote.RemoteUseCases
import com.mr.anonym.toyonamobile.R
import com.mr.anonym.toyonamobile.presentation.state.ListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    context: Context,
    savedState: SavedStateHandle,
//    sharedPrefs: SharedPreferencesInstance,
    private val remoteUseCases: RemoteUseCases,
    private val localUseCases: LocalUseCases,
): ViewModel() {

//    private val id = mutableIntStateOf( sharedPrefs.getID() )
//    private val _sender = mutableStateOf(UserModelItem() )
//    val sender: State<UserModelItem> = _sender
    private val _user = mutableStateOf(UserModelItem())
    val user: State<UserModelItem> = _user
    private val _cards = mutableStateOf(ListState().cards )
    val cards: State<List<CardModel>> = _cards
    private val _senderCard = mutableStateOf(context.getString(R.string.you_have_not_active_card))
    val senderCard: State<String> = _senderCard
    private val _senderName = mutableStateOf(context.getString(R.string.you_have_not_active_card))
    val senderName: State<String> = senderCard
    private val _profileAvatar = mutableIntStateOf( R.drawable.ic_default_avatar )
    val profileAvatar: State<Int> = _profileAvatar

    init {
        savedState.get<Int>("userID")?.let { userID->
            if ( userID != -1 ){
                getUserByID()
            }
        }
    }
//    fun getUser() = viewModelScope.launch {
//        remoteUseCases.getUserByIdUseCase.execute(id.intValue).collect {
//            _sender.value = it
//        }
//    }
    fun getUserByID() = viewModelScope.launch {
        remoteUseCases.getUserUseCase.execute().collect {
            _user.value = it
            _profileAvatar.intValue = when(it.sex){
                0 -> R.drawable.ic_default_avatar
                1 -> R.drawable.ic_man
                2 -> R.drawable.ic_woman
                else -> R.drawable.ic_default_avatar
            }
        }
    }
    fun changeSenderCard(card: String){
        _senderCard.value = card
    }
    fun changeSenderName(name: String){
        _senderName.value = name
    }
}