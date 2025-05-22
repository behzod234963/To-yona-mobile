package com.mr.anonym.toyonamobile.ui.screens.changePasswordScreen.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mr.anonym.data.instance.local.SharedPreferencesInstance
import com.mr.anonym.domain.model.UserModelItem
import com.mr.anonym.domain.useCases.remote.RemoteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChangePasswordViewModel @Inject constructor(
    sharedPrefs: SharedPreferencesInstance,
    private val remoteUseCases: RemoteUseCases
): ViewModel() {

    private val _id = mutableIntStateOf( sharedPrefs.getID() )
    private val _user = mutableStateOf(UserModelItem())
    val user: State<UserModelItem> = _user
    init {
        getUserByID()
    }
    fun getUserByID() = viewModelScope.launch {
        remoteUseCases.getUserByIdUseCase.execute(_id.intValue).collect {
            _user.value = it
        }
    }
    fun updateUser(userModelItem: UserModelItem) = viewModelScope.launch {
        remoteUseCases.updateUserUseCase.execute(_id.intValue,userModelItem).collect {
            _user.value = it
        }
    }
}