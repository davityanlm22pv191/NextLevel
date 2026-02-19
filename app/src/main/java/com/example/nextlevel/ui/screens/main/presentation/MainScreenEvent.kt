package com.example.nextlevel.ui.screens.main.presentation

import com.example.nextlevel.data.profile.model.ProfileShortInfo
import com.example.nextlevel.ui.base.BaseEvent

sealed interface MainScreenEvent : BaseEvent {
	data class ProfileInfoLoaded(val data: ProfileShortInfo) : MainScreenEvent
	data object ProfileInfoLoadFail: MainScreenEvent
	data object ProfileInfoLoading: MainScreenEvent
}