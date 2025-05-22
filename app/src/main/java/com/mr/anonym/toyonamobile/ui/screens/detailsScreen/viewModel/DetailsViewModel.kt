package com.mr.anonym.toyonamobile.ui.screens.detailsScreen.viewModel

import android.content.Context
import androidx.compose.runtime.State
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

    init {
        savedState.get<Int>("userID")?.let { userID->
            if ( userID != -1 ){
                getUserByID(userID)
            }
        }
    }
//    fun getUser() = viewModelScope.launch {
//        remoteUseCases.getUserByIdUseCase.execute(id.intValue).collect {
//            _sender.value = it
//        }
//    }
    fun getUserByID(id: Int) = viewModelScope.launch {
        remoteUseCases.getUserByIdUseCase.execute(id).collect {
            _user.value = it
        }
    }
    fun changeSenderCard(card: String){
        _senderCard.value = card
    }
    fun changeSenderName(name: String){
        _senderName.value = name
    }
}