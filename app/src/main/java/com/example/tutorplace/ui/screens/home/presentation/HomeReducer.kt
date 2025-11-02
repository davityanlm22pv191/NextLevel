package com.example.tutorplace.ui.screens.home.presentation

import com.example.tutorplace.domain.model.failure
import com.example.tutorplace.domain.model.loaded
import com.example.tutorplace.domain.model.loading
import com.example.tutorplace.ui.base.BaseReducer

object HomeReducer : BaseReducer<HomeState, HomeEvent> {

	override fun reduce(oldState: HomeState, event: HomeEvent): HomeState = when (event) {
		is HomeEvent.Domain -> when (event) {
			is HomeEvent.Domain.SetProfileInfo -> oldState.copy(profileShortInfo = event.profileShortInfo)
			is HomeEvent.Domain.FortuneWheelLoading -> reduceFortuneWheelLoading(oldState)
			is HomeEvent.Domain.FortuneWheelFailed -> reduceFortuneWheelFailed(oldState, event)
			is HomeEvent.Domain.FortuneWheelLoaded -> reduceFortuneWheelLoaded(oldState, event)
			is HomeEvent.Domain.MyCoursesLoading -> reduceMyCoursesLoading(oldState)
			is HomeEvent.Domain.MyCoursesFailed -> reduceMyCoursesFailed(oldState, event)
			is HomeEvent.Domain.MyCoursesLoaded -> reduceMyCoursesLoaded(oldState, event)
		}
		is HomeEvent.UI -> oldState
	}

	private fun reduceFortuneWheelFailed(
		oldState: HomeState,
		event: HomeEvent.Domain.FortuneWheelFailed
	): HomeState {
		return oldState.copy(
			fortuneWheelLastRotation = oldState.fortuneWheelLastRotation.failure(event.throwable)
		)
	}

	private fun reduceFortuneWheelLoading(oldState: HomeState): HomeState {
		return oldState.copy(fortuneWheelLastRotation = oldState.fortuneWheelLastRotation.loading())
	}

	private fun reduceFortuneWheelLoaded(
		oldState: HomeState,
		event: HomeEvent.Domain.FortuneWheelLoaded
	): HomeState {
		return oldState.copy(
			fortuneWheelLastRotation = oldState.fortuneWheelLastRotation.loaded(event.lastRotation)
		)
	}

	private fun reduceMyCoursesFailed(
		oldState: HomeState,
		event: HomeEvent.Domain.MyCoursesFailed
	): HomeState {
		return oldState.copy(myCourses = oldState.myCourses.failure(event.throwable))
	}

	private fun reduceMyCoursesLoading(oldState: HomeState): HomeState {
		return oldState.copy(myCourses = oldState.myCourses.loading())
	}

	private fun reduceMyCoursesLoaded(
		oldState: HomeState,
		event: HomeEvent.Domain.MyCoursesLoaded
	): HomeState {
		return oldState.copy(myCourses = oldState.myCourses.loaded(event.courses))
	}
}