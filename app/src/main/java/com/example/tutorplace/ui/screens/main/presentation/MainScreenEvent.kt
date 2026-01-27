package com.example.tutorplace.ui.screens.main.presentation

import androidx.navigation3.runtime.NavKey
import com.example.tutorplace.data.profile.model.ProfileShortInfo
import com.example.tutorplace.ui.base.BaseEvent

sealed interface MainScreenEvent : BaseEvent {
	data class UserIsAuthorized(val startRoute: NavKey) : MainScreenEvent
	data class UserIsNotAuthorized(val startRoute: NavKey): MainScreenEvent
	data class ProfileInfoLoaded(val data: ProfileShortInfo) : MainScreenEvent
}