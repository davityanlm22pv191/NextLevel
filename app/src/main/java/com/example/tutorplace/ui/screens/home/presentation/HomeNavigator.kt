package com.example.tutorplace.ui.screens.home.presentation

import com.example.tutorplace.navigation.Destinations
import com.example.tutorplace.navigation.Navigator
import com.example.tutorplace.navigation.ViewModelNavigator
import com.example.tutorplace.ui.screens.coursedetailed.model.CourseDetailedParams

class HomeNavigator(
	private val navigator: Navigator
) : ViewModelNavigator(navigator) {

	fun navigateToMail() = Unit

	fun navigateToProfile() = Unit

	fun navigateToSearch() = Unit

	fun navigateToFortuneWheelScreen() {
		navigator.navigate(Destinations.FortuneWheel)
	}

	fun navigateToFortuneWheelInformationBottomSheet() {
		navigator.navigate(Destinations.FortuneWheel)
		navigator.navigate(Destinations.FortuneWheelInformation)
	}

	fun switchToCatalogTab() {
		navigator.navigate(Destinations.Catalog)
	}

	fun switchToMyCoursesTab() {
		navigator.navigate(Destinations.MyCourses)
	}

	fun navigateToCourseDetailed(courseId: String) {
		navigator.navigate(
			Destinations.CourseDetailed(CourseDetailedParams(courseId))
		)
	}
}