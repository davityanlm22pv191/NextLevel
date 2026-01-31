package com.example.tutorplace.ui.screens.coursedetailed.presentation

import com.example.tutorplace.data.courses.course.CourseDetailed
import com.example.tutorplace.data.profile.model.ProfileShortInfo
import com.example.tutorplace.ui.base.BaseEvent
import com.example.tutorplace.ui.screens.coursedetailed.model.CourseDetailedParams

sealed interface CourseDetailedEvent : BaseEvent {
	data class AttachParams(val params: CourseDetailedParams) : CourseDetailedEvent
	data class CourseDetailedLoaded(val course: CourseDetailed) : CourseDetailedEvent
	data class CourseDetailedFailed(val throwable: Throwable) : CourseDetailedEvent
}