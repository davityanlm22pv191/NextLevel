package com.example.tutorplace.ui.screens.main.presentation

import com.example.tutorplace.domain.model.DataInfo
import com.example.tutorplace.ui.base.BaseReducer
import com.example.tutorplace.ui.screens.main.presentation.MainScreenEvent.ProfileInfoLoaded

object MainScreenReducer : BaseReducer<MainScreenState, MainScreenEvent> {

	override fun reduce(
		oldState: MainScreenState,
		event: MainScreenEvent
	): MainScreenState = when (event) {
		is ProfileInfoLoaded -> oldState.copy(profileShortInfo = DataInfo.Success(event.data))
	}
}