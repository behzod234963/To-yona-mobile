package com.mr.anonym.toyonamobile.ui.screens.profileScreen.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mr.anonym.data.instance.local.SharedPreferencesInstance
import com.mr.anonym.domain.model.UserModelItem
import com.mr.anonym.domain.useCases.remote.RemoteUseCases
import com.mr.anonym.toyonamobile.presentation.event.ProfileEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val sharedPreferences: SharedPreferencesInstance,
    private val remoteUseCases: RemoteUseCases
) : ViewModel() {

    private val _firstname = mutableStateOf("")
    val firstname: State<String> = _firstname
    private val _lastname = mutableStateOf("")
    val lastname: State<String> = _lastname
    private val _profileAvatar = mutableIntStateOf(sharedPreferences.getAvatar())
    val profileAvatar: State<Int> = _profileAvatar
    private val _user = mutableStateOf(UserModelItem() )
    val user: State<UserModelItem> = _user

    fun getUserById(id: Int) = viewModelScope.launch {
        remoteUseCases.getUserByIdUseCase.execute(id).collect {
            _user.value = it
            _firstname.value = it.username?:""
            _lastname.value = it.surname?:""
        }
    }
    fun updateUser(id: Int,user: UserModelItem) = viewModelScope.launch {
        remoteUseCases.updateUserUseCase.execute(id,user).collect {
            sharedPreferences.saveId(it.id?:-1)
        }
    }
    fun onEvent(event: ProfileEvent) {
        when (event) {
            is ProfileEvent.ChangeFirstname -> {
                _firstname.value = event.firstname
            }
            is ProfileEvent.ChangeLastname -> {
                _lastname.value = event.lastname
            }
            is ProfileEvent.ChangeAvatar -> {
                _profileAvatar.intValue = event.avatar
            }
        }
    }
}