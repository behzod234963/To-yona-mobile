package com.mr.anonym.toyonamobile.presentation.event

sealed class ProfileEvent {
    data class ChangeFirstname(val firstname: String): ProfileEvent()
    data class ChangeLastname(val lastname: String): ProfileEvent()
    data class ChangeAvatar(val avatar: Int): ProfileEvent()
}