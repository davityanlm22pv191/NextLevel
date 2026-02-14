package com.example.nextlevel.ui.screens.coursedetailed.presentation

import com.example.nextlevel.domain.model.DataInfo
import com.example.nextlevel.ui.base.BaseReducer
import com.example.nextlevel.ui.screens.coursedetailed.presentation.CourseDetailedEvent.CourseDetailedFailed
import com.example.nextlevel.ui.screens.coursedetailed.presentation.CourseDetailedEvent.CourseDetailedLoaded

object CourseDetailedReducer : BaseReducer<CourseDetailedState, CourseDetailedEvent> {

	override fun reduce(
		oldState: CourseDetailedState,
		event: CourseDetailedEvent
	): CourseDetailedState {
		return when (event) {
			is CourseDetailedLoaded -> reduceCourseDetailedLoadedEvent(oldState, event)
			is CourseDetailedFailed -> reduceCourseDetailedFailed(oldState, event)
			else -> oldState
		}
	}

	private fun reduceCourseDetailedLoadedEvent(
		oldState: CourseDetailedState,
		event: CourseDetailedLoaded
	): CourseDetailedState = with(oldState) {
		return copy(courseDetailed = DataInfo.Success(event.course))
	}

	private fun reduceCourseDetailedFailed(
		oldState: CourseDetailedState,
		event: CourseDetailedFailed
	): CourseDetailedState = with(oldState) {
		return copy(courseDetailed = DataInfo.Error(event.throwable))
	}
}