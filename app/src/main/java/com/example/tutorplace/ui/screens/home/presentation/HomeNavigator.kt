package com.example.tutorplace.ui.screens.home.presentation

import androidx.navigation.NavHostController
import com.example.tutorplace.navigation.Destinations
import com.example.tutorplace.navigation.ViewModelNavigator
import com.example.tutorplace.navigation.ktx.navigateTo
import com.example.tutorplace.navigation.ktx.switchTab

class HomeNavigator(
	private val navController: NavHostController
) : ViewModelNavigator(navController) {

	fun navigateToMail() = Unit

	fun navigateToProfile() = Unit

	fun navigateToSearch() = Unit

	fun navigateToFortuneWheelScreen() {
		navController.navigateTo(Destinations.FortuneWheelFlow.FortuneWheel)
	}

	fun navigateToFortuneWheelInformationBottomSheet() {
		navController.navigateTo(
			Destinations.FortuneWheelFlow.FortuneWheel,
			Destinations.FortuneWheelFlow.FortuneWheelInformation
		)
	}

	fun switchToCatalogTab() {
		navController.switchTab(Destinations.Catalog.route)
	}

	fun switchToMyCoursesTab() {
		navController.switchTab(Destinations.MyCourses.route)
	}
}