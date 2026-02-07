package com.example.tutorplace.ui.screens.coursedetailed.presentation

import com.example.tutorplace.ui.base.BaseEffect

sealed interface CourseDetailedEffect : BaseEffect {
	data object NavigateToStartLesson: CourseDetailedEffect
	data object NavigateToMaterialsForCourse: CourseDetailedEffect
	data object NavigateToDashboardDetailed: CourseDetailedEffect
	data object NavigateToCertificateDetailed: CourseDetailedEffect
}