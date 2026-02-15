package com.example.nextlevel.ui.screens.main.presentation

import com.example.nextlevel.domain.model.DataInfo.Success
import com.example.nextlevel.ui.base.BaseReducer
import com.example.nextlevel.ui.screens.main.presentation.MainScreenEvent.ProfileInfoLoaded

object MainScreenReducer : BaseReducer<MainScreenState, MainScreenEvent> {

	override fun reduce(
		oldState: MainScreenState,
		event: MainScreenEvent
	): MainScreenState = when (event) {
		is ProfileInfoLoaded -> oldState.copy(profileShortInfo = Success(event.data))
	}
}