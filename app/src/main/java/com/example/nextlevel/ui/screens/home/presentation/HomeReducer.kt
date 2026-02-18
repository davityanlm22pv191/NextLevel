package com.example.nextlevel.ui.screens.home.presentation

import com.example.nextlevel.domain.model.DataInfo
import com.example.nextlevel.ui.base.BaseReducer

object HomeReducer : BaseReducer<HomeState, HomeEvent> {

	override fun reduce(oldState: HomeState, event: HomeEvent): HomeState = when (event) {
		is HomeEvent.Domain -> when (event) {
			is HomeEvent.Domain.FortuneWheelLoading -> reduceFortuneWheelLoading(oldState)
			is HomeEvent.Domain.FortuneWheelFailed -> reduceFortuneWheelFailed(oldState)
			is HomeEvent.Domain.FortuneWheelLoaded -> reduceFortuneWheelLoaded(oldState, event)
			is HomeEvent.Domain.MyCoursesLoading -> reduceMyCoursesLoading(oldState)
			is HomeEvent.Domain.MyCoursesFailed -> reduceMyCoursesFailed(oldState)
			is HomeEvent.Domain.MyCoursesLoaded -> reduceMyCoursesLoaded(oldState, event)
			is HomeEvent.Domain.SpeciallyForYouLoading -> reduceSpeciallyForYouLoading(oldState)
			is HomeEvent.Domain.SpeciallyForLoaded -> reduceSpeciallyForYouLoaded(oldState, event)
			is HomeEvent.Domain.SpeciallyForFailed -> reduceSpeciallyForYouFailed(oldState)
		}
		is HomeEvent.UI -> oldState
	}

	private fun reduceFortuneWheelFailed(oldState: HomeState): HomeState {
		return oldState.copy(fortuneWheelLastRotation = DataInfo.Error)
	}

	private fun reduceFortuneWheelLoading(oldState: HomeState): HomeState {
		return oldState.copy(fortuneWheelLastRotation = DataInfo.Loading)
	}

	private fun reduceFortuneWheelLoaded(
		oldState: HomeState,
		event: HomeEvent.Domain.FortuneWheelLoaded
	): HomeState {
		return oldState.copy(fortuneWheelLastRotation = DataInfo.Success(event.lastRotation))
	}

	private fun reduceMyCoursesFailed(oldState: HomeState): HomeState {
		return oldState.copy(myCourses = DataInfo.Error)
	}

	private fun reduceMyCoursesLoading(oldState: HomeState): HomeState {
		return oldState.copy(myCourses = DataInfo.Loading)
	}

	private fun reduceMyCoursesLoaded(
		oldState: HomeState,
		event: HomeEvent.Domain.MyCoursesLoaded
	): HomeState {
		return oldState.copy(myCourses = DataInfo.Success(event.courses))
	}

	private fun reduceSpeciallyForYouFailed(oldState: HomeState): HomeState {
		return oldState.copy(speciallyForYou = DataInfo.Error)
	}

	private fun reduceSpeciallyForYouLoading(oldState: HomeState): HomeState {
		return oldState.copy(speciallyForYou = DataInfo.Loading)
	}

	private fun reduceSpeciallyForYouLoaded(
		oldState: HomeState,
		event: HomeEvent.Domain.SpeciallyForLoaded
	): HomeState {
		return oldState.copy(speciallyForYou = DataInfo.Success(event.courses))
	}
}