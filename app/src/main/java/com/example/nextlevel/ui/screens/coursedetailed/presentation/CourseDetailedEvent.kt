package com.example.nextlevel.ui.screens.coursedetailed.presentation

import com.example.nextlevel.data.courses.course.CourseDetailed
import com.example.nextlevel.ui.base.BaseEvent
import com.example.nextlevel.ui.screens.coursedetailed.model.CourseDetailedParams

sealed interface CourseDetailedEvent : BaseEvent {
	data class AttachParams(val params: CourseDetailedParams) : CourseDetailedEvent
	data class CourseDetailedLoaded(val course: CourseDetailed) : CourseDetailedEvent
	data class CourseDetailedFailed(val throwable: Throwable) : CourseDetailedEvent

	data object StartLessonClicked : CourseDetailedEvent
	data object MaterialsForCoursesClicked : CourseDetailedEvent
	data object DashboardClicked : CourseDetailedEvent
	data object CertificateClicked : CourseDetailedEvent
}