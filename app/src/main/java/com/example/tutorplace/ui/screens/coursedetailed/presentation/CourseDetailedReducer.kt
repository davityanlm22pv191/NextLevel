package com.example.tutorplace.ui.screens.coursedetailed.presentation

import com.example.tutorplace.domain.model.failure
import com.example.tutorplace.domain.model.loaded
import com.example.tutorplace.ui.base.BaseReducer
import com.example.tutorplace.ui.screens.coursedetailed.presentation.CourseDetailedEvent.CourseDetailedFailed
import com.example.tutorplace.ui.screens.coursedetailed.presentation.CourseDetailedEvent.CourseDetailedLoaded
import com.example.tutorplace.ui.screens.coursedetailed.presentation.CourseDetailedEvent.SetProfileInfo

object CourseDetailedReducer : BaseReducer<CourseDetailedState, CourseDetailedEvent> {

	override fun reduce(
		oldState: CourseDetailedState,
		event: CourseDetailedEvent
	): CourseDetailedState {
		return when (event) {
			is SetProfileInfo -> reduceSetProfileInfoEvent(oldState, event)
			is CourseDetailedLoaded -> reduceCourseDetailedLoadedEvent(oldState, event)
			is CourseDetailedFailed -> reduceCourseDetailedFailed(oldState, event)
			is CourseDetailedEvent.AttachParams -> oldState
		}
	}

	private fun reduceSetProfileInfoEvent(
		oldState: CourseDetailedState,
		event: SetProfileInfo
	): CourseDetailedState = with(oldState) {
		return copy(profileShortInfo = profileShortInfo.loaded(event.profileShortInfo))
	}

	private fun reduceCourseDetailedLoadedEvent(
		oldState: CourseDetailedState,
		event: CourseDetailedLoaded
	): CourseDetailedState = with(oldState) {
		return copy(courseDetailed = courseDetailed.loaded(event.course))
	}

	private fun reduceCourseDetailedFailed(
		oldState: CourseDetailedState,
		event: CourseDetailedFailed
	): CourseDetailedState = with(oldState) {
		return copy(courseDetailed = courseDetailed.failure(event.throwable))
	}
}