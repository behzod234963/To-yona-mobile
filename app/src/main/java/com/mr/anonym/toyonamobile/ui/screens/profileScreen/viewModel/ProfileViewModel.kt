package com.mr.anonym.toyonamobile.ui.screens.profileScreen.viewModel

import android.content.SharedPreferences
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mr.anonym.data.instance.local.DataStoreInstance
import com.mr.anonym.data.instance.local.SharedPreferencesInstance
import com.mr.anonym.toyonamobile.R
import com.mr.anonym.toyonamobile.presentation.event.ProfileEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val sharedPreferences: SharedPreferencesInstance
) : ViewModel() {

    private val _firstname = mutableStateOf(sharedPreferences.getFirstname())
    val firstname: State<String> = _firstname
    private val _lastname = mutableStateOf(sharedPreferences.getLastname())
    val lastname: State<String> = _lastname
    private val _profileAvatar = mutableStateOf(sharedPreferences.getAvatar())
    val profileAvatar: State<Int> = _profileAvatar

    fun onEvent(event: ProfileEvent) {
        when (event) {
            is ProfileEvent.ChangeFirstname -> {
                _firstname.value = event.firstname
            }

            is ProfileEvent.ChangeLastname -> {
                _lastname.value = event.lastname
            }

            is ProfileEvent.ChangeAvatar -> {
                _profileAvatar.value = event.avatar
            }
        }
    }
}