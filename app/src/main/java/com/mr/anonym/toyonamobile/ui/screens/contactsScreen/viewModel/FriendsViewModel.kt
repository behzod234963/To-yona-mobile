package com.mr.anonym.toyonamobile.ui.screens.contactsScreen.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mr.anonym.domain.model.AddedByMeItem
import com.mr.anonym.domain.useCases.remote.RemoteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FriendsViewModel @Inject constructor(
    private val remoteUseCases: RemoteUseCases
): ViewModel() {

    private val _friends = mutableStateOf( emptyList<AddedByMeItem>() )
    val friends: State<List<AddedByMeItem>> = _friends

    private val _isRefresh = MutableStateFlow(true)
    val isRefresh = _isRefresh.asStateFlow()

    init {
        isLoading()
    }

    fun getAllFriends() = viewModelScope.launch {
        remoteUseCases.getAllMyFriendUseCase.execute().collect {
            _friends.value = it
        }
    }
    fun isLoading() {
        viewModelScope.launch {
            _isRefresh.value = true
            delay(2000L)
            _isRefresh.value = false
            getAllFriends()
        }
    }
    //    private val _contacts = mutableStateOf(ListState().users)
//    fun getPhoneNumbers(context: Context,isPermissionGranted: Boolean) = viewModelScope.launch {
//        if (isPermissionGranted){
//            _contacts.value = getContacts(context)
//        }
//    }

//    val contacts: State<List<UserModelItem>> = _contacts
}