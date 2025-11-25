package com.example.tutorplace.ui.screens.coursedetailed.presentation

import com.example.tutorplace.data.courses.CoursesService
import com.example.tutorplace.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CourseDetailedViewModel @Inject constructor(
	private val coursesService: CoursesService,
) : BaseViewModel<CourseDetailedEvent, CourseDetailedState, CourseDetailedEffect>() {

	override fun initialState() = CourseDetailedState()

	override fun onEvent(event: CourseDetailedEvent) = Unit
}