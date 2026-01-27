package com.example.tutorplace.ui.screens.main.presentation

import com.example.tutorplace.data.profile.model.ProfileShortInfo
import com.example.tutorplace.ui.base.BaseEvent

sealed interface MainScreenEvent : BaseEvent {
	data class ProfileInfoLoaded(val data: ProfileShortInfo) : MainScreenEvent
}