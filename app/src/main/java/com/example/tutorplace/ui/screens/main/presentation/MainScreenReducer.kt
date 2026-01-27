package com.example.tutorplace.ui.screens.main.presentation

import com.example.tutorplace.domain.model.DataInfo.Success
import com.example.tutorplace.ui.base.BaseReducer
import com.example.tutorplace.ui.screens.main.presentation.MainScreenEvent.ProfileInfoLoaded
import com.example.tutorplace.ui.screens.main.presentation.MainScreenEvent.UserIsAuthorized
import com.example.tutorplace.ui.screens.main.presentation.MainScreenEvent.UserIsNotAuthorized

object MainScreenReducer : BaseReducer<MainScreenState, MainScreenEvent> {

	override fun reduce(
		oldState: MainScreenState,
		event: MainScreenEvent
	): MainScreenState = when (event) {
		is ProfileInfoLoaded -> oldState.copy(profileShortInfo = Success(event.data))
		is UserIsAuthorized -> oldState.copy(startRoute = event.startRoute)
		is UserIsNotAuthorized -> oldState.copy(startRoute = event.startRoute)
	}
}