package com.example.tutorplace.ui.screens.auth.registration.presentation

import androidx.navigation.NavHostController
import com.example.tutorplace.navigation.Destinations
import com.example.tutorplace.navigation.Destinations.MainScreen.MainScreenParams
import com.example.tutorplace.navigation.ViewModelNavigator

class RegistrationNavigator(
	private val navController: NavHostController
) : ViewModelNavigator(navController) {

	fun navigateToHome() {
		navController.navigate(
			Destinations.MainScreen(MainScreenParams(isShouldShowOnboarding = true)).route
		) {
			popUpTo(Destinations.AuthorizationFlow.FLOW_ROUTE) { inclusive = true }
		}
	}

	fun navigateToOffer() = Unit
	fun navigateToTerms() = Unit
	fun navigateToYandexAuthorization() = Unit
}