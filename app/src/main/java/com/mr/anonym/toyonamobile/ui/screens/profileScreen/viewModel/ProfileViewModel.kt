package com.mr.anonym.toyonamobile.ui.screens.profileScreen.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mr.anonym.data.instance.local.SharedPreferencesInstance
import com.mr.anonym.domain.model.UserModelItem
import com.mr.anonym.domain.useCases.remote.RemoteUseCases
import com.mr.anonym.toyonamobile.R
import com.mr.anonym.toyonamobile.presentation.event.ProfileEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val sharedPreferences: SharedPreferencesInstance,
    private val remoteUseCases: RemoteUseCases
) : ViewModel() {

    private val _id = mutableIntStateOf( sharedPreferences.getID() )
    private val _phoneNumber = mutableStateOf( "" )
    val phoneNumber: State<String> = _phoneNumber
    private val _firstname = mutableStateOf("")
    val firstname: State<String> = _firstname
    private val _lastname = mutableStateOf("")
    val lastname: State<String> = _lastname
    private val _profileAvatar = mutableIntStateOf(R.drawable.ic_default_avatar)
    val profileAvatar: State<Int> = _profileAvatar
    private val _avatarIndex = mutableIntStateOf(0)
    val avatarIndex: State<Int> = _avatarIndex
    private val _user = mutableStateOf(UserModelItem())
    val user: State<UserModelItem> = _user

    init {
        getUserById()
    }

    fun getUserById() = viewModelScope.launch {
        remoteUseCases.getUserUseCase.execute().collect {
            _user.value = it
            _avatarIndex.intValue = it.sex
            when(it.sex){
                0-> _profileAvatar.intValue = R.drawable.ic_default_avatar
                1-> _profileAvatar.intValue = R.drawable.ic_man
                2-> _profileAvatar.intValue = R.drawable.ic_woman
                else -> _profileAvatar.intValue = R.drawable.ic_default_avatar
            }
            _firstname.value = it.username
            _lastname.value = it.surname
            _phoneNumber.value = it.phonenumber
        }
    }
    fun updateUser(user: UserModelItem) = viewModelScope.launch {
        remoteUseCases.updateUserUseCase.execute(user).collect {
            sharedPreferences.saveId(it.id)
        }
    }
    fun onEvent(event: ProfileEvent) {
        when (event) {
            is ProfileEvent.ChangeFirstname -> _firstname.value = event.firstname
            is ProfileEvent.ChangeLastname -> _lastname.value = event.lastname
            is ProfileEvent.ChangeAvatar -> _profileAvatar.intValue = event.avatar
            is ProfileEvent.ChangeIndex -> _avatarIndex.intValue = event.index
        }
    }
}