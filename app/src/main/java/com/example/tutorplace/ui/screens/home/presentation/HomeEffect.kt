package com.example.tutorplace.ui.screens.home.presentation

import com.example.tutorplace.ui.base.BaseEffect

sealed interface HomeEffect : BaseEffect {
	data object NavigateToMail : HomeEffect
	data object NavigateToProfile : HomeEffect
	data object NavigateToSearch : HomeEffect
	data object NavigateToFortuneWheel : HomeEffect
	data object NavigateToFortuneWheelInformation : HomeEffect
	data object NavigateToCatalog : HomeEffect
	data object NavigateToMyCourses : HomeEffect
	data class NavigateToCourseDetailed(val courseId: String) : HomeEffect
}