package com.mr.anonym.toyonamobile.ui.activity.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mr.anonym.data.instance.local.DataStoreInstance
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val dataStoreInstance: DataStoreInstance
): ViewModel() {

    private val _isBiometricAuthOn = mutableStateOf( false )
    val isBiometricAuthOn: State<Boolean> = _isBiometricAuthOn

    init {
        getIsBiometricOn()
    }

    fun getIsBiometricOn() = viewModelScope.launch {
        dataStoreInstance.getIsBiometricAuthOn().collect{
            _isBiometricAuthOn.value = it
        }
    }
}