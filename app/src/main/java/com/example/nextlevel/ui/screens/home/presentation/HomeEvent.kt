package com.example.nextlevel.ui.screens.home.presentation

import com.example.nextlevel.data.courses.course.Course
import com.example.nextlevel.ui.base.BaseEvent
import java.time.LocalDateTime

sealed interface HomeEvent : BaseEvent {
	sealed interface Domain : HomeEvent {
		data object FortuneWheelLoading : Domain
		data class FortuneWheelLoaded(val lastRotation: LocalDateTime) : Domain
		data class FortuneWheelFailed(val throwable: Throwable) : Domain

		data object MyCoursesLoading : Domain
		data class MyCoursesLoaded(val courses: List<Course>) : Domain
		data class MyCoursesFailed(val throwable: Throwable) : Domain

		data object SpeciallyForYouLoading : Domain
		data class SpeciallyForLoaded(val courses: List<Course>) : Domain
		data class SpeciallyForFailed(val throwable: Throwable) : Domain
	}

	sealed interface UI : HomeEvent {
		data object NotificationClicked : UI
		data object SearchClicked : UI
		data object ProfileClicked : UI
		data object FortuneWheelClicked : UI
		data object FortuneWheelInformationClicked : UI
		data object MyCoursesClicked : UI
		data object CatalogClicked : UI
		data class CourseClicked(val courseId: String): UI
	}
}