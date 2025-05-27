package com.mr.anonym.toyonamobile.ui.screens.settingsScreen.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mr.anonym.data.instance.local.SharedPreferencesInstance
import com.mr.anonym.domain.model.UserModelItem
import com.mr.anonym.domain.useCases.remote.RemoteUseCases
import com.mr.anonym.toyonamobile.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    sharedPrefs: SharedPreferencesInstance,
    private val remoteUseCases: RemoteUseCases
): ViewModel() {
    private val _id = mutableIntStateOf( sharedPrefs.getID() )
    private val _user = mutableStateOf(UserModelItem())
    val user: State<UserModelItem> = _user
    private val _profileAvatar = mutableIntStateOf( R.drawable.ic_default_avatar )
    val profileAvatar: State<Int> = _profileAvatar
    init {
        getUserByID()
    }
    fun getUserByID() = viewModelScope.launch {
        remoteUseCases.getUserUseCase.execute(_id.intValue).collect {
            _user.value = it
            _profileAvatar.intValue = when(it.sex){
                0 -> R.drawable.ic_default_avatar
                1 -> R.drawable.ic_man
                2 -> R.drawable.ic_woman
                else -> R.drawable.ic_default_avatar
            }
        }
    }
}