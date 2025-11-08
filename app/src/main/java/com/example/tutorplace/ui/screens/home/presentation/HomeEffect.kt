package com.example.tutorplace.ui.screens.home.presentation

import com.example.tutorplace.ui.base.BaseEffect

sealed interface HomeEffect : BaseEffect {
	object NavigateToMail : HomeEffect
	object NavigateToSearchScreen : HomeEffect
	object NavigateToProfile : HomeEffect
	object NavigateToFortuneWheelScreen : HomeEffect
	object NavigateToFortuneWheelInformationBottomSheet : HomeEffect
	object NavigateToMyCoursesTab : HomeEffect
	object NavigateToCatalogTab : HomeEffect
}