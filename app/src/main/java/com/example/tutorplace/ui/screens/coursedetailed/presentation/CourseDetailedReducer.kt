package com.example.tutorplace.ui.screens.coursedetailed.presentation

import com.example.tutorplace.domain.model.loaded
import com.example.tutorplace.ui.base.BaseReducer
import com.example.tutorplace.ui.screens.coursedetailed.presentation.CourseDetailedEvent.SetProfileInfo

object CourseDetailedReducer : BaseReducer<CourseDetailedState, CourseDetailedEvent> {

	override fun reduce(
		oldState: CourseDetailedState,
		event: CourseDetailedEvent
	): CourseDetailedState {
		return when (event) {
			is SetProfileInfo -> reduceSetProfileInfoEvent(oldState, event)
		}
	}

	private fun reduceSetProfileInfoEvent(
		oldState: CourseDetailedState,
		event: SetProfileInfo
	): CourseDetailedState = with(oldState) {
		return copy(profileShortInfo = profileShortInfo.loaded(event.profileShortInfo))
	}
}