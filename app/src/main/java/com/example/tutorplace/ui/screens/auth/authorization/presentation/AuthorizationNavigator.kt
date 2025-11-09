package com.example.tutorplace.ui.screens.auth.authorization.presentation

import androidx.navigation.NavHostController
import com.example.tutorplace.navigation.Destinations
import com.example.tutorplace.navigation.Destinations.MainScreen.MainScreenParams
import com.example.tutorplace.navigation.ViewModelNavigator
import com.example.tutorplace.navigation.ktx.navigateTo

class AuthorizationNavigator(
	private val navController: NavHostController
) : ViewModelNavigator(navController) {

	fun navigateToHome() {
		navController.navigate(
			Destinations.MainScreen(MainScreenParams(isShouldShowOnboarding = false)).route
		) {
			popUpTo(Destinations.AuthorizationFlow.FLOW_ROUTE) { inclusive = true }
		}
	}

	fun navigateToRestorePassword() {
		navController.navigateTo(Destinations.AuthorizationFlow.RestorePassword)
	}

	fun navigateToRegistration() {
		navController.navigateTo(Destinations.AuthorizationFlow.Registration)
	}

	fun navigateToSupport() = Unit

	fun navigateToYandexAuthorization() = Unit
}