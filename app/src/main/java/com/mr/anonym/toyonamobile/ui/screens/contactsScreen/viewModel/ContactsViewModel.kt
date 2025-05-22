package com.mr.anonym.toyonamobile.ui.screens.contactsScreen.viewModel

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mr.anonym.domain.model.UserModelItem
import com.mr.anonym.toyonamobile.presentation.state.ListState
import com.mr.anonym.toyonamobile.presentation.utils.getContacts
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactsViewModel @Inject constructor(): ViewModel() {

    private val _isRefresh = MutableStateFlow(false)
    val isRefresh = _isRefresh.asStateFlow()

    private val _contacts = mutableStateOf(ListState().users)
    val contacts: State<List<UserModelItem>> = _contacts

    fun getPhoneNumbers(context: Context,isPermissionGranted: Boolean) = viewModelScope.launch {
        if (isPermissionGranted){
            _contacts.value = getContacts(context)
        }
    }
    fun isLoading(context: Context,isPermissionGranted: Boolean) {
        viewModelScope.launch {
            _isRefresh.value = true
            delay(3000L)
            _isRefresh.value = false
            getPhoneNumbers(context,isPermissionGranted)
        }
    }
}